package com.gestordetrabajo.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.applikeysolutions.cosmocalendar.view.CalendarView;

public class List_Events_AdapterVH extends RecyclerView.ViewHolder {


    TextView name, description, sesion, hora;
    private List_Events_Adapter adapter;
    CalendarView calendarView;

    public List_Events_AdapterVH(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.cliente_tv_name);
        description = itemView.findViewById(R.id.description_tv);
        sesion = itemView.findViewById(R.id.sesion_tv);
        hora = itemView.findViewById(R.id.hora_tv);
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
