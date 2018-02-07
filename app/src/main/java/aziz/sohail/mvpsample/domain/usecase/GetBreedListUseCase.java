package aziz.sohail.mvpsample.domain.usecase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import aziz.sohail.mvpsample.data.repository.Repository;
import aziz.sohail.mvpsample.domain.model.Breed;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import timber.log.Timber;


public class GetBreedListUseCase extends UseCase<List<Breed>, GetBreedListUseCase.Params> {

    private final Repository dogRepository;


    @Inject
    public GetBreedListUseCase(@Named("ui_thread") Scheduler uiThread, @Named("executor_thread") Scheduler executorThread, Repository dogRepository) {
        super(uiThread, executorThread);
        this.dogRepository = dogRepository;
    }

    @Override
    public Observable<List<Breed>> createObservableUseCase(Params params) {
        Timber.d("createObservableUseCase");

        return dogRepository.getBreedList();

    }

    public static final class Params {
        public Params() {
        }
    }
}
