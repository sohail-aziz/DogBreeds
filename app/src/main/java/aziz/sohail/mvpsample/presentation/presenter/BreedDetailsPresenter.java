package aziz.sohail.mvpsample.presentation.presenter;

import java.util.List;

import javax.inject.Inject;

import aziz.sohail.mvpsample.domain.model.Dog;
import aziz.sohail.mvpsample.domain.usecase.GetDogForBreedUseCase;
import aziz.sohail.mvpsample.domain.usecase.UserCaseObserver;
import aziz.sohail.mvpsample.presentation.ErrorMessageFactory;
import aziz.sohail.mvpsample.presentation.viewmodel.mapper.DogToDogViewModelMapper;
import aziz.sohail.mvpsample.presentation.viewmodel.BreedViewModel;
import aziz.sohail.mvpsample.presentation.viewmodel.DogViewModel;
import timber.log.Timber;

/**
 * BreedDetails Presenter in MVP
 */
public class BreedDetailsPresenter extends Presenter<BreedDetailsPresenter.BreedDetailsView> {


    private final GetDogForBreedUseCase getBreedDetailsUseCase;
    private final DogToDogViewModelMapper mapper;
    private final ErrorMessageFactory errorMessageFactory;

    @Inject
    public BreedDetailsPresenter(GetDogForBreedUseCase getBreedDetailsUseCase, DogToDogViewModelMapper mapper, ErrorMessageFactory errorMessageFactory) {
        this.getBreedDetailsUseCase = getBreedDetailsUseCase;
        this.mapper = mapper;
        this.errorMessageFactory = errorMessageFactory;
    }


    /**
     * Gets BreedDetails
     * Set {@link #setView(View)} before calling this method
     *
     * @param breedViewModel {@link BreedViewModel}
     * @throws IllegalStateException if view not set
     */
    public void getBreedDetails(final BreedViewModel breedViewModel) {
        if (getView() == null) {
            throw new IllegalStateException("view not set");
        }

        getView().showLoading();
        getBreedDetailsUseCase.execute(new BreedDetailsObserver(), new GetDogForBreedUseCase.Params(breedViewModel.breedName));
    }


    public void destroy() {
        getBreedDetailsUseCase.dispose();
        setView(null);

    }


    public void showDogDetails(DogViewModel dogViewModel) {
        getView().openDogView(dogViewModel);
    }


    private final class BreedDetailsObserver extends UserCaseObserver<List<Dog>> {

        @Override
        public void onNext(List<Dog> dogs) {
            super.onNext(dogs);
            Timber.d("onNext:size=" + dogs.size());
            getView().renderDetails(mapper.map(dogs));
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Timber.d("onError:e=" + e);
            getView().hideLoading();
            getView().handleError(errorMessageFactory.getErrorMessage(e));

        }

        @Override
        public void onComplete() {
            super.onComplete();
            Timber.d("onComplete");

            getView().hideLoading();
        }
    }

    public interface BreedDetailsView extends Presenter.View {
        void renderDetails(List<DogViewModel> dogs);

        void handleError(String message);

        void openDogView(DogViewModel model);
    }
}
