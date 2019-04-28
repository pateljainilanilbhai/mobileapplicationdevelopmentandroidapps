/*
 * TaskDateCreatedComparator.java
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
 * Comparator for sorting tasks by creation date.
 * @author Jonathan Hasenzahl
 */
public class TaskDateCreatedComparator implements Comparator<Task> {
	
	/**
	 * Compares two tasks by their creation date. Newer tasks come first.
	 * @param lhs the first task
	 * @param rhs the second task
	 * @return A negative value if the first task was created later, a positive
	 *         value if the second task was created later, or 0 if they were 
	 *         created at the same time
	 */	
	public int compare(Task lhs, Task rhs) {
		// Compare by date
		// Ex. LHS -> date 5000ms (earlier)
		//     RHS -> date 6000ms (later)
		//     LHS - RHS = 5000 - 6000 = -1000 = RHS ordered first
		long diff = lhs.getDateCreated() - rhs.getDateCreated();
		
		if (diff < 0)
			return 1;
		
		if (diff > 0)
			return -1;
		
		return 0;
	}
}