package aziz.sohail.mvpsample.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import aziz.sohail.mvpsample.data.repository.mapper.DogMapper;
import aziz.sohail.mvpsample.domain.model.Breed;
import aziz.sohail.mvpsample.domain.model.Dog;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

/**
 * Created by sohailaziz on 17/1/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class DogMapperTest {

    DogMapper dogMapper;

    @Before
    public void setup() {
        dogMapper = new DogMapper();
    }

    @Test
    public void testDogMap() {

        Dog dog = dogMapper.map("dog_url");

        assertThat(dog.imageUrl, is("dog_url"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapNullArgument() {

        String s = null;
        dogMapper.map(s);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testReverseMapThrowsException() {
        dogMapper.reverseMap(mock(Dog.class));
    }

    @Test
    public void testMapList() {
        List<String> dogUrls = Arrays.asList("url_one", "url_two", "url_three");

        List<Dog> dogModels = dogMapper.map(dogUrls);

        assertThat(dogModels.size(), is(3));
        assertThat(dogModels.get(0).imageUrl, is("url_one"));
        assertThat(dogModels.get(2).imageUrl, is("url_three"));
    }

    @Test
    public void testMapNullListReturnsEmpty() {

        List<String> dogUrls = null;

        List<Dog> dogModels = dogMapper.map(dogUrls);

        assertThat(dogModels.size(), is(0));
    }
}
