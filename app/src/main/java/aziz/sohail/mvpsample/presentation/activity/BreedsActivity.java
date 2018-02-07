package aziz.sohail.mvpsample.presentation.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import aziz.sohail.mvpsample.MyApplication;
import aziz.sohail.mvpsample.R;
import aziz.sohail.mvpsample.presentation.Navigator.Navigator;
import aziz.sohail.mvpsample.presentation.adapter.BreedAdapter;
import aziz.sohail.mvpsample.presentation.presenter.BreedListPresenter;
import aziz.sohail.mvpsample.presentation.viewmodel.BreedViewModel;
import butterknife.BindView;
import timber.log.Timber;

public class BreedsActivity extends BaseActivity implements BreedListPresenter.BreedListView {


    @Inject
    BreedListPresenter presenter;
    @BindView(R.id.recycler_view_breeds)
    RecyclerView recyclerViewBreeds;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    BreedAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Timber.d("initView: savedInstance=" + savedInstanceState);

        initializeDagger();
        initializePresenter();
        initializeAdapter();
        initializeRecyclerView();
        initializeSwipeRefresh();

        loadBreedList();


    }

    private void initializeSwipeRefresh() {

        swipeRefreshLayout.setOnRefreshListener(refreshListener);
    }

    private final SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadBreedList();
        }
    };


    private void loadBreedList() {
        Timber.d("loadBreedList");
        presenter.getBreedList();

    }

    private void initializeAdapter() {
        adapter = new BreedAdapter(this, presenter);
    }

    private void initializeRecyclerView() {
        recyclerViewBreeds.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBreeds.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewBreeds.setHasFixedSize(true);
        recyclerViewBreeds.setAdapter(adapter);

    }


    private void initializeDagger() {
        ((MyApplication) getApplication()).getApplicationComponent().inject(this);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        Timber.d("onDestroy");
        super.onDestroy();
        presenter.destroy();

    }


    @Override
    public void showLoading() {
        Timber.d("showLoading");
        swipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void hideLoading() {
        Timber.d("hideLoading");
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void openDetailView(BreedViewModel breedViewModel) {

        Navigator.startBreedDetailsActivity(this, breedViewModel);

    }

    @Override
    public void renderBreedList(List<BreedViewModel> breedList) {
        Timber.d("renderBreedList: size=" + breedList.size());
        adapter.update(breedList);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void handleError(String message) {

        Timber.e("handleError: message=%s", message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
