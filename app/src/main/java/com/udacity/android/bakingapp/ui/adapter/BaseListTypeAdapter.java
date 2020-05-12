package com.udacity.android.bakingapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.model.RecipeUmbrella;

import java.util.List;

/**
 * Base Adapter for displaying Recipe (both in phone and tablet based configurations), and Step information.
 *
 * @param <T>
 */
public abstract class BaseListTypeAdapter<T extends RecipeUmbrella> extends RecyclerView.Adapter<BaseListTypeAdapter.BaseListTypeViewHolder> {

    private List<T> mDataList;
    private BakingClickListener mClickListener;

    BaseListTypeAdapter(BakingClickListener clickListener) {
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public BaseListTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return createListViewHolder(DataBindingUtil.inflate(
                inflater,
                // TODO check if two layouts (1 for phone & 1 for tablet) are needed.
                // need to extract anyway once a StepListTypeAdapter & IngredientListTypeAdapter are added.
                R.layout.recipe_list_item_phone,
                viewGroup,
                false));
    }

    @Override
    public void onBindViewHolder(BaseListTypeAdapter.BaseListTypeViewHolder holder, int position) {
        holder.bind(mDataList.get(position));

    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public void swapData(List<T> newData) {
        mDataList = newData;
        notifyDataSetChanged();

        // TODO experiment with DiffUtil
//        DiffUtil.DiffResult result = DiffUtil
//                    .calculateDiff(new MoviesListDifferenceCallback(mMovies, newMovies));
//            mMovies = newMovies;
//            result.dispatchUpdatesTo(this);
    }

    abstract BaseListTypeViewHolder createListViewHolder(ViewDataBinding binding);


    abstract class BaseListTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public BaseListTypeViewHolder(@NonNull View itemView, ViewDataBinding binding) {
            super(itemView);
            binding.getRoot().setOnClickListener(this);
        }

        abstract void bind(T data);

        @Override
        public void onClick(View v) {
            mClickListener.onClick(mDataList.get(getAdapterPosition()));
        }
    }

}
