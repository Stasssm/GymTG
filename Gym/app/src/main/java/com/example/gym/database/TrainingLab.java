package com.example.gym.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gym.model.Exercise;
import com.example.gym.model.TrainExercise;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Stas on 01.08.2014.
 */
public class TrainingLab {
    private ExerciseDataBase dataBase;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private int i = 0;

    private static final String[] D = {"Пн","Вт","Ср","Чт","Пт","Сб","Вс"};
    private static final List<String> DAYS = new ArrayList<String>(Arrays.asList(D));

    public TrainingLab(Context context){
        this.context = context;
    }

    public void open() {
        dataBase = new ExerciseDataBase(context);
        sqLiteDatabase =dataBase.getWritableDatabase();
    }
     public  void  close(){
         if(dataBase != null) {
             dataBase.close();
         }
     }

    public void addEx (TrainExercise trainExercise){
        ContentValues cv = new ContentValues() ;
        cv.put("ex_name",trainExercise.getName());
        cv.put("quant",trainExercise.getQuantity());
        cv.put("quant_in_one", trainExercise.getQuantity_in_one());
        cv.put("day", DAYS.indexOf(trainExercise.getDay()));
        cv.put("_id",trainExercise.get_id());
        Log.d("!!!!!!!!!!!",DAYS.indexOf(trainExercise.getDay())+"");
        //cv.put("num",i++);
        //
        cv.put("train_name_id",0);
        //
        sqLiteDatabase.insert(ExerciseDataBase.train_ex,null,cv);

    }

    public ArrayList<TrainExercise> getAllData (){
        Cursor cursor = sqLiteDatabase.query(ExerciseDataBase.train_ex, null, null, null, null, null,"day", null);
        if (cursor.moveToFirst()){
            int nameIndex = cursor.getColumnIndex("ex_name");
            int quantIndex = cursor.getColumnIndex("quant");
            int quant_in_oneIndex = cursor.getColumnIndex("quant_in_one");
            int dayIndex = cursor.getColumnIndex("day");
            int idIndex = cursor.getColumnIndex("_id");
            ArrayList<TrainExercise> exercises = new ArrayList<TrainExercise>();
            Log.d("My",cursor.getString(nameIndex));
            Log.d("My",cursor.getInt(dayIndex)+"");
            Log.d("My",cursor.getInt(quantIndex)+"");
            do {
                TrainExercise trainExercise = new TrainExercise(cursor.getString(nameIndex), DAYS.get(cursor.getInt(dayIndex)), cursor.getInt(quantIndex), cursor.getInt(quant_in_oneIndex),cursor.getLong(idIndex));
                exercises.add(trainExercise);
            } while (cursor.moveToNext());
            return exercises;
        } else {
            return null;
        }
    }

    public void deleteRow(TrainExercise trainExercise){
       final String where = "Training_Exer.ex_name = '" +trainExercise.getName()+ "' "+
               " AND quant = " +trainExercise.getQuantity()+" "+
               " AND quant_in_one = " + trainExercise.getQuantity_in_one()+" "+
               "AND day = '" + DAYS.indexOf(trainExercise.getDay())+"'";
        final String where1 = "_id = "+ trainExercise.get_id();
        //String[] strings = {trainExercise.getName(),String.valueOf(trainExercise.getQuantity()),String.valueOf(trainExercise.getQuantity_in_one()),trainExercise.getDay()};
        sqLiteDatabase.delete(ExerciseDataBase.train_ex,where1,null);
    }

    public void deleteAll() {
        sqLiteDatabase.delete(ExerciseDataBase.train_ex,null,null);
    }

}
