package com.example.demo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleImageView extends ImageView {

	private Paint paint;
	private Path path;
	private boolean init = false;
	private int background = Color.WHITE;//default background color
	private int circleLineWidth = 3;
	private int circleColor = Color.WHITE;

	public CircleImageView(Context context) {
		super(context);
		initColor(null);
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initColor(attrs);
	}

	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initColor(attrs);
	}

	private void initColor(AttributeSet attrs) {
		if (attrs != null) {
			String v = attrs.getAttributeValue(
					"http://schemas.android.com/apk/res/android", "background");
			if (v != null) {
				
				if (v.startsWith("#")) {
					//Set by color code, e.x.:#000000
					background = Color.parseColor(v);
				} else {
					//Set by color reference, e.x.:@color/white
					background = getResources().getColor(
							Integer.parseInt(v.replaceAll("@", "")));
				}
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (!init) {
			initPaint();
		}
	}

	private void initPaint() {
		
		circleLineWidth = getPaddingLeft();
		setPadding(0, 0, 0, 0);
		paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setColor(background);
		paint.setAntiAlias(true);
		
		int width = getMeasuredWidth();
		float radius = width / (float) 2;

		path = new Path();
		path.moveTo(0, radius);
		path.lineTo(0, 0);
		path.lineTo(width, 0);
		path.lineTo(width, width);
		path.lineTo(0, width);
		path.lineTo(0, radius);
		path.arcTo(new RectF(0, 0, width, width), 180, -359, true);
		path.close();
		
		try {
			String circleColorsString = (String) getTag();
			
			if(circleColorsString != null){
				circleColor = Color.parseColor(circleColorsString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		init = true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(background);
		paint.setStyle(Style.FILL);
		canvas.drawPath(path, paint);
		
		// Draw the border of the circle
		paint.setColor(circleColor);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(circleLineWidth);
		int width = getMeasuredHeight();
		canvas.drawCircle(width / 2, width / 2,
				(float) (width / 2 - circleLineWidth * .5), paint);
	}
}