package aziz.sohail.mvpsample.presentation.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import aziz.sohail.mvpsample.domain.usecase.GetBreedDetailsUseCase;
import aziz.sohail.mvpsample.presentation.ErrorMessageFactory;
import aziz.sohail.mvpsample.presentation.presenter.BreedDetailsPresenter;
import aziz.sohail.mvpsample.presentation.viewmodel.BreedViewModel;
import aziz.sohail.mvpsample.presentation.viewmodel.mapper.DogToDogViewModelMapper;
import io.reactivex.observers.DisposableObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by sohailaziz on 16/1/18.
 */


@RunWith(MockitoJUnitRunner.class)
public class BreedDetailsPresenterTest {

    BreedDetailsPresenter presenter;
    @Mock
    GetBreedDetailsUseCase mockUseCase;
    @Mock
    DogToDogViewModelMapper mockMapper;
    @Mock
    ErrorMessageFactory mockErrorMessageFactory;
    @Mock
    BreedDetailsPresenter.BreedDetailsView mockView;

    @Before
    public void setup() {
        presenter = new BreedDetailsPresenter(mockUseCase, mockMapper, mockErrorMessageFactory);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetBreedDetailsThrowsExceptionIfViewNotSet() {
        presenter.getBreedDetails(mock(BreedViewModel.class));
    }

    @Test(expected = IllegalStateException.class)
    public void testGetLocalBreedDetailsThrowsExceptionIfViewNotSet() {
        presenter.getLocalBreedDetails(mock(BreedViewModel.class));
    }

    @Test
    public void testShowProgressCalledOnGetBreedsDetails() {


        presenter.setView(mockView);
        presenter.getBreedDetails(mock(BreedViewModel.class));

        verify(mockView).showLoading();

    }

    @Test
    public void testShowProgressCalledOnGetBreedsDetailsLocal() {


        presenter.setView(mockView);
        presenter.getLocalBreedDetails(mock(BreedViewModel.class));

        verify(mockView).showLoading();

    }

    @Test
    public void testUseCaseCalledOnGetBreedsDetails() {

        presenter.setView(mockView);
        presenter.getBreedDetails(mock(BreedViewModel.class));

        verify(mockUseCase).execute(any(DisposableObserver.class), any(GetBreedDetailsUseCase.Params.class));
        verifyNoMoreInteractions(mockUseCase);

    }

    @Test
    public void testUseCaseCalledOnGetBreedsDetailsLocal() {

        presenter.setView(mockView);
        presenter.getLocalBreedDetails(mock(BreedViewModel.class));

        verify(mockUseCase).execute(any(DisposableObserver.class), any(GetBreedDetailsUseCase.Params.class));
        verifyNoMoreInteractions(mockUseCase);

    }
}