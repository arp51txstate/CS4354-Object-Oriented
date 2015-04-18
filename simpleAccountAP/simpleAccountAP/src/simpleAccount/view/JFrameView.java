package simpleAccount.view;

import javax.swing.*;
import simpleAccount.controller.Controller;
import simpleAccount.model.AbstractModel;
import simpleAccount.model.Model;
import simpleAccount.model.ModelListener;


abstract public class JFrameView extends JFrame implements View, ModelListener 
{
	private static final long serialVersionUID = 1L;
	private Model model;
	private Controller controller;
	
	//constructor for JFrameView
	public JFrameView(Model model, Controller controller)
	{
		setModel(model);
		setController(controller);
	}
	//registers view with model
	public void registerWithModel()
	{
		((AbstractModel)model).addModelListener(this);
	}
	
	//getter for view's controller
	public Controller getController()
	{
		return controller;
	}
	
	//setter for view's controller
	public void setController(Controller controller)
	{
		this.controller = controller;
	}

	//getter for view's model
	public Model getModel() 
	{
		return model;
	}

	//setter for view's model
	public void setModel(Model model)
	{
	  this.model = model;
	  registerWithModel();
	}
}
