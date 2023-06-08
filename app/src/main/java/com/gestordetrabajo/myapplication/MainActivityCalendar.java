package com.gestordetrabajo.myapplication;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applikeysolutions.cosmocalendar.model.Day;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.gestordetrabajo.myapplication.databinding.ActivityMainCalendarBinding;
import com.gestordetrabajo.myapplication.db.MyDB;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivityCalendar extends AppCompatActivity {


    private ActivityMainCalendarBinding binding;

    List<Day> days;
    private String fechaEscogida;
    private Dialog dialog;
    private MyDB database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DynamicColors.applyToActivitiesIfAvailable(this.getApplication());

        binding = ActivityMainCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MaterialToolbar materialToolbar = binding.materialToolbar;
        setSupportActionBar(materialToolbar);
         final TextView textView = binding.textDashboard;
        //Inicializacion del CalendarView
        final CalendarView calendarView = binding.calendarView;
        final RecyclerView recyclerView = binding.recyclerview;


        List<Event> item = new ArrayList<>();

        List_Events_Adapter adapter = new List_Events_Adapter(item);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);




        textView.setText(Formato(Calendar.getInstance().getTime().toString()));
        calendarView.setNextMonthIconRes(R.drawable.round_chevron_right_24);
        calendarView.setPreviousMonthIconRes(R.drawable.round_chevron_left_24);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   days = calendarView.getSelectedDays();
//                    if (days.size() > 0) {
//                        Set<Long> daysmarked = new TreeSet<>();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                        String dateFormat = sdf.format(days.get(0).getCalendar().getTime());
//                        try {
//                            Date date = sdf.parse(dateFormat);
//                            long timeInMillis = date.getTime();
//                            daysmarked.add(timeInMillis);
//                        } catch (ParseException e) {
//                            throw new RuntimeException(e);
//                        }
////
//                        connectedDays = new ConnectedDays(daysmarked,R.color.md_theme_light_onError,R.color.md_theme_dark_error);
//                        calendarView.addConnectedDays(connectedDays);
//                        calendarView.setConnectedDayIconRes(R.drawable.baseline_event_note_24);
//                        calendarView.setConnectedDayIconPosition(ConnectedDayIconPosition.BOTTOM);
//                        calendarView.update();
//
////                item.add(new Event("Daniel", "Esta es", "Tarde", "Hora"));
////                adapter.notifyItemInserted(item.size()-1);
//
//                    }else { Toast.makeText(getApplicationContext(),"Seleccione una fecha",Toast.LENGTH_SHORT).show();}

                    dialog = new Dialog(MainActivityCalendar.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.fragment_dialog);
                MaterialButton btn_add = (MaterialButton) dialog.findViewById(R.id.btn_add_new_event);
                MaterialButton btn_cancel = (MaterialButton)dialog.findViewById(R.id.btn_cancel_new_event);
                MaterialTextView textView = (MaterialTextView) dialog.findViewById(R.id.title_tv);
                TextInputEditText textInputname = (TextInputEditText) dialog.findViewById(R.id.txt_edit_text);
                Chip chipclock = (Chip) dialog.findViewById(R.id.chip_clock);
                Chip chipfecha = (Chip) dialog.findViewById(R.id.chip_fecha_escogida);
                fechaEscogida = sdf.format(days.get(0).getCalendar().getTime());
                chipfecha.setText(fechaEscogida);



                chipclock.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int minute = calendar.get(Calendar.MINUTE);
                        TimePickerDialog pickerDialog = new TimePickerDialog(MainActivityCalendar.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                        String time = timePicker.getHour() + ":" +timePicker.getMinute();
                                    chipclock.setText(time);
                                    }
                                }, hour, minute, false);
                        pickerDialog.show();

                    }
                });
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        database = new MyDB(MainActivityCalendar.this);
                        String time = String.valueOf(chipclock.getText());
                        String fecha = chipfecha.getText().toString();
                        String name = textInputname.getText().toString();
                        database.insertData(name,time,fecha);
                        Toast.makeText(MainActivityCalendar.this,name, Toast.LENGTH_SHORT).show();
                    }
                });

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                        Toast.makeText(MainActivityCalendar.this, "add Cancelado", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.finace) {

        } else if (itemId == R.id.info) {
            Intent intent = new Intent(this, Info.class);
            startActivity(intent);
        }

        return true;
    }

    // Formato a la fecha de d mm yyyy
    public String Formato(String fecha) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dsf  = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault());
        fecha = dsf.format(calendar.getTime());
        return fecha;
    }

    public void newEvent(){

    }



}





