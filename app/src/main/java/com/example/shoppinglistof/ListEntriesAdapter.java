package com.example.shoppinglistof;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListEntriesAdapter extends RecyclerView.Adapter<ListEntriesAdapter.ViewHolder  > {

        private Context context;
        private ListService listService;
        private BackCall backCall;

/*
        public List<ShoppingListEntry> checkedEntries;
*/
        public List<ShoppingListEntry> uncheckedEntries;


        public ListEntriesAdapter(List<ShoppingListEntry> uncheckedEntries, ListService listService, Context context,BackCall backCall) {
            this.context = context;
            this.listService = listService;
            this.uncheckedEntries = uncheckedEntries;
            this.backCall = backCall;
            this.getUncheckedEntries();

        }


        public List<ShoppingListEntry> getUncheckedEntries() {
            return uncheckedEntries;
        }

        public void setUncheckedEntries(List<ShoppingListEntry> uncheckedEntries) {
            this.uncheckedEntries = uncheckedEntries;
        }

    public BackCall getBackCall() {
        return backCall;
    }

    public void setBackCall(BackCall backCall) {
        this.backCall = backCall;
    }

        /*  public ListUnchecked(List<ShoppingList> uncheckedEntries, ListService listService, Context context) {
        this.uncheckedEntries = uncheckedEntries;
        this.context = context;
        this.listService = listService;
    }*/


/*    public ListEntriesAdapter(ShoppingListEntry shoppingListEntry){
        uncheckedEntries.add(shoppingListEntry);
        notifyDataSetChanged();
    }*/

    public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public ListService getListService() {
            return listService;
        }

        public void setListService(ListService listService) {
            this.listService = listService;
        }
        public static class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textView;
            public EditText editText;
            public EditText editText3;
            private TextView textView2;
            public FloatingActionButton floatingActionButton;
            private Button button;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                textView2 = itemView.findViewById(R.id.editText12);
                floatingActionButton = itemView.findViewById(R.id.floatingButton2);




            }
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.entryxml,parent,false);

            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final ShoppingListEntry shoppingList = uncheckedEntries.get(position);

            holder.textView2.setText(shoppingList.getName().toString());

  /*          holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backCall.addEntry(shoppingList);
                    notifyDataSetChanged();
                }
            });*/

        /*    holder.floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                     View view2 = LayoutInflater.from(getContext()).inflate(R.layout.alertdialog,null);

                     builder.setView(view2);
                     AlertDialog dialog = builder.create();
                     dialog.show();
                }
            });


*/

        }

        @Override
        public int getItemCount() {
            return uncheckedEntries.size();
        }


    public void add ( List<ShoppingListEntry> newList){
        this.uncheckedEntries = newList;
    }


    }