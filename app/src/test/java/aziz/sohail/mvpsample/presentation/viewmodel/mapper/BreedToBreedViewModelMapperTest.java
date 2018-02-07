package aziz.sohail.mvpsample.presentation.viewmodel.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import aziz.sohail.mvpsample.domain.model.Breed;
import aziz.sohail.mvpsample.presentation.viewmodel.BreedViewModel;
import aziz.sohail.mvpsample.presentation.viewmodel.mapper.BreedToBreeViewModelMapper;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by sohailaziz on 16/1/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class BreedToBreedViewModelMapperTest {

    BreedToBreeViewModelMapper mapper;

    @Before
    public void setup() {
        mapper = new BreedToBreeViewModelMapper();
    }

    @Test
    public void testBreedToBreedViewModelMapping() {

        Breed breed = new Breed("TEST_BREED");

        BreedViewModel breedViewModel = mapper.map(breed);

        assertThat(breedViewModel.breedName, is("TEST_BREED"));

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testBreedViewModelToBreedThrowsException() {
        BreedViewModel breedViewModel = new BreedViewModel("ABC_BREED");
        Breed breed = mapper.reverseMap(breedViewModel);
    }

    @Test
    public void testBreedListMapping() {

        List<Breed> breedList = new ArrayList<>(3);
        breedList.add(new Breed("one"));
        breedList.add(new Breed("two"));
        breedList.add(new Breed("three"));

        List<BreedViewModel> breedViewModels = mapper.map(breedList);

        assertThat(breedViewModels.size(), is(3));
        assertThat(breedViewModels.get(0).breedName, is("one"));
        assertThat(breedViewModels.get(2).breedName, is("three"));

    }
}
