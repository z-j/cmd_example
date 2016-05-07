package cmd_example;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dal.ServerDAL;
import model.Server;
import parser.Parser;
import parser.XMLParser;
import utility.Constants;
import utility.MySqlConnection;


public class ServerTest
{
	static Logger log = Logger.getLogger(ServerTest.class.getName());
	
	
	@Test
	public void testParser()
	{
		try{
			log.info("test Parser");
			Parser parser = new XMLParser();
			List<Server> servers = parser.parse("server_test.xml");

			Assert.assertEquals(1, servers.size());
			Assert.assertEquals(servers.get(0).getId(), "testId");
			Assert.assertEquals(servers.get(0).getName(), "testName");
			
		} catch(Exception e) {
			log.error(e.getMessage(), e);
			Assert.fail();
		}
	}

	@Test
	public void testReadAndSaveDatabase()
	{
		MySqlConnection.setDbUrl(Constants.dbUrl_test);
		
		try{
			ShellAPI shellApi = new ShellAPI();
			
			int error_code = shellApi.readAndSaveToDatabase("file_not_found.xml");
			Assert.assertEquals(error_code, Constants.ERROR_CODE_FILE_NOT_FOUND);
			
			error_code = shellApi.readAndSaveToDatabase("server_test_no_data.xml");
			Assert.assertEquals(error_code, Constants.ERROR_CODE_SERVER_DATA_NULL);
			
			error_code = shellApi.readAndSaveToDatabase("server_test.xml");
			Assert.assertEquals(error_code, Constants.MSG_CODE_SERVER_SAVED);
			
			error_code = shellApi.readAndSaveToDatabase("server_test.xml");
			Assert.assertEquals(error_code, Constants.ERROR_CODE_DUPLICATE_SERVER);
			
		} catch(Exception e) {
			log.error(e.getMessage(), e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllServer()
	{
		MySqlConnection.setDbUrl(Constants.dbUrl_test);
		
		try{
			ShellAPI shellApi = new ShellAPI();
			Assert.assertEquals(0, shellApi.getAllServers().size());
			
			shellApi.readAndSaveToDatabase("server_test.xml");
			Assert.assertEquals(1, shellApi.getAllServers().size());
			Assert.assertEquals("testId", shellApi.getAllServers().get(0).getId());
			Assert.assertEquals("testName", shellApi.getAllServers().get(0).getName());
			
			shellApi.readAndSaveToDatabase("server_test_1.xml");
			Assert.assertEquals(2, shellApi.getAllServers().size());
		
		} catch(Exception e) {
			log.error(e.getMessage(), e);
			Assert.fail();
		}
	}


	@Test
	public void testUpdateServerName()
	{
		MySqlConnection.setDbUrl(Constants.dbUrl_test);
		
		try{
			ShellAPI shellApi = new ShellAPI();
			shellApi.readAndSaveToDatabase("server_test.xml");
			Assert.assertEquals(1, shellApi.getAllServers().size());
			Assert.assertEquals("testId", shellApi.getAllServers().get(0).getId());
			Assert.assertEquals("testName", shellApi.getAllServers().get(0).getName());
			
			shellApi.updateServerName("testId", "newTestName");
			Assert.assertEquals(1, shellApi.getAllServers().size());
			Assert.assertEquals("testId", shellApi.getAllServers().get(0).getId());
			Assert.assertEquals("newTestName", shellApi.getAllServers().get(0).getName());
			
		} catch(Exception e) {
			log.error(e.getMessage(), e);
			Assert.fail();
		}
	}

	@Before
	public void setup() throws Exception {
	}
	
	@After
	public void teardown() throws Exception {
		ServerDAL serverDal = new ServerDAL();
		serverDal.deleteAllServerData();
		
	}
	
}