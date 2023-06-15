package com.gestordetrabajo.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class List_Events_Adapter extends RecyclerView.Adapter<List_Events_AdapterVH>  {

    List<Event>items;
    Context context;

    public List_Events_Adapter(Context context, List<Event> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public List_Events_AdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new List_Events_AdapterVH(this.context,view).linkAdapter(this);
    }


    @Override
    public void onBindViewHolder(@NonNull List_Events_AdapterVH holder, int position) {
        String text = items.get(position).getName() + " tiene una reservación para las " + items.get(position).getHora();
        holder.name.setText(text);



    }

   @Override
    public int getItemCount() {
        return items.size();
    }
}


