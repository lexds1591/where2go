package com.example.where2go;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class NewAdapter extends BaseExpandableListAdapter {

	 public ArrayList<String> groupItem;
	 private Componentes[][] componentes;
	 public LayoutInflater minflater;
	 public Activity activity;
 
	 public NewAdapter(ArrayList<String> grList, Componentes[][] componentes) {
	  groupItem = grList;
	  this.componentes = componentes;
	 }
	
	 public void setInflater(LayoutInflater mInflater, Activity act) {
	  this.minflater = mInflater;
	  activity = act;
	 }
	
	 @Override
	 public Object getChild(int groupPosition, int childPosition) {
	  return null;
	 }
	
	 @Override
	 public long getChildId(int groupPosition, int childPosition) {
	  return 0;
	 }
	
	 @Override
	 public View getChildView(int groupPosition,  int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {
		 
		 final int grupo = groupPosition;
		 final int hijo = childPosition;
		 
		  if (convertView == null) {
		   convertView = minflater.inflate(R.layout.childrow, null);//es necesario para identificar el archivo
		  }
		  if( childPosition == 0 || childPosition == 2 ){
			  
			  componentes[groupPosition][childPosition].ll_titulo.removeAllViewsInLayout();
			  componentes[groupPosition][childPosition].ll_titulo.addView(componentes[groupPosition][childPosition].titulo);
			  return componentes[groupPosition][childPosition].ll_titulo;
			  
		  }
		  else{
			  componentes[groupPosition][childPosition].seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {            
					 
		            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
		            {

		            	componentes[grupo][hijo].text.setText(Integer.toString(progress));
		            }

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						
						
					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						
					}
			 });
			  componentes[groupPosition][childPosition].ll.removeAllViewsInLayout();
			  componentes[groupPosition][childPosition].ll.addView(componentes[groupPosition][childPosition].text);
			  componentes[groupPosition][childPosition].ll.addView(componentes[groupPosition][childPosition].seekbar);
		  }
		  
		return componentes[groupPosition][childPosition].ll;
		  
	 }
	
	 @Override
	 public int getChildrenCount(int groupPosition) {
		 return 4;
	 }
	
	 @Override
	 public Object getGroup(int groupPosition) {
		 return null;
	 }
	
	 @Override
	 public int getGroupCount() {
		 return groupItem.size();
	 }
	
	 @Override
	 public void onGroupCollapsed(int groupPosition) {
		 super.onGroupCollapsed(groupPosition);
	 }
	
	 @Override
	 public void onGroupExpanded(int groupPosition) {
		 super.onGroupExpanded(groupPosition);
	 }
	
	 @Override
	 public long getGroupId(int groupPosition) {
		 return 0;
	 }
	
	 @Override
	 public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
		  if (convertView == null) {
			  convertView = minflater.inflate(R.layout.grouprow, null);
		  }

		  ((CheckedTextView) convertView).setText(groupItem.get(groupPosition));
		  ((CheckedTextView) convertView).setChecked(isExpanded);
		  //if( componentes[groupPosition][1].seekbarEstado == true ){
			  //((CheckedTextView) convertView).setBackgroundColor(Color.GREEN);
		 // }
		  
		  return convertView;
	 }
	
	 @Override
	 public boolean hasStableIds() {
		 return false;
	 }
	
	 @Override
	 public boolean isChildSelectable(int groupPosition, int childPosition) {
		 return false;
	 }

}
