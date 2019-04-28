/*
 * ComparatorListAdapter.java
 *
 * Copyright 2012 Jonathan Hasenzahl, James Celona, Dhimitraq Jorgji
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.worcester.cs499summer2012.adapter;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import edu.worcester.cs499summer2012.R;
import edu.worcester.cs499summer2012.database.TasksDataSource;
import edu.worcester.cs499summer2012.task.Comparator;

/**
 * 
 * @author Jonathan Hasenzahl
 */
public class ComparatorListAdapter extends ArrayAdapter<Comparator> {

	/**************************************************************************
	 * Static fields and methods                                              *
	 **************************************************************************/
	
	static class ViewHolder {
		public CheckBox enabled;
		public TextView name;
		public ImageView up;
		public ImageView down;
	}
	
	/**************************************************************************
	 * Private fields                                                         *
	 **************************************************************************/
	
	private final Activity activity;
	private final ArrayList<Comparator> comparators;
	private TasksDataSource data_source;
	
	/**************************************************************************
	 * Constructors                                                           *
	 **************************************************************************/
	
	/**
	 * Default constructor.
	 * @param
	 * @param
	 */
	public ComparatorListAdapter(Activity activity, ArrayList<Comparator> comparators) {
		super(activity, R.layout.row_comparator, comparators);
		this.activity = activity;
		this.comparators = comparators;
		data_source = TasksDataSource.getInstance(this.activity);
		setNotifyOnChange(true);
	}
	
	/**************************************************************************
	 * Overridden parent methods                                              *
	 **************************************************************************/
	
	/**
	 * This method is called automatically when the user scrolls the ListView.
	 * Updates the View of a single visible row, reflecting the list being 
	 * scrolled by the user.
	 * @param position the index of the TaskList
	 * @param convert_view the View to be updated
	 * @param parent the parent ViewGroup of convert_view
	 * @return the updated View
	 */
	@Override
	public View getView(int position, View convert_view, ViewGroup parent) {
		View view = convert_view;
		Comparator comparator = comparators.get(position);
		
		if (view == null) {		
			LayoutInflater inflater = activity.getLayoutInflater();
			view = inflater.inflate(R.layout.row_comparator, null);
			
			final ViewHolder view_holder = new ViewHolder();
			view_holder.enabled = (CheckBox) view.findViewById(R.id.checkbox_row_comparator_enabled);
			view_holder.name = (TextView) view.findViewById(R.id.text_row_comparator_name);
			view_holder.up = (ImageView) view.findViewById(R.id.image_row_comparator_up);
			view_holder.up.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// User wants to move this comparator up in the list (decrease order by 1)
					Comparator comparator = (Comparator) view_holder.enabled.getTag();
					int old_order = comparator.getOrder();
					
					// Check to see if this comparator is at the top of the list
					if (old_order == 0)
						return;
					
					// Grab the affected comparator and modify both of their orders
					int new_order = old_order - 1;
					Comparator displaced_comparator = comparators.get(new_order);
					comparator.setOrder(new_order);
					displaced_comparator.setOrder(old_order);
					
					// Update their positions in the adapter
					comparators.set(old_order, displaced_comparator);
					comparators.set(new_order, comparator);
					
					// Update both comparators in the database
					data_source.updateComparator(comparator);
					data_source.updateComparator(displaced_comparator);
					
					notifyDataSetChanged();
				}
				
			});
			view_holder.down = (ImageView) view.findViewById(R.id.image_row_comparator_down);
			view_holder.down.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// User wants to move this comparator down in the list (increase order by 1)
					Comparator comparator = (Comparator) view_holder.enabled.getTag();
					int old_order = comparator.getOrder();
					
					// Check to see if this comparator is at the bottom of the list
					if (old_order == Comparator.NUM_COMPARATORS - 1)
						return;
					
					// Grab the affected comparator and modify both of their orders
					int new_order = old_order + 1;
					Comparator displaced_comparator = comparators.get(new_order);
					comparator.setOrder(new_order);
					displaced_comparator.setOrder(old_order);
					
					// Update their positions in the adapter
					comparators.set(old_order, displaced_comparator);
					comparators.set(new_order, comparator);
					
					// Update both comparators in the database
					data_source.updateComparator(comparator);
					data_source.updateComparator(displaced_comparator);
					
					notifyDataSetChanged();
				}
				
			});
			
			view.setTag(view_holder);
			view_holder.enabled.setTag(comparator);
		} else
			((ViewHolder) view.getTag()).enabled.setTag(comparator); 

		ViewHolder holder = (ViewHolder) view.getTag();
		
		// Set is enabled
		boolean is_enabled = comparator.isEnabled();
		holder.enabled.setChecked(is_enabled);
		
		// Set name
		holder.name.setText(comparator.getName());
		holder.name.setEnabled(is_enabled);
		
		// Set arrows
		holder.up.setImageResource(is_enabled ? R.drawable.ic_up_active : R.drawable.ic_up_inactive);
		holder.down.setImageResource(is_enabled ? R.drawable.ic_down_active : R.drawable.ic_down_inactive);
		
		return view;
	}
}
