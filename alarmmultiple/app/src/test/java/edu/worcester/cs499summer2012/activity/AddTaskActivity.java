/* 
 * AddTaskActivity.java
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

package edu.worcester.cs499summer2012.activity;

import android.content.Intent;
import android.os.Bundle;
import edu.worcester.cs499summer2012.R;
import edu.worcester.cs499summer2012.database.DatabaseHandler;
import edu.worcester.cs499summer2012.service.TaskAlarm;
import edu.worcester.cs499summer2012.service.TaskButlerWidgetProvider;
import edu.worcester.cs499summer2012.task.Category;
import edu.worcester.cs499summer2012.task.Task;
import edu.worcester.cs499summer2012.task.ToastMaker;

/**
 * Activity for adding a new task.
 * @author Jonathan Hasenzahl
 * @author James Celona
 */
public class AddTaskActivity extends BaseTaskActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
        // Initialize priority spinner
        s_priority.setSelection(Task.NORMAL);
        
        // Initialize repeat type spinner
        s_repeat_type.setSelection(Task.DAYS);
        
        // Make the displayed category in MainActivity the default selection
        default_category = data_source.getCategory(prefs.getInt(SettingsActivity.DISPLAY_CATEGORY, MainActivity.DISPLAY_ALL_CATEGORIES));
        s_category.setSelection(category_adapter.getPosition(default_category));
	}

	protected boolean addTask() {
    	// Get task name
    	String name = et_name.getText().toString().trim();
    	
    	// If there is no task name, don't create the task
    	if (name.equals(""))
    	{
    		ToastMaker.toast(this, R.string.toast_task_no_name);
    		return false;
    	}
    	
    	// Get task category
    	int categoryID = ((Category) s_category.getSelectedItem()).getID();
    	
    	// Get repeat interval
    	int interval = 1;
    	String interval_string = et_repeat_interval.getText().toString();
    	if (!interval_string.equals("")) {
    		interval =  Integer.parseInt(interval_string);
    		if (interval == 0)
    			interval = 1;
    	}
    	
    	// Get task due date
    	long due_date_ms = 0;
    	if (cb_due_date.isChecked())
    		due_date_ms = due_date_cal.getTimeInMillis();
    	    	
    	// Current time
    	long current_time = System.currentTimeMillis();
    	
    	// Create the task
    	Task task = new Task(
    			data_source.getNextID(DatabaseHandler.TABLE_TASKS),
    			name,
    			false, 
    			s_priority.getSelectedItemPosition(), 
    			categoryID,
    			cb_due_date.isChecked(),
    			cb_final_due_date.isChecked(),
    			cb_repeating.isChecked(),
    			s_repeat_type.getSelectedItemPosition(),
    			interval,
    			current_time,
    			current_time,
    			due_date_ms,
    			"",
    			et_notes.getText().toString());
    	
    	// Assign the task a unique ID and store it in the database
    	task.setID(data_source.getNextID(DatabaseHandler.TABLE_TASKS));
    	data_source.addTask(task);
    	
    	// Alarm logic: Add a task (AddTaskActivity)
    	// * Task must be added to database first
    	// * If has due date:
    	// *	Set alarm
    	// * 	(Repeating due date will be handled by the service after alarm rings)
    	if (task.hasDateDue() && !task.isPastDue()) {
    		TaskAlarm alarm = new TaskAlarm();
    		alarm.setAlarm(this, task);
    	}
    	
		// Update homescreen widget (after change has been saved to DB)
		TaskButlerWidgetProvider.updateWidget(this);
    	
    	// Create the return intent and add the task ID
    	intent = new Intent(this, MainActivity.class);    	
    	intent.putExtra(Task.EXTRA_TASK_ID, task.getID());
    	
    	return true;
    }
	
}
