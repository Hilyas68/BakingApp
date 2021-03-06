package com.mediclink.hassan.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.mediclink.hassan.bakingapp.R;
import com.mediclink.hassan.bakingapp.adapter.RecipeAdapter;
import com.mediclink.hassan.bakingapp.model.Recipe;
import com.mediclink.hassan.bakingapp.network.ApiService;
import com.mediclink.hassan.bakingapp.network.RetroClient;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mediclink.hassan.bakingapp.adapter.RecipeAdapter.RECIPE;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Recipe> mRecipesList;
    ProgressBar mLoadingIndicator;
    RecyclerView mRecipeRecyclerView;
    TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorTextView = (TextView) findViewById(R.id.error);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.progressBar);
        mRecipeRecyclerView = (RecyclerView) findViewById(R.id.recipeRecycler);
        mRecipeRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        //Retrieve saved state
        if(savedInstanceState != null){
            mRecipesList = savedInstanceState.getParcelableArrayList(RECIPE);
            loadData();
        }else {
            getRecipes();
        }
    }

    private void getRecipes() {
        ApiService apiInterface = RetroClient.getRetrofitInstance().create(ApiService.class);
        final Type TYPE = new TypeToken<ArrayList<Recipe>>() {
        }.getType();
        Call<JsonArray> call = apiInterface.getRecipe();
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                mRecipesList = new Gson().fromJson(response.body(), TYPE);
                loadData();
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Check your internet connection", Toast.LENGTH_LONG).show();
                onError();
            }
        });
    }

    public void onError(){
        errorTextView.setVisibility(View.VISIBLE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
    }

    private void loadData() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        RecipeAdapter recipeAdapter = new RecipeAdapter(MainActivity.this, mRecipesList);
        mRecipeRecyclerView.setAdapter(recipeAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPE, mRecipesList);

    }
}


