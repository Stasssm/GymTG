package traindate;

import com.example.gym.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Stas on 13.08.2014.
 */
public class StringDataParser {

    private String data;
    private static final String[] weekArray = {"пн","вт","ср","чт","пт","сб","вс"};
    private static final String[] weekArrayShort = {"понедельник","вторник","среда","четверг","пятница","суббота","воскресенье"};
    private static final String[] weekArrayEng = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    private static final String[] weekArrayShortEng = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};

    private ArrayList<String> weekArrayList;
    private ArrayList<String> weekArrayListShort;
    private ArrayList<String> weekArrayListEng;
    private ArrayList<String> weekArrayListShortEng;

   public StringDataParser(String s){
       data = s;
       weekArrayList= new ArrayList<String>(Arrays.asList(weekArray));
       weekArrayListShort= new ArrayList<String> (Arrays.asList(weekArrayShort));
       weekArrayListEng= new ArrayList<String>(Arrays.asList(weekArrayEng));
       weekArrayListShortEng= new ArrayList<String>(Arrays.asList(weekArrayShortEng));
}
   public boolean[] getDays() {
       String days=data;
       int i = data.indexOf(";");
       if (i!=-1) {
         days   = data.substring(0, i);
       }
       days = days.replace("Weekly on ","");
       String[] dayStrings = days.split(",");
       boolean[] dayBooleans = new boolean[7];
       for (String myday : dayStrings){
           String string = myday.trim();
           int pos = weekArrayListShort.indexOf(string);
           if (pos == -1){
               pos = weekArrayListShortEng.indexOf(string);
               if (pos == -1){
                   pos = weekArrayList.indexOf(string);
                   if (pos == -1){
                       pos = weekArrayListEng.indexOf(string);
                   }
               }
           }

           if (pos != -1) {
               dayBooleans[pos] = true ;
           }

       }
       return dayBooleans;
   }

   public Date getDate() {
       int i = data.indexOf(";");
       String date =data;
       if (date.contains("for")){
           return null;
       }

       if (i!=-1) {
           date   = data.substring(i+1, data.length());
       } else {
           return null;
       }
       date = date.replace("until","");
       date = date.trim();

       DateFormat formatter ;
       Date dMy = null ;
       if (date.contains("/")) {
           formatter = new SimpleDateFormat("MM/dd/yyyy");
       } else  {
           formatter = new SimpleDateFormat("dd.MM.yyyy");
       }
       try {
           dMy = formatter.parse(date);
       } catch (ParseException e) {
           e.printStackTrace();
       }
       return dMy;
   }

    public int getEventsNum(){
        int i = data.indexOf(";");
        String event =data;
        if (!event.contains("for")){
            return -1;
        }
        if (event.contains("one")){
            return 1;
        } else {
            return Integer.parseInt(event.replaceAll("[\\D]", ""));
        }

    }
}
