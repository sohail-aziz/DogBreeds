package aziz.sohail.mvpsample.presentation.activity;

import android.support.annotation.NonNull;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class BreedsActivityTest {


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

        onView(withId(R.id.recycler_view_breeds)).check(new RecyclerViewItemCountAssertion(5));
    }

    @Test
    public void testHasFirstItem() {
        onView(withId(R.id.recycler_view_breeds))
                .check(matches(atPosition(1, hasDescendant(withText("affenpinscher")))));
    }


    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        //checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    public static class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
    }
}

