package aziz.sohail.mvpsample.data.repository.mapper;

import javax.inject.Inject;

import aziz.sohail.mvpsample.domain.model.Breed;


public class BreedMapper extends Mapper<String,Breed> {

    @Inject
    public BreedMapper() {
    }

    @Override
    public Breed map(String s) {

        if (s == null) {
            throw new IllegalArgumentException("null argument");
        }
        return new Breed(s);
    }

    @Override
    public String reverseMap(Breed breed) {
        throw new UnsupportedOperationException("reverseMap not allowed");
    }
}
