package aziz.sohail.mvpsample.presentation.viewmodel.mapper;

import javax.inject.Inject;

import aziz.sohail.mvpsample.data.repository.mapper.Mapper;
import aziz.sohail.mvpsample.domain.model.Breed;
import aziz.sohail.mvpsample.presentation.viewmodel.BreedViewModel;

/**
 * Created by sohailaziz on 15/1/18.
 */

public class BreedToBreeViewModelMapper extends Mapper<Breed, BreedViewModel> {

    @Inject
    public BreedToBreeViewModelMapper() {
    }

    @Override
    public BreedViewModel map(Breed breed) {
        return new BreedViewModel(breed.breedName);
    }

    @Override
    public Breed reverseMap(BreedViewModel breedViewModel) {
        throw new UnsupportedOperationException("not supported");
    }
}
