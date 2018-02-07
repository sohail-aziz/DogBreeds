package aziz.sohail.mvpsample.presentation.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sohailaziz on 15/1/18.
 */

public class DogViewModel implements Parcelable {


    public final String imageUrl;

    public DogViewModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
    }

    protected DogViewModel(Parcel in) {
        this.imageUrl = in.readString();
    }

    public static final Parcelable.Creator<DogViewModel> CREATOR = new Parcelable.Creator<DogViewModel>() {
        @Override
        public DogViewModel createFromParcel(Parcel source) {
            return new DogViewModel(source);
        }

        @Override
        public DogViewModel[] newArray(int size) {
            return new DogViewModel[size];
        }
    };
}

