package com.mediclink.hassan.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediclink.hassan.bakingapp.R;
import com.mediclink.hassan.bakingapp.model.Ingredient;
import com.mediclink.hassan.bakingapp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hassan on 6/20/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{

    public static final String RECIPE = "recipe";
    private Context context;
    private ArrayList<Recipe> mRecipe;

    public RecipeAdapter(Context context, ArrayList<Recipe> mRecipe) {
        this.context = context;
        this.mRecipe = mRecipe;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list, parent, false);
        RecipeViewHolder recipeViewHolder = new RecipeViewHolder(view);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        String servings = "Servings : ";
        holder.setRecipeName(mRecipe.get(position).getName());
        holder.setRecipeImage(context , mRecipe.get(position).getImageUrl());
        holder.setServings(servings + String.valueOf(mRecipe.get(position).getServings()));
    }

    @Override
    public int getItemCount() {
        if(mRecipe == null) {
            return 0;
        }else
            return mRecipe.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mRecipeName;
        ImageView mRecipeImageView;
        TextView mServing;
        TextView mIngredients;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            mRecipeName = (TextView)itemView.findViewById(R.id.recipeName);
            mServing = (TextView) itemView.findViewById(R.id.serving);
            mRecipeImageView = (ImageView) itemView.findViewById(R.id.recipeimage);
            mIngredients = (TextView) itemView.findViewById(R.id.ingredientsList);
            itemView.setOnClickListener(this);
        }

        public void setRecipeName (String recipeName){
            mRecipeName.setText(recipeName);
        }

        public void setServings (String serving){
            mServing.setText(serving);
        }

        public void setRecipeImage(final Context context, final String recipeImage) {

            if (!recipeImage.isEmpty()) {
                mRecipeImageView.setVisibility(View.VISIBLE);
                Picasso.with(context)
                        .load(recipeImage)
                        .into(mRecipeImageView);
            }
        }

        @Override
        public void onClick(View view) {
//            int clickedPosition = getAdapterPosition();
//            Toast.makeText(context, mRecipe.get(clickedPosition).getName(), Toast.LENGTH_LONG).show();

//            Intent intent = new Intent(context, IngredientList.class);
//            intent.putExtra(RECIPE, mRecipe.get(getAdapterPosition()));
//            context.startActivity(intent);
        }
    }
}
