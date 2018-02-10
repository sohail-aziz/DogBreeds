package aziz.sohail.mvpsample.data.repository;

import java.util.List;

import aziz.sohail.mvpsample.domain.model.Breed;
import io.reactivex.Observable;

/**
 * Created by sohailaziz on 11/2/18.
 */

public interface BreedRepository {

    Observable<List<Breed>> getBreedList();
}
