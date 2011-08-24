package com.encima.filedeleter;

import java.io.File;
import java.util.Vector;

public class FileFinder extends Thread {
	File userDir;
	static File[] userDirLof;
	static Vector<File> sameName = new Vector<File>();
	static Vector<File> similarName = new Vector<File>();
	public FileFinder(File file) {
		userDir = file;
		userDirLof = file.listFiles();
	}
	
	public void run() {
		System.out.println("Running...");
		checkDir();
	}
	
	public static void checkDir() {
		System.out.println("Checking Main Directory, may take some time...");
		
		for(int i=0;i<userDirLof.length;i++) {
			int counter=0;
			for(int j=1;j<userDirLof.length;j++) {
				
				if(userDirLof[i].toString().equalsIgnoreCase(userDirLof[j].toString())) {
					counter++;
				}else if(userDirLof[i].toString().contains(userDirLof[j].toString())) {
					counter++;
				}else if(userDirLof[j].toString().contains(userDirLof[i].toString())) {
					counter++;
				}
				
				if(userDirLof[i].toString().equalsIgnoreCase(userDirLof[j].toString()) && counter>2) {
					sameName.add(userDirLof[i]);
				}else if(userDirLof[j].toString().contains(userDirLof[i].toString()) && counter>2) {
					similarName.add(userDirLof[i]);
				}else if(userDirLof[j].toString().contains(userDirLof[i].toString())) {
					similarName.add(userDirLof[i]);
				}
			}
		}
		System.out.println("Done!");
		FileDeleterFrame.updateList(sameName, similarName);
	}

}
