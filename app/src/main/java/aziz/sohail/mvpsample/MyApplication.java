package aziz.sohail.mvpsample;

import android.app.Application;

import aziz.sohail.mvpsample.di.ApplicationComponent;
import aziz.sohail.mvpsample.di.ApplicationModule;
import aziz.sohail.mvpsample.di.DaggerApplicationComponent;
import aziz.sohail.mvpsample.di.DatabaseModule;
import aziz.sohail.mvpsample.di.NetworkModule;
import timber.log.Timber;

/**
 * Application class to initialize Dagger and Timber
 */
public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("onCreate");

        initializeInjector();
        initializeTimber();

    }

    private void initializeTimber() {

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initializeInjector() {
        applicationComponent =
                DaggerApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(this))
                        .networkModule(new NetworkModule())
                        .databaseModule(new DatabaseModule())
                        .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
