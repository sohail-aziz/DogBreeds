package aziz.sohail.mvpsample.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import aziz.sohail.mvpsample.data.repository.Repository;
import aziz.sohail.mvpsample.domain.usecase.GetBreedListUseCase;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by sohailaziz on 17/1/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class GetBreedListUseCaseTest {

    GetBreedListUseCase getBreedListUseCase;

    @Mock
    Scheduler mockExecutorThread;
    @Mock
    Scheduler mockUiThread;
    @Mock
    Repository mockRepository;

    @Before
    public void setup() {
        getBreedListUseCase = new GetBreedListUseCase(mockUiThread, mockExecutorThread, mockRepository);
    }

    @Test
    public void testRepositoryGetBreedListCalled() {

        getBreedListUseCase.createObservableUseCase(new GetBreedListUseCase.Params(false));

        verify(mockRepository).getBreedList();
        verifyNoMoreInteractions(mockRepository);

    }

    @Test
    public void testRepositoryGetBreedListLocalCalled() {

        getBreedListUseCase.createObservableUseCase(new GetBreedListUseCase.Params(true));

        verify(mockRepository).getBreedListLocal();
        verifyNoMoreInteractions(mockRepository);

    }


}
