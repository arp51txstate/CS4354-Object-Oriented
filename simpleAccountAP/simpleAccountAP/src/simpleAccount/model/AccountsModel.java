package simpleAccount.model;

import java.util.ArrayList;
import java.io.*;

import javax.swing.JOptionPane;

public class AccountsModel extends AbstractModel 
{
	private int accountNumber[];
	private double accountValue[];
	private String accountName[];	
	private ArrayList<String> accountNameList;
	private ArrayList<Double> accountValueList;
    private ArrayList<Integer> accountNumberList;   
    private String saveFile;

	public AccountsModel(ArrayList<String> accountNAME,ArrayList<Integer>accountNUMBER,
			             ArrayList<Double>accountVALUE,String file)
	{
		accountNameList = accountNAME;
		accountNumberList = accountNUMBER;
		accountValueList = accountVALUE;		
		int num = accountNameList.size();		
		accountName = new String[num];
		accountValue = new double[num];
		accountNumber = new int[num];		
		
		for(int i = 0; i < num; i++)
		{
			accountName[i] = accountNameList.get(i);
			accountValue[i] = accountValueList.get(i);
			accountNumber[i] = accountNumberList.get(i);
		}		
		saveFile = file;
	}
		
	//get name and account number for drop down list
	public String[] getNames()
	{
		int num = accountNameList.size();		
		String nameAndNumber[] = new String[num];
				
		for(int i = 0; i < num; i++)
		{
			nameAndNumber[i] = accountNameList.get(i) + " : " + accountNumber[i];		
		}		
		return nameAndNumber;
	}

	public String getAName(int index)
	{
		return accountName[index];
	}

	public double getAccountValue(int index)
	{
		return accountValue[index];
	}

	//deposit
	synchronized public void deposit(int index,double depositValue)
	{
		System.out.print("so " + accountValue[index] + " + " + depositValue);		
		accountValue[index] += depositValue;		
		System.out.println(" = " + accountValue[index]);		
		ModelEvent me = new ModelEvent(this, index, "", accountValue[index]);
		notifyChanged(me);
	}

	//withdraw
	synchronized public void withdraw(int index, double withdrawValue)
	{		
		if(withdrawValue < accountValue[index])	
		{
			accountValue[index] -= withdrawValue;
		}
		else
		{
			String warning = ("Error: UNABLE TO WITHDRAW THAT AMOUNT");
			System.out.println(warning);
			JOptionPane.showMessageDialog(null, warning,"ERROR", JOptionPane.ERROR_MESSAGE);		
		}		
		ModelEvent me = new ModelEvent(this, index, "", accountValue[index]);
		notifyChanged(me);
	}

	//save file
	public void save()
	{
		try
		{
			FileWriter ofstream = new FileWriter(saveFile);
			BufferedWriter out = new BufferedWriter(ofstream);		
			int num = accountNameList.size();
			
			for(int i = 0; i < num; i++)
			{
				out.write(accountName[i] + " ");
				out.write(accountNumber[i] + " ");
				out.write(accountValue[i] + " ");
				out.newLine();			
			}			
			out.close();			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
	}
}
