package aziz.sohail.mvpsample.data.repository.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Named;

import aziz.sohail.mvpsample.data.local.BreedDao;
import aziz.sohail.mvpsample.data.local.DogDao;
import aziz.sohail.mvpsample.data.local.entity.BreedEntity;
import aziz.sohail.mvpsample.data.local.entity.DogEntity;
import aziz.sohail.mvpsample.data.repository.mapper.DogMapper;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * LocalDataStore, provides disk caching using Room database
 */

public class LocalDataSource {

    private final BreedDao breedDao;
    private final DogDao dogDao;
    private final Scheduler executorThread;
    private final DogMapper dogMapper;

    @Inject
    public LocalDataSource(BreedDao breedDao, DogDao dogDao, @Named("executor_thread") Scheduler executorThread, DogMapper dogMapper) {
        this.breedDao = breedDao;
        this.dogDao = dogDao;
        this.executorThread = executorThread;
        this.dogMapper = dogMapper;
    }

    public void storeBreeds(List<String> breeds) {
        final List<BreedEntity> breedEntities = map(breeds);

        Observable.fromCallable(new Callable<List<BreedEntity>>() {
            @Override
            public List<BreedEntity> call() throws Exception {
                breedDao.addAllBreeds(breedEntities);

                return breedEntities;
            }
        }).subscribeOn(executorThread)
                .observeOn(executorThread)
                .subscribe(new Consumer<List<BreedEntity>>() {
                    @Override
                    public void accept(List<BreedEntity> breedEntities) throws Exception {

                        Timber.d("stored %d breeds in db", breedEntities.size());
                    }
                });
    }

    private void addBreeds(List<String> breeds) {

        List<BreedEntity> breedEntities = map(breeds);
        breedDao.addAllBreeds(breedEntities);

    }

    private List<BreedEntity> map(List<String> breeds) {
        List<BreedEntity> breedEntities = new ArrayList<>(breeds.size());

        for (String s : breeds) {
            BreedEntity breedEntity = new BreedEntity();
            breedEntity.setBreedName(s);

            breedEntities.add(breedEntity);
        }
        return breedEntities;
    }

    private List<String> mapDog(List<DogEntity> dogEntities) {
        List<String> breeds = new ArrayList<>(dogEntities.size());

        for (DogEntity d : dogEntities) {
            breeds.add(d.getImageUrl());
        }
        return breeds;
    }


    public Observable<List<String>> getBreedList() {
        return breedDao.getAllBreeds()
                .toObservable()
                .map(new Function<List<BreedEntity>, List<String>>() {
                    @Override
                    public List<String> apply(List<BreedEntity> breedEntities) throws Exception {
                        List<String> breeds = new ArrayList<>(breedEntities.size());
                        for (BreedEntity breedEntity : breedEntities) {
                            breeds.add(breedEntity.getBreedName());
                        }
                        return breeds;
                    }
                });
    }


    public Observable<List<String>> getDogsForBreed(String breedName) {
        return dogDao.getAllDogs(breedName)
                .toObservable()
                .map(new Function<List<DogEntity>, List<String>>() {
                    @Override
                    public List<String> apply(List<DogEntity> dogEntities) throws Exception {
                        return mapDog(dogEntities);
                    }
                });
    }

    public void storeDogsForBreed(List<String> dogUrls, final String breedName) {

        Timber.d("storeDogsForBreed: urls.size=" + dogUrls.size());
        final List<DogEntity> dogEntities = new ArrayList<>(dogUrls.size());
        for (String s : dogUrls) {
            DogEntity dogEntity = new DogEntity();
            dogEntity.setImageUrl(s);
            dogEntity.setBreedId(breedName);

            dogEntities.add(dogEntity);
        }

        Timber.d("storeDogsForBreed: entities.size=" + dogEntities.size());

        Observable.fromCallable(new Callable<List<DogEntity>>() {
            @Override
            public List<DogEntity> call() throws Exception {

                dogDao.addAllDog(dogEntities);
                return dogEntities;
            }
        }).subscribeOn(executorThread)
                .observeOn(executorThread)
                .subscribe(new Consumer<List<DogEntity>>() {
                    @Override
                    public void accept(List<DogEntity> dogEntities) throws Exception {

                        Timber.d("dogEntities saved in db=" + dogEntities.size());
                    }
                });

    }
}
