package aziz.sohail.mvpsample.presentation.viewmodel.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import aziz.sohail.mvpsample.domain.model.Breed;
import aziz.sohail.mvpsample.domain.model.Dog;
import aziz.sohail.mvpsample.presentation.viewmodel.BreedViewModel;
import aziz.sohail.mvpsample.presentation.viewmodel.DogViewModel;
import aziz.sohail.mvpsample.presentation.viewmodel.mapper.BreedToBreeViewModelMapper;
import aziz.sohail.mvpsample.presentation.viewmodel.mapper.DogToDogViewModelMapper;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by sohailaziz on 16/1/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class DogToDogViewModelMapperTest {

    DogToDogViewModelMapper mapper;

    @Before
    public void setup() {
        mapper = new DogToDogViewModelMapper();
    }

    @Test
    public void testBreedToBreedViewModelMapping() {

        Dog dog = new Dog("TEST_URL");

        DogViewModel dogViewModel = mapper.map(dog);

        assertThat(dogViewModel.imageUrl, is("TEST_URL"));

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testBreedViewModelToBreedThrowsException() {
        DogViewModel dogViewModel = new DogViewModel("TEST_URL");
        Dog dog = mapper.reverseMap(dogViewModel);
    }

    @Test
    public void testBreedListMapping() {

        List<Dog> dogList = new ArrayList<>(4);
        dogList.add(new Dog("one"));
        dogList.add(new Dog("two"));
        dogList.add(new Dog("three"));
        dogList.add(new Dog("four"));

        List<DogViewModel> dogViewModels = mapper.map(dogList);

        assertThat(dogViewModels.size(), is(4));
        assertThat(dogViewModels.get(0).imageUrl, is("one"));
        assertThat(dogViewModels.get(3).imageUrl, is("four"));

    }
}
