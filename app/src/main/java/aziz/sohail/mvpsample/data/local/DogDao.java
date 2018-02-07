package aziz.sohail.mvpsample.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import aziz.sohail.mvpsample.data.local.entity.DogEntity;
import io.reactivex.Maybe;


@Dao
public interface DogDao {

    @Query("SELECT * from dog where breedId=:breedName")
    Maybe<List<DogEntity>> getAllDogs(String breedName);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllDog(List<DogEntity> dogEntity);


}
