package com.mediclink.hassan.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mediclink.hassan.bakingapp.R;
import com.mediclink.hassan.bakingapp.model.Recipe;

import static com.mediclink.hassan.bakingapp.adapter.IngredientStepsAdapter.INGREDIENTS;
import static com.mediclink.hassan.bakingapp.adapter.IngredientStepsAdapter.STEPS;
import static com.mediclink.hassan.bakingapp.adapter.RecipeAdapter.RECIPE;

public class IngredientsStepActivity extends AppCompatActivity implements MasterListFragment.OnIngredientStepClickListener {

    ActionBar actionBar;
    public static final String POSITION = "position";
    public static final String PANES = "panes";
    private boolean mTwoPane;
    private Recipe mRecipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredients_step);

        mRecipe = getIntent().getParcelableExtra(RECIPE);
         actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle( mRecipe.getName());
        }

        mTwoPane = findViewById(R.id.detail_linear_layout) != null;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // When the home button is pressed, take the user back to the VisualizerActivity
        if (id == android.R.id.home) {
            onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onIngredientStepSelected(int position) {

        Bundle bundle = new Bundle();
        if (mTwoPane && position == 0) {
            IngredientStepDetailFragment fragment = new IngredientStepDetailFragment();
            bundle.putInt(POSITION, position);
            bundle.putBoolean(PANES, mTwoPane);
            bundle.putParcelableArrayList(INGREDIENTS, mRecipe.getIngredients());
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_ingredient_container, fragment)
                    .commit();

        } else if (mTwoPane) {
            IngredientStepDetailFragment fragment = new IngredientStepDetailFragment();
            bundle.putInt(POSITION, position);
            bundle.putBoolean(PANES, mTwoPane);
            bundle.putParcelableArrayList(STEPS, mRecipe.getSteps());
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_ingredient_container, fragment)
                    .commit();
        } else if (position == 0) {
            bundle.putInt(POSITION, position);
            bundle.putBoolean(PANES, mTwoPane);
            bundle.putParcelableArrayList(INGREDIENTS, mRecipe.getIngredients());
            Intent intent = new Intent(this, IngredientStepDetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            bundle.putInt(POSITION, position);
            bundle.putBoolean(PANES, mTwoPane);
            bundle.putParcelableArrayList(STEPS, mRecipe.getSteps());
            Intent intent = new Intent(this, IngredientStepDetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
