package aziz.sohail.mvpsample.presentation.viewmodel.mapper;

import javax.inject.Inject;

import aziz.sohail.mvpsample.data.repository.mapper.Mapper;
import aziz.sohail.mvpsample.domain.model.Dog;
import aziz.sohail.mvpsample.presentation.viewmodel.DogViewModel;


public class DogToDogViewModelMapper extends Mapper<Dog, DogViewModel> {

    @Inject
    public DogToDogViewModelMapper() {
    }

    @Override
    public DogViewModel map(Dog dog) {
        return new DogViewModel(dog.imageUrl);
    }

    @Override
    public Dog reverseMap(DogViewModel dogViewModel) {
        throw new UnsupportedOperationException("not supported");
    }
}
