package com.example.CustomerUIDemo.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.CustomerUIDemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ViewHolderDemoItemAdapter extends BaseAdapter {

	private ArrayList<HashMap<String, Object>> myList = new ArrayList<HashMap<String, Object>>();
	private LayoutInflater myInflater;
	
	public ViewHolderDemoItemAdapter(Context context,
			ArrayList<HashMap<String, Object>> myList) {
		
		this.myList = myList;
		myInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return myList.size();
	}

	@Override
	public HashMap<String, Object> getItem(int position) {
		return myList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		
		if (convertView == null) {
			
			convertView = myInflater.inflate(R.layout.item_adapter, null);
			viewHolder = new ViewHolder();			
			viewHolder.setView(convertView);
			convertView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.keyTextView.setText("Key "+position);
		viewHolder.valueTextView.setText(myList.get(position).get("Key "+position).toString());
		
		
		return convertView;
	}
	
	private class ViewHolder {
		private TextView keyTextView;
		private TextView valueTextView;

		private void setView(View convertView){
			keyTextView = (TextView) convertView.findViewById(R.id.Key);
			valueTextView = (TextView) convertView.findViewById(R.id.value);
		}
		
	}

	

}

