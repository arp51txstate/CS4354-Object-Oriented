package simpleAccount.view;

import javax.swing.*; 
import simpleAccount.controller.AccountsController;
import simpleAccount.model.AccountsModel;
import simpleAccount.model.ModelEvent;
import java.awt.*; 
import java.awt.event.*;

public class AccountsView extends JFrameView
{
	private static final long serialVersionUID = 1L;
	public static final String DOLLAR = " Edit account in $USD ";
	public static final String EURO = "Edit account in Euros";
	public static final String YUAN = "Edit account in Yuan";
	public static final String SAVE = "Save";
	public static final String EXIT = "Exit";	
	public int comboChoice;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AccountsView(AccountsModel model, AccountsController controller)
	{
		//setting up the main window
		super(model, controller);
		String[] accountnames = ((AccountsModel)getModel()).getNames();	
		JComboBox combo = new JComboBox(accountnames);
		this.getContentPane().add(combo,BorderLayout.NORTH); 
		JPanel buttonPanel = new JPanel();
		Handler handler = new Handler();
		ComboHandler combohandler = new ComboHandler();		
		JButton jbuttonDollar = new JButton(DOLLAR);
		jbuttonDollar.addActionListener(handler);
		JButton jbuttonEuro = new JButton(EURO);
		jbuttonEuro.addActionListener(handler);
		JButton jbuttonYuan = new JButton(YUAN);
		jbuttonYuan.addActionListener(handler);
		JButton jbuttonSave = new JButton(SAVE);
		jbuttonSave.addActionListener(handler);
		JButton jbuttonExit = new JButton(EXIT);
		jbuttonExit.addActionListener(handler);
		comboChoice = combo.getSelectedIndex();
		combo.addActionListener(combohandler);	
		buttonPanel.setLayout(new GridLayout(3,2));	
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);	
		buttonPanel.add(jbuttonDollar,null);
		buttonPanel.add(jbuttonEuro,null);
		buttonPanel.add(jbuttonYuan,null);
		buttonPanel.add(jbuttonSave,null);		
		buttonPanel.add(jbuttonExit,null);
		this.setTitle("Simple Account HW4");	
		pack();		
	}

	public int getCurrentCombo()
	{
		System.out.println("this was the choice " + comboChoice);
		return comboChoice;
	}
	
	public void modelChanged(ModelEvent event)
	{
	
	}
	
	class Handler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{	
			((AccountsController)getController()).operation(e.getActionCommand());	
		}
	}
	
	class ComboHandler implements ActionListener
	{
		@SuppressWarnings("rawtypes")
		public void actionPerformed(ActionEvent e) 
		{
	        JComboBox cb = (JComboBox)e.getSource();
	        comboChoice = cb.getSelectedIndex();     
		}
	}
}
