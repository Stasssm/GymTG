package traindate;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gym.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stas on 22.07.2014.
 */
public class TrainDialog extends DialogFragment implements View.OnClickListener {

    Button ok_btn;
    Spinner day_spinner;
    EditText ex_name;
    EditText ex_quantity;
    EditText ex_quantity_in_one;
    private String[] weekArray;
    private String[] weekArrayShort;


    private boolean[] bool_days;

    String day;


   TrainDialog (boolean[] booleans){
       super();
       bool_days = booleans;

   }

    public interface EditNameDialogListener {
        void onEditDialogInfo(String inputText,int quantity,int quantity_in_one,String day);
    }

    EditNameDialogListener activity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(R.string.add_title);
        View v = inflater.inflate(R.layout.dialog_add_ex, null);
        v.findViewById(R.id.btnYes).setOnClickListener(this);
        v.findViewById(R.id.btnNo).setOnClickListener(this);

        day_spinner = (Spinner)v.findViewById(R.id.day_spinner);
        weekArray = getResources().getStringArray(R.array.week_days);
        weekArrayShort = getResources().getStringArray(R.array.week_days_short);
        reformArrays();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, weekArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day_spinner.setAdapter(adapter);
        day_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day = weekArrayShort[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ex_name = (EditText) v.findViewById(R.id.ex_name_edit_text);
        ex_quantity = (EditText) v.findViewById(R.id.ex_quant);
        ex_quantity_in_one = (EditText) v.findViewById(R.id.ex_in_one_quant);


        return v;
    }

    public void onClick(View v) {
        if (v.getId()== R.id.btnYes){
            String name = ex_name.getText().toString();
            String quantity = ex_quantity.getText().toString();
            String quantity_in_one = ex_quantity_in_one.getText().toString();
            Log.d("My",name);
            if (!name.equals("")&&!quantity.equals("") &&!quantity_in_one.equals("")) {
                        activity = (EditNameDialogListener) getActivity();
                int a,b;
              //  if (!quantity.equals("") &&!quantity_in_one.equals("")){
                //    a=0;
                 //   b=0;
             //   } else if(quantity.equals("") ){
              //      a =0;
              //      b= Integer.parseInt(quantity_in_one);
             //   } else  if (quantity_in_one.equals("")){
             //       a=Integer.parseInt(quantity);
             //       b = 0;
              //  } else {
                    a =Integer.parseInt(quantity);
                    b = Integer.parseInt(quantity_in_one);
               // }
                activity.onEditDialogInfo(name, a, b,day);
                dismiss();
            }
        } else {
            dismiss();
        }
        ex_name.getText().clear();
        ex_quantity_in_one.getText().clear();
        ex_quantity.getText().clear();
    }

    private void reformArrays(){
        List<String> wA = new LinkedList<String>();
        List<String> wAS = new LinkedList<String>();

        for(int i =0 ;i<7;i++){
            if (bool_days[i]){
                wA.add(weekArray[i]);
                wAS.add(weekArrayShort[i]);
            }
        }
        weekArray = wA.toArray(new String[wA.size()]);
        weekArrayShort = wAS.toArray(new String[wAS.size()]);
    }
}
