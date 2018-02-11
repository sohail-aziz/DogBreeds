package aziz.sohail.mvpsample;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import aziz.sohail.mvpsample.data.repository.BreedRepository;
import aziz.sohail.mvpsample.data.repository.BreedRepositoryImpl;
import aziz.sohail.mvpsample.data.repository.DogRepository;
import aziz.sohail.mvpsample.data.repository.DogRepositoryImpl;
import aziz.sohail.mvpsample.data.repository.mapper.BreedMapper;
import aziz.sohail.mvpsample.data.repository.mapper.DogMapper;
import aziz.sohail.mvpsample.di.ApplicationComponent;
import aziz.sohail.mvpsample.di.ApplicationModule;
import aziz.sohail.mvpsample.di.DaggerApplicationComponent;
import aziz.sohail.mvpsample.domain.model.Breed;
import aziz.sohail.mvpsample.domain.model.Dog;
import io.reactivex.Observable;

/**
 * TestApplication provides mock responses for UI testing
 */

public class TestApplication extends MyApplication {

    @Override
    public ApplicationComponent getApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new MockAppModule(this)).build();
    }

    //mock module
    private class MockAppModule extends ApplicationModule {

        public MockAppModule(Context context) {
            super(context);
        }

        @Override
        public BreedRepository provideBreedRepository(BreedRepositoryImpl repository) {
            return new BreedRepository() {
                @Override
                public Observable<List<Breed>> getBreedList() {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<String>>() {
                    }.getType();
                    List<String> breedList = gson.fromJson(JsonUtils.breedListJson, listType);

                    BreedMapper breedMapper = new BreedMapper();


                    return Observable.just(breedMapper.map(breedList));
                }
            };
        }


        @Override
        public DogRepository provideDogRepository(DogRepositoryImpl repository) {
            return new DogRepository() {

                @Override
                public Observable<List<Dog>> getDogForBreed(String breedName) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<String>>() {
                    }.getType();
                    List<String> dogs = gson.fromJson(JsonUtils.breedDetailsJson, listType);

                    DogMapper dogMapper = new DogMapper();


                    return Observable.just(dogMapper.map(dogs));
                }

            };
        }
    }
}
