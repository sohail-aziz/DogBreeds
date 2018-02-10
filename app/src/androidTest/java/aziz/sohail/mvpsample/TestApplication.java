package aziz.sohail.mvpsample;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import aziz.sohail.mvpsample.data.repository.Repository;
import aziz.sohail.mvpsample.data.repository.RepositoryImpl;
import aziz.sohail.mvpsample.data.repository.mapper.BreedMapper;
import aziz.sohail.mvpsample.data.repository.mapper.DogMapper;
import aziz.sohail.mvpsample.di.ApplicationComponent;
import aziz.sohail.mvpsample.di.ApplicationModule;
import aziz.sohail.mvpsample.di.DaggerApplicationComponent;
import aziz.sohail.mvpsample.domain.model.Breed;
import aziz.sohail.mvpsample.domain.model.Dog;
import io.reactivex.Observable;

/**
 * Created by sohailaziz on 3/2/18.
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
        public Repository provideDogRepository(RepositoryImpl repository) {
            return new Repository() {
                @Override
                public Observable<List<Breed>> getBreedList() {


                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<String>>() {
                    }.getType();
                    List<String> breedList = gson.fromJson(JsonUtils.breedListJson, listType);

                    BreedMapper breedMapper = new BreedMapper();


                    return Observable.just(breedMapper.map(breedList));

                }

                @Override
                public Observable<List<Dog>> getBreedDetails(String breedName) {

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
