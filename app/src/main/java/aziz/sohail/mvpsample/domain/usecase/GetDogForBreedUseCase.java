package aziz.sohail.mvpsample.domain.usecase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import aziz.sohail.mvpsample.data.repository.DogRepository;
import aziz.sohail.mvpsample.di.ApplicationModule;
import aziz.sohail.mvpsample.domain.model.Dog;
import io.reactivex.Observable;
import io.reactivex.Scheduler;


public class GetDogForBreedUseCase extends UseCase<List<Dog>, GetDogForBreedUseCase.Params> {

    private final DogRepository dogRepository;

    @Inject
    public GetDogForBreedUseCase(@Named(ApplicationModule.NAME_UI_THREAD) Scheduler uiThread,
                                 @Named(ApplicationModule.NAME_EXECUTOR_THREAD) Scheduler executorThread,
                                 DogRepository dogRepository) {
        super(uiThread, executorThread);
        this.dogRepository = dogRepository;
    }

    @Override
    public Observable<List<Dog>> createObservableUseCase(Params params) {

        return dogRepository.getDogForBreed(params.breed);

    }

    public static final class Params {
        public final String breed;


        public Params(String breedName) {
            this.breed = breedName;
        }
    }

}
