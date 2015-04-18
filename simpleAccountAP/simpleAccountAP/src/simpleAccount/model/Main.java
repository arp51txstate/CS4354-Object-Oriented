package simpleAccount.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import simpleAccount.controller.AccountsController;

public class Main 
{

	public static void main(String [] args)
	{		
		 String file = "HW4testfile.txt";		
		 ArrayList<String> accountNameList = new ArrayList<String>();
		 ArrayList<Double> accountValueList = new ArrayList<Double>();
		 ArrayList<Integer> accountNumberList = new ArrayList<Integer>();
			
		 //read the HW4testfile.txt input file
		try
		{		
			FileInputStream fstream =  new FileInputStream(file);
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader inline = new BufferedReader(new InputStreamReader(in));		   
		    String fullLine = " ";
		    String name;
		    int account;
		    double value;
		   	   
		    while((fullLine = inline.readLine()) != null)
		    {			   
			   Scanner scan = new Scanner(fullLine);
			   name = scan.next();
			   account = scan.nextInt();
			   value = scan.nextDouble();			   
			   System.out.println(name + " " + account + " " + value);			   
			   accountNameList.add(name);
			   accountValueList.add(value);
			   accountNumberList.add(account);
			   scan.close();			   
		     }		   	
		  fstream.close();
	   }
	   catch(IOException ex)
	   {
		   ex.fillInStackTrace();
		   System.out.println(ex.getMessage());   
	   }   
	 new AccountsController(accountNameList,accountNumberList,accountValueList,file);
	 }	
}
