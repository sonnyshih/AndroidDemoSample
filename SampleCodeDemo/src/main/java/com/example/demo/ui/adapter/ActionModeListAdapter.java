package com.example.demo.ui.adapter;

import java.util.ArrayList;

import com.example.demo.R;
import com.example.demo.activity.MenuAndActionBarDemo.ActionModeItemEntity;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionModeListAdapter extends BaseAdapter{

	private LayoutInflater myInflater;
	private ArrayList<ActionModeItemEntity> itemEntities;
	private Context context;
	public ActionModeListAdapter(Context context, ArrayList<ActionModeItemEntity> itemEntities){
		this.context = context;
		this.itemEntities = itemEntities;
		myInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return itemEntities.size();
	}

	@Override
	public Object getItem(int position) {
		return itemEntities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void selected(int position){
		itemEntities.get(position).setCheck(true);
		notifyDataSetChanged();
	}
	
	public void unselected(int position){
		itemEntities.get(position).setCheck(false);
		notifyDataSetChanged();
	}
	
	public void cleanAllSelected(){
		for (ActionModeItemEntity actionModeItemEntity : itemEntities) {
			actionModeItemEntity.setCheck(false);
		}
		notifyDataSetChanged();
	}
	
	public void selectAll(){
		for (ActionModeItemEntity actionModeItemEntity : itemEntities) {
			actionModeItemEntity.setCheck(true);
		}
		notifyDataSetChanged();
	}
	
	public ArrayList<ActionModeItemEntity> getItemEntities(){
		return itemEntities;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		
		if (convertView == null) {
			
			convertView = myInflater.inflate(R.layout.list_view_action_moode_demo_adapter, null);
			viewHolder = new ViewHolder();			
			viewHolder.setView(convertView);
			convertView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Resources resources = context.getResources();
		
		// default color
		viewHolder.itemLayout.setBackgroundColor(resources
				.getColor(android.R.color.background_light));

		// If item is checked, set the color.
		if (itemEntities.get(position).isCheck()) {
			viewHolder.itemLayout.setBackgroundColor(resources
					.getColor(android.R.color.holo_blue_light));
		}
		
		viewHolder.nameTextView.setText(itemEntities.get(position).getName());

		
		return convertView;
	}

	private class ViewHolder {
		private TextView nameTextView;
		private LinearLayout itemLayout;

		private void setView(View convertView){
			nameTextView = (TextView) convertView.findViewById(R.id.listViewActionModeDemo_nameTextView);
			itemLayout = (LinearLayout) convertView.findViewById(R.id.listViewActionModeDemo_itemLayout);
		}
		
	}

}
