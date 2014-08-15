package com.example.gym;

import java.util.ArrayList;





import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gym.database.ExersiceLab;
import com.example.gym.model.Exercise;

public class ExerciseListFragment extends ListFragment {
	public static final String EXTRA_BODY_PART ="com.example.gym.ExerciseListFragment";
	
	private ArrayList<Exercise> mExercises;
	private String bodyPart;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bodyPart= getActivity().getIntent().getStringExtra(EXTRA_BODY_PART);
		getActivity().setTitle(bodyPart);
		mExercises = ExersiceLab.get(getActivity(), bodyPart, true).getExc();
		Log.d("My", "Work");
		EAdapter adapter = new EAdapter(mExercises);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Exercise c = ((EAdapter) getListAdapter()).getItem(position);
		Intent i = new Intent(getActivity(),YouTubeAPIActivity.class);
		//Intent i = new Intent(getActivity(), FragmentActivity.class);
		i.putExtra(YouTubeAPIActivity.EXTRA_EXERCISE_BODY, bodyPart);
		i.putExtra(YouTubeAPIActivity.EXTRA_EXERCISE_ID, c.getId());
		startActivity(i);
	}
	
	private class EAdapter extends ArrayAdapter<Exercise> {
		public EAdapter(ArrayList<Exercise> exercise) {
			super(getActivity(), 0, exercise);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// If we weren't given a view, inflate one
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_exercises, null);
			}
			Exercise ex = getItem(position);
			TextView titleTextView = (TextView) convertView
					.findViewById(R.id.exercise_list_item_titleTextView);
			titleTextView.setText(ex.getName());
			ImageView image =(ImageView) convertView.findViewById(R.id.exersice_list_image);
			//ImageView arrow =(ImageView) convertView.findViewById(R.id.arrow_list_image);
			return convertView;
		}
	}

}
