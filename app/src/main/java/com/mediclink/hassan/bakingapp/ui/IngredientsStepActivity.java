package com.mediclink.hassan.bakingapp.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mediclink.hassan.bakingapp.R;
import com.mediclink.hassan.bakingapp.model.Ingredient;
import com.mediclink.hassan.bakingapp.model.Recipe;
import com.mediclink.hassan.bakingapp.model.RecipeContract.RecipeEntry;

import java.util.ArrayList;

import static com.mediclink.hassan.bakingapp.adapter.IngredientStepsAdapter.INGREDIENTS;
import static com.mediclink.hassan.bakingapp.adapter.IngredientStepsAdapter.STEPS;
import static com.mediclink.hassan.bakingapp.adapter.RecipeAdapter.RECIPE;
import static com.mediclink.hassan.bakingapp.model.RecipeContract.RECIPE_CONTENT_URI;

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
//        }
//
//        if (id == android.R.id.home) {
            if (isAdd()) {
                removeRecipe();

                Toast.makeText(this, String.format(getString(R.string.removed_message), mRecipe.getName()), Toast.LENGTH_LONG).show();
            } else {
                addRecipe();

                Toast.makeText(this, String.format(getString(R.string.added_message), mRecipe.getName()), Toast.LENGTH_LONG).show();
            }
            return true;
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

    private boolean isAdd() {
        String[] projection = {RecipeEntry.COLUMN_RECIPE_ID};
        String selection = RecipeEntry.COLUMN_RECIPE_ID + " = " + mRecipe.getId();
        Cursor cursor = getContentResolver().query(RECIPE_CONTENT_URI,
                projection,
                selection,
                null,
                null,
                null);

        return (cursor != null ? cursor.getCount() : 0) > 0;
    }

    synchronized private void removeRecipe() {
        getContentResolver().delete(RECIPE_CONTENT_URI, null, null);
    }

    synchronized private void addRecipe() {
        getContentResolver().delete(RECIPE_CONTENT_URI, null, null);
        getIngredient(mRecipe.getIngredients());
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_add);
        return super.onPrepareOptionsMenu(menu);
    }

    private void getIngredient(ArrayList<Ingredient> ingredients) {

        for (Ingredient ingredient : ingredients) {
            ContentValues values = new ContentValues();
            values.put(RecipeEntry.COLUMN_RECIPE_ID, mRecipe.getId());
            values.put(RecipeEntry.COLUMN_RECIPE_NAME, mRecipe.getName());
            values.put(RecipeEntry.COLUMN_INGREDIENT_NAME, ingredient.getIngredient());
            values.put(RecipeEntry.COLUMN_INGREDIENT_MEASURE, ingredient.getMeasure());
            values.put(RecipeEntry.COLUMN_INGREDIENT_QUANTITY, ingredient.getQuantity());
            getContentResolver().insert(RECIPE_CONTENT_URI, values);
        }
    }

}

