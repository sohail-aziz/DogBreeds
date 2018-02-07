package aziz.sohail.mvpsample.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import aziz.sohail.mvpsample.data.repository.mapper.BreedMapper;
import aziz.sohail.mvpsample.domain.model.Breed;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

/**
 * Created by sohailaziz on 17/1/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class BreedMapperTest {

    BreedMapper mapper;

    @Before
    public void setup() {
        mapper = new BreedMapper();
    }

    @Test
    public void testMap() {

        Breed breed = mapper.map("akita");

        assertThat(breed.breedName, is("akita"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapNullArgument() {

        String s = null;
        mapper.map(s);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testReverseMapThrowsException() {
        mapper.reverseMap(mock(Breed.class));
    }

    @Test
    public void testMapList() {
        List<String> breedList = Arrays.asList("one", "two", "three");

        List<Breed> breedModels = mapper.map(breedList);

        assertThat(breedModels.size(), is(3));
        assertThat(breedModels.get(0).breedName, is("one"));
        assertThat(breedModels.get(2).breedName, is("three"));
    }

    @Test
    public void testMapNullListReturnsEmpty() {

        List<String> breedList = null;

        List<Breed> breedModels = mapper.map(breedList);

        assertThat(breedModels.size(), is(0));
    }
}
