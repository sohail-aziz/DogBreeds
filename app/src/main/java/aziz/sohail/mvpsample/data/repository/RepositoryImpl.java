package aziz.sohail.mvpsample.data.repository;

import java.util.List;

import javax.inject.Inject;

import aziz.sohail.mvpsample.data.repository.datasource.LocalDataSource;
import aziz.sohail.mvpsample.data.repository.datasource.RemoteDataSource;
import aziz.sohail.mvpsample.data.repository.mapper.BreedMapper;
import aziz.sohail.mvpsample.data.repository.mapper.DogMapper;
import aziz.sohail.mvpsample.domain.model.Breed;
import aziz.sohail.mvpsample.domain.model.Dog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import timber.log.Timber;


public class RepositoryImpl implements Repository {

    private final LocalDataSource localDataSource;
    private final RemoteDataSource remoteDataSource;
    private final BreedMapper breedMapper;
    private final DogMapper dogMapper;

    @Inject
    public RepositoryImpl(LocalDataSource localDataSource, RemoteDataSource remoteDataSource, BreedMapper breedMapper, DogMapper dogMapper) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.breedMapper = breedMapper;
        this.dogMapper = dogMapper;
    }

    @Override
    public Observable<List<Breed>> getBreedList() {
        Timber.d("getBreedList");

        return Observable.concat(getBreedsFromLocal(), getBreedsFromRemote())
                .map(new Function<List<String>, List<Breed>>() {
                    @Override
                    public List<Breed> apply(List<String> strings) throws Exception {
                        return breedMapper.map(strings);
                    }
                });
    }


    private Observable<List<String>> getBreedsFromLocal() {
        Timber.d("getBreedsFromLocal");
        return localDataSource.getBreedList()
                .filter(new Predicate<List<String>>() { //don't return empty list
                    @Override
                    public boolean test(List<String> strings) throws Exception {
                        return strings.size() > 0;
                    }
                });
    }

    private Observable<List<String>> getBreedsFromRemote() {
        Timber.d("getBreedsFromRemote");

        return remoteDataSource.getBreedList()
                .doOnNext(new Consumer<List<String>>() { //store breedlist in db
                    @Override
                    public void accept(List<String> breeds) throws Exception {

                        storeBreedListInDb(breeds);
                    }
                });

    }

    private void storeBreedListInDb(List<String> breeds) {
        localDataSource.storeBreeds(breeds);
    }

    @Override
    public Observable<List<Dog>> getBreedDetails(String breedName) {
        return Observable
                .concat(getDogsFromLocal(breedName),
                        getDogsFromRemote(breedName))
                .map(new Function<List<String>, List<Dog>>() {
                    @Override
                    public List<Dog> apply(List<String> strings) throws Exception {
                        return dogMapper.map(strings);
                    }
                });
    }


    private Observable<List<String>> getDogsFromLocal(String breedName) {
        return localDataSource.getDogsForBreed(breedName);
    }

    private Observable<List<String>> getDogsFromRemote(final String breedName) {
        return remoteDataSource.getDogsForBreed(breedName)
                .doOnNext(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> dogs) throws Exception {
                        Timber.d("getDosFromRemote: size=" + dogs.size());
                        storeDogsWithBreed(dogs, breedName);
                    }
                });
    }

    private void storeDogsWithBreed(List<String> dogs, final String breedName) {

        localDataSource.storeDogsForBreed(dogs, breedName);
    }


}
