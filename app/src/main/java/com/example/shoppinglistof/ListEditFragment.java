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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ListEditFragment extends Fragment {

    private EditText editText;
    private ListService listService;
    private Button addButton;
    private View dice;
    ImageView imageView;
    private int flag;
    private ConstraintLayout constraintLayout;
    private int mDefaultColor;
    private View colorPicker;
    private int icon;


    public ListEditFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        flag = 0;
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
        editText = view.findViewById(R.id.etPrice);
        imageView = view.findViewById(R.id.ivicon);
        dice = view.findViewById(R.id.dice);
        addButton = view.findViewById(R.id.addButton);

        constraintLayout = view.findViewById(R.id.constraint);
        mDefaultColor = ContextCompat.getColor(getActivity(), R.color.design_default_color_on_primary);
        colorPicker = view.findViewById(R.id.colorPicker);

        super.onViewCreated(view, savedInstanceState);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataStorage();
            }
        });
    }

    public void dataStorage() {
        listService.add(editText.getText().toString(), flag, mDefaultColor);
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
           if (item.getItemId()==android.R.id.home){
            ((MainActivity) getActivity()).getSupportFragmentManager().popBackStack();
            return true;
        }

      else   if (item.getItemId() == R.id.colorPicker) {
            mDefaultColor = ContextCompat.getColor(getActivity(), R.color.material_on_primary_emphasis_medium);
            openColorPicker();
        }

       else if (item.getItemId() == R.id.dice) {

            if (flag == 0) {
                imageView.setImageResource(R.drawable.basket_24);
                flag = 1;
            } else if (flag == 1) {
                imageView.setImageResource(R.drawable.book_24   );
                flag = 2;
            } else if (flag == 2) {
                imageView.setImageResource(R.drawable.store_24);
                flag = 0;
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
                imageView.setBackgroundColor(color);
            }
        });
        colorPicker.show();
    }
    }
