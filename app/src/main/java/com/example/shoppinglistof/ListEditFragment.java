package com.example.shoppinglistof;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import room.DaoListService;
import room.DataBase;
import yuku.ambilwarna.AmbilWarnaDialog;

public class ListEditFragment extends Fragment {

    private EditText etName;
    private ListService listService;
    private Button addButton;
    private View dice;
    ImageView ivIcon;
    private int random;
    private ConstraintLayout constraintLayout;
    private int mDefaultColor;
    private View colorPicker;
    private ShoppingList shoppingList;
    private int icon;
    private ApiListService apiListService;
    private DaoListService daoListService;


    public ListEditFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        apiListService = ((MyApp) getActivity().getApplication()).getApiListService();


        if (getArguments() != null) {
            shoppingList = (ShoppingList) getArguments().getSerializable(KEYS.SHOPPINGLIST);
        }
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.Liste);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        random = 0;
        View view = inflater.inflate(R.layout.shoppinglistedit, container, false);

        return view;


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu2, menu);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listService = ((MyApp) getActivity().getApplication()).getListService();
        etName = view.findViewById(R.id.etPrice);
        ivIcon = view.findViewById(R.id.ivicon);
        dice = view.findViewById(R.id.dice);
        addButton = view.findViewById(R.id.addButton);

        constraintLayout = view.findViewById(R.id.constraint);
        mDefaultColor = ContextCompat.getColor(getActivity(), R.color.design_default_color_on_primary);
        colorPicker = view.findViewById(R.id.colorPicker);

        super.onViewCreated(view, savedInstanceState);
        if (shoppingList != null) {
            ivIcon.setColorFilter(shoppingList.getColor());
            etName.setText(shoppingList.getName());

            if (shoppingList.getIcon() == 0) {
                ivIcon.setImageResource(R.drawable.basket_24);
            } else if (shoppingList.getIcon() == 1) {
                ivIcon.setImageResource(R.drawable.book_24);
            } else if (shoppingList.getIcon() == 2) {
                ivIcon.setImageResource(R.drawable.store_24);
            }


        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shoppingList != null) {
                    if (!etName.getText().toString().isEmpty()) {
                        apiListService.changeName(shoppingList.getId(), etName.getText().toString());
                        apiListService.changeIcon(shoppingList.getId(), random);
                    }
                    getActivity().getSupportFragmentManager().popBackStack();
                } else
                    addList();
            }
        });
    }

    public void addList() {

        if (etName.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Bitte noch eine Modellnamen eingeben", Toast.LENGTH_SHORT).show();
            return;
        }

        DataBase.getInstance(getContext()).shoppingDao().add();
        apiListService.add(etName.getText().toString(), random, mDefaultColor, new ListSave() {
            @Override
            public void listToSave() {


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                });


            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            ((MainActivity) getActivity()).getSupportFragmentManager().popBackStack();
            return true;
        } else if (item.getItemId() == R.id.colorPicker) {
            mDefaultColor = ContextCompat.getColor(getActivity(), R.color.material_on_primary_emphasis_medium);
            openColorPicker();
        } else if (item.getItemId() == R.id.dice) {

            if (random == 0) {
                ivIcon.setImageResource(R.drawable.basket_24);
                random = 1;
                return true;
            } else if (random == 1) {
                ivIcon.setImageResource(R.drawable.book_24);
                random = 2;
                return true;
            } else if (random == 2) {
                ivIcon.setImageResource(R.drawable.store_24);
                random = 3;
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }


    public void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefaultColor = color;
                ivIcon.setBackgroundColor(color);
            }
        });
        colorPicker.show();
    }


}