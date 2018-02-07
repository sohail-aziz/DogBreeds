package aziz.sohail.mvpsample.data.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 *  Dog REST API
 */
public interface RestAPI {

    @GET("api/breeds/list")
    Observable<ResponseBreedList> getBreedList();

    @GET("/api/breed/{breed_name}/images")
    Observable<ResponseBreedDetails> getBreedDetails(@Path("breed_name") String breedName);

}
