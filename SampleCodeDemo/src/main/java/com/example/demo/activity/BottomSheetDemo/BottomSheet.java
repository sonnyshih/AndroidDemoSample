package com.example.demo.activity.BottomSheetDemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.demo.R;

public class BottomSheet extends Dialog {

    private static final double MAX_HEIGHT_RATIO = 0.75;

    private BaseAdapter adapter;
    private AdapterView.OnItemClickListener listener;
    private ListView dialogListView;

    public BottomSheet(Context context) {
        super(context, R.style.BottomSheet_Dialog);
        dialogListView = (ListView) View.inflate(getContext(), R.layout.bottom_sheet_list_view, null);
    }

    public BottomSheet(Context context, BaseAdapter adapter, AdapterView.OnItemClickListener listener) {
        super(context, R.style.BottomSheet_Dialog);
        this.adapter = adapter;
        this.listener = listener;
        dialogListView = (ListView) View.inflate(getContext(), R.layout.bottom_sheet_list_view, null);
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    public void setListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        initListView();
        initDialogHeight(getContext());
    }

    private void initListView() {
        dialogListView.setAdapter(adapter);
        dialogListView.setOnItemClickListener(listener);
        setContentView(dialogListView);
    }

    private void initDialogHeight(Context context) {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);

        int dialogMaxHeight = (int) (getDisplayHeight(context) * MAX_HEIGHT_RATIO);
        if (getListViewHeight(context) > dialogMaxHeight) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, dialogMaxHeight);
        } else {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }

    private int getDisplayHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, displayMetrics);
        }
        return (int) (displayMetrics.heightPixels - actionBarHeight * displayMetrics.density);
    }

    private int getListViewHeight(Context context) {
        View itemView = adapter.getView(0, null, dialogListView);
        itemView.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int itemViewMeasuredHeight = itemView.getMeasuredHeight();
        return adapter.getCount() * itemViewMeasuredHeight;
    }

    public ListView getDialogListView(){
        if (dialogListView == null) {
            return null;
        }

        return  dialogListView;
    }

    public void onConfigurationChanged(Context context) {
        initDialogHeight(context);
    }

}

