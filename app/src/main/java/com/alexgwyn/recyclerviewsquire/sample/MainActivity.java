package com.alexgwyn.recyclerviewsquire.sample;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alexgwyn.recyclerviewsquire.ClickableArrayAdapter;
import com.alexgwyn.recyclerviewsquire.FullScreenLinearLayoutManager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.root) ViewGroup mRoot;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.recyclerview) RecyclerView mCheeseRecyclerView;
    CheeseAdapter mCheeseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mToolbar.setTitle(R.string.app_name);
        mToolbar.inflateMenu(R.menu.main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_change_layout_manager:
                        mCheeseRecyclerView.setLayoutManager(new FullScreenLinearLayoutManager(MainActivity.this));
                        return true;
                }
                return false;
            }
        });

        mCheeseRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mCheeseAdapter = new CheeseAdapter();
        mCheeseRecyclerView.setAdapter(mCheeseAdapter);
        addCheeses();
        mCheeseAdapter.setOnItemClickListener(new ClickableArrayAdapter.OnItemClickListener<Cheese>() {
            @Override
            public void onItemClicked(ClickableArrayAdapter adapter, View view, int position) {
                Cheese item = (Cheese) adapter.get(position);
                Snackbar.make(mRoot, item.getName() + " clicked", Snackbar.LENGTH_SHORT)
                        .show();
            }

        });
    }

    private void addCheeses() {
        ArrayList<Cheese> cheeses = new ArrayList<>();
        for (int i=0; i<20; i++) {
            cheeses.add(Cheeses.getRandomCheese());
        }
        mCheeseAdapter.addAll(cheeses);
    }
}
