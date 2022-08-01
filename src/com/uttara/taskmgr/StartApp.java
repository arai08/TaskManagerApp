package com.uttara.taskmgr;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class StartApp {

	public static void main(String[] args) {
		
		try 
		{
			Scanner sc = new Scanner(System.in);
			
			Logger.getInstance().log("Starting Task Manager...", 3);
			TaskModel model = new TaskModel();
			
			int ch = 0, ch2 = 0;
			String catName, taskName, desc, tags, sPlannedDate;
			int priority;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			while(ch != 6) {
				
				System.out.println("");
				System.out.println("Press 1 to create category");
				System.out.println("Press 2 to load category");
				System.out.println("Press 3 to remove category");
				System.out.println("Press 4 to list category");
				System.out.println("Press 5 to search category");
				System.out.println("Press 6 to exit");
				
				ch = sc.nextInt();
				sc.nextLine();
				
				switch(ch) {
					case 1: System.out.println("To CREATE category");
							System.out.println("Enter the category name : ");
							catName = sc.nextLine();
							while(! TaskUtil.validateName(catName)) 
							{
								System.out.println("Category name must be single word, start with a letter and contains only letters and digits");
								System.out.println("Enter again : ");
								catName = sc.nextLine();
							}
							//check if category name is unique
							if(model.checkCategoryExists(catName)) {
								System.out.println("Duplicate. Enter unique category name");
							}
							else {
								ch2 = 0;
								while(ch2 != 6 ) {
									System.out.println("Press 1 to create task");
									System.out.println("Press 2 to update task");
									System.out.println("Press 3 to remove task");
									System.out.println("Press 4 to list task");
									System.out.println("Press 5 to search task");
									System.out.println("Press 6 to go back");
									
									ch2 = sc.nextInt();
									sc.nextLine();
									
									switch(ch2) {
										case 1: System.out.println("To CREATE Task");
												//Logger.getInstance().log("To CREATE Task", 3);
												System.out.println("Enter Task Name : ");
												taskName = sc.nextLine();
												System.out.println("Enter description : ");
												desc = sc.nextLine();
												System.out.println("Enter tags : ");
												tags = sc.nextLine();
												System.out.println("Enter planned end date (dd/mm/yyyy) : ");
												sPlannedDate = sc.nextLine();
												System.out.println("Enter priority (low=1 , high=10) : ");
												priority = sc.nextInt();
												sc.nextLine();
												
												Date dt = sdf.parse(sPlannedDate);
												
												TaskBean bean = new TaskBean(taskName,desc,tags,dt,priority);
												String result = model.addTask(bean,catName);
												if(result.equals(Constants.SUCCESS)) {
													System.out.println("Task "+taskName+" got added successfully");
													Logger.getInstance().log("Task "+taskName+" created", 3);
												}
												else {
													System.out.println("Adding task "+taskName+" failed.\n"+result);
												}
												
												break;
										
										case 2: System.out.println("To EDIT Task");
												System.out.println("Enter the task name to edit : ");
												taskName = sc.nextLine();
												TaskBean task1 = model.searchTask(taskName,catName);
												if(task1 == null)
													System.out.println("Task NOT FOUND");
												else {
													TaskBean task2;
													System.out.println("Enter Task Name : ");
													taskName = sc.nextLine();
													System.out.println("Enter description : ");
													desc = sc.nextLine();
													System.out.println("Enter tags : ");
													tags = sc.nextLine();
													System.out.println("Enter planned end date (dd/mm/yyyy) : ");
													sPlannedDate = sc.nextLine();
													System.out.println("Enter priority (low=1 , high=10) : ");
													priority = sc.nextInt();
													sc.nextLine();
													
													Date dte = sdf.parse(sPlannedDate);
													
													task2 = new TaskBean(taskName,desc,tags,dte,priority);
													TaskBean newTask = model.editTask(task1, task2,catName);
													System.out.println(newTask);
													
												}
												
												break;
								
										case 3: System.out.println("To DELETE Task");
												System.out.println("Enter the task name to delete : ");
												taskName = sc.nextLine();
												boolean b = model.deleteTask(taskName,catName);
												if(b == false)
													System.out.println("Task NOT FOUND");
												else
													System.out.println("Task DELETED");
												break;
								
										case 4: System.out.println("To LIST task");
												List<TaskBean> tasks = model.getTasks(catName);
												for(TaskBean task : tasks) {
													System.out.println(task.toString());
												}
												break;
								
										case 5: System.out.println("To SEARCH task");
												System.out.println("Enter the task name to search : ");
												taskName = sc.nextLine();
												TaskBean task = model.searchTask(taskName,catName);
												if(task == null)
													System.out.println("Task NOT FOUND");
												else
													System.out.println(task.toString());
												break;
								
										case 6: System.out.println("Going back to Main Menu");
												break;
								
										default: System.out.println("Not a valid option !");
									}
								}
							}
							break;
					
					case 2: System.out.println("To LOAD category");
							System.out.println("Enter the category name : ");
							catName = sc.nextLine();
							
							if(!model.checkCategoryExists(catName)) {
								System.out.println("The entered category doesn't exist");
							}
							else {
								ch2 = 0;
								while(ch2 != 6 ) {
									System.out.println("Press 1 to create task");
									System.out.println("Press 2 to update task");
									System.out.println("Press 3 to remove task");
									System.out.println("Press 4 to list task");
									System.out.println("Press 5 to search task");
									System.out.println("Press 6 to go back");
									
									ch2 = sc.nextInt();
									sc.nextLine();
									
									switch(ch2) {
										case 1: System.out.println("To CREATE Task");
												//Logger.getInstance().log("To CREATE Task", 3);
												System.out.println("Enter Task Name : ");
												taskName = sc.nextLine();
												System.out.println("Enter description : ");
												desc = sc.nextLine();
												System.out.println("Enter tags : ");
												tags = sc.nextLine();
												System.out.println("Enter planned end date (dd/mm/yyyy) : ");
												sPlannedDate = sc.nextLine();
												System.out.println("Enter priority (low=1 , high=10) : ");
												priority = sc.nextInt();
												sc.nextLine();
												
												Date dt = sdf.parse(sPlannedDate);
												
												TaskBean bean = new TaskBean(taskName,desc,tags,dt,priority);
												String result = model.addTask(bean,catName);
												if(result.equals(Constants.SUCCESS)) {
													System.out.println("Task "+taskName+" got added successfully");
													Logger.getInstance().log("Task "+taskName+" created", 3);
												}
												else {
													System.out.println("Adding task "+taskName+" failed.\n"+result);
												}
												
												break;
										
										case 2: System.out.println("To EDIT Task");
												System.out.println("Enter the task name to edit : ");
												taskName = sc.nextLine();
												TaskBean task1 = model.searchTask(taskName,catName);
												if(task1 == null)
													System.out.println("Task NOT FOUND");
												else {
													TaskBean task2;
													System.out.println("Enter Task Name : ");
													taskName = sc.nextLine();
													System.out.println("Enter description : ");
													desc = sc.nextLine();
													System.out.println("Enter tags : ");
													tags = sc.nextLine();
													System.out.println("Enter planned end date (dd/mm/yyyy) : ");
													sPlannedDate = sc.nextLine();
													System.out.println("Enter priority (low=1 , high=10) : ");
													priority = sc.nextInt();
													sc.nextLine();
													
													Date dte = sdf.parse(sPlannedDate);
													
													task2 = new TaskBean(taskName,desc,tags,dte,priority);
													TaskBean newTask = model.editTask(task1, task2,catName);
													System.out.println(newTask);
													
												}
												
												break;
								
										case 3: System.out.println("To DELETE Task");
												System.out.println("Enter the task name to delete : ");
												taskName = sc.nextLine();
												boolean b = model.deleteTask(taskName,catName);
												if(b == false)
													System.out.println("Task NOT FOUND");
												else
													System.out.println("Task DELETED");
												break;
								
										case 4: System.out.println("To LIST task");
												List<TaskBean> tasks = model.getTasks(catName);
												for(TaskBean task : tasks) {
													System.out.println(task.toString());
												}
												break;
								
										case 5: System.out.println("To SEARCH task");
												System.out.println("Enter the task name to search : ");
												taskName = sc.nextLine();
												TaskBean task = model.searchTask(taskName,catName);
												if(task == null)
													System.out.println("Task NOT FOUND");
												else
													System.out.println(task.toString());
												break;
								
										case 6: System.out.println("Going back to Main Menu");
												break;
								
										default: System.out.println("Not a valid option !");
									}
								}
							}
							break;
					
					case 3:	System.out.println("To REMOVE Category");
							System.out.println("Enter the category name : ");
							catName = sc.nextLine();
							
							if(!model.checkCategoryExists(catName)) {
								System.out.println("The entered category doesn't exist");
							}
							else {
								model.deleteCategory(catName);
								System.out.println("Category removed successfully");
							}
							break;
					
					case 4:	System.out.println("To LIST Category");
							
							
							String names[] = model.listCategory();
							if(names.length == 0)
							{
								System.out.println("No category present");
							}
							else {
								System.out.println("Category Names : ");
								for(String name : names) {
									System.out.println(name);
								}
							}
							break;
				
					case 5:	System.out.println("To SEARCH Category");
							System.out.println("Enter the category name : ");
							catName = sc.nextLine();
							
							if(!model.checkCategoryExists(catName)) {
								System.out.println("The entered category doesn't exist");
							}
							else {
								System.out.println("Category present");
							}
							break;
				
					case 6: System.out.println("Exiting");
							break;
													
					default: System.out.println("Not supported");
							break;
					
				} //switch
			} //while
		} //try
		
		catch(Throwable t) 
		{
			t.printStackTrace();
		}
		
	}

}
