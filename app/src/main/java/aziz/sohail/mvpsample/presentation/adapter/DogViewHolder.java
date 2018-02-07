package aziz.sohail.mvpsample.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import aziz.sohail.mvpsample.R;
import aziz.sohail.mvpsample.presentation.presenter.BreedDetailsPresenter;
import aziz.sohail.mvpsample.presentation.viewmodel.DogViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DogViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_view_dog)
    ImageView imageViewDog;


    private BreedDetailsPresenter presenter;

    public DogViewHolder(View itemView, BreedDetailsPresenter presenter) {
        super(itemView);
        this.presenter = presenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(@NonNull final DogViewModel dogViewModel) {


        if (dogViewModel == null || dogViewModel.imageUrl == null) {

            throw new IllegalArgumentException("dogViewModel or url is null");

        }

        Context context = imageViewDog.getContext();
        int height = context.getResources().getDimensionPixelSize(R.dimen.dog_view_height);
        int width = context.getResources().getDimensionPixelSize(R.dimen.dog_view_width);

        Picasso.with(imageViewDog.getContext())
                .load(dogViewModel.imageUrl)
                .placeholder(R.drawable.ic_dog_placeholder)
                .resize(width, height)
                .centerCrop()
                .into(imageViewDog);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showDogDetails(dogViewModel);
            }
        });


    }
}
