/* TaskAlarm.java
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

package edu.worcester.cs499summer2012.service;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import edu.worcester.cs499summer2012.activity.SettingsActivity;
import edu.worcester.cs499summer2012.database.TasksDataSource;
import edu.worcester.cs499summer2012.task.Task;


/**
 * Wrapper Class for setRepeatingAlarm(), cancelAlarm(), setOnetimeAlarm()
 * @author Dhimitraq Jorgji
 */
public class TaskAlarm {

	public static final String ALARM_EXTRA ="edu.worcester.cs499summer2012.TaskAlarm";
	public static final int REPEATING_ALARM = 1;
	public static final int PROCRASTINATOR_ALARM =2;

	/**
	 * Cancel alarm using the task id, PendingIntent is created using the Task id
	 * @param context
	 * @param intent
	 */
	public void cancelAlarm(Context context, int id)
	{	
		//cancel regular alarms
		PendingIntent pi = getPendingIntent(context, id);
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(pi);
		pi.cancel();

		//cancel Reminder Alarm
		Intent intent =  new Intent(context, OnAlarmReceiver.class)
			.putExtra(Task.EXTRA_TASK_ID, id)
			.putExtra(TaskAlarm.ALARM_EXTRA, SettingsActivity.REMINDER_TIME);
		pi = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		alarmManager.cancel(pi);
		pi.cancel();
		
		//cancel procrastinator Alarm
		intent =  new Intent(context, OnAlarmReceiver.class)
			.putExtra(Task.EXTRA_TASK_ID, id)
			.putExtra(TaskAlarm.ALARM_EXTRA, SettingsActivity.ALARM_TIME);
		pi = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		alarmManager.cancel(pi);
		pi.cancel();
	}
	
	/**
	 * Use this call in activity code to cancel existing notifications
	 * @param context
	 * @param id The ID of the task
	 */
	public void cancelNotification(Context context, int id) {
		NotificationHelper cancel = new NotificationHelper();
		cancel.cancelNotification(context, id);
	}

	/**
	 * Set a One Time Alarm using the taskID
	 * @param context
	 * @param id id of task to retrieve task from SQLite database
	 */
	public void setAlarm(Context context, Task task){
		AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, task.getDateDue(), 
				getPendingIntent(context, task.getID()));
	}

	/**
	 * Sets DateDue field to the next repeat cycle, you still need to call setAlarm()
	 * @param context
	 * @param id
	 */
	public Task setRepeatingAlarm(Context context, int id){
		TasksDataSource db = TasksDataSource.getInstance(context);
		Task task = db.getTask(id);    	
		Calendar newDateDue = (Calendar) task.getDateDueCal().clone();
		int repeatType;
		
		switch(task.getRepeatType()){
		case Task.MINUTES:
			repeatType = Calendar.MINUTE;
			break;
		case Task.HOURS:
			repeatType = Calendar.HOUR_OF_DAY;
			break;
		case Task.DAYS:
			repeatType = Calendar.DAY_OF_YEAR;
			break;
		case Task.WEEKS:
			repeatType = Calendar.WEEK_OF_YEAR;
			break;
		case Task.MONTHS:
			repeatType = Calendar.MONTH;
			break;
		case Task.YEARS:
			repeatType = Calendar.YEAR;
			break;
		default:
			repeatType = Calendar.DAY_OF_YEAR;
			break;
		}
		
		// Due date is behind current time, task was finished late
		if (newDateDue.getTimeInMillis() <= System.currentTimeMillis()) {
			while(newDateDue.getTimeInMillis() <= System.currentTimeMillis()){
				newDateDue.add(repeatType, task.getRepeatInterval());
			}
		} else {
			// Due date is ahead of current time, task was finished early
			newDateDue.add(repeatType, task.getRepeatInterval());
			
			AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
			am.set(AlarmManager.RTC_WAKEUP, newDateDue.getTimeInMillis(), 
					getPendingIntent(context, task.getID()));
			
			// Return task unchanged, it will uncomplete automatically when
			// TaskAlarmService when current time >= date due
			return task;
		}
		
		task.setDateDue(newDateDue.getTimeInMillis());
		task.setDateModified(System.currentTimeMillis());
		task.setIsCompleted(false);
		db.updateTask(task);

		return task;
	}
	
	/**
	 * Reads preferences, and schedule a procrastinator alarm for a past due task.
	 * @param context
	 * @param id
	 * @deprecated Use setReminder for procrastinator alarms
	 */
	public void setProcrastinatorAlarm(Context context, int id){
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		String strAlarm = prefs.getString(SettingsActivity.ALARM_TIME, SettingsActivity.DEFAULT_ALARM_TIME);
		Calendar cal = Calendar.getInstance();    	
		int iAlarm = Integer.parseInt(strAlarm);
		cal.add(Calendar.MINUTE, iAlarm);
		long lAlarm = cal.getTimeInMillis();
		
		Intent intent =  new Intent(context, OnAlarmReceiver.class)
			.putExtra(Task.EXTRA_TASK_ID, id)
			.putExtra(TaskAlarm.ALARM_EXTRA, SettingsActivity.ALARM_TIME);
		
		AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, lAlarm, PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT));
	}
	
	/**
	 * Reads preferences, and schedule a reminder alarm for a past due task
	 * @param context
	 * @param id
	 */
	public void setReminder(Context context, int id){
		TasksDataSource db = TasksDataSource.getInstance(context);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

		Task task = db.getTask(id);
		Calendar dueCal = task.getDateDueCal();
		boolean isProcrastinator = task.hasFinalDateDue();
		
		String strReminder;
		int iInterval;
		
		if (isProcrastinator) {
			// Procrastinator alarm
			strReminder = prefs.getString(SettingsActivity.ALARM_TIME, SettingsActivity.DEFAULT_ALARM_TIME);
			iInterval = Calendar.MINUTE;
		} else {
			// Regular alarm
			strReminder = prefs.getString(SettingsActivity.REMINDER_TIME, SettingsActivity.DEFAULT_REMINDER_TIME);
			iInterval = Calendar.HOUR;
		}
		
		int iReminder = Integer.parseInt(strReminder);
		
		do {
			dueCal.add(iInterval, iReminder);
		} while(dueCal.getTimeInMillis() < System.currentTimeMillis());
		
		Intent intent =  new Intent(context, OnAlarmReceiver.class)
			.putExtra(Task.EXTRA_TASK_ID, id)
			.putExtra(TaskAlarm.ALARM_EXTRA, isProcrastinator ? SettingsActivity.ALARM_TIME : SettingsActivity.REMINDER_TIME);
		
		AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, dueCal.getTimeInMillis(), PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT));
	}

	//get a PendingIntent 
	PendingIntent getPendingIntent(Context context, int id) {
		Intent intent =  new Intent(context, OnAlarmReceiver.class)
		.putExtra(Task.EXTRA_TASK_ID, id);
		return PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}
}
