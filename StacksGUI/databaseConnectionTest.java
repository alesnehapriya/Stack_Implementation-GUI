/**
 * 
 */
package StacksGUI;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

/**
 * @author aelay
 *
 */
public class databaseConnectionTest {

	/**
	 * Test method for {@link StacksGUI.databaseConnection#connector()}.
	 * @throws SQLException 
	 */
	@Test
	public final void testConnector() throws SQLException {
		Connection connect=null;
		connect = databaseConnection.connector();
		assertTrue("True",connect!=null);
	}

}
