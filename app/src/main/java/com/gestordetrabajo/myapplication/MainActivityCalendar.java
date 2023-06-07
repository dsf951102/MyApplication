package com.gestordetrabajo.myapplication;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.applikeysolutions.cosmocalendar.dialog.CalendarDialog;
import com.applikeysolutions.cosmocalendar.dialog.OnDaysSelectionListener;
import com.applikeysolutions.cosmocalendar.model.Day;
import com.applikeysolutions.cosmocalendar.settings.appearance.ConnectedDayIconPosition;
import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.gestordetrabajo.myapplication.databinding.ActivityMainCalendarBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class MainActivityCalendar extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainCalendarBinding binding;
    private SimpleDateFormat df;
    private String formatedDate;
    private Calendar c;





    List_Events_Adapter adapter;

    List<Event> events;
    MaterialToolbar toolbar;
    ConnectedDays connectedDays;
    List<Day> days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
                    if (days.size() > 0) {
                        Set<Long> daysmarked = new TreeSet<>();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        String dateFormat = sdf.format(days.get(0).getCalendar().getTime());
                        try {
                            Date date = sdf.parse(dateFormat);
                            long timeInMillis = date.getTime();
                            daysmarked.add(timeInMillis);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
//
                        connectedDays = new ConnectedDays(daysmarked,R.color.md_theme_light_onError,R.color.md_theme_dark_error);
                        calendarView.addConnectedDays(connectedDays);
                        calendarView.setConnectedDayIconRes(R.drawable.baseline_event_note_24);
                        calendarView.setConnectedDayIconPosition(ConnectedDayIconPosition.BOTTOM);
                        calendarView.update();

//                item.add(new Event("Daniel", "Esta es", "Tarde", "Hora"));
//                adapter.notifyItemInserted(item.size()-1);

                    }else { Toast.makeText(getApplicationContext(),"Seleccione una fecha",Toast.LENGTH_SHORT).show();}





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
        SimpleDateFormat dsf  = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        fecha = dsf.format(calendar.getTime());
        return fecha;
    }
}





