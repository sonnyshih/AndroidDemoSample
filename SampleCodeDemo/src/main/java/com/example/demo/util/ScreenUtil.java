package com.example.demo.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class ScreenUtil {

	public static int getPxByDp(Context context, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				context.getResources().getDisplayMetrics());
	}

	public static int getPxByDp(Context context, float dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				context.getResources().getDisplayMetrics());
	}

	public static int getDpByPx(Context context, int px) {

		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static float pxToSp(Context context, Float px) {
		float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
		return px / scaledDensity;
	}

	public static void initScreenPara(Activity activity) {
		getScreenWidth(activity);
		getScreenHeight(activity);
		getScreenDensity(activity);
		getScreenPPI(activity);
		getScreenInch(activity);
	}

	private static int getScreenWidth(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

		return metrics.widthPixels;
	}

	private static int getScreenHeight(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.heightPixels;
	}

	private static float getScreenDensity(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.density;
	}

	private static float getScreenPPI(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.densityDpi;
	}

	private static float getScreenInch(Activity activity) {

		float screenInch = (float) Math.sqrt((Math.pow(
				getScreenHeight(activity), 2) + Math.pow(
				getScreenWidth(activity), 2)))
				/ getScreenPPI(activity);

		return screenInch;
	}

}
