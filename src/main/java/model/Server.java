package model;

public class Server {

	private String id;
	private String name;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Server() {
	}
	public Server(String id, String name) {
		this.id = id; 
		this.name = name;
	}
	
	public String toString() {
		return this.id + "," + this.name;
	}
	
	public boolean isDataNull() {
		
		if(this.getId() != null && this.getId().length() > 0 &&
				this.getName() != null && this.getName().length() >0 ) {
			
			return false;
		}
		return true;
		
	}
}
