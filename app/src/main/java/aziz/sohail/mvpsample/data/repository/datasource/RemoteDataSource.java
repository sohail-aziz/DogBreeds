package aziz.sohail.mvpsample.data.repository.datasource;

import java.util.List;

import javax.inject.Inject;

import aziz.sohail.mvpsample.data.remote.RestAPI;
import aziz.sohail.mvpsample.data.remote.ResponseBreedDetails;
import aziz.sohail.mvpsample.data.remote.ResponseBreedList;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import timber.log.Timber;


/**
 * RemoteDataSource provides data from REST API
 */
public class RemoteDataSource {

    private final RestAPI dogRestAPI;

    @Inject
    public RemoteDataSource(RestAPI dogRestAPI) {
        this.dogRestAPI = dogRestAPI;
    }

    public Observable<List<String>> getBreedList() {
        Timber.d("getBreedList");

        return dogRestAPI.getBreedList()
                .map(new Function<ResponseBreedList, List<String>>() {
                    @Override
                    public List<String> apply(ResponseBreedList responseBreedList) throws Exception {
                        return responseBreedList.message;
                    }
                }).doOnNext(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        Timber.d("getBreedList: accept: size=" + strings.size());
                    }
                });
    }


    public Observable<List<String>> getDogsForBreed(String breedName) {

        return dogRestAPI.getBreedDetails(breedName)
                .map(new Function<ResponseBreedDetails, List<String>>() {
                    @Override
                    public List<String> apply(ResponseBreedDetails responseBreedDetails) throws Exception {
                        return responseBreedDetails.message;
                    }
                });
    }
}
