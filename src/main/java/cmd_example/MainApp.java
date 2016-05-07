package cmd_example;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import model.Server;
import utility.Constants;

/**
 * Hello world!
 *
 */
public class MainApp 
{
	static Logger log = Logger.getLogger(MainApp.class.getName());


	@Command(description="read servers data from file and save to database")
	public void readServers (@Param(name="file name") String fileName) {

		ShellAPI sp = new ShellAPI();
		int code = sp.readAndSaveToDatabase(fileName);
		System.out.println(Constants.getMessage(code));
	}

	@Command(description="count persisted servers")
	public void countServers () {

		ShellAPI sp = new ShellAPI();
		try {
			System.out.println("Total Servers are: "+sp.countServers());
		} catch(SQLException se) {
			System.out.println("Database exception. Please try later !!!");
		}
	}

	@Command(description="get all servers")
	public void getServers () {

		ShellAPI sp = new ShellAPI();
		try {
			List<Server> servers = sp.getAllServers();
			if(servers.size() > 0) {
				System.out.println("Id\tName");
				for(Server s : servers) {
					System.out.println(s.getId()+"\t"+s.getName());
				}
			} else {
				System.out.println("No Servers found");
			}
		} catch(SQLException se) {
			System.out.println("Database exception. Please try later !!!");
		}
	}

	@Command(description="Delete server with given id")
	public void deleteServer (@Param(name="server id") String id) {

		ShellAPI sp = new ShellAPI();
		try {
			int code = sp.deleteServer(id);
			System.out.println(Constants.getMessage(code));
		} catch(SQLException se) {
			System.out.println("Database exception. Please try later !!!");
		}
	}

	@Command(description="update server name for given id")
	public void updateServer (@Param(name="server id") String id, @Param(name="updated name") String name) {

		ShellAPI sp = new ShellAPI();
		try {
			int code = sp.updateServerName(id, name);
			System.out.println(Constants.getMessage(code));
		} catch(SQLException se) {
			System.out.println("Database exception. Please try later !!!");
		}
	}

	public static void main(String[] args) throws Exception
	{
		log.info("coming into this function");

		MainApp main = new MainApp();
		Shell shell = ShellFactory.createConsoleShell("$", "Persist Server Console - ?list to see all commands (abbrev is command)", main);
		shell.commandLoop();

	} 
}
