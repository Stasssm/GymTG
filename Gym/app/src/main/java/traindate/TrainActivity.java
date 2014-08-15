package traindate;

import android.app.DialogFragment;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Layout;
import android.text.format.Time;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import be.billington.calendar.recurrencepicker.EventRecurrence;
import be.billington.calendar.recurrencepicker.EventRecurrenceFormatter;
import be.billington.calendar.recurrencepicker.RecurrencePickerDialog;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import com.example.gym.R;
import com.example.gym.model.TrainExercise;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TrainActivity  extends FragmentActivity implements TrainDialog.EditNameDialogListener  {


    private static final int CM_DELETE_ID = 1;

    private Button recurrence;
    private ImageButton add_btn ;

    DialogFragment add_ex_dialog;
    DialogFragment delete_dialog;
    MyAdapter adapter;
    boolean[] booleans ;
    private boolean contains = false;

    private String recurrenceRule;
    private TrainExercise trainExercise;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        //язык нашего приложения
        setLocale("ru");
        PreferenceSaver preferenceSaver = new PreferenceSaver(getApplicationContext());
        booleans = new PreferenceSaver(getApplicationContext()).loadText();

        if (PreferenceSaver.MODE_FOR.equals(preferenceSaver.getMode())){
            if (preferenceSaver.getFor()){
                delete_dialog = new DeleteDialog(adapter);
                delete_dialog.show(getFragmentManager(),"dlg_delete");
            }
        } else if (PreferenceSaver.MODE_UNTIL.equals(preferenceSaver.getMode())){
            if (preferenceSaver.getUntil()){
                delete_dialog = new DeleteDialog(adapter);
                delete_dialog.show(getFragmentManager(),"dlg_delete");
            }
        }

        for (boolean b : booleans){
            if (b){
                contains = true;
            }
            Log.d("My",b+"");
        }

        StickyListHeadersListView stickyList = (StickyListHeadersListView) findViewById(R.id.list);
        adapter = new MyAdapter(this);
        stickyList.setAdapter(adapter);

        registerForContextMenu(stickyList);

        recurrence = (Button) findViewById(R.id.recurrence);
        add_btn = (ImageButton) findViewById(R.id.add_ex_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contains) {
                    add_ex_dialog = new TrainDialog(booleans);
                    add_ex_dialog.show(getFragmentManager(), "dlg1");
                } else {
                    Toast.makeText(getApplicationContext(),R.string.not_chosen,Toast.LENGTH_LONG).show();
                }
            }
        });

        recurrence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog recurrencePickerDialog = new MyDialog();
                if (recurrenceRule != null && recurrenceRule.length() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString(RecurrencePickerDialog.BUNDLE_RRULE, recurrenceRule);
                    recurrencePickerDialog.setArguments(bundle);
                }

                recurrencePickerDialog.setOnRecurrenceSetListener(new RecurrencePickerDialog.OnRecurrenceSetListener() {
                    @Override
                    public void onRecurrenceSet(String rrule) {
                        recurrenceRule = rrule;

                        if (recurrenceRule != null && recurrenceRule.length() > 0) {
                            EventRecurrence recurrenceEvent = new EventRecurrence();
                            recurrenceEvent.setStartDate(new Time("" + new Date().getTime()));
                            recurrenceEvent.parse(rrule);
                            String srt = EventRecurrenceFormatter.getRepeatString(TrainActivity.this, getResources(), recurrenceEvent, true);
                            recurrence.setText(srt);
                            parse(srt);
                        } else {
                            recurrence.setText("No recurrence");
                        }
                    }
                });
                recurrencePickerDialog.show(getSupportFragmentManager(), "recurrencePicker");
            }
        });
    }

    @Override
    public void onEditDialogInfo(String inputText,int quantity,int quantity_in_one,String day) {
        trainExercise = new TrainExercise(inputText,day,quantity,quantity_in_one);
        Log.d("My","onResume" + trainExercise.getName());
        adapter.addEx(trainExercise);
        adapter.notifyDataSetChanged();
        trainExercise = null;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {

            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            adapter.deleteEx((int)acmi.id);
            adapter.notifyDataSetChanged();

            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.train_dropdown,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.dellete_all :{
                delete_dialog = new DeleteDialog(adapter);
                delete_dialog.show(getFragmentManager(),"dlg_delete");
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.closeDB();
    }

    public void setLocale(String languageToLoad) {
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
    public void parse(String s){
        StringDataParser dataParser = new StringDataParser(s);
        boolean[] days = dataParser.getDays();
        booleans = days;
        PreferenceSaver preferenceSaver= new PreferenceSaver(getApplicationContext());
        preferenceSaver.saveText(booleans);
        contains = true;
        for (Boolean aBoolean : days){
            Log.d("My",aBoolean+"");
        }
        Date until = dataParser.getDate();
        if (until!=null){
            preferenceSaver.saveUntil(until);
            Log.d("My",until.toString());
        }
        int event = dataParser.getEventsNum();
        if (event != -1){
            preferenceSaver.saveFor(event);
            Log.d("My",event+"");
        }
      //  Toast.makeText(getApplicationContext(),dataParser.getDays().toString(),Toast.LENGTH_SHORT).show();
    }
}
