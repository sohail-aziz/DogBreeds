package aziz.sohail.mvpsample.presentation.activity;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import aziz.sohail.mvpsample.JsonUtils;
import aziz.sohail.mvpsample.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.times;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static aziz.sohail.mvpsample.presentation.activity.RecyclerViewAssertions.atPosition;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class BreedsActivityTest {

    public static final int BREED_LIST_SIZE = 5;
    public static final String FIRST_BREED_NAME ="affenpinscher" ;

    @Rule
    public ActivityTestRule<BreedsActivity> breedsActivityActivityTestRule = new ActivityTestRule<BreedsActivity>(BreedsActivity.class);

    @Test
    public void testRecyclerViewIsVisible() {
        onView(withId(R.id.recycler_view_breeds)).check(matches(isDisplayed()));
    }

    @Test
    public void testItemClickLaunchesBreedDetailsActivity() {
        Intents.init();
        onView(withId(R.id.recycler_view_breeds)).perform(actionOnItemAtPosition(0, click()));
        intended(hasComponent(BreedDetailsActivity.class.getName()), times(1));
        Intents.release();
    }

    @Test
    public void testBreedListSize() {
        onView(withId(R.id.recycler_view_breeds)).check(new RecyclerViewAssertions.RecyclerViewItemCountAssertion(BREED_LIST_SIZE));
    }

    @Test
    public void testHasFirstItem() {
        onView(withId(R.id.recycler_view_breeds))
                .check(matches(atPosition(0, hasDescendant(withText(FIRST_BREED_NAME)))));//affenpinscher
    }


}

