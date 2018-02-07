package aziz.sohail.mvpsample.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import aziz.sohail.mvpsample.R;
import aziz.sohail.mvpsample.data.repository.mapper.BreedMapper;
import aziz.sohail.mvpsample.presentation.viewmodel.BreedViewModel;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.times;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by sohailaziz on 16/1/18.
 */

@RunWith(AndroidJUnit4.class)
public class BreedDetailsActivityTest {

    public static final String BREED_NAME = "akita";


    @Rule
    public ActivityTestRule<BreedDetailsActivity> mActivityRule =
            new ActivityTestRule<BreedDetailsActivity>(BreedDetailsActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context context = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();

                    return BreedDetailsActivity.getCallingIntent(context, new BreedViewModel(BREED_NAME));

                }
            };


    @Test
    public void testDogRecyclerViewIsVisible() {
        onView(withId(R.id.recycler_view_dogs)).check(matches(isDisplayed()));
    }

    @Test
    public void testTitleIsSameAsReceivedInIntent() {

        String title = mActivityRule.getActivity().getSupportActionBar().getTitle().toString();

        assertThat(title, is(BREED_NAME));
    }

    @Test
    public void testItemClickLaunchesDogActivity() {

        Intents.init();
        onView(withId(R.id.recycler_view_dogs)).perform(actionOnItemAtPosition(0, click()));
        intended(hasComponent(DogActivity.class.getName()), times(1));
        Intents.release();

    }


}
