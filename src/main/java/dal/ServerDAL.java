package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.Server;
import utility.MySqlConnection;

public class ServerDAL {

	static Logger log = Logger.getLogger(ServerDAL.class.getName());
	
	public void saveServer(Server server) throws SQLException {
		
		try(Connection conn = MySqlConnection.getConnection()) {
			
			String sql = "insert into server (id, name) values (?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, server.getId());
			ps.setString(2,  server.getName());
			ps.executeUpdate();
			
		} catch(SQLException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public int updateServer(Server server) throws SQLException {
		
		try(Connection conn = MySqlConnection.getConnection()) {
			
			String sql = "update server set name = ? where id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, server.getName());
			ps.setString(2,  server.getId());
			int effectedRows = ps.executeUpdate();
			return effectedRows;
		} catch(SQLException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	
	public List<Server> getAllServers() throws SQLException {
		
		List<Server> servers = new ArrayList<Server>();
		
		try(Connection conn = MySqlConnection.getConnection()) {
			
			String sql = "select * from server;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				servers.add(new Server(rs.getString("id"), rs.getString("name")));
			}
			return servers;
		} catch(SQLException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public int deleteServer(String id) throws SQLException {
		
		try(Connection conn = MySqlConnection.getConnection()) {
			
			String sql = "delete from server where id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			int effectedRow = ps.executeUpdate();
			log.info("effected number of row is:"+effectedRow);
			return effectedRow;
		} catch(SQLException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public int deleteAllServerData() throws SQLException {
		
		try(Connection conn = MySqlConnection.getConnection()) {
			
			String sql = "delete from server;";
			PreparedStatement ps = conn.prepareStatement(sql);
			int effectedRow = ps.executeUpdate();
			log.info("effected number of row is:"+effectedRow);
			return effectedRow;
		} catch(SQLException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	
}
