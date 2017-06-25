/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.mediclink.hassan.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediclink.hassan.bakingapp.R;
import com.mediclink.hassan.bakingapp.adapter.IngredientStepsAdapter;
import com.mediclink.hassan.bakingapp.model.Ingredient;
import com.mediclink.hassan.bakingapp.model.Recipe;

import java.util.ArrayList;

import static com.mediclink.hassan.bakingapp.adapter.RecipeAdapter.RECIPE;


// This fragment displays all of the AndroidMe images in one large list
// The list appears as a grid of images
public class MasterListFragment extends Fragment {
    private Recipe mRecipe;
    RecyclerView ingredientStepsrecyclerView;
    TextView mIngredientTv;
    ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
    private IngredientStepsAdapter ingredientStepsAdapter;
    // Define a new interface OnImageClickListener that triggers a callback in the host activity
    OnIngredientStepClickListener mClickListener;

    // OnImageClickListener interface, calls a method in the host activity named onImageSelected
    public interface OnIngredientStepClickListener {
        void onIngredientStepSelected(int position);
    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mClickListener = (OnIngredientStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnIngredientStepClickListener");
        }
    }


    // Mandatory empty constructor
    public MasterListFragment() {
    }

    // Inflates the GridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mRecipe = savedInstanceState.getParcelable(RECIPE);
        }

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
                mRecipe = getActivity().getIntent().getParcelableExtra(RECIPE);

        //set Ingredients

        // Get a reference to the GridView in the fragment_master_list xml layout file
        ingredientStepsrecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_ingredient_steps);
        // Create the adapter
        // This adapter takes in the context and an ArrayList of ALL the image resources to display
         ingredientStepsAdapter = new IngredientStepsAdapter(getContext(), mRecipe, mClickListener);

        // Set the adapter on the GridView
        ingredientStepsrecyclerView.setAdapter(ingredientStepsAdapter);

        // Return the root view
        return rootView;
    }

    String getIngredientsString(ArrayList<Ingredient> ingredients) {
        String ingredientlist ;
        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < ingredients.size() - 1; i++) {
            String singleIngredient = ". " + ingredients.get(i).getIngredient() + "(" + ingredients.get(i).getQuantity() + " " +
                    ingredients.get(i).getMeasure() +")"+ "\n";
            sb.append(singleIngredient);
        }

        ingredientlist = sb.toString();

        return ingredientlist;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECIPE, mRecipe);
    }
}
