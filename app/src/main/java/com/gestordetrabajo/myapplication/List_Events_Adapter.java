package com.gestordetrabajo.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class List_Events_Adapter extends RecyclerView.Adapter<List_Events_AdapterVH>  {

    List<Event>items;

    public List_Events_Adapter(List<Event> items) {
        this.items = items;
    }

    @Override
    public List_Events_AdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new List_Events_AdapterVH(view).linkAdapter(this);
    }


    @Override
    public void onBindViewHolder(@NonNull List_Events_AdapterVH holder, int position) {

        holder.name.setText(items.get(position).getName());
        holder.description.setText(items.get(position).description);
        holder.sesion.setText(items.get(position).sesion);
        holder.hora.setText(items.get(position).hora);

    }

   @Override
    public int getItemCount() {
        return items.size();
    }
}


