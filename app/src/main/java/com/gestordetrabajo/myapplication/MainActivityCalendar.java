package com.gestordetrabajo.myapplication;



import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applikeysolutions.cosmocalendar.model.Day;
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener;
import com.applikeysolutions.cosmocalendar.selection.SingleSelectionManager;
import com.applikeysolutions.cosmocalendar.settings.appearance.ConnectedDayIconPosition;
import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.gestordetrabajo.myapplication.databinding.ActivityMainCalendarBinding;
import com.gestordetrabajo.myapplication.db.MyDB;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class MainActivityCalendar extends AppCompatActivity {


    private ActivityMainCalendarBinding binding;

    List<Day> days;
    private String fechaEscogida;

    private MyDB database;
    ConnectedDays connectedDays;

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
        CalendarView calendarView = binding.calendarView;
        calendarView.setNextMonthIconRes(R.drawable.round_chevron_right_24);
        calendarView.setPreviousMonthIconRes(R.drawable.round_chevron_left_24);

        final RecyclerView recyclerView = binding.recyclerview;
        View bottomSheet = findViewById(R.id.button_sheet);

        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);



        List<Event> item = getEventsPerDay();
        List<String> item2 = getAllEventsDates();
        List_Events_Adapter adapter = new List_Events_Adapter(getApplicationContext(), item);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        textView.setText(Formato(Calendar.getInstance().getTime().toString()));
        eventIndicator(calendarView, item2);
            calendarView.setSelectionManager(new SingleSelectionManager(() -> {
            SimpleDateFormat dsf = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault());
            if (getEventsDaySelection(calendarView.getSelectedDays().get(0)).size() == 0) {
                recyclerView.setVisibility(View.GONE);
                textView.setText(dsf.format(calendarView.getSelectedDays().get(0).getCalendar().getTime()));
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                List<Event> item1 = getEventsDaySelection(calendarView.getSelectedDays().get(0));
                List_Events_Adapter adapter1 = new List_Events_Adapter(getApplicationContext(), item1);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter1);
                textView.setText(dsf.format(calendarView.getSelectedDays().get(0).getCalendar().getTime()));

            }


        }));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            @Override
            public void onClick(View view) {
                days = calendarView.getSelectedDays();

                if (days.size() != 0) {
                    if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        bottomSheetBehavior.setDraggable(false);
                    }
                    MaterialButton btn_add = (MaterialButton) findViewById(R.id.btn_add_new_event);
                    MaterialButton btn_cancel = (MaterialButton) findViewById(R.id.btn_cancel_new_event);
                    TextInputEditText textInputname = (TextInputEditText) findViewById(R.id.txt_edit_text);
                    Chip chipclock = (Chip) findViewById(R.id.chip_clock);
                    Chip chipfecha = (Chip) findViewById(R.id.chip_fecha_escogida);
                    days = calendarView.getSelectedDays();
                    fechaEscogida = sdf.format(days.get(0).getCalendar().getTime());
                    chipfecha.setText(fechaEscogida);

                    chipclock.setOnClickListener(view12 -> {
                        Calendar calendar = Calendar.getInstance();
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int minute = calendar.get(Calendar.MINUTE);


                        TimePickerDialog pickerDialog = new TimePickerDialog(MainActivityCalendar.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                        String am_pm = (hour >= 12) ? "pm" : "am";
                                        String time = timePicker.getHour() + ":" + timePicker.getMinute() + " " + am_pm;
                                        chipclock.setText(time);
                                    }
                                }, hour, minute, false);
                        pickerDialog.show();

                    });
                    btn_cancel.setOnClickListener(view1 ->
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN));
                    btn_add.setOnClickListener(view13 -> {
                        if (Objects.requireNonNull(textInputname.getText()).toString().isEmpty()){
                            textInputname.setError("Campo obigatorio");

                        }else{
                           if (chipclock.getText().toString().equals("Seleccione la Hora")){
                               chipclock.setError("Campo obigatorio");

                           }else {

                               String name = Objects.requireNonNull(textInputname.getText()).toString();
                               String hora = chipclock.getText().toString();
                               String fecha = chipfecha.getText().toString();
                               database = new MyDB(getApplicationContext());
                               long result = database.insert(name, hora, fecha);
                               if (result != -1) {
                                   bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                   chipclock.setText(R.string.seleccione_la_hora);
                                   textInputname.getText().clear();
                                   Set<Long> daysmarked = new TreeSet<>();
                                   SimpleDateFormat dsf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                                   String dateformat = dsf.format((calendarView.getSelectedDays().get(0).getCalendar().getTime()));
                                   Date date = null;
                                   try {
                                       date = sdf.parse(dateformat);

                                       long timeInMillis = date.getTime();
                                       daysmarked.add(timeInMillis);
                                   } catch (ParseException e) {
                                       throw new RuntimeException(e);
                                   }
                                   connectedDays = new ConnectedDays(daysmarked, R.color.md_theme_light_primary, R.color.md_theme_dark_error);
                                   calendarView.addConnectedDays(connectedDays);
                                   calendarView.setConnectedDaySelectedIconRes(R.drawable.baseline_event_note_24);
                                   //calendarView.setConnectedDayIconRes(R.drawable.baseline_event_note_24);
                                   calendarView.setConnectedDayIconPosition(ConnectedDayIconPosition.BOTTOM);
                                   calendarView.update();

                                   List<Event> item12;
                                   item12 = getEventsDaySelection(calendarView.getSelectedDays().get(0));
                                   List_Events_Adapter adapter12 = new List_Events_Adapter(getApplicationContext(), item12);
                                   recyclerView.setHasFixedSize(true);
                                   recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                   recyclerView.setAdapter(adapter12);
                                   recyclerView.setVisibility(RecyclerView.VISIBLE);

                           }
                        }


                        }


                    });
                } else {
                    Toast.makeText(MainActivityCalendar.this, "Selecione una fecha.", Toast.LENGTH_SHORT)
                            .show();
                }

            }

        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.finace) {
            startActivity(new Intent(this, FinaceActivity.class));
        } else if (itemId == R.id.info) {
            startActivity(new Intent(this, Info.class));
        }

        return true;
    }

    // Formato a la fecha de d mm yyyy
    public String Formato(String fecha) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dsf = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault());
        fecha = dsf.format(calendar.getTime());
        return fecha;
    }

    public void newEvent() {

    }

    //Obtener todos los eventos para mostrarlos en el recycler
    private List<Event> getEventsPerDay() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(calendar.getTime());
        MyDB db = new MyDB(getApplicationContext());
        Cursor cursor = db.readData();
        List<Event> events = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                if (Objects.equals(cursor.getString(3), date)) {
                    events.add(new Event(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
                }

            }
            return events;
        } else {
            Toast.makeText(this, "No existen eventos programados", Toast.LENGTH_SHORT).show();
        }
        return events;
    }

    private List<Event> getEventsDaySelection(Day day) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(day.getCalendar().getTime());
        MyDB db = new MyDB(getApplicationContext());
        Cursor cursor = db.readData();
        List<Event> events = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                if (Objects.equals(cursor.getString(3), date)) {
                    events.add(new Event(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
                }

            }
            return events;
        } else {
            Toast.makeText(this, "No existen eventos programados", Toast.LENGTH_SHORT).show();
        }
        return events;
    }

    private List<String> getAllEventsDates() {
        MyDB db = new MyDB(getApplicationContext());
        Cursor cursor = db.readData();
        List<String> events = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()){
                events.add(cursor.getString(3));
            }
        }return events;
    }
    private void eventIndicator(CalendarView calendarView, List<String> events) {
        int cont = 0;
        if (events.size()>0){
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            List<ConnectedDays> connectedDaysList = new ArrayList<>();
            Set<Long> daysmarked = new TreeSet<>();

            Date date = null;

            while (cont < events.size()){
                try {

                    date = format.parse(events.get(cont));
                    long timeInMillis = date.getTime();
                    daysmarked.add(timeInMillis);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                ConnectedDays connectedDays = new ConnectedDays(daysmarked, R.color.md_theme_light_primary, R.color.md_theme_dark_primaryContainer);

                connectedDaysList.add(connectedDays);

                cont ++;

                }

            for (int i = 1; i < connectedDaysList.size(); i++){
                calendarView.addConnectedDays(connectedDaysList.get(i));
                calendarView.setConnectedDaySelectedIconRes(R.drawable.baseline_event_note_24);
                calendarView.setConnectedDayIconPosition(ConnectedDayIconPosition.BOTTOM);
               }
            calendarView.update();
            }


    }


}





