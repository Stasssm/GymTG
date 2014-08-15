package com.example.gym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.Toast;

import traindate.TrainActivity;

public class MainActivity extends Activity implements OnClickListener {
	private static class ViewHolder {
		static ImageButton gymBtn;
		static ImageButton diarybtn;
		static ImageButton weightbtn;
		static ImageButton lentbtn;
	}
	private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewHolder.gymBtn = (ImageButton) findViewById(R.id.imageButton1);
		ViewHolder.diarybtn = (ImageButton) findViewById(R.id.imageButton2);
		ViewHolder.weightbtn = (ImageButton) findViewById(R.id.imageButton3);
		ViewHolder.lentbtn = (ImageButton) findViewById(R.id.imageButton4);
		ViewHolder.gymBtn.setOnClickListener(this);
		ViewHolder.diarybtn.setOnClickListener(this);
		ViewHolder.lentbtn.setOnClickListener(this);
		ViewHolder.weightbtn.setOnClickListener(this);
		buttonClick.setDuration(70);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		v.startAnimation(buttonClick);
		switch (v.getId()) {
		case R.id.imageButton1: {
			Intent intent = new Intent(this, BodyActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.imageButton2: {
            Intent intent = new Intent(this, TrainActivity.class);
            startActivity(intent);
			 Toast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.imageButton3: {
			Toast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.imageButton4: {
			Toast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
			break;
		}
		}
	}

}
