package traindate;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.gym.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Stas on 15.08.2014.
 */
public class PreferenceSaver {

    private static final String MODE = "mode";
    public static final String MODE_FOR = "for";
    public static final String MODE_UNTIL = "until";

    private SharedPreferences sPref;
    Context context;

    public PreferenceSaver(Context context){
        this.context = context;
    }

   public void saveText(boolean[] booleans) {
       sPref = context.getSharedPreferences("Days",Context.MODE_PRIVATE);
       SharedPreferences.Editor ed = sPref.edit();
       String[] weekArray =context.getResources().getStringArray(R.array.week_days);

       for (int i = 0;i<booleans.length;i++){
               ed.putBoolean(weekArray[i], booleans[i]);
       }


        ed.commit();
    }

   public boolean[] loadText() {
        sPref = context.getSharedPreferences("Days",Context.MODE_PRIVATE);
       String[] weekArray =context.getResources().getStringArray(R.array.week_days);
       boolean[] days = new boolean[7];
       for (int i = 0;i<7;i++){
           days[i] = sPref.getBoolean(weekArray[i],false);
       }
       return days;
   }

    public void saveFor(int i){
        sPref = context.getSharedPreferences("Days",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("For",i);
        Calendar ca1 = Calendar.getInstance();

        int WEEK_OF_YEAR=ca1.get(Calendar.WEEK_OF_YEAR);
        ed.putInt("Week_number",WEEK_OF_YEAR);
        ed.putString(MODE,MODE_FOR);

    }

    public boolean getFor(){
        sPref = context.getSharedPreferences("Days",Context.MODE_PRIVATE);
        int prev_week = sPref.getInt("Week_number",0);
        int numb = sPref.getInt("For",0);

        Calendar ca1 = Calendar.getInstance();
        int WEEK_OF_YEAR=ca1.get(Calendar.WEEK_OF_YEAR);
        if (WEEK_OF_YEAR-prev_week > numb) {
            return  true;
        } else {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("Week_number",WEEK_OF_YEAR);
            return false;
        }
    }

    public String getMode(){
        sPref = context.getSharedPreferences("Days",Context.MODE_PRIVATE);
        return  sPref.getString(MODE, null);

    }

    public void saveUntil(Date date) {
        sPref = context.getSharedPreferences("Days",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(MODE,MODE_UNTIL);
        ed.putString("date",date.toString());
    }

    public boolean getUntil(){
        sPref = context.getSharedPreferences("Days",Context.MODE_PRIVATE);
        String s = sPref.getString("date", null);
        Date date = new Date();
        Log.d("My", date.toString() +" and "+s );
        if (date.toString().equals(s)){
            return true;
        }
        return false;
    }

    public void clear(){
        sPref = context.getSharedPreferences("Days",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.remove(MODE);

    }

}
