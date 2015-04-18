package simpleAccount.controller;

import simpleAccount.model.AccountsModel;
import simpleAccount.view.JFrameView;
import simpleAccount.view.YuanView;

public class YuanController extends AbstractController 
{
	private double yuanExchange = 6.23;
	public int accountIndex = 0;
	
	public YuanController(AccountsModel model,int pick)
	{
		setModel(model);
		setView(new YuanView(model, this, pick));
		((JFrameView)getView()).setVisible(true);		
		accountIndex = pick;
	}
	public void operation(String option)
	{	
		//get deposit value
		if(option.equals(YuanView.DEPOSIT))
		{
			double depositValue = ((YuanView)getView()).getEditField();			
			depositValue = depositValue/yuanExchange;			
			((AccountsModel)getModel()).deposit(accountIndex,depositValue);			
		}
		//get withdraw value
		else if(option.equals(YuanView.WITHDRAW))
		{
			double withdrawValue = ((YuanView)getView()).getEditField();
			withdrawValue = withdrawValue/yuanExchange;			
			((AccountsModel)getModel()).withdraw(accountIndex,withdrawValue);
		}
		//exit window
		else if(option.equals(YuanView.DISMISS))
		{
			((YuanView)getView()).dispose();
		}		
	}
}
