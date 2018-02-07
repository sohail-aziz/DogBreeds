package aziz.sohail.mvpsample.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import aziz.sohail.mvpsample.MyApplication;
import aziz.sohail.mvpsample.R;
import aziz.sohail.mvpsample.presentation.presenter.DogPresenter;
import aziz.sohail.mvpsample.presentation.viewmodel.DogViewModel;
import butterknife.BindView;

public class DogActivity extends BaseActivity implements DogPresenter.DogView {

    private static final String EXTRA_ID = "DogActivity.extra_id";

    @Inject
    DogPresenter presenter;

    @BindView(R.id.image_view_dog)
    ImageView imageViewDog;


    public static Intent getCallingIntent(Context context, DogViewModel dogViewModel) {

        Intent intent = new Intent(context, DogActivity.class);
        intent.putExtra(EXTRA_ID, dogViewModel);

        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dog;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        initializeDagger();
        setupToolbar();
        initializePresenter();
        loadDogDetails();

    }

    private void setupToolbar() {
        getSupportActionBar().setTitle("Dog");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadDogDetails() {

        DogViewModel dogViewModel = getIntent().getParcelableExtra(EXTRA_ID);
        if (dogViewModel == null) {
            throw new IllegalArgumentException("dogViewModel is null");
        }
        presenter.loadDogDetails(dogViewModel);
    }

    private void initializePresenter() {
        presenter.setView(this);

    }

    private void initializeDagger() {
        ((MyApplication) getApplication()).getApplicationComponent().inject(this);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadImage(String url) {

        Picasso.with(this)
                .load(url)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(imageViewDog);

    }
}
