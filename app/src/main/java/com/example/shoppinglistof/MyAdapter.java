package com.example.shoppinglistof;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.telephony.mbms.MbmsErrors;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private Context context;
    private ListService listService;

    public List <ShoppingList> shoppingLists;

    public MyAdapter(List<ShoppingList> shoppingLists, ListService listService, Context context) {

        this.shoppingLists = shoppingLists;
        this.context = context;
        this.listService = listService;

    }

    public ListService getListService() {
        return listService;
    }

    public void setListService(ListService listService) {
        this.listService = listService;
    }

    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }


    public Context getContext() {
        return context;
    }



    public void setContext(Context context) {
        this.context = context;
    }






    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public EditText editText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivicon);
            editText = itemView.findViewById(R.id.etPrice);
            textView = itemView.findViewById(R.id.tvName);



        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrecylerview,parent,false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        final ShoppingList shoppingList = shoppingLists.get(position);

        int icons;
        switch (shoppingList.getIcon()){
            case 0:
                icons =  R.drawable.basket_24;
                break;

            case 1:
                icons = R.drawable.book_24;
                break;

            case 2:
                icons = R.drawable.store_24;
                break;

            default:
                icons = 0;
        }

        holder.imageView.setImageResource(icons);
        holder.textView.setText(shoppingList.getName());
        holder.imageView.setColorFilter(shoppingList.getColor());


        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
               final  AlertDialog  dialog = new AlertDialog.Builder(context)
                        .setTitle("Title")
                        .setMessage("Wollen Sie es wirklich Löschen?")
                        .setPositiveButton("Ok",  null)
                        .setNegativeButton("Cancel",  null)
                        .show();

                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     shoppingLists.remove(shoppingList);
                     dialog.dismiss();
                     notifyItemRemoved(position);
                     notifyDataSetChanged();
                    }
                });






                return false;


            }

        });
    }



    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }
    public void add ( List<ShoppingList> newList){
        this.shoppingLists = newList;
    }

}
