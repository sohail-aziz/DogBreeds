package aziz.sohail.mvpsample.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import aziz.sohail.mvpsample.data.local.AppDatabase;
import aziz.sohail.mvpsample.data.local.BreedDao;
import aziz.sohail.mvpsample.data.local.DogDao;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module provides Database dependencies
 */

@Module
public class DatabaseModule {

    private static final String DATABASE_NAME = "breed_app_database";


    @Provides
    @Singleton
    AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                .build();
    }

    @Singleton
    @Provides
    BreedDao provideBreedDao(AppDatabase database) {
        return database.breedDao();
    }

    @Singleton
    @Provides
    DogDao provideDogDao(AppDatabase database) {
        return database.dogDao();
    }
}
