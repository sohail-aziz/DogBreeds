package aziz.sohail.mvpsample.data.repository;

import java.util.List;

import javax.inject.Inject;

import aziz.sohail.mvpsample.data.repository.datasource.LocalDataSource;
import aziz.sohail.mvpsample.data.repository.datasource.RemoteDataSource;
import aziz.sohail.mvpsample.data.repository.mapper.DogMapper;
import aziz.sohail.mvpsample.domain.model.Dog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by sohailaziz on 11/2/18.
 */

public class DogRepositoryImpl implements DogRepository {

    private final LocalDataSource localDataSource;
    private final RemoteDataSource remoteDataSource;
    private final DogMapper dogMapper;

    @Inject
    public DogRepositoryImpl(LocalDataSource localDataSource, RemoteDataSource remoteDataSource, DogMapper dogMapper) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.dogMapper = dogMapper;
    }

    @Override
    public Observable<List<Dog>> getDogForBreed(String breedName) {
        return Observable
                .concat(getDogsFromLocal(breedName),
                        getDogsFromRemote(breedName))
                .map(new Function<List<String>, List<Dog>>() {
                    @Override
                    public List<Dog> apply(List<String> urls) throws Exception {
                        return dogMapper.map(urls);
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
