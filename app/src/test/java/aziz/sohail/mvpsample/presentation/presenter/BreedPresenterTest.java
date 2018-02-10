package aziz.sohail.mvpsample.presentation.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import aziz.sohail.mvpsample.domain.usecase.GetBreedListUseCase;
import aziz.sohail.mvpsample.presentation.ErrorMessageFactory;
import aziz.sohail.mvpsample.presentation.viewmodel.mapper.BreedToBreeViewModelMapper;
import io.reactivex.observers.DisposableObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by sohailaziz on 16/1/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class BreedPresenterTest {

    @Mock
    GetBreedListUseCase mockUseCase;
    @Mock
    BreedToBreeViewModelMapper mockMapper;
    @Mock
    ErrorMessageFactory mockErrorMessageFactory;

    @Mock
    BreedListPresenter.BreedListView mockView;
    BreedListPresenter presenter;


    @Before
    public void setup() {
        presenter = new BreedListPresenter(mockUseCase, mockMapper, mockErrorMessageFactory);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetLocalBreedListThrowsExceptionWithoutView() {
        presenter.getLocalBreedList();
    }

    @Test(expected = IllegalStateException.class)
    public void testGetBreedListThrowsExceptionWithoutView() {
        presenter.getBreedList();
    }

    @Test
    public void testShowProgressCalledOnOGetBreeds() {

        presenter.setView(mockView);

        presenter.getBreedList();

        verify(mockView).showLoading();
    }

    @Test
    public void testUseCaseCalledOnGetBreeds() {
        presenter.setView(mockView);
        presenter.getBreedList();

        verify(mockUseCase).execute(any(DisposableObserver.class), any(GetBreedListUseCase.Params.class));
        verifyNoMoreInteractions(mockUseCase);

    }

    @Test
    public void testShowProgressCalledOnOGetBreedsLocal() {

        presenter.setView(mockView);

        presenter.getLocalBreedList();

        verify(mockView).showLoading();
    }

    @Test
    public void testUseCaseCalledOnGetBreedsLocal() {
        presenter.setView(mockView);
        presenter.getLocalBreedList();

        verify(mockUseCase).execute(any(DisposableObserver.class), any(GetBreedListUseCase.Params.class));
        verifyNoMoreInteractions(mockUseCase);

    }
}
