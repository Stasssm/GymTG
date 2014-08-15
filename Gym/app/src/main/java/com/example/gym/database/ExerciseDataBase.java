package com.example.gym.database;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gym.R;

public class ExerciseDataBase extends SQLiteOpenHelper {

	private final Context fContext;
	//убрал s
	static final String dbName = "DBjy";
	static final String exercisesTable = "Exercises";
	static final String em_table = "Exer_Muscle";
	static final String muscle_table = "MuscleGroups";
    static final String train_ex = "Training_Exer";
    static final String train_name ="Training_Name";

	public ExerciseDataBase(Context context) {
		super(context, dbName, null, 2);
		fContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.d("My", "DB");
		try {
			db.beginTransaction();
			db.execSQL("create table " + muscle_table + "("
					+ "muscles_id integer primary key ,"
					+ " muscle_name text NOT NULL"
					+ " );");
					// убрал атрибут muscles_id
			db.execSQL("create table " + exercisesTable + "("
					+ "id integer primary key  ,"
					+ " name text NOT NULL ," + " exercise_link text ,"
					+ " youtube_link text );");
			db.execSQL("create table " + em_table + " ("
					+ "em_id integer primary key ,"
					+ " id integer ," + " muscles_id integer ," +" FOREIGN KEY (id) REFERENCES " + exercisesTable
					+ "(id),"+ " FOREIGN KEY (muscles_id) REFERENCES " + muscle_table
					+ "(muscles_id));");

            db.execSQL("create table " + train_name + "(" + "_id integer primary key ," +
                    " training_name text NOT NULL );");

            db.execSQL("create table " + train_ex + "( _id integer primary key , ex_name text NOT NULL ," +
                    " quant integer , quant_in_one integer , day integer ," +
                    " train_name_id integer , num integer );");


            db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
		ContentValues values = new ContentValues();
		// Получим массив строк из ресурсов
		Resources res = fContext.getResources();
		String[] muscle_records = res.getStringArray(R.array.muscleList);
		// проходим через массив и вставляем записи в таблицу
		int length = muscle_records.length;
		try {
			db.beginTransaction();
			for (int i = 0; i < length; i++) {
				Log.d("My",muscle_records[i]);
				values.put("muscle_name", muscle_records[i]);
				db.insert(muscle_table, null, values);
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		// Добавляем записи в таблицу
		values = new ContentValues();

		// Открываем xml-файл
		XmlResourceParser _xml = res.getXml(R.xml.exercise_record);
		try {
			// Ищем конец документа
			int eventType = _xml.getEventType();
			db.beginTransaction();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				// Ищем теги record
				if ((eventType == XmlPullParser.START_TAG)
						&& (_xml.getName().equals("record"))) {
					// Тег Record найден, теперь получим его атрибуты и
					// вставляем в таблицу
					String name = _xml.getAttributeValue(0);
					String exercise_link = _xml.getAttributeValue(1);
					String youtube_link = _xml.getAttributeValue(2);
					values.put("name", name);
					values.put("exercise_link", exercise_link );
					values.put("youtube_link", youtube_link);
					Log.d("My",values.toString());
					db.insert(exercisesTable, null, values);
				}
				eventType = _xml.next();
			}
			db.setTransactionSuccessful();
		}
		// Catch errors
		catch (XmlPullParserException e) {
			Log.e("Test", e.getMessage(), e);
		} catch (IOException e) {
			System.out.print(e);
			Log.e("Test", e.getMessage(), e);

		} finally {
			// Close the xml file
			_xml.close();
			db.endTransaction();
		}
		
		// Добавляем записи в таблицу
		values = new ContentValues();

		// Открываем xml-файл
		_xml = res.getXml(R.xml.ex_musc_record);
		try {
			// Ищем конец документа
			int eventType = _xml.getEventType();
			db.beginTransaction();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				// Ищем теги record
				if ((eventType == XmlPullParser.START_TAG)
						&& (_xml.getName().equals("record"))) {
					// Тег Record найден, теперь получим его атрибуты и
					// вставляем в таблицу
					String id = _xml.getAttributeValue(0);
					String muscles_id = _xml.getAttributeValue(1);
					values.put("id", id);
					values.put("muscles_id", muscles_id );
					db.insert(em_table, null, values);
				}
				eventType = _xml.next();
			}
			db.setTransactionSuccessful();
		}
		// Catch errors
		catch (XmlPullParserException e) {
			Log.e("Test", e.getMessage(), e);
		} catch (IOException e) {
			Log.e("Test", e.getMessage(), e);

		} finally {
			// Close the xml file
			_xml.close();
			db.endTransaction();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		onCreate(db);
		
	}


}
