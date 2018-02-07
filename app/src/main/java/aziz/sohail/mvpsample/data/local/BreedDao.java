package aziz.sohail.mvpsample.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import aziz.sohail.mvpsample.data.local.entity.BreedEntity;
import io.reactivex.Maybe;


@Dao
public interface BreedDao {

    @Query("SELECT * from breed")
    Maybe<List<BreedEntity>> getAllBreeds();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllBreeds(
            List<BreedEntity> breeds);


}
