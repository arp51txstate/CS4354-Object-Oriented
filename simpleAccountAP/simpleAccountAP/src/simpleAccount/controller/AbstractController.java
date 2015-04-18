package simpleAccount.controller;

import simpleAccount.model.Model;
import simpleAccount.view.View;

public abstract class AbstractController implements Controller 
{
	
	private View view;
	private Model model;
	
	//setter for model
	public void setModel(Model model)
	{
		this.model = model;
	}
	
	//getter for model
	public Model getModel() 
	{
		return model;
	}
	
	//setter for view
	public void setView(View view)
	{
		this.view = view;
	}
	
	//getter for view
	public View getView()
	{
		return view;
	}

}
