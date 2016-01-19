package StacksGUI;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.Test;

/**
 * @author aelay
 *
 */
public class Stacks_GUITest {


	/**
	 * Test method for {@link StacksGUI.Stacks_GUI#push()}.
	 */
	@Test
	public void testPush() {
		// TODO
		Stacks_GUI str = new Stacks_GUI();
		String expected_result= "3456";
		String actual_result= str.push("3456");
		assertEquals(expected_result,actual_result);
	}


	/**
	 * Test method for {@link StacksGUI.Stacks_GUI#pop()}.
	 */
	@Test
	public void testPop() {
		Stacks_GUI str = new Stacks_GUI();// TODO
		String expected_result=null;
		str.push("3456");
		String actual_result= str.pop();
		assertEquals(expected_result,actual_result);
	}



	/**
	 * Test method for {@link StacksGUI.Stacks_GUI#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		Stacks_GUI str = new Stacks_GUI();
		assertTrue("True",str.isEmpty());// TODO
	}

	/**
	 * Test method for {@link StacksGUI.Stacks_GUI#getObject()}.
	 */
	@Test
	public final void testGetObject() {
		 // TODO
		Stacks_GUI str = new Stacks_GUI();
		str.getObject();
		assertTrue(str.isEmpty());
	}

	/**
	 * Test method for {@link StacksGUI.Stacks_GUI#setObject(int)}.
	 */
	@Test
	public final void testSetObject() {
		Stacks_GUI str = new Stacks_GUI();
		str.setObject(0);// TODO
		assertTrue(str.isEmpty());
	}

}
