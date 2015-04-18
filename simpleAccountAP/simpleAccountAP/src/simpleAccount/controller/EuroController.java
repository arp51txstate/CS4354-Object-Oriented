package simpleAccount.controller;

import simpleAccount.model.AccountsModel;
import simpleAccount.view.EuroView;
import simpleAccount.view.JFrameView;

public class EuroController extends AbstractController
{	
	private final double  euroExchange = 0.92;
	public int accountIndex = 0;
	
	public EuroController(AccountsModel model,int pick)
	{
		setModel(model);
		setView(new EuroView(model, this, pick));
		((JFrameView)getView()).setVisible(true);		
		accountIndex = pick;
	}
	public void operation(String option)
	{		
		//get deposit value
		if(option.equals(EuroView.DEPOSIT))
		{
			double depositValue = ((EuroView)getView()).getEditField();			
			depositValue = depositValue/euroExchange;			
			((AccountsModel)getModel()).deposit(accountIndex,depositValue);			
		}
		//get withdraw value
		else if(option.equals(EuroView.WITHDRAW))
		{
			double withdrawValue = ((EuroView)getView()).getEditField();
			withdrawValue = withdrawValue/euroExchange;			
			((AccountsModel)getModel()).withdraw(accountIndex,withdrawValue);
		}
		//exit window
		else if(option.equals(EuroView.DISMISS))
		{
			((EuroView)getView()).dispose();
		}		
	}
}

