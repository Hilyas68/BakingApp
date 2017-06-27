
package com.mediclink.hassan.bakingapp;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mediclink.hassan.bakingapp.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * This test demos a user clicking the decrement button and verifying that it properly decrease
 * the quantity the total cost.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityRecyclerTest {

    /**
     * The ActivityTestRule is a rule provided by Android used for functional testing of a single
     * activity. The activity that will be tested will be launched before each test that's annotated
     * with @Test and before methods annotated with @before. The activity will be terminated after
     * the test and methods annotated with @After are complete. This rule allows you to directly
     * access the activity during the test.
     */

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkifRecyclerisPresent() {


        onView(withId(R.id.recipeRecycler)).check(matches(hasDescendant(withText("Brownies"))));
//               .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//
//
//    }


//    private static Matcher<View> childAtPosition(
//            final Matcher<View> parentMatcher, final int position) {
//
//        return new TypeSafeMatcher<View>() {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Child at position " + position  + " in parent ");
//                parentMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                ViewParent parent = view.getParent();
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position ));
//            }
//        };
//    }
//
//    @Test
//    public void mainActivityTest() {
//        ViewInteraction textView = onView(
//                allOf(withId(R.id.recipeName), withText("Nutella Pie"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.recipeRecycler),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textView.check(matches(withText("Nutella Pie")));
//
//        ViewInteraction recyclerView = onView(
//                allOf(withId(R.id.recipeRecycler), isDisplayed()));
//        recyclerView.perform(actionOnItemAtPosition(0, click()));
//
//    }

    }
}