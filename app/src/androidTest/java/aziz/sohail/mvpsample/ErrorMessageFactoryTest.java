package aziz.sohail.mvpsample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import aziz.sohail.mvpsample.presentation.ErrorMessageFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(AndroidJUnit4.class)
public class ErrorMessageFactoryTest {


    @Test
    public void testIOExceptionError() {

        Context context = InstrumentationRegistry.getTargetContext();

        String expectedError = context.getString(R.string.error_internet);

        ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory(context);

        String actualError = errorMessageFactory.getErrorMessage(new IOException(""));

        assertThat(actualError, is(expectedError));

    }


}
