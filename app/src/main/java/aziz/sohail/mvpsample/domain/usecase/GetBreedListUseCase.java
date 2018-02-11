package aziz.sohail.mvpsample.domain.usecase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import aziz.sohail.mvpsample.data.repository.BreedRepository;
import aziz.sohail.mvpsample.di.ApplicationModule;
import aziz.sohail.mvpsample.domain.model.Breed;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import timber.log.Timber;


public class GetBreedListUseCase extends UseCase<List<Breed>, GetBreedListUseCase.Params> {

    private final BreedRepository breedRepository;


    @Inject
    public GetBreedListUseCase(@Named(ApplicationModule.NAME_UI_THREAD) Scheduler uiThread, @Named(ApplicationModule.NAME_EXECUTOR_THREAD) Scheduler executorThread, BreedRepository breedRepository) {
        super(uiThread, executorThread);
        this.breedRepository = breedRepository;
    }

    @Override
    public Observable<List<Breed>> createObservableUseCase(Params params) {
        Timber.d("createObservableUseCase");

        return breedRepository.getBreedList();

    }

    public static final class Params {
        public Params() {
        }
    }
}
