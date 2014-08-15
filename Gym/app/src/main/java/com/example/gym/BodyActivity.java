package com.example.gym;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import bodyGrid.GridBodyActivity;

public class BodyActivity extends Activity implements OnTouchListener ,OnClickListener{

	private ImageView myImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_model);
		myImg = (ImageView) findViewById(R.id.image);
		myImg.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			final int evX = (int) ev.getX();
			final int evY = (int) ev.getY();
			Log.d("My", "onTouch");
			int touchColor = getHotspotColor(R.id.image_areas, evX, evY);
			ColorTool ct = new ColorTool();
			int tolerance = 25;
			Intent intent = new Intent(this, ExerciseListActivity.class);
			if (ct.closeMatch(Color.RED, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART, "triceps");
				startActivity(intent);
			} else if (ct.closeMatch(Color.GREEN, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART, "quads");
				startActivity(intent);
			} else if (ct.closeMatch(Color.BLUE, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART, "biceps");
				startActivity(intent);
			} else if (ct.closeMatch(0xff00fff2, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART,
						"middleback");
				startActivity(intent);
			} else if (ct.closeMatch(0xff11116b, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART,
						"lowerback");
				startActivity(intent);
			} else if (ct.closeMatch(0xff695e12, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART, "glutes");
				startActivity(intent);
			} else if (ct.closeMatch(0xff76c261, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART,
						"hamstrings");
				startActivity(intent);
			} else if (ct.closeMatch(0xffc26461, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART, "calves");
				startActivity(intent);
			} else if (ct.closeMatch(Color.BLACK, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART,
						"shoulders");
				startActivity(intent);
			} else if (ct.closeMatch(0xffff7070, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART,
						"forearms");
				startActivity(intent);
			} else if (ct.closeMatch(Color.DKGRAY, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART, "chest");
				startActivity(intent);
			} else if (ct.closeMatch(Color.GRAY, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART, "abs");
				startActivity(intent);
			} else if (ct.closeMatch(Color.LTGRAY, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART, "neck");
				startActivity(intent);
			} else if (ct.closeMatch(Color.MAGENTA, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART, "traps");
				startActivity(intent);
			} else if (ct.closeMatch(Color.YELLOW, touchColor, tolerance)) {
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				intent.putExtra(ExerciseListFragment.EXTRA_BODY_PART, "lats");
				startActivity(intent);
			}
			break;
		}
		}

		return true;
	}

	public int getHotspotColor(int hotspotId, int x, int y) {
		ImageView img = (ImageView) findViewById(hotspotId);
		img.setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
		img.setDrawingCacheEnabled(false);
		return hotspots.getPixel(x, y);
	}

	@Override
	public void onClick(View v) {

		if (v.getId()==R.id.muscinf){
			Intent i = new Intent(this, GridBodyActivity.class);
			startActivity(i);
		}
	}
}
