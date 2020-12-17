package com.example.shoppinglistof;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class ListEntriesAdapter extends RecyclerView.Adapter<ListEntriesAdapter.ViewHolder> {

    private Context context;
    private ListService listService;
    private BackCall backCall;
    private GetShoppingListsCallBack getShoppingListsCallBack;
    private ApiListService apiListService;


    public List<ShoppingListEntry> unchecked;


    public ListEntriesAdapter(ApiListService apiListService, Context context, BackCall backCall) {

        this.context = context;
        this.listService = listService;
        this.unchecked = new ArrayList<>();
        this.getShoppingListsCallBack = getShoppingListsCallBack;
        this.backCall = backCall;
        this.apiListService = apiListService;

    }

    public List<ShoppingListEntry> getUnchecked() {
        return unchecked;
    }

    public void setUnchecked(List<ShoppingListEntry> unchecked) {
        this.unchecked = unchecked;
    }

    public void setUncheckedEntries(List<ShoppingListEntry> uncheckedEntries) {
        this.unchecked = uncheckedEntries;
    }

    public BackCall getBackCall() {
        return backCall;
    }

    public void setBackCall(BackCall backCall) {
        this.backCall = backCall;
    }


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
        public ImageView ivCheck;
        private TextView tvaddEntry;
        public FloatingActionButton floatingActionButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvaddEntry = itemView.findViewById(R.id.tventry);
            floatingActionButton = itemView.findViewById(R.id.fbtalert);
            ivCheck = itemView.findViewById(R.id.ivCheck);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.entryxml, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ShoppingListEntry listEntry = unchecked.get(position);
        holder.tvaddEntry.setText(listEntry.getName());

        if (listEntry.isChecked()) {
            holder.ivCheck.setVisibility(View.VISIBLE);

        } else {
            holder.ivCheck.setVisibility(View.GONE);
        }

        holder.tvaddEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backCall.addEntry(listEntry, position);

            }
        });

        holder.tvaddEntry.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("yarrak")
                        .setMessage("Wollen Sie die ausgewählte Liste wirklich Löschen?")
                        .setNeutralButton("Cancel", null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                backCall.removeEntry(listEntry);
                                dialog.dismiss();
                                notifyItemRemoved(position);
                                notifyDataSetChanged();
                            }
                        })
                        .show();


                return false;
            }

        });
    }

    @Override
    public int getItemCount() {
        return unchecked.size();
    }

    public void addsavelist(List<ShoppingListEntry> newEntry) {
        this.unchecked = newEntry;
    }

}