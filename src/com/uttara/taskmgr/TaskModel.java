package com.uttara.taskmgr;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskModel {


	public boolean checkCategoryExists(String catName) 
	{		
		return new File(catName+".todo").exists();
	}
	
	// Add a new task in a given Category
	public String addTask(TaskBean task, String catName)
	{
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(catName+".todo",true));
			
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String plDt = sdf.format(task.getPlannedDate());
			
			bw.write(task.getTaskName()+":"+task.getDesc()+":"+task.getPriority()+":"+plDt+":"+task.getTags()+":"+d.getTime());
			bw.newLine();
			bw.flush();
			
			return Constants.SUCCESS;
		}
		catch(IOException e) {
			e.printStackTrace();
			return "OOPS! : "+e.getMessage();
		}
		finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// Retrieve all tasks in a given Category
	public List<TaskBean> getTasks(String catName) 
	{
		BufferedReader br = null;
		String line;
		try {
			List<TaskBean> tasks = new ArrayList<>();
			TaskBean task;
			br = new BufferedReader(new FileReader(catName+".todo"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			
			while((line = br.readLine()) != null) {
				String sa[] = line.split(":");
				task = new TaskBean(sa[0],sa[1],sa[4],sdf.parse(sa[3]),Integer.parseInt(sa[2]));
				tasks.add(task);
			}
			return tasks;
		}
		catch(IOException e) {
			e.printStackTrace();
			return null;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	// Edit a given task in a given Category
	public TaskBean editTask(TaskBean task1, TaskBean task2, String catName) {
				
		String taskName1 = task1.getTaskName();
		
		String taskName2 = task2.getTaskName();
		String desc2 = task2.getDesc();
		String tags2 = task2.getTags();
		Date dt2 = task2.getPlannedDate();
		int priority2 = task2.getPriority();
		
		TaskBean newTask = null;
		
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = "";
		File tempFile = new File("myTempFile.todo");
		File inputFile = new File(catName+".todo");
		boolean result = false;
		
		try {
			br = new BufferedReader(new FileReader(catName+".todo"));
			bw = new BufferedWriter(new FileWriter(tempFile));
			
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String plDt = "";
			
			while((line=br.readLine()) != null) {
				String sa[] = line.split(":");
				
				if(! sa[0].equals(taskName1)) {
					newTask = new TaskBean(sa[0],sa[1],sa[4],sdf.parse(sa[3]),Integer.parseInt(sa[2]));
				}
				else {
					newTask = new TaskBean(taskName2,desc2,tags2,dt2,priority2);
				}
				plDt = sdf.format(newTask.getPlannedDate());
				bw.write(newTask.getTaskName()+":"+newTask.getDesc()+":"+newTask.getPriority()+":"+plDt+":"+newTask.getTags()+":"+d.getTime());
				bw.newLine();
				bw.flush();
				
			}
			return newTask;
		}
		catch(IOException | NumberFormatException | ParseException e) {
			e.printStackTrace();
			return newTask;
		}
		finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			inputFile.delete();
			tempFile.renameTo(inputFile);
		}
		
		
	}
	
	// Delete a task in a given Category
	public boolean deleteTask(String taskName, String catName) {
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = "";
		File tempFile = new File("myTempFile.todo");
		File inputFile = new File(catName+".todo");
		boolean result = false;
		TaskBean task = null;
		
		try {
			br = new BufferedReader(new FileReader(catName+".todo"));
			bw = new BufferedWriter(new FileWriter(tempFile));
			
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String plDt = "";
			
			while((line=br.readLine()) != null) {
				String sa[] = line.split(":");
				
				if(! sa[0].equals(taskName)) {
					task = new TaskBean(sa[0],sa[1],sa[4],sdf.parse(sa[3]),Integer.parseInt(sa[2]));
					plDt = sdf.format(task.getPlannedDate());
				}
				else {
					result = true;
					continue;
				}
				
				bw.write(task.getTaskName()+":"+task.getDesc()+":"+task.getPriority()+":"+plDt+":"+task.getTags()+":"+d.getTime());
				bw.newLine();
				bw.flush();
				
			}
			return result;
		}
		catch(IOException | NumberFormatException | ParseException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			inputFile.delete();
			tempFile.renameTo(inputFile);
		}
		
	}
	
	// Search a task in a given Category
	public TaskBean searchTask(String taskName, String catName) {
		
		BufferedReader br = null;
		String line = "";
		try {
			TaskBean task = null;
			br = new BufferedReader(new FileReader(catName+".todo"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			while((line = br.readLine()) != null) {
				String sa[] = line.split(":");
				if(sa[0].equals(taskName))
					task = new TaskBean(sa[0],sa[1],sa[4],sdf.parse(sa[3]),Integer.parseInt(sa[2]));
			}
			return task;
		}
		catch(IOException | NumberFormatException | ParseException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void deleteCategory(String catName) {
		
		File inputFile = new File(catName+".todo");
		inputFile.delete();
	}
	
	public String[] listCategory() {
		
		String path = System.getProperty("user.dir");
		File f = new File(path);
		String names[] = null;
		try {
			f = new File(f.getPath());
			File files[] = f.listFiles();
			names = new String[files.length];
			
			int i=0;
			for(File f1 : files) {
				if(f1.toString().contains(".todo")) {
					names[i] = f1.getName();
					i++;
				}
			}
			
			return names;
		}
		catch(Exception e) {
			e.printStackTrace();
			return names;
		}
	}
	
	
}
