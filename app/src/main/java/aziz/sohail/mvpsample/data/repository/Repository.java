package aziz.sohail.mvpsample.data.repository;

import java.util.List;

import aziz.sohail.mvpsample.domain.model.Breed;
import aziz.sohail.mvpsample.domain.model.Dog;
import io.reactivex.Observable;


/**
 *  Main Repository to interact with domain (Interactors)
 */
public interface Repository {

    Observable<List<Breed>> getBreedList();

    Observable<List<Dog>> getBreedDetails(String breedName);


}
