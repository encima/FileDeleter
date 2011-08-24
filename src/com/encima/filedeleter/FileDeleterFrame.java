package com.encima.filedeleter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class FileDeleterFrame extends JFrame{

	JPanel chooseDir = new JPanel();
	JPanel showDir = new JPanel();
	JTextField dirTextField = new JTextField();
	JButton dirButton = new JButton("Find Deleted Files");
	static JList sameFileList = new JList();
	static JList similarFileList = new JList();
	static JScrollPane sameScroll = new JScrollPane(sameFileList);
	static JScrollPane similarScroll  = new JScrollPane(similarFileList);
	
	public FileDeleterFrame() {
		setTitle("File Deleter");
		setSize(600, 600);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				System.exit(0);
			}
		});
		setLayout(new BorderLayout());
		chooseDir.setLayout(new GridLayout(1,2));
		showDir.setLayout(new GridLayout(1,2));
		chooseDir.add(dirTextField);
		dirTextField.setText("Enter File Dir");
		chooseDir.add(dirButton);
		showDir.add(sameScroll);
		showDir.add(similarScroll);
		add(chooseDir, BorderLayout.NORTH);
		add(showDir, BorderLayout.CENTER);
		setVisible(true);
		dirButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = new File(dirTextField.getText());
				if(file.isDirectory()) {
					FileFinder f = new FileFinder(file);
					f.start();
				}else{
					System.out.println("Error: " + file);
					System.out.println("Not a directory, try again");
				}
			}
			
		});
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileDeleterFrame fdf = new FileDeleterFrame();
	}
	
	public static void updateList(Vector<File> sameName, Vector<File> similarName) {
		sameFileList.setListData(sameName);
		similarFileList.setListData(similarName);
	}

}
