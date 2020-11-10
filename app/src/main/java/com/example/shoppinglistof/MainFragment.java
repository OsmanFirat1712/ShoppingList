package com.example.shoppinglistof;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog;
import com.github.dhaval2404.colorpicker.model.ColorShape;
import com.github.dhaval2404.colorpicker.model.ColorSwatch;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainFragment extends Fragment implements CallBack {
    private FloatingActionButton floatingActionButton;
    private ListService listService;
    private MyAdapter myAdapter;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ShoppingList shoppingList;

    public MainFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shoppinglist, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addicontoolbar();
        recyclerView = getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        listService = ((MyApp) getActivity().getApplication()).getListService();
        myAdapter = new MyAdapter(listService.getShoppingList(ListService.SortOrder.Alphabetical),  this.listService, getContext(),this);
        recyclerView.setAdapter(myAdapter);


        floatingActionButton = view.findViewById(R.id.floatingButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDetailListFragment();
            }
        });

    }

    @Override
    public void onResume() {
        myAdapter.add2(listService.getShoppingList(ListService.SortOrder.Alphabetical));
        myAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onPause() {
        listService.safeList(myAdapter.getShoppingLists());
        super.onPause();
    }


    private void startDetailListFragment() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new ListEditFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addicontoolbar() {
        ActionBar actionbar = ((MainActivity) getActivity()).getSupportActionBar();
        actionbar.setHomeButtonEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setTitle(R.string.Einkaufsliste);

    }

    @Override
    public void getList(ShoppingList checkedUnchecked) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        ListEntriesFragment details = new ListEntriesFragment();
        Bundle arg = new Bundle();
        arg.putSerializable(KEYS.CHECKED,checkedUnchecked.getId());
        details.setArguments(arg);
        transaction.replace(R.id.container, details);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void remove(UUID uuid) {
            listService.remove(uuid);
            myAdapter.setShoppingLists(listService.getShoppingList(ListService.SortOrder.Alphabetical));
            myAdapter.notifyDataSetChanged();
        }


    @Override
    public void editlist(ShoppingList shoppingList) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        ListEditFragment details = new ListEditFragment();
        Bundle arg = new Bundle();
        arg.putSerializable(KEYS.SHOPPINGLIST,shoppingList);
        details.setArguments(arg);
        transaction.replace(R.id.container, details);
        transaction.addToBackStack(null);
        transaction.commit();

    }

}


/*

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.extras3) {

        }

    }
}*/
