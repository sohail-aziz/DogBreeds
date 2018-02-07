package aziz.sohail.mvpsample.di;

import javax.inject.Singleton;

import aziz.sohail.mvpsample.MyApplication;
import aziz.sohail.mvpsample.presentation.activity.BreedDetailsActivity;
import aziz.sohail.mvpsample.presentation.activity.BreedsActivity;
import aziz.sohail.mvpsample.presentation.activity.DogActivity;
import dagger.Component;

/**
 * Dagger Application Component defines injection points in app
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(BreedsActivity activity);

    void inject(BreedDetailsActivity activity);

    void inject(DogActivity activity);

}