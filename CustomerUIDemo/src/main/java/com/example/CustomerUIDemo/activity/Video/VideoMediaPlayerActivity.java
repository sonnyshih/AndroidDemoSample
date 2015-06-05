package com.example.CustomerUIDemo.activity.Video;

import java.io.IOException;

import com.example.CustomerUIDemo.R;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.app.Activity;

public class VideoMediaPlayerActivity extends Activity implements
		SurfaceHolder.Callback, OnPreparedListener {

	MediaPlayer mediaPlayer;
	SurfaceHolder surfaceHolder;
	SurfaceView playerSurfaceView;
	String videoSrc = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
//	String videoSrc = "http://admin:admin@192.168.60.1:8081/USB/aa.avi";
//	String videoSrc = "http://admin:admin@192.168.60.1:8081/USB/20130911_Square2_%E9%87%9D%E5%B0%8DAndroidTeam%E7%9A%84%E6%B3%A8%E6%84%8F.wmv";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_mediaplayer_activity);
		playerSurfaceView = (SurfaceView) findViewById(R.id.playersurface);

		surfaceHolder = playerSurfaceView.getHolder();
		surfaceHolder.addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {

		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setDisplay(surfaceHolder);
			mediaPlayer.setDataSource(videoSrc);
			mediaPlayer.prepare();
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mediaPlayer.start();
	}

}
