package aziz.sohail.mvpsample.domain.usecase;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * BaseUse cases contract for all the interactors
 *
 * @param <T>      UseCases Result type
 * @param <Params> UseCaes parameters
 */
public abstract class UseCase<T, Params> {

    private final CompositeDisposable compositeDisposable;
    private final Scheduler uiThread;
    private final Scheduler executorThread;


    UseCase(Scheduler uiThread, Scheduler executorThread) {
        this.compositeDisposable = new CompositeDisposable();
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    public void execute(DisposableObserver<T> disposableObserver, Params params) {
        if (disposableObserver == null || params == null) {
            throw new IllegalArgumentException("null observer or params");
        }

        final Observable<T> observable = createObservableUseCase(params)
                .subscribeOn(executorThread)
                .observeOn(uiThread, true);

        Disposable disposable = observable.subscribeWith(disposableObserver);
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    /**
     * Child observable
     *
     * @return
     */
    public abstract Observable<T> createObservableUseCase(Params params);
}
