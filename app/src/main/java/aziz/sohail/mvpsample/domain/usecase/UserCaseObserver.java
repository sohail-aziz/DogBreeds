package aziz.sohail.mvpsample.domain.usecase;

import io.reactivex.observers.DisposableObserver;

/**
 * Base observer for all the use cases
 * @param <T> Result type
 */
public abstract class UserCaseObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
