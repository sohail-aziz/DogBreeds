package aziz.sohail.mvpsample.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import aziz.sohail.mvpsample.data.repository.DogRepository;
import aziz.sohail.mvpsample.domain.usecase.GetDogForBreedUseCase;
import io.reactivex.Scheduler;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Integration Test tests Interaction with DogRepository
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GetDogForBreedUseCaseTest {

    GetDogForBreedUseCase getBreedDetailsUseCase;

    @Mock
    Scheduler mockExecutorThread;
    @Mock
    Scheduler mockUiThread;
    @Mock
    DogRepository mockRepository;

    @Before
    public void setup() {
        getBreedDetailsUseCase = new GetDogForBreedUseCase(mockUiThread, mockExecutorThread, mockRepository);
    }


    @Test
    public void testGetBreedDetails() {

        getBreedDetailsUseCase.createObservableUseCase(new GetDogForBreedUseCase.Params("breed_name"));
        verify(mockRepository).getDogForBreed(eq(("breed_name")));
        verifyNoMoreInteractions(mockRepository);

    }
}
