/*
 * TaskPriorityComparator.java
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

package edu.worcester.cs499summer2012.comparator;

import java.util.Comparator;

import edu.worcester.cs499summer2012.task.Task;

/**
 * Comparator for sorting tasks by priority.
 * @author Jonathan Hasenzahl
 */
public class TaskPriorityComparator implements Comparator<Task> {
	
	/**
	 * Compares two tasks by their priorities. Tasks with a higher priority
	 * come before tasks with a lower priority.
	 * @param lhs the first task
	 * @param rhs the second task
	 * @return A negative number if the first task has a higher priority, a 
	 *         positive number if the second task has a higher priority, or 0
	 *         if they have the same priority
	 */	
	public int compare(Task lhs, Task rhs) {
		// Ex: LHS -> priority 2 (urgent)
		//     RHS -> priority 1 (normal)
		//     RHS - LHS = 1 - 2 = -1 = LHS ordered first
		return rhs.getPriority() - lhs.getPriority();
	}
}
