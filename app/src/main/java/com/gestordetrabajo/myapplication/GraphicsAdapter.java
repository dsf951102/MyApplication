package com.gestordetrabajo.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GraphicsAdapter extends RecyclerView.Adapter<List_Graphics_VH>{

   private Context context;
   private final List<RegistroGastos> items ;

    public GraphicsAdapter(Context context, List<RegistroGastos> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public List_Graphics_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_graphics, parent, false);

        return new List_Graphics_VH(context,view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull List_Graphics_VH holder, int position) {

    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
