package com.example.gym.database;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gym.model.Exercise;

public class ExersiceLab {
	private ArrayList<Exercise> mExc;
	private static ExersiceLab sExersiceLab;
	private ExerciseDataBase db;

	public ExersiceLab(Context appContext, String muscle_name) {
		db = new ExerciseDataBase(appContext);
		mExc = new ArrayList<Exercise>();
		SQLiteDatabase sqlbase = db.getWritableDatabase();
		String sqlQuery = "Exer_Muscle INNER JOIN MuscleGroups ON MuscleGroups.muscles_id=Exer_Muscle.muscles_id AND muscle_name='"
				+ muscle_name
				+ "'"
				+ " INNER JOIN Exercises ON Exercises.id=Exer_Muscle.id";

		String columns[] = { "DISTINCT Exercises.name as Name",
				"Exercises.exercise_link as Exercise_link",
				"Exercises.youtube_link as Youtube_link" };
		Cursor c = sqlbase.query(sqlQuery, columns, null, null, null, null,
				null);
		Log.d("My", "ExersiceLab3" + c.toString());
		if (c.moveToFirst()) {
			Log.d("My", "cursor");
			int name = c.getColumnIndex("Name");
			Log.d("My", "h " + name);
			int elink = c.getColumnIndex("Exercise_link");
			Log.d("My", "h " + elink);
			int ylink = c.getColumnIndex("Youtube_link");
			Log.d("My", "h " + ylink);
			// int id = c.getColumnIndex("id");
			do {
				// Log.d("My","LL "+c.getString(id));
				Exercise ex = new Exercise();
				ex.setExlink(c.getString(elink));
				ex.setYlink(c.getString(ylink));
				Log.d("My", ex.getYlink());
				ex.setName(c.getString(name));
				Log.d("My", ex.getName());
				mExc.add(ex);
			} while (c.moveToNext());
		}

		String q = "Exer_Muscle";
		// String columns[] = { "Exercises.name as Name",
		// "Exercises.exercise_link as Exercise_link",
		// "Exercises.youtube_link as Youtube_link" };
		Cursor cu = sqlbase.query(q, null, null, null, null, null, null);
		Log.d("My", "ExersiceLab3" + cu.toString());
		if (cu.moveToFirst()) {
			Log.d("My", "cursor");
			int name = cu.getColumnIndex("em_id");
			Log.d("My", "h " + name);
			int elink = cu.getColumnIndex("muscles_id");
			int k = cu.getColumnIndex("id");
			do {
				Log.d("My", "em_id " + cu.getInt(elink));

				Log.d("My", "muscles_id " + cu.getInt(name));
				Log.d("My", "id " + cu.getInt(k));

			} while (cu.moveToNext());
		}

		Log.d("My", mExc.toString());
		db.close();
	}

	public static ExersiceLab get(Context c, String muscle_name,boolean create) {
		if (sExersiceLab == null || create) {
			sExersiceLab = new ExersiceLab(c.getApplicationContext(),
					muscle_name);
		}
		return sExersiceLab;
	}

	public ArrayList<Exercise> getExc() {
		return mExc;
	}

	public Exercise getExercise(UUID id) {
		for (Exercise c : mExc) {
			if (c.getId().equals(id))
				return c;
		}
		return null;
	}

}
