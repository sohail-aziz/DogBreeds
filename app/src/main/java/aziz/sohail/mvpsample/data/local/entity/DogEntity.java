package aziz.sohail.mvpsample.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;


/**
 * Dog belongs to Breed
 * One to many relationship
 */
@Entity(tableName = "dog",
        foreignKeys = @ForeignKey(entity = BreedEntity.class,
                parentColumns = "breedName",
                childColumns = "breedId", onDelete = CASCADE),
        indices = @Index(value = {"breedId"}))

public class DogEntity {


    private String breedId;
    @PrimaryKey
    @NonNull
    private String imageUrl;


    public String getBreedId() {
        return breedId;
    }

    public void setBreedId(String breedId) {
        this.breedId = breedId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
