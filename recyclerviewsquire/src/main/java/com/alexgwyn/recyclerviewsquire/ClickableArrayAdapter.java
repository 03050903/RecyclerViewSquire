package com.alexgwyn.recyclerviewsquire;

import android.support.annotation.CallSuper;
import android.view.View;


/**
 * {@link ArrayAdapter} which allows clicks on each item
 *
 * @param <T>
 * @param <V>
 */
public abstract class ClickableArrayAdapter<T, V extends ClickableViewHolder> extends ArrayAdapter<T, V> {

    private OnItemClickListener<T> mItemClickListener;
    private ClickableViewHolder.OnClickListener mClickListener = new ClickableViewHolder.OnClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            performItemClicked(v, position);
        }
    };

    @Override
    @CallSuper
    public void onBindViewHolder(V holder, int position) {
        //TODO move this to the creation of the ViewHolder instead of every time it binds
        holder.setClickListener(mClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener<T> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    protected void performItemClicked(View view, int position) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClicked(this, view, position);
        }
    }

    public interface OnItemClickListener<T> {
        void onItemClicked(ClickableArrayAdapter<T, ?> adapter, View view, int position);
    }
}
