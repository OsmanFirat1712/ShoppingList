package com.example.shoppinglistof;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.shoppinglistof.KEYS.CHECKED;

public class ListEntriesFragment extends Fragment implements BackCall {
    private ListEntriesAdapter listEntriesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ListService listService;
    private GridLayoutManager gridLayoutManager;
    private List<ShoppingListEntry> allEntrys;
    private FloatingActionButton fbtalert;
    private RecyclerView recyclerViewCheckedunChecked;
    private UUID uuid;
    private ShoppingList shoppingList;
    private TextView tventry;
    private ApiListService apiListService;
    private String entries;


    public ListEntriesFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        addicontoolbar();
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            uuid = (UUID) bundle.getSerializable(CHECKED);
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recylerview2, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiListService = ((MyApp) getActivity().getApplication()).getApiListService();

        listService = ((MyApp) getActivity().getApplication()).getListService();
        recyclerViewCheckedunChecked = getActivity().findViewById(R.id.my_recycler_view_unchecked);


        recyclerViewCheckedunChecked.setLayoutManager(new GridLayoutManager(getContext(), 1));
        listEntriesAdapter = new ListEntriesAdapter(this.apiListService, getContext(), ListEntriesFragment.this);
        recyclerViewCheckedunChecked.setAdapter(listEntriesAdapter);

        fbtalert = view.findViewById(R.id.fbtalert);
        tventry = view.findViewById(R.id.ivCheck);

        fbtalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view2 = LayoutInflater.from(getContext()).inflate(R.layout.alertdialog, null);
                final EditText editText = view2.findViewById(R.id.textView);
                Button button = view2.findViewById(R.id.btsave);
                builder.setView(view2);
                AlertDialog dialog = builder.create();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        apiListService.addEntry(uuid, entries, new CompletionCallBack() {
                            @Override
                            public void onComplete() {
                                setShopping();
                                getActivity().getSupportFragmentManager();
                                dialog.dismiss();
                            }
                        });
                     /*   listService.addEntry(uuid, editText.getText().toString());
                        allEntrys = new ArrayList<>();
                        listEntriesAdapter.notifyDataSetChanged()*/
                        ;


                    }
                });

                dialog.show();

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();
        /*setShopping();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(shoppingList.getName());*/
    }

    @Override
    public void addButton() {

    }

    @Override
    public void addEntry(ShoppingListEntry shoppingListEntry, int position) {

        if (shoppingListEntry.isChecked()) {
            apiListService.uncheckEntry(uuid, shoppingListEntry.getId(), new CompletionCallBack() {
                @Override
                public void onComplete() {
                    setShopping();
                }
            });

        } else {
            apiListService.checkEntry(uuid, shoppingListEntry.getId(), new CompletionCallBack() {
                @Override
                public void onComplete() {
                    setShopping();
                }
            });
            listService.checkEntry(uuid, position);
        }
        setShopping();
    }

    @Override
    public void removeEntry(ShoppingListEntry shoppingListEntry) {
        listService.removeEntry(uuid, shoppingListEntry.getId());
        setShopping();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            ((MainActivity) getActivity()).getSupportFragmentManager().popBackStack();
        }
        return false;
    }

    public void setShopping() {


/*        apiListService.ShoppingList(uuid, new CompletionCallBack() {
            @Override
            public void onComplete() {
                allEntrys = new ArrayList<>();
                allEntrys.addAll(shoppingList.getUncheckedEntries());
                allEntrys.addAll(shoppingList.getCheckedEntries());
                listEntriesAdapter.addsavelist(allEntrys);
                listEntriesAdapter.notifyDataSetChanged();

            }
        });*/


    }

    public void addicontoolbar() {
        ActionBar actionbar = ((MainActivity) getActivity()).getSupportActionBar();
        actionbar.setHomeButtonEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setTitle(R.string.Einkaufsliste);

    }


}


