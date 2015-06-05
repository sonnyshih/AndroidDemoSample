package com.example.CustomerUIDemo.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomerEditText extends EditText {

	private static final String START_CHAR = "[";
	private static final String END_CHAR = "]";
	
	public CustomerEditText(Context context) {
		super(context);
		init();
	}

	public CustomerEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomerEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		SpannableString content = new SpannableString(text);
		motifySpannable(content);
		super.setText(content, BufferType.SPANNABLE);
	}
	
	private void init() {
		this.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void afterTextChanged(Editable editable) {
				motifySpannable(editable);
			}
		});
		
	}

	private void motifySpannable(Spannable spannable) {
		
		String textString = spannable.toString();
		int startPosition = 0;
		int endPosition = 0;

		
		for (int i = 0; i < textString.length(); i++) {
			
			if (String.valueOf(textString.charAt(i)).equals(START_CHAR) ) {
				startPosition = i;
			}
			
			if (String.valueOf(textString.charAt(i)).equals(END_CHAR) ) {
				endPosition = i;
				
				String picMapKey = textString.substring(startPosition+1, endPosition);
				ImageSpan imageSpan = getImageSpan(picMapKey);
				spannable.setSpan(imageSpan, startPosition, endPosition + 1,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			
		}
		
	}

	private ImageSpan getImageSpan(String chartlet){
		
		// Set the picture size.
		Resources resources = getContext().getResources();
		int id = resources.getIdentifier(chartlet, "drawable",
				getContext().getPackageName());

		if (id > 0) {
			try {
				int size = Math.round(getTextSize()) + 10;
				Drawable drawable = getContext().getResources().getDrawable(id);
				drawable.setBounds(0, 0, size, size);
				ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
				return imageSpan;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
			
	}
	
	
}
