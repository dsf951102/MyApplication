package com.gestordetrabajo.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.applikeysolutions.cosmocalendar.view.CalendarView;

public class List_Events_AdapterVH extends RecyclerView.ViewHolder {


    TextView name;
    private List_Events_Adapter adapter;


    public List_Events_AdapterVH(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.cliente_tv_name);
        itemView.findViewById(R.id.btn_modificar_trabajo).setOnClickListener(view -> {

        });
        itemView.findViewById(R.id.btn_eliminar_trabajo).setOnClickListener(view -> {
            adapter.items.remove(getBindingAdapterPosition());
            adapter.notifyItemRemoved(getBindingAdapterPosition());

        });
    }

    public List_Events_AdapterVH linkAdapter(List_Events_Adapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
