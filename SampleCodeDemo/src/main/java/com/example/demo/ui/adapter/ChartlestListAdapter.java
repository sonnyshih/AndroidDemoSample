package com.example.demo.ui.adapter;

import com.example.demo.R;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ChartlestListAdapter extends BaseAdapter{
	private Context context;
	
	private String[] chartletList;
	
	public ChartlestListAdapter(Context context, String[] chartletList){
		this.context = context;
		this.chartletList = chartletList;
	}

	public String[] getChartletList() {
		return chartletList;
	}

	public void setChartletList(String[] chartletList) {
		this.chartletList = chartletList;		
	}

	@Override
	public int getCount() {
		return chartletList.length;
	}

	@Override
	public Object getItem(int position) {
		return chartletList[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.chartlet_adapter,
					null);
			
			ImageView imageView = (ImageView) convertView.findViewById(R.id.chartlet_imageView);
			
			viewHolder = new ViewHolder(imageView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Resources resources = context.getResources();
		int id = resources.getIdentifier(chartletList[position], "drawable",
				context.getPackageName());
		viewHolder.imageView.setImageResource(id);
		
		return convertView;
	}

	private class ViewHolder {
		private ImageView imageView;
		

		private ViewHolder(ImageView imageView) {

			this.imageView = imageView;
		}

	}


}
