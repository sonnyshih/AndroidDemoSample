package com.example.demo.activity.TextviewEditTextDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;

public class TextViewHtmlUrlDemoActivity extends AppCompatActivity {

    private TextView urlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textview_html_url_demo);

        Spanned sp = Html.fromHtml(getString(R.string.html));
        urlTextView = (TextView) findViewById(R.id.textviewHtmlUrlDemo_urlTextView);
        urlTextView.setText(sp);
        urlTextView.setMovementMethod(LinkMovementMethod.getInstance());
        setTextHyperLinkListener(urlTextView, sp);
    }

    private void setTextHyperLinkListener(TextView textView, Spanned sp) {
        CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.clearSpans();

            for (URLSpan url : urls) {
                CustomTextClick click = new CustomTextClick(url.getURL());
                style.setSpan(click, sp.getSpanStart(url), sp.getSpanEnd(url),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            textView.setText(style);
        }
    }

    private class CustomTextClick extends ClickableSpan {
        private String url;

        public CustomTextClick(String url) {
            this.url = url;
        }

        @Override
        public void onClick(View widget) {

            Log.d("Mylog", "url="+url);
//            if (mUrl.equals("1")) {
//                Log.d("Mylog", "你點選了:周杰倫");
////                Toast.makeText(context, "你點選了:周杰倫", Toast.LENGTH_SHORT).show();
//            } else {
//                Log.d("Mylog", "你點選了:方文山");
////                Toast.makeText(context, "你點選了:方文山", Toast.LENGTH_SHORT).show();
//            }
        }

    }
}


