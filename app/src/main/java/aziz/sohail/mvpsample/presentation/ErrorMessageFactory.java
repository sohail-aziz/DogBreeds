package aziz.sohail.mvpsample.presentation;

import android.content.Context;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import aziz.sohail.mvpsample.R;
import timber.log.Timber;


@Singleton
public class ErrorMessageFactory {
    private final Context context;

    @Inject
    public ErrorMessageFactory(Context context) {
        this.context = context;
    }

    public String getErrorMessage(Throwable throwable) {
        Timber.e(throwable);
        String errorMessage = throwable.getMessage();
        if (throwable instanceof IOException) {

            errorMessage = context.getString(R.string.error_internet);
        }

        return errorMessage;
    }
}
