package aziz.sohail.mvpsample.data.repository.mapper;

import javax.inject.Inject;

import aziz.sohail.mvpsample.domain.model.Dog;


public class DogMapper extends Mapper<String, Dog> {
    @Inject
    public DogMapper() {
    }

    @Override
    public Dog map(String s) {
        if (s == null) {
            throw new IllegalArgumentException("null argument");
        }
        return new Dog(s);
    }

    @Override
    public String reverseMap(Dog dog) {
        throw new UnsupportedOperationException("reverseMap not allowed.");
    }
}
