package aziz.sohail.mvpsample.data.repository;

import java.util.List;

import javax.inject.Inject;

import aziz.sohail.mvpsample.data.repository.datasource.LocalDataSource;
import aziz.sohail.mvpsample.data.repository.datasource.RemoteDataSource;
import aziz.sohail.mvpsample.data.repository.mapper.BreedMapper;
import aziz.sohail.mvpsample.domain.model.Breed;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import timber.log.Timber;

/**
 * Created by sohailaziz on 11/2/18.
 */

public class BreedRepositoryImpl implements BreedRepository {

    private final LocalDataSource localDataSource;
    private final RemoteDataSource remoteDataSource;
    private final BreedMapper breedMapper;

    @Inject
    public BreedRepositoryImpl(LocalDataSource localDataSource, RemoteDataSource remoteDataSource, BreedMapper breedMapper) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.breedMapper = breedMapper;
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
}
