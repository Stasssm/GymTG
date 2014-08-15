package com.example.gym;

import videowall.ListMap;
import videowall.VideoWallDemoActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public abstract class SingleFragmentActivity extends FragmentActivity {

	protected abstract Fragment createFragment();

	private ListMap playlist;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercises_activity_fragment);

		playlist = new ListMap() ;
		
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		if (fragment == null) {
			fragment = createFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, fragment)
					.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.eye, menu);
		Log.d("my",
				this.getIntent().getStringExtra(
						ExerciseListFragment.EXTRA_BODY_PART));
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, VideoWallDemoActivity.class);
		String list = playlist.get(this.getIntent().getStringExtra(
				ExerciseListFragment.EXTRA_BODY_PART));
		 intent.putExtra(VideoWallDemoActivity.EXTRA_LIST,list); 
		startActivity(intent);
		return super.onOptionsItemSelected(item);
	}

}
