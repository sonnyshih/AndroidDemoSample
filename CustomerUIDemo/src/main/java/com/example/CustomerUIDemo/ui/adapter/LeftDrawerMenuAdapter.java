package com.example.CustomerUIDemo.ui.adapter;

import java.util.ArrayList;
import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.activity.DrawerDemo.DrawerItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LeftDrawerMenuAdapter extends BaseAdapter{
	private ArrayList<DrawerItem> arrayList;
	private Context context;
	private LayoutInflater myInflater;
	
	public LeftDrawerMenuAdapter(Context context, ArrayList<DrawerItem> arrayList){
		this.context = context;
		this.arrayList = arrayList;
		myInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		
		if (convertView == null) {
			convertView = myInflater.inflate(R.layout.left_drawer_menu_adapter, null);
			viewHolder = new ViewHolder();			
			viewHolder.setView(convertView);
			convertView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.menuItemNameTextView.setText(arrayList.get(position).getName());
		viewHolder.menuItemImageView.setImageResource(arrayList.get(position).getIcon());
		
		
		return convertView;
	}

	private class ViewHolder {
		private ImageView menuItemImageView;
		private TextView menuItemNameTextView;

		private void setView(View convertView){
			menuItemNameTextView = (TextView) convertView.findViewById(R.id.main_menuItemNameTextView);
			menuItemImageView = (ImageView)  convertView.findViewById(R.id.main_menuItemImageView);
		}
		
	}
	
}
