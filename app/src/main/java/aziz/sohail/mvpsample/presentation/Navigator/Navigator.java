package aziz.sohail.mvpsample.presentation.Navigator;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import aziz.sohail.mvpsample.presentation.activity.BreedDetailsActivity;
import aziz.sohail.mvpsample.presentation.activity.DogActivity;
import aziz.sohail.mvpsample.presentation.viewmodel.BreedViewModel;
import aziz.sohail.mvpsample.presentation.viewmodel.DogViewModel;

/**
 * Navigator responsible for app-wide screen navigation
 */

public class Navigator {


    public static void startBreedDetailsActivity(Context context, BreedViewModel breedViewModel) {

        if (context == null || breedViewModel == null) {
            throw new IllegalArgumentException("context or breedName is null");
        }

        Intent intent = BreedDetailsActivity.getCallingIntent(context, breedViewModel);
        context.startActivity(intent);

    }

    public static void startDogActivity(Context context, DogViewModel dogViewModel) {

        if (context == null || dogViewModel == null) {
            throw new IllegalArgumentException("context or dogViewModel is null");
        }

        Intent intent = DogActivity.getCallingIntent(context, dogViewModel);
        context.startActivity(intent);
    }
}
