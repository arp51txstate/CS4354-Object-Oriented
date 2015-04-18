package simpleAccount.controller;

import simpleAccount.model.Model;
import simpleAccount.view.View;

public interface Controller 
{
	void setModel(Model model);
	void setView(View view);
	Model getModel();
	View getView();	
}
