package traindate;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import be.billington.calendar.recurrencepicker.RecurrencePickerDialog;

/**
 * Created by Stas on 12.08.2014.
 */
public class MyDialog extends RecurrencePickerDialog {

    LinearLayout linearLayout;
    @Override
    public void onResume() {
        super.onResume();
        getView().findViewById(be.billington.calendar.recurrencepicker.R.id.freqSpinner).setVisibility(View.INVISIBLE);
        Switch sw = (Switch)getView().findViewById(be.billington.calendar.recurrencepicker.R.id.repeat_switch);
        sw.setChecked(true);
        sw.setVisibility(View.INVISIBLE);
        TextView textView = new TextView(getActivity().getApplicationContext());
        textView.setText("Дни тренировки");
        textView.setTextColor(Color.parseColor("#127094"));
        linearLayout = (LinearLayout)getView().findViewById(be.billington.calendar.recurrencepicker.R.id.freqSpinner).getParent();
        textView.setTextSize(26);
        linearLayout.setBackgroundColor(Color.parseColor("#5558ccc6"));
        LayoutParams layoutParam = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        linearLayout.addView(textView,0,layoutParam);
        LinearLayout intervalGroup =(LinearLayout) getView().findViewById((be.billington.calendar.recurrencepicker.R.id.intervalGroup));
        intervalGroup.setVisibility(View.INVISIBLE);
        Log.d("my","resume");
    }

}
