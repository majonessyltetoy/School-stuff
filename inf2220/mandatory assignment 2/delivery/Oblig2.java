import java.io.*;
import java.util.*;

class Oblig2 {

	private static class  Task {
		int id, time, staff;
		String name;
		int earliestStart, latestStart;
		List<Task> outEdges = new ArrayList<Task>();
		List<Task> inEdges = new ArrayList<Task>();
		
		/*
		 * Efficient traverse method
		 */
		boolean[] traverse(boolean[] checked, ArrayList<Task> traversed) {
			traversed.add(this);
			for (Task t : outEdges) {
				if (traversed.contains(t)) {
					System.out.println("Found loop:");
					traversed = new ArrayList<Task>(traversed.subList(traversed.indexOf(t), traversed.size()));
					for (int i=0; i<traversed.size(); i++) {
						System.out.println(traversed.get(i).id + " " + traversed.get(i).name);
					}
					System.out.println(t.id + " " + t.name);
					System.out.println("exiting");
					System.exit(0);
				}
				if (!checked[t.id - 1]) {
					checked = t.traverse(checked, traversed);
				}
			}
			checked[id - 1] = true;
			traversed.remove(this);
			return checked;
		}
		
		void findEarliestTime() {
			for (Task t : inEdges) {
				if (earliestStart < (t.earliestStart + t.time)) {
					earliestStart = t.earliestStart + t.time;
				}
			}
			
			for (Task t : outEdges) {
				t.findEarliestTime();
			}
		}
		void findLatestTime() {
			for (Task t : inEdges) {
				for (Task q : t.outEdges) {
					if (t.latestStart == 0 || q.earliestStart < t.latestStart) {
						t.latestStart = q.earliestStart - t.time;
					}
				}
				t.findLatestTime();
			}
		}
		ArrayList<Task> allDepends(ArrayList<Task> list, Task start) {
			if (!list.contains(this) && start != this) {
				list.add(this);
			}
			for (Task t : inEdges) {
				if (!list.contains(t)) {
					list = t.allDepends(list, start);
				}
			}
			return list;
		}
	}
	
	public static void main(String[] args) {
		
		File project = new File(args[0]);
		int num_tasks;
		int manpower;
		String[] line;
		Task[] tasks;
		
		Scanner read = null;
		
		if (args.length < 2) {
			System.out.println("Program need input file and manpower");
			System.exit(1);
		}
		
		try {
			read = new Scanner(project);
		}
		catch (FileNotFoundException e) {
			System.out.println("Error reading file: " + args[0]);
			System.exit(1);
		}
		
		try {
			manpower = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException e) {
			System.out.println("Manpower not a number: " + args[1]);
			System.exit(1);
		}
		
		// read task count
		num_tasks = Integer.parseInt(read.nextLine());
		if (num_tasks == 0) System.exit(0);
		read.nextLine(); // remove blank line
		tasks = new Task[num_tasks];
		
		// initialize task array
		for (int i=0; i < num_tasks; i++) {
			tasks[i] = new Task(); 
		}
		
		// read and parse tasks
		// sorry for the mess, it was beautiful before parsing to int
		for (int i=num_tasks; i > 0; i--) {
			line = read.nextLine().split("\\s+");
			tasks[Integer.parseInt(line[0]) - 1].id = Integer.parseInt(line[0]);
			tasks[Integer.parseInt(line[0]) - 1].name = line[1];
			tasks[Integer.parseInt(line[0]) - 1].time = Integer.parseInt(line[2]);
			tasks[Integer.parseInt(line[0]) - 1].staff = Integer.parseInt(line[3]);
			for (int n=4; Integer.parseInt(line[n]) != 0; n++) {
				tasks[Integer.parseInt(line[n]) - 1].outEdges.add(tasks[Integer.parseInt(line[0]) - 1]);
				tasks[Integer.parseInt(line[0]) - 1].inEdges.add(tasks[Integer.parseInt(line[n]) - 1]);
			}
		}
		
		// If cycle exist, print it and exit
		findCycle(new ArrayList<Task>(Arrays.asList(tasks)));
		
		// find earliest and latest start unit for all tasks
		findTimes(tasks);
		
		// simulate execution
		simulateProject(new ArrayList<Task>(Arrays.asList(tasks)));
		
		// Halt execution
		System.out.println("\nPress Enter to print task list. Or press CTRL-c to quit");
		try {
			System.in.read();
		}
		catch (Exception e) {
			// carry on
		}
		
		// print info about all tasks
		printAllTasksInfo(tasks);
		System.out.println();
	}
	
	/*
	 * Find cycle by recursivly traverse nodes in graph
	 */
	private static void findCycle(ArrayList<Task> remain) {
		boolean[] checked = new boolean[remain.size()];
		
		while (remain.size() > 0) {
			if (checked[remain.get(0).id - 1]) {
				remain.remove(0);
				continue;
			}
			checked = remain.get(0).traverse(checked, new ArrayList<Task>());
		}
	}
	
	private static void findTimes(Task[] tasks) {
		int maxTime = 0;
		
		// find earliest time
		for (Task t : tasks) {
			if (t.inEdges.size() == 0) {
				t.findEarliestTime();
			}
		}
		
		// find time usage for entire project
		for (Task t : tasks) {
			if (t.outEdges.size() == 0) {
				if (maxTime < t.earliestStart + t.time) {
					maxTime = t.earliestStart + t.time;
				}
			}
		}
		
		// find latest start time for end nodes
		for (Task t : tasks) {
			if (t.outEdges.size() == 0) {
				t.latestStart = maxTime - t.time;
			}
		}
		
		// find latest start time for all nodes
		for (Task t : tasks) {
			if (t.outEdges.size() == 0) {
				t.findLatestTime();
			}
		}
	}
	
	private static void simulateProject(ArrayList<Task> tasks) {
		int time = -1;
		int currentStaff = 0;
		ArrayList<Task> finished;
		ArrayList<Task> starting;
		while (true) {
			time++;
			finished = new ArrayList<Task>();
			starting = new ArrayList<Task>();
			for (Task t : tasks) {
				if (t.earliestStart + t.time == time) {
					finished.add(t);
					currentStaff -= t.staff;
				}
				if (t.earliestStart == time) {
					starting.add(t);
					currentStaff += t.staff;
				}
			}
			for (Task t : finished) {
				tasks.remove(t);
			}
			
			// nothing to print carry on
			if (finished.size() == 0 && starting.size() == 0) {
				continue;
			}
			
			System.out.print("Time:  " + time + "");
			for (Task t : finished) {
				System.out.print("\tFinished: " + t.id + "\n\t");
			}
			for (Task t : starting) {
				System.out.print("\tStarting: " + t.id + "\n\t");
			}
			
			if (currentStaff == 0) {
				System.out.println("\n**** Shortest possible project execution is " + time + " ****");
				break;
			}
			
			System.out.println("   Current staff: " + currentStaff);
			System.out.println();
		}
	}
	
	private static void printAllTasksInfo(Task[] tasks) {
		for (Task t : tasks) {
			System.out.println("\n");
			System.out.println("Task id: " + t.id);
			System.out.println("Name: " + t.name);
			System.out.println("Time required: " + t.time);
			System.out.println("Earliest start: " + t.earliestStart);
			System.out.println("Latest start: " + t.latestStart);
			System.out.println("Slack: " + (t.latestStart - t.earliestStart));
			System.out.print("Dependent tasks: ");
			for (Task q : t.allDepends(new ArrayList<Task>(), t)) {
				System.out.print(q.id + " ");
			}
		}
	}
}
		
		
