package com.example.shoppinglistof;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainFragment extends Fragment implements CallBack {
    private FloatingActionButton floatingActionButton;
    private ListService listService;
    private MyAdapter myAdapter;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ShoppingList shoppingLisst;
    private ApiListService apiListService;
    private List<ShoppingList> shoppingListList;

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
        apiListService = ((MyApp) getActivity().getApplication()).getApiListService();
        listService = ((MyApp) getActivity().getApplication()).getListService();

        recyclerView = getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        myAdapter = new MyAdapter(new ArrayList<>(), getContext(), MainFragment.this);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setHasFixedSize(true);




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
        super.onResume();
        apiListService.getShoppingList(ApiListService.SortOrder.Alphabetical, new GetShoppingListsCallBack() {
            @Override
            public void onShopppingListsLoaded(List<ShoppingList> shoppingLists) {
                myAdapter.add2(shoppingLists);
                myAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onPause() {
  /*      listService.safeList(myAdapter.getShoppingLists());*/
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


            apiListService.remove(uuid, new CompletionCallBack() {
                @Override
                public void onComplete() {

                }
            });

            apiListService.getShoppingList(ApiListService.SortOrder.Alphabetical,new GetShoppingListsCallBack() {
            @Override
            public void onShopppingListsLoaded(List<ShoppingList> shoppingLists) {
                myAdapter.setShoppingLists(shoppingLists);
                myAdapter.notifyDataSetChanged();
            }
        });
          /*  listService.remove(uuid);
            myAdapter.setShoppingLists(listService.getShoppingList(ListService.SortOrder.Alphabetical));
            myAdapter.notifyDataSetChanged();*/
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

    private void getData () {



    }

}


/*

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.extras3) {

        }

    }
}*/
