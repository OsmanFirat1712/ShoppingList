package com.example.shoppinglistof;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.example.shoppinglistof.KEYS.CHECKED;

public class ListEntriesFragment extends Fragment implements BackCall {
    private ShoppingList checkedUnchecked;
    private ListEntriesAdapter listEntriesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ListService listService;
    private GridLayoutManager gridLayoutManager;
    private List<ShoppingList> shoppingListEntries = new ArrayList<>();
    private FloatingActionButton floatingActionButton2;
    private RecyclerView recyclerViewCheckedunChecked;
    private EditText editText;
    private Button button;

    public ListEntriesFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (getArguments() != null) {
            checkedUnchecked = (ShoppingList) bundle.getSerializable(CHECKED);

        }
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
        listService = ((MyApp) getActivity().getApplication()).getListService();
        recyclerViewCheckedunChecked = getActivity().findViewById(R.id.my_recycler_view_unchecked);
        recyclerViewCheckedunChecked.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerViewCheckedunChecked.setAdapter(listEntriesAdapter);
        listEntriesAdapter = new ListEntriesAdapter(checkedUnchecked.getUncheckedEntries(), this.listService, getContext(),this);
        checkedUnchecked.getUncheckedEntries();
/*
        button = view.findViewById(R.id.button);
        editText=view.findViewById(R.id.editTextTextPersonName);
*/


        floatingActionButton2 = view.findViewById(R.id.floatingButton2);


        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view2 = LayoutInflater.from(getContext()).inflate(R.layout.alertdialog, null);
                final EditText editText = view2.findViewById(R.id.textView);
                Button button = view2.findViewById(R.id.btsave);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < shoppingListEntries.size(); i++) {
                            listService.addEntry(shoppingListEntries.get(i).getId(), editText.getText().toString());
                        }
                        listEntriesAdapter.add(checkedUnchecked.getCheckedEntries());
                        listEntriesAdapter.notifyDataSetChanged();
                        getActivity().getSupportFragmentManager();

                    }
                });
                builder.setView(view2);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

/*    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.extras:
                final EditText editText = new EditText(getContext());
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("Add New List")
                        .setMessage("sure")
                        .setView(editText)
                        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String string = String.valueOf(editText.getText());

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
        }


        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onResume() {
        listEntriesAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void addButton() {

    }

    @Override
    public void addEntry(ShoppingListEntry shoppingListEntry) {



    }


}
