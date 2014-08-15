package com.example.gym;


import java.util.UUID;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gym.database.ExersiceLab;
import com.example.gym.model.Exercise;


public class ExerciseFragment extends Fragment{
	public static final String EXTRA_EXERCISE_ID = "com.example.gym.ex_id";
	public static final String EXTRA_EXERCISE_BODY="com.example.gym.ex_body";
	
	private Exercise mEx;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID id = (UUID) getActivity().getIntent().getSerializableExtra(EXTRA_EXERCISE_ID);
		String bodyPart= getActivity().getIntent().getStringExtra(EXTRA_EXERCISE_BODY);
		mEx = ExersiceLab.get(getActivity(), bodyPart, false).getExercise(id);
		getActivity().setTitle(mEx.getName());
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.exersice_fragment, parent, false);
		
				return v;
		
	}
}
