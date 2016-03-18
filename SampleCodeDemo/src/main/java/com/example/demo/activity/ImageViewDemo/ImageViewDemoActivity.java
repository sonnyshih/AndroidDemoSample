package com.example.demo.activity.ImageViewDemo;

import com.example.demo.R;
import com.example.demo.ui.CircleImageView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class ImageViewDemoActivity extends Activity {
	private CircleImageView circleImageView;
	private ImageView clipImageView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview_demo_activity);

		circleImageView = (CircleImageView) findViewById(R.id.imageViewDemo_circleImageView);
		circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.duck));
		
		clipImageView = (ImageView) findViewById(R.id.imageViewDemo_clipImageView);
		int radius = clipImageView.getLayoutParams().width;
		
		Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pikachu_170px);
		Bitmap handleImageBitmap = getCroppedBitmap(imageBitmap, radius);
		clipImageView.setImageBitmap(handleImageBitmap);

	}
	
	// Produce the circle image bitmap 
	public Bitmap getCroppedBitmap(Bitmap sourceBitmap, int width) {

		float scaleWidth;
		float scaleHeight;
		float scaleMutiple;
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, width, width);

		// Create the Canvas to draw
		Bitmap output = Bitmap.createBitmap(width, width, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		// Set up the paint
		paint.setAntiAlias(true);
		paint.setColor(color);

		// Set black canvas and draw a circle mask with radius width/2
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawCircle(width / 2, width / 2, width / 2, paint);

		// Set the type of the mask
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

		// Set the bitmap
		Log.i("PhotoTest", "source = (" + sourceBitmap.getWidth() + ", "
				+ sourceBitmap.getHeight() + ")");

		if (sourceBitmap.getWidth() <= sourceBitmap.getHeight()) {

			scaleWidth = width;
			scaleMutiple = scaleWidth / sourceBitmap.getWidth();
			scaleHeight = (int) (((float) sourceBitmap.getHeight()) * scaleMutiple);

		} else {
			scaleHeight = width;
			scaleMutiple = scaleHeight / sourceBitmap.getHeight();
			scaleWidth = (int) (((float) sourceBitmap.getWidth()) * scaleMutiple);
		}

		Bitmap scaleBitmap = Bitmap.createScaledBitmap(sourceBitmap,
				(int) scaleWidth, (int) scaleHeight, false);
		canvas.drawBitmap(scaleBitmap, rect, rect, paint);

		return output;
	}
	
}
