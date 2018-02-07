package aziz.sohail.mvpsample.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import aziz.sohail.mvpsample.R;
import aziz.sohail.mvpsample.presentation.presenter.BreedListPresenter;
import aziz.sohail.mvpsample.presentation.viewmodel.BreedViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;


public class BreedViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view_breed_name)

    TextView textViewBreedName;

    private BreedListPresenter presenter;

    public BreedViewHolder(View itemView, BreedListPresenter presenter) {
        super(itemView);
        this.presenter = presenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(final BreedViewModel model) {

        textViewBreedName.setText(model.breedName);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showBreedDetails(model);
            }
        });
    }
}
