package aziz.sohail.mvpsample.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import aziz.sohail.mvpsample.data.repository.Repository;
import aziz.sohail.mvpsample.domain.usecase.GetBreedDetailsUseCase;
import aziz.sohail.mvpsample.domain.usecase.GetBreedListUseCase;
import io.reactivex.Scheduler;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by sohailaziz on 17/1/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class GetBreedDetailsUseCaseTest {

    GetBreedDetailsUseCase getBreedDetailsUseCase;

    @Mock
    Scheduler mockExecutorThread;
    @Mock
    Scheduler mockUiThread;
    @Mock
    Repository mockRepository;

    @Before
    public void setup() {
        getBreedDetailsUseCase = new GetBreedDetailsUseCase(mockUiThread, mockExecutorThread, mockRepository);
    }

    @Test
    public void testGetBreedDetailsLocal() {

        getBreedDetailsUseCase.createObservableUseCase(new GetBreedDetailsUseCase.Params("breed_name", true));
        verify(mockRepository).getBreedDetailsLocal(eq(("breed_name")));
        verifyNoMoreInteractions(mockRepository);

    }

    @Test
    public void testGetBreedDetails() {

        getBreedDetailsUseCase.createObservableUseCase(new GetBreedDetailsUseCase.Params("breed_name", false));
        verify(mockRepository).getBreedDetails(eq(("breed_name")));
        verifyNoMoreInteractions(mockRepository);

    }
}
