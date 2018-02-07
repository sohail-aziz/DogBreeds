package aziz.sohail.mvpsample.domain.usecase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import aziz.sohail.mvpsample.data.repository.Repository;
import aziz.sohail.mvpsample.domain.model.Dog;
import io.reactivex.Observable;
import io.reactivex.Scheduler;


public class GetBreedDetailsUseCase extends UseCase<List<Dog>, GetBreedDetailsUseCase.Params> {

    private final Repository repository;

    @Inject
    public GetBreedDetailsUseCase(@Named("ui_thread") Scheduler uiThread,
                                  @Named("executor_thread") Scheduler executorThread,
                                  Repository repository) {
        super(uiThread, executorThread);
        this.repository = repository;
    }

    @Override
    public Observable<List<Dog>> createObservableUseCase(Params params) {

        return repository.getBreedDetails(params.breed);

    }

    public static final class Params {
        public final String breed;


        public Params(String breedName) {
            this.breed = breedName;
        }
    }

}
