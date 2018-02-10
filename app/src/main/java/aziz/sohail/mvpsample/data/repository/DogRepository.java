package aziz.sohail.mvpsample.data.repository;

import java.util.List;

import aziz.sohail.mvpsample.domain.model.Dog;
import io.reactivex.Observable;

/**
 * Created by sohailaziz on 11/2/18.
 */

public interface DogRepository {
    Observable<List<Dog>> getDogForBreed(String breedName);

}
