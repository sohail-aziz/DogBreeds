package aziz.sohail.mvpsample.presentation.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sohailaziz on 15/1/18.
 */

public class BreedViewModel implements Parcelable {
    public final String breedName;

    public BreedViewModel(String breedName) {
        this.breedName = breedName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.breedName);
    }

    protected BreedViewModel(Parcel in) {
        this.breedName = in.readString();
    }

    public static final Parcelable.Creator<BreedViewModel> CREATOR = new Parcelable.Creator<BreedViewModel>() {
        @Override
        public BreedViewModel createFromParcel(Parcel source) {
            return new BreedViewModel(source);
        }

        @Override
        public BreedViewModel[] newArray(int size) {
            return new BreedViewModel[size];
        }
    };
}

