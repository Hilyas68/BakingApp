package com.mediclink.hassan.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mediclink.hassan.bakingapp.R;
import com.mediclink.hassan.bakingapp.model.Ingredient;
import com.mediclink.hassan.bakingapp.model.Recipe;
import com.mediclink.hassan.bakingapp.ui.MasterListFragment;

import java.util.ArrayList;


/**
 * Created by hassan on 6/23/2017.
 */

public class IngredientStepsAdapter extends RecyclerView.Adapter<IngredientStepsAdapter.IngredientStepViewHolder> {

    public static final String INGREDIENTS = "ingredients";
    public static final String STEPS = "steps";
    private MasterListFragment.OnIngredientStepClickListener mClickListener;
    private Context mContext;
    private Recipe mRecipe;

    public IngredientStepsAdapter(Context context, Recipe recipe, MasterListFragment.OnIngredientStepClickListener listener)  {
        this.mContext = context;
        this.mRecipe = recipe;
        mClickListener = listener;
    }

    @Override
    public IngredientStepsAdapter.IngredientStepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_step_list_item, parent, false);
        IngredientStepViewHolder ingredientStepViewHolder = new IngredientStepViewHolder(view);
        return ingredientStepViewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientStepsAdapter.IngredientStepViewHolder holder, int position) {
            if (position == 0) {
            holder.setSteps("Recipe Ingredients");
        } else {
            String shortDescription = mRecipe.getSteps().get(position).getShortDescription();
            holder.setSteps(shortDescription);
                //holder.setIngredientsString(mRecipe.getIngredients().get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(mRecipe == null) {
            return 0;
        }else
            return mRecipe.getSteps().size()    ;
    }


    public class IngredientStepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mIngredientSteps;
        TextView mIngredients;
        private ArrayList<Ingredient> ingredients;

        public IngredientStepViewHolder(View itemView) {
            super(itemView);
            mIngredientSteps = (TextView) itemView.findViewById(R.id.tv_ingredient_step);
            itemView.setOnClickListener(this);

        }

        public void setSteps (String recipeName){
            mIngredientSteps.setText(recipeName);
        }

//        String getIngredientsString(ArrayList<Ingredient> ingredients) {
//            this.ingredients = ingredients;
//            String ingredientlist ;
//            StringBuilder sb = new StringBuilder();
//
//
//            for (int i = 0; i < ingredients.size() - 1; i++) {
//                String singleIngredient = ". " + ingredients.get(i).getIngredient() + "(" + ingredients.get(i).getQuantity() + " " +
//                        ingredients.get(i).getMeasure() +")"+ "\n";
//                sb.append(singleIngredient);
//            }
//
//            ingredientlist = sb.toString();
//
//            return ingredientlist;
//        }


        @Override
        public void onClick(View view) {
            mClickListener.onIngredientStepSelected(getAdapterPosition());
            Toast.makeText(mContext, "Item $" + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }
    }
}
