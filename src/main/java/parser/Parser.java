package parser;

import java.util.List;

import model.Server;

public interface Parser {
	
	public List<Server> parse(String fileName) throws Exception;

}
