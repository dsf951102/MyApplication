package com.gestordetrabajo.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gestordetrabajo.myapplication.db.MyDB;

public class List_Events_AdapterVH extends RecyclerView.ViewHolder {


    TextView name;
    private List_Events_Adapter adapter;
    MyDB database;

    Context context;
    public List_Events_AdapterVH(@NonNull Context context,View itemView) {
     super(itemView);
     this.context =context;

        name = itemView.findViewById(R.id.cliente_tv_name);
        itemView.findViewById(R.id.btn_modificar_trabajo).setOnClickListener(view -> {

        });
        itemView.findViewById(R.id.btn_eliminar_trabajo).setOnClickListener(view -> {
            String id = adapter.items.get(getBindingAdapterPosition()).getId();
            adapter.items.remove(getBindingAdapterPosition());
            adapter.notifyItemRemoved(getBindingAdapterPosition());
            database = new MyDB(this.context);
            database.deleteData(id);


        });
    }

    public List_Events_AdapterVH linkAdapter(List_Events_Adapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
