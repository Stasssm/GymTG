package com.example.gym;

import android.support.v4.app.Fragment;



public class ExerciseListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new ExerciseListFragment();
	}

}
