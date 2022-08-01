package com.uttara.taskmgr;

import java.io.*;
import java.util.*;

public class Logger {
	
	private static Logger obj = null;
	
	public static final boolean LOGTOMONITOR = true;
	
	private static final int CONF = 1;
	public static final String PATH = Constants.PATH;
	
	public static final int CRITICAL = 4;
	public static final int HIGH = 3;
	public static final int MEDIUM = 2;
	public static final int LOW = 1;
	
	private Logger() {
		System.out.println("in Logger() no-argument constr");
	}
	
	public static Logger getInstance() {
		if(obj == null) {
			obj = new Logger();
		}
		return obj;
	}
	
	public void log(String message, int priority) {
		
		new Thread(new Runnable() {
			
			public void run() {
				
				if(priority >= CONF) {
					
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter(PATH,true));
						Date dt = new Date();
						bw.write(dt.toString()+":"+message);
						bw.newLine();
						
						if(Logger.LOGTOMONITOR == true)
							System.out.println("Logger:"+message);
						
						bw.flush();
					}
					catch(IOException e) {
						e.printStackTrace();
						try {
							bw.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}	
			}
		}).start();
	}
	
}





