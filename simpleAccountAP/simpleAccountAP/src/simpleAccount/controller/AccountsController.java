package simpleAccount.controller;

import java.util.ArrayList;

import simpleAccount.model.AccountsModel;
import simpleAccount.view.AccountsView;
import simpleAccount.view.JFrameView;

public class AccountsController extends AbstractController 
{

	public AccountsController(ArrayList<String> accountName,ArrayList<Integer>accountNumber,
		                      ArrayList<Double>accountValue,String file)
	{
		setModel(new AccountsModel(accountName,accountNumber,accountValue,file));
		setView(new AccountsView((AccountsModel)getModel(), this));
		((JFrameView)getView()).setVisible(true);
	}

	//if option equals edit in USD
	public void operation(String option)
	{
		if(option.equals(AccountsView.DOLLAR))
		{
			int pick = ((AccountsView)getView()).getCurrentCombo();	
			new DollarController((AccountsModel)getModel(),pick);	
		}
		
		//if option equals edit in Euro
		else if(option.equals(AccountsView.EURO))
		{
			int pick = ((AccountsView)getView()).getCurrentCombo();	
			new EuroController((AccountsModel)getModel(),pick);		
		}
		
		//if option equals edit in Yuan
		else if(option.equals(AccountsView.YUAN))
		{
			int pick = ((AccountsView)getView()).getCurrentCombo();	
			new YuanController((AccountsModel)getModel(),pick);	
		}
		//if option equals save
		else if(option.equals(AccountsView.SAVE))
		{
			((AccountsModel)getModel()).save();
		}
		//if option equals exit
		else if(option.equals(AccountsView.EXIT))
		{	
			System.exit(1);
		}	
	}
}
