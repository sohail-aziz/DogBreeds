package aziz.sohail.mvpsample.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import aziz.sohail.mvpsample.MyApplication;
import aziz.sohail.mvpsample.R;
import aziz.sohail.mvpsample.presentation.Navigator.Navigator;
import aziz.sohail.mvpsample.presentation.adapter.DogAdapter;
import aziz.sohail.mvpsample.presentation.presenter.BreedDetailsPresenter;
import aziz.sohail.mvpsample.presentation.viewmodel.BreedViewModel;
import aziz.sohail.mvpsample.presentation.viewmodel.DogViewModel;
import butterknife.BindView;
import timber.log.Timber;

public class BreedDetailsActivity extends BaseActivity implements BreedDetailsPresenter.BreedDetailsView {

    private static final String EXTRA_ID = "BreedDetailsActivity.extra_id";

    @Inject
    BreedDetailsPresenter presenter;

    @BindView(R.id.recycler_view_dogs)
    RecyclerView recyclerViewDogs;
    @BindView(R.id.progress_bar_loading)
    ProgressBar progressBar;

    DogAdapter adapter;
    BreedViewModel breedViewModel;


    public static Intent getCallingIntent(Context context, BreedViewModel breedViewModel) {
        Intent intent = new Intent(context, BreedDetailsActivity.class);
        intent.putExtra(EXTRA_ID, breedViewModel);
        return intent;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Timber.d("initView: savedInstance=" + savedInstanceState);

        initializeDagger();
        setupToolbar();
        initializePresenter();
        initializeAdapter();
        initializeRecyclerView();

        loadBreedDetails(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("breed", breedViewModel);

        super.onSaveInstanceState(outState);
    }

    private void setupToolbar() {
        breedViewModel = getIntent().getParcelableExtra(EXTRA_ID);
        if (breedViewModel != null) {
            getSupportActionBar().setTitle(breedViewModel.breedName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    private void loadBreedDetails(Bundle savedInstanceState) {


        if (savedInstanceState == null) {
            breedViewModel = getIntent().getParcelableExtra(EXTRA_ID);
        } else {
            breedViewModel = savedInstanceState.getParcelable("breed");
        }

        presenter.getBreedDetails(breedViewModel);


    }

    private void initializeRecyclerView() {

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        recyclerViewDogs.setLayoutManager(gridLayoutManager);
        recyclerViewDogs.setHasFixedSize(true);
        recyclerViewDogs.setAdapter(adapter);

    }

    /**
     * Provides different spam count based on screen orientation
     * This method can be improved to actually calculate the number of spans based on screen width
     *
     * @return
     */
    private int getSpanCount() {
        int spanCount = 3;
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE))
                .getDefaultDisplay();

        int orientation = display.getRotation();
        if (orientation == Surface.ROTATION_90
                || orientation == Surface.ROTATION_270) {
            spanCount = 5;
        }

        return spanCount;
    }

    private void initializeAdapter() {
        adapter = new DogAdapter(this, presenter);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }


    private void initializeDagger() {
        ((MyApplication) getApplication()).getApplicationComponent().inject(this);
    }


    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void renderDetails(List<DogViewModel> dogs) {

        adapter.update(dogs);

    }

    @Override
    public void handleError(String message) {
        showToast(message);

    }

    @Override
    public void openDogView(DogViewModel model) {
        Navigator.startDogActivity(this, model);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
