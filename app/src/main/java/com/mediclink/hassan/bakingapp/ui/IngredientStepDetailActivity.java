package com.mediclink.hassan.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mediclink.hassan.bakingapp.R;
import com.mediclink.hassan.bakingapp.model.Ingredient;
import com.mediclink.hassan.bakingapp.model.Step;

import java.util.ArrayList;

import static com.mediclink.hassan.bakingapp.adapter.IngredientStepsAdapter.INGREDIENTS;
import static com.mediclink.hassan.bakingapp.adapter.IngredientStepsAdapter.STEPS;
import static com.mediclink.hassan.bakingapp.ui.IngredientsStepActivity.PANES;
import static com.mediclink.hassan.bakingapp.ui.IngredientsStepActivity.POSITION;

public class IngredientStepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_step_detail);

        if (savedInstanceState == null) {
            ArrayList<Ingredient> ingredients = getIntent().getParcelableArrayListExtra(INGREDIENTS);
            ArrayList<Step> steps = getIntent().getParcelableArrayListExtra(STEPS);
            int position = getIntent().getIntExtra(POSITION, 0);
            boolean mTwoPane = getIntent().getBooleanExtra(PANES, false);
            IngredientStepDetailFragment fragment = new IngredientStepDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(POSITION, position);
            bundle.putBoolean(PANES, mTwoPane);
            bundle.putParcelableArrayList(INGREDIENTS, ingredients);
            bundle.putParcelableArrayList(STEPS, steps);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_container, fragment)
                    .commit();

        }
    }
}
