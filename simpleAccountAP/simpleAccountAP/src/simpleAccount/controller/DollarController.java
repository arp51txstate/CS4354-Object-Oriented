package simpleAccount.controller;

import simpleAccount.model.AccountsModel;
import simpleAccount.view.DollarView;
import simpleAccount.view.JFrameView;

public class DollarController extends AbstractController 
{	
	public int accountIndex = 0;
	
	public DollarController(AccountsModel model,int pick)
	{
		setModel(model);
		setView(new DollarView(model, this, pick));
		((JFrameView)getView()).setVisible(true);
		accountIndex = pick;
	}
	
	public void operation(String option)
	{	
		//get deposit value
		if(option.equals(DollarView.DEPOSIT))
		{
			double depositValue = ((DollarView)getView()).getEditField();			
			((AccountsModel)getModel()).deposit(accountIndex,depositValue);			
		}
		//get withdraw value
		else if(option.equals(DollarView.WITHDRAW))
		{
			double withdrawValue = ((DollarView)getView()).getEditField();
			((AccountsModel)getModel()).withdraw(accountIndex,withdrawValue);
		}
		//exit window
		else if(option.equals(DollarView.DISMISS))
		{
			((DollarView)getView()).dispose();
		}	
	}
}
