package aziz.sohail.mvpsample.presentation.presenter;

import java.util.List;

import javax.inject.Inject;

import aziz.sohail.mvpsample.domain.model.Breed;
import aziz.sohail.mvpsample.domain.usecase.GetBreedListUseCase;
import aziz.sohail.mvpsample.domain.usecase.UserCaseObserver;
import aziz.sohail.mvpsample.presentation.ErrorMessageFactory;
import aziz.sohail.mvpsample.presentation.viewmodel.mapper.BreedToBreeViewModelMapper;
import aziz.sohail.mvpsample.presentation.viewmodel.BreedViewModel;
import timber.log.Timber;

/**
 * BreedList Presenter in MVP
 */
public class BreedListPresenter extends Presenter<BreedListPresenter.BreedListView> {

    private final GetBreedListUseCase getBreedListUseCase;
    private final BreedToBreeViewModelMapper mapper;
    private final ErrorMessageFactory errorMessageFactory;

    @Inject
    public BreedListPresenter(GetBreedListUseCase getBreedListUseCase, BreedToBreeViewModelMapper mapper, ErrorMessageFactory errorMessageFactory) {
        this.getBreedListUseCase = getBreedListUseCase;
        this.mapper = mapper;
        this.errorMessageFactory = errorMessageFactory;
    }


    /**
     * Gets breed list.
     * call {@link #setView(View)}} before calling this method
     *
     * @throws IllegalStateException if view not set
     */
    public void getBreedList() {
        Timber.d("getBreedList");
        if (getView() == null) {
            throw new IllegalStateException("view not set");
        }
        getView().showLoading();
        getBreedListUseCase.execute(new GetBreedListObserver(), new GetBreedListUseCase.Params());
    }


    public void destroy() {
        getBreedListUseCase.dispose();
        setView(null);
    }

    public void showBreedDetails(BreedViewModel model) {
        getView().openDetailView(model);
    }

    public final class GetBreedListObserver extends UserCaseObserver<List<Breed>> {
        @Override
        public void onNext(List<Breed> breeds) {
            super.onNext(breeds);
            Timber.d("onNext: size=" + breeds.size());
            getView().renderBreedList(mapper.map(breeds));
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Timber.e(e);
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


    public interface BreedListView extends Presenter.View {

        void renderBreedList(List<BreedViewModel> breedList);

        void handleError(String message);

        void openDetailView(BreedViewModel breedViewModel);

    }
}
