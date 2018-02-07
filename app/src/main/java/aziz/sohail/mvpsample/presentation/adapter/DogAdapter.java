package aziz.sohail.mvpsample.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import aziz.sohail.mvpsample.R;
import aziz.sohail.mvpsample.presentation.presenter.BreedDetailsPresenter;
import aziz.sohail.mvpsample.presentation.viewmodel.DogViewModel;


public class DogAdapter extends RecyclerView.Adapter<DogViewHolder> {

    private final Context context;
    private List<DogViewModel> dogViewModels;
    private BreedDetailsPresenter presenter;

    public DogAdapter(Context context, BreedDetailsPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.dogViewModels = new ArrayList<>();
    }

    public void update(List<DogViewModel> dogViewModels) {

        this.dogViewModels.addAll(dogViewModels);
        notifyDataSetChanged();
    }


    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_dog_item, parent, false);
        return new DogViewHolder(root, presenter);
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, int position) {

        DogViewModel model = dogViewModels.get(position);
        holder.render(model);

    }

    @Override
    public int getItemCount() {
        return dogViewModels.size();
    }
}
