package com.example.gym;

import java.util.UUID;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.gym.database.ExersiceLab;
import com.example.gym.model.Exercise;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import customization.NotifyingScrollView;

public class YouTubeAPIActivity extends YouTubeBaseActivity implements
		YouTubePlayer.OnInitializedListener,YouTubePlayer.PlayerStateChangeListener
		, PlaybackEventListener{

	private YouTubePlayer YPlayer;
	private static final String YoutubeDeveloperKey = "AIzaSyA7WNm2eJMGM9reo0_-rvOJlD2mRmTY0zA";
	private static final int RECOVERY_DIALOG_REQUEST = 1;

	public static final String EXTRA_EXERCISE_ID = "com.example.gym.ex_id";
	public static final String EXTRA_EXERCISE_BODY = "com.example.gym.ex_body";
	
	private Exercise mEx;
	private String url;
	
	private WebView mWv;
	private MenuItem play_btn;
	private ImageView iv ;
	
	private Drawable mActionBarBackgroundDrawable;
	private Drawable mItemPlay;
	private Drawable mItemStop;
	

	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_you_tube_api);
		
		 iv =(ImageView)findViewById(R.id.imageView1);
		 
		 if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){			 
			 iv.getLayoutParams().height = 0;
		 }

		UUID id = (UUID) this.getIntent().getSerializableExtra(
				EXTRA_EXERCISE_ID);
		String bodyPart = this.getIntent().getStringExtra(EXTRA_EXERCISE_BODY);
		mEx = ExersiceLab.get(this, bodyPart, false).getExercise(id);
		this.setTitle(mEx.getName());
		//String ylink = mEx.getYlink();
		//url = ylink.substring(ylink.indexOf('=') + 1);
		url = mEx.getYlink();
		Log.d("My", url);
		mWv = (WebView) findViewById(R.id.exinfo_webView);
		mWv.loadUrl("file:///android_asset/html/" + mEx.getExlink());

		mActionBarBackgroundDrawable = getResources().getDrawable(
				R.drawable.ab_background);
		mItemPlay = getResources().getDrawable(R.drawable.play);
		mItemStop = getResources().getDrawable(R.drawable.stopblack);
		mItemStop.setAlpha(255);
		mItemPlay.setAlpha(255);
		mActionBarBackgroundDrawable.setAlpha(0);

		getActionBar().setBackgroundDrawable(mActionBarBackgroundDrawable);
		
		((NotifyingScrollView) findViewById(R.id.scrollView1))
				.setOnScrollChangedListener(mOnScrollChangedListener);
		
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
			mActionBarBackgroundDrawable.setCallback(mDrawableCallback);
		}

		YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		youTubeView.initialize(YoutubeDeveloperKey, this);
	}

	private Drawable.Callback mDrawableCallback = new Drawable.Callback() {
		@Override
		public void invalidateDrawable(Drawable who) {
			getActionBar().setBackgroundDrawable(who);
		}

		@Override
		public void scheduleDrawable(Drawable who, Runnable what, long when) {
		}

		@Override
		public void unscheduleDrawable(Drawable who, Runnable what) {
		}
	};

	private NotifyingScrollView.OnScrollChangedListener mOnScrollChangedListener = new NotifyingScrollView.OnScrollChangedListener() {
		public void onScrollChanged(ScrollView who, int l, int t, int oldl,
				int oldt) {
			final int headerHeight = findViewById(R.id.youtube_view)
					.getHeight()
					+ findViewById(R.id.imageView1).getHeight()
					- getActionBar().getHeight();
			final float ratio = (float) Math.min(Math.max(t, 0), headerHeight)
					/ headerHeight;
			final int newAlpha = (int) (ratio * 255);

			mActionBarBackgroundDrawable.setAlpha(newAlpha);

			mItemPlay.setAlpha(255-newAlpha);


			mItemStop.setAlpha(255-newAlpha);
			if (t>headerHeight) {
				play_btn.setVisible(false);
			} else {
				play_btn.setVisible(true);
			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.findItem(R.id.item2);
		play_btn =menu.findItem(R.id.item2);
		play_btn.setVisible(true); 
		Log.d("My","k="+getActionBar().getHeight());
		Log.d("My","k="+findViewById(R.id.imageView1).getHeight());
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
		if (YPlayer != null) {
			if (item.getTitle().equals("play")) {
				item.setIcon(R.drawable.stopblack);
				item.setTitle("stop");
				YPlayer.play();
			} else {
				item.setIcon(R.drawable.play);
				item.setTitle("play");
				YPlayer.pause();
			}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onInitializationFailure(YouTubePlayer.Provider provider,
			YouTubeInitializationResult errorReason) {
		if (errorReason.isUserRecoverableError()) {
			errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
		} else {
			String errorMessage = String.format(
					"There was an error initializing the YouTubePlayer",
					errorReason.toString());
			Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RECOVERY_DIALOG_REQUEST) {
			// Retry initialization if user performed a recovery action
			getYouTubePlayerProvider().initialize(YoutubeDeveloperKey, this);
		}
	}

	protected YouTubePlayer.Provider getYouTubePlayerProvider() {
		return (YouTubePlayerView) findViewById(R.id.youtube_view);
	}

	@Override
	public void onInitializationSuccess(Provider provider,
			YouTubePlayer player, boolean wasRestored) {
		if (!wasRestored) {
			YPlayer = player;
			/*
			 * Now that this variable YPlayer is global you can access it
			 * throughout the activity, and perform all the player actions like
			 * play, pause and seeking to a position by code.
			 */
			YPlayer.cueVideo(url);
			YPlayer.setPlayerStateChangeListener(this);
			YPlayer.setPlaybackEventListener(this);
		}
	}

	@Override
	public void onAdStarted() {
		// TODO Auto-generated method stub
		play_btn.setIcon(R.drawable.stopblack);
		play_btn.setTitle("stop");
	}

	@Override
	public void onError(ErrorReason arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaded(String arg0) {
		// TODO Auto-generated method stub
		play_btn.setEnabled(true);
	}

	@Override
	public void onLoading() {
		// TODO Auto-generated method stub
		play_btn.setEnabled(false);
	}

	@Override
	public void onVideoEnded() {
		// TODO Auto-generated method stub
		play_btn.setIcon(R.drawable.play);
		play_btn.setTitle("play");
	}

	@Override
	public void onVideoStarted() {
		// TODO Auto-generated method stub
		play_btn.setIcon(R.drawable.stopblack);
		play_btn.setTitle("stop");
	}

	@Override
	public void onBuffering(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPaused() {
		// TODO Auto-generated method stub
		play_btn.setIcon(R.drawable.play);
		play_btn.setTitle("play");
	}

	@Override
	public void onPlaying() {
		// TODO Auto-generated method stub
		play_btn.setIcon(R.drawable.stopblack);
		play_btn.setTitle("stop");
	}

	@Override
	public void onSeekTo(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopped() {
		// TODO Auto-generated method stub
		play_btn.setIcon(R.drawable.play);
		play_btn.setTitle("play");
	}

}