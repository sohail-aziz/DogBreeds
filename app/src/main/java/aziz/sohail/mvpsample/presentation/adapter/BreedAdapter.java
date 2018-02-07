package aziz.sohail.mvpsample.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import aziz.sohail.mvpsample.R;
import aziz.sohail.mvpsample.presentation.presenter.BreedListPresenter;
import aziz.sohail.mvpsample.presentation.viewmodel.BreedViewModel;


public class BreedAdapter extends RecyclerView.Adapter<BreedViewHolder> {

    private final Context context;
    private List<BreedViewModel> breedModels;
    private BreedListPresenter presenter;

    public BreedAdapter(Context context, BreedListPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.breedModels = new ArrayList<>();
    }

    public void update(List<BreedViewModel> models) {
        this.breedModels.addAll(models);
    }

    @Override

    public BreedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_breed_item, parent, false);
        return new BreedViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(BreedViewHolder holder, int position) {

        BreedViewModel breedViewModel = breedModels.get(position);
        holder.render(breedViewModel);

    }

    @Override
    public int getItemCount() {
        return breedModels.size();
    }


}
