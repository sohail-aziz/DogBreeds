package aziz.sohail.mvpsample.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import aziz.sohail.mvpsample.data.repository.BreedRepository;
import aziz.sohail.mvpsample.domain.usecase.GetBreedListUseCase;
import io.reactivex.Scheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Integration Test, tests interactions with BreedRepository.
 */

@RunWith(MockitoJUnitRunner.class)
public class GetBreedListUseCaseTest {

    GetBreedListUseCase getBreedListUseCase;

    @Mock
    Scheduler mockExecutorThread;
    @Mock
    Scheduler mockUiThread;
    @Mock
    BreedRepository mockRepository;

    @Before
    public void setup() {
        getBreedListUseCase = new GetBreedListUseCase(mockUiThread, mockExecutorThread, mockRepository);
    }

    @Test
    public void testRepositoryGetBreedListCalled() {

        getBreedListUseCase.createObservableUseCase(new GetBreedListUseCase.Params());

        verify(mockRepository).getBreedList();
        verifyNoMoreInteractions(mockRepository);

    }


}
