
package StacksGUI;


import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Stack;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.sql.*;
import javax.swing.*;
/**
 * Class Stacks_GUI implements the interface Stacks, which is an collection of Objects that are processed through index.
 * Uses SQLite database connectivity for storing and retrieving the data.
 * SQLite database connectivity reference: https://en.wikibooks.org/wiki/Java_JDBC_using_SQLite/Connecting
 * @author Sneha Priya Ale
 * @version 1.0 10/19/2015
 * @since JDK 1.5
 */
public class Stacks_GUI extends JApplet implements Stacks {
	private JTextField ObjtextField;
	JPanel panel_display = new JPanel();
	int object=0,p=0,obj,row_num=0;
	String input=null,input_pop=null;
	JLabel lblNewLabel_1,Result,stack_objects;
	GridBagConstraints gbc_lblNewLabel_1;
	//Database assignment
	Connection connect=null;
	
	/**
	 * Create the applet.
	 * 
	 */
	public Stacks_GUI() {
		//Connecting To Database
		connect = databaseConnection.connector();
		this.setSize(400,400);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("No. of Objects for Stacking");
		lblNewLabel.setBounds(15, 27, 199, 20);
		getContentPane().add(lblNewLabel);
		
		ObjtextField = new JTextField();
		ObjtextField.setText("0");
		ObjtextField.setBounds(211, 24, 47, 26);
		getContentPane().add(ObjtextField);
		ObjtextField.setColumns(10);
		JLabel lblStackOperations = new JLabel("Stack Operations");
		lblStackOperations.setBounds(15, 78, 119, 20);
		getContentPane().add(lblStackOperations);
		
		//PUSH button
		JButton btnPush = new JButton("PUSH");
		btnPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				push(null);
			}
		});
		btnPush.setBounds(15, 105, 97, 29);
		getContentPane().add(btnPush);
		
		//POP Button
		JButton btnNewButton = new JButton("POP");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pop();
			}
		});
		btnNewButton.setBounds(15, 133, 97, 29);
		getContentPane().add(btnNewButton);
		
		//SIZE Button
		JButton btnSize = new JButton("SIZE");
		btnSize.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Size();
			}
		});
		btnSize.setBounds(15, 162, 97, 29);
		getContentPane().add(btnSize);
		//Top Button
		JButton btnTop = new JButton("TOP");
		btnTop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					Top();
			}
		});
		btnTop.setBounds(15, 190, 97, 29);
		getContentPane().add(btnTop);
		//Display Button
		JButton btnDisplay = new JButton("DISPLAY");
		btnDisplay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				displayStack();
			}
		});
		btnDisplay.setBounds(15, 218, 97, 29);
		getContentPane().add(btnDisplay);
		//Submit Button
		JButton btnSubmit = new JButton("SUBMIT");
		/**
		 * @param object number of objects to be entered into the stack 
		 * @throw exception if number is not an positive integer.
		 */
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
				
				object = Integer.parseInt(ObjtextField.getText());
				if(object<1)
					throw new Exception();
				
				}
				catch(Exception exc){
					JOptionPane.showMessageDialog(null, "Please enter a Valid Number.");
				}
			}
		});
		btnSubmit.setBounds(273, 23, 89, 29);
		getContentPane().add(btnSubmit);
		getContentPane().setLayout(null);
		


		panel_display.setBounds(273, 90, 112, 169);
		getContentPane().add(panel_display);
		GridBagLayout gbl_panel_display = new GridBagLayout();
		gbl_panel_display.columnWidths = new int[]{0, 0};
		gbl_panel_display.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_display.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_display.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_display.setLayout(gbl_panel_display);
		
		//Display through Index button
		JButton btn_Index = new JButton("Display Through Index");
		btn_Index.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String index_column = JOptionPane.showInputDialog("Enter the index to retrieve the stack Object");
				try{
					String query ="select Object from StackData where ObjectID=?";
					PreparedStatement statement = connect.prepareStatement(query);
					statement.setString(1,index_column);
					
					ResultSet result_query= statement.executeQuery();
					JOptionPane.showMessageDialog(null,	"The Object is "+result_query.getString(1));
					result_query.close();
					statement.close();
					
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});

		btn_Index.setBounds(15, 248, 199, 29);
		getContentPane().add(btn_Index);
		
		JLabel lblNewLabel_2 = new JLabel("Stack Bucket");
		lblNewLabel_2.setBounds(273, 67, 112, 20);
		getContentPane().add(lblNewLabel_2);

		
		

	}
	/**
	 * Insert the elements in  the stack
	 * 
	 * @param Object Is used to push those many number of Objects
	 * @return 
	 * @see #insert the new Object into the stack
	 * @pre Object >0
	 * @result stack.push
	 * @result nochange
	 */
	@Override
	public String push(String input){
		try{
			assert object!=0;
			int disp_cont = object;
			if(object>0){
		for(int i=0; i<object;i++){
			input= JOptionPane.showInputDialog(null, "Please enter the object to push");
			try{
				String query ="insert into StackData (ObjectID,Object) Values(?,?)";
				PreparedStatement statement = connect.prepareStatement(query);
				statement.setString(1,Integer.toString(i+1));
				statement.setString(2,input);
				statement.execute();
				JOptionPane.showMessageDialog(null,	"The Object is successfully Pushed");
				statement.close();
				lblNewLabel_1 = new JLabel();
				gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = disp_cont;
				panel_display.add(lblNewLabel_1, gbc_lblNewLabel_1);
				lblNewLabel_1.setText("Object["+(i+1)+"] "+input);
				disp_cont--;
				
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
			}

		}
		}
			else
				throw new Exception();
		}
		catch(Exception exc){
			JOptionPane.showMessageDialog(null,"Enter the the objects to push.");
		}
		return input;


	}
	/**
	 * @pre isEmpty()
	 * @post @result pop Stack.Top
	 * @post @nochange
	 */
	@Override
	public String pop(){
			try{
				String num_row = "SELECT COUNT(*) AS rowcount FROM StackData";
				PreparedStatement statement = connect.prepareStatement(num_row);
				ResultSet row = statement.executeQuery();
				int row_num= row.getInt("rowcount");
				if(row_num!=0){
					String query ="delete from StackData where ObjectID=?";
					PreparedStatement statement_delete = connect.prepareStatement(query);
						statement_delete.setString(1,Integer.toString(row_num));
						statement_delete.execute();
						JOptionPane.showMessageDialog(null,	"The Object is successfully Popped");	
						statement_delete.close();
				}
				else
					JOptionPane.showMessageDialog(null, "The Stack is Empty");

				
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null,e);
			}
			return null;
	}
	/**
	 * @pre isEmpty
	 * @result zero
	 * @post stack size
	 * @return stack size
	 */
	@Override
	public void Size(){
		try{
			String num_row = "SELECT COUNT(*) AS rowcount FROM StackData";
			PreparedStatement statement = connect.prepareStatement(num_row);
			ResultSet row = statement.executeQuery();
			row_num= row.getInt("rowcount");
			if(row_num>0)
			JOptionPane.showMessageDialog(null, "The current size of the stack is "+row_num);
			else
				JOptionPane.showMessageDialog(null, "The current size of the stack is 0");
			row.close();
			statement.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
		
	}
	/**
	 * @pre !isEmpty()
	 * @post stack size>0
	 * @return stack.top
	 * @throws SQLException 
	 */
	@Override
	public void Top(){
		try{
			String num_row = "SELECT COUNT(*) AS rowcount FROM StackData";
			PreparedStatement statement = connect.prepareStatement(num_row);
			ResultSet row = statement.executeQuery();
			int row_num= row.getInt("rowcount");
			String query_top= "select Object from StackData where ObjectID=?";
			PreparedStatement statement1 = connect.prepareStatement(query_top);
			statement1.setString(1,Integer.toString(row_num));
			ResultSet RS= statement1.executeQuery();
			JOptionPane.showMessageDialog(null,	"The Object is "+RS.getString(1));
		RS.close();
		statement1.close();
		}
		catch(Exception ese){
			JOptionPane.showMessageDialog(null,ese);
		}
	}
	/**
	 * @pre isEmpty
	 * @post stacksize == 0
	 * @return true
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		assert isEmpty();
		return true;
	}
	/**
	 * Display the elements in the stack
	 * 
	 * @Label stack_objects print "The objects in the Stack are"
	 * @pre !isEmpty
	 * @post Result is printed
	 * @Label Result Objects in the stack
	 * @return 0 if there is no Objects in  the stack
	 */
	@Override
	public void displayStack() {
		// TODO Auto-generated method stub
		
		assert !isEmpty();
		assert Result != null;
		try{
			String[] array=new String[object+1];
			String[] array_result=new String[object];
			ResultSet RS = null;
			String num_row = "SELECT COUNT(*) AS rowcount FROM StackData";
			PreparedStatement statement = connect.prepareStatement(num_row);
			ResultSet row = statement.executeQuery();
			int row_num= row.getInt("rowcount");
			int j=0;
			if(row_num!=0){
			for(int i=row_num;i>0;i--){
				array[i]="select Object from StackData where ObjectID=?";
				PreparedStatement statement1 = connect.prepareStatement(array[i]);
				statement1.setString(1, Integer.toString(i));
				RS= statement1.executeQuery();
				array_result[j]= RS.getString(1);
				j++;
				
			}
			String finalresult = Arrays.deepToString(array_result);
		JOptionPane.showMessageDialog(null,	"The Objects are \n TOP-->"+finalresult.trim());
		stack_objects = new JLabel();

		stack_objects.setText("The objects in the Stack are ");
		Result = new JLabel();
		getContentPane().add(stack_objects);
		stack_objects.setBounds(15, 288+p, 214, 20);

		getContentPane().add(Result);
		Result.setBounds(174, 288+p, 226, 20);

		Result.setText("TOP-->"+finalresult);
		p=p+12;


		}
			else{
				JOptionPane.showMessageDialog(null,"The stack is now Empty.");
				}

		}
		catch(Exception eees){
			JOptionPane.showMessageDialog(null,eees);
		}

	}
	public int getObject() {
		// TODO Auto-generated method stub
		return object;
	}
	public int setObject(int obj) {
		// TODO Auto-generated method stub
		return this.object=object;
	}

}





