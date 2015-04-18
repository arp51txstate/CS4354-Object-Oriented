package simpleAccount.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import simpleAccount.controller.YuanController;
import simpleAccount.model.AccountsModel;
import simpleAccount.model.ModelEvent;

public class YuanView extends JFrameView 
{
	private static final long serialVersionUID = 1L;
	public static final String DEPOSIT = "Deposit";
	public static final String WITHDRAW = "Withdraw";
	public static final String DISMISS = "Dismiss";
	private double yuanExchange = 6.23;
	double editFieldText;
	public boolean dismiss = false;
	private JTextField textField = new JTextField();
	JTextField editField = new JTextField();
	int userID;

	public YuanView(AccountsModel model, YuanController controller, int pick) 
	{
		//setting up the "edit in Yuan" window
		super(model, controller);
		userID = pick;
		double accountvalue = ((AccountsModel) getModel()).getAccountValue(pick);
		String user = ((AccountsModel) getModel()).getAName(pick);
		this.setTitle(user + " ¥ ");
		textField.setText("¥ " + accountvalue * yuanExchange);
		editField.setText("0.00");
		this.getContentPane().add(textField, BorderLayout.NORTH);
		this.getContentPane().add(editField, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		Handler handler = new Handler();
		EditFieldHandler editHandler = new EditFieldHandler();
		JButton jbuttonDep = new JButton(DEPOSIT);
		jbuttonDep.addActionListener(handler);
		JButton jbuttonWith = new JButton(WITHDRAW);
		jbuttonWith.addActionListener(handler);
		JButton jbuttonDis = new JButton(DISMISS);
		jbuttonDis.addActionListener(handler);
		editField.addActionListener(editHandler);
		buttonPanel.setLayout(new GridLayout(3, 2));
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(jbuttonDep, null);
		buttonPanel.add(jbuttonWith, null);
		buttonPanel.add(jbuttonDis, null);
		pack();
	}

	public double getEditField() 
	{
		return editFieldText;
	}
	public void modelChanged(ModelEvent event) 
	{
		if(event.getId() == userID)
		{
			String msg = "¥ " + (event.getAmount() * yuanExchange);
			textField.setText(msg);
		}
	}

	class Handler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			((YuanController) getController()).operation(e.getActionCommand());
		}
	}

	class EditFieldHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			String editFieldStuff = editField.getText();
			editFieldText = Double.parseDouble(editFieldStuff);
		}
	}
}
