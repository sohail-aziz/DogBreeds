package aziz.sohail.mvpsample.presentation.presenter;

import javax.inject.Inject;

import aziz.sohail.mvpsample.presentation.viewmodel.DogViewModel;

/**
 * Dog Presenter in MVP
 */

public class DogPresenter extends Presenter<DogPresenter.DogView> {

    @Inject
    public DogPresenter() {
    }

    public void loadDogDetails(DogViewModel dogViewModel) {
        if (getView() == null) {
            throw new IllegalStateException("view not set");
        }

        getView().loadImage(dogViewModel.imageUrl);
    }

    public interface DogView extends Presenter.View {
        void loadImage(String url);
    }
}
