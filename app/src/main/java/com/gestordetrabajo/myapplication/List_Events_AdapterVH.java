package com.gestordetrabajo.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gestordetrabajo.myapplication.databinding.ActivityMainCalendarBinding;
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
            database = new MyDB(this.context);
            AlertDialog.Builder dialog = new AlertDialog.Builder(itemView.getContext());
            dialog.setTitle("Cuidado");
            dialog.setMessage("Está seguro de eliminar el evento?");
            dialog.setCancelable(false);
            dialog.setIcon(R.drawable.baseline_delete_24);
            dialog.setPositiveButton("Sí", (dialogInterface, i) -> {
                String id = adapter.items.get(getBindingAdapterPosition()).getId();
                adapter.items.remove(getBindingAdapterPosition());
                adapter.notifyItemRemoved(getBindingAdapterPosition());
                database.deleteData(id);
            }).setNegativeButton("Cancelar", (dialogInterface, i) -> {

            });
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();

        });
    }

    public List_Events_AdapterVH linkAdapter(List_Events_Adapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
