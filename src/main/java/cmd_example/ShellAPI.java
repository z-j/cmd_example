package cmd_example;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.log4j.Logger;

import dal.ServerDAL;
import model.Server;
import parser.Parser;
import parser.XMLParser;
import utility.Constants;

public class ShellAPI {

	static Logger log = Logger.getLogger(ShellAPI.class.getName());

	public int readAndSaveToDatabase(String fileName) {

		List<Server> servers = null;
		ServerDAL sd = new ServerDAL();
		Parser p = new XMLParser();

		try {
			servers = p.parse(fileName);
		} catch(FileNotFoundException e) {
			return Constants.ERROR_CODE_FILE_NOT_FOUND;
		} catch(Exception e) {
			return Constants.ERROR_CODE_PARSING_ERROR;
		}

		if(servers != null && servers.size() == 1) {
			Server serv = servers.get(0);

			if(!(serv.isDataNull())) {
				try{
					sd.saveServer(serv);
					return Constants.MSG_CODE_SERVER_SAVED;
				} catch(SQLException e) {
					if(e instanceof SQLIntegrityConstraintViolationException) {
						return Constants.ERROR_CODE_DUPLICATE_SERVER;
					}
					return Constants.ERROR_CODE_DATABASE_ERROR;
				}
			} else {
				return Constants.ERROR_CODE_SERVER_DATA_NULL;
			}
		} else {
			return Constants.ERROR_CODE_NO_SERVER_IN_FILE;
		}
	}

	public int countServers() throws SQLException {
		ServerDAL sd = new ServerDAL();
		List<Server> servers = sd.getAllServers();
		log.info("servers size:"+servers.size());
		return servers.size();
	}

	public List<Server> getAllServers() throws SQLException {
		ServerDAL sd = new ServerDAL();
		List<Server> servers = sd.getAllServers();
		log.info("servers size:"+servers.size());
		return servers;
	}

	public int deleteServer(String id) throws SQLException {
		ServerDAL sd = new ServerDAL();
		int effectedRows = sd.deleteServer(id);
		log.info("effectedRows:"+effectedRows);

		if(effectedRows == 1) {
			return Constants.MSG_CODE_SERVER_DELETED;
		} else {
			return Constants.MSG_CODE_SERVER_NOT_DELETED;
		}
	}

	public int updateServerName(String id, String name) throws SQLException {

		ServerDAL sd = new ServerDAL();
		Server serv = new Server(id, name);

		if(!(serv.isDataNull())) {
			int effectedRows = sd.updateServer(serv);
			if(effectedRows == 1) {
				return Constants.MSG_CODE_SERVER_UPDATED;
			} else {
				return Constants.ERROR_CODE_SERVER_NOT_UPDATED;
			}
		} else {
			return Constants.ERROR_CODE_SERVER_DATA_NULL;
		}

	}

}
