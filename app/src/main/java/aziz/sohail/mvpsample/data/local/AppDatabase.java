package aziz.sohail.mvpsample.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import aziz.sohail.mvpsample.data.local.entity.BreedEntity;
import aziz.sohail.mvpsample.data.local.entity.DogEntity;

@Database(entities = {BreedEntity.class, DogEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    public abstract BreedDao breedDao();

    public abstract DogDao dogDao();
}
