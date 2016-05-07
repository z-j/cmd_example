package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Server;

public class XMLParser implements Parser {

	static Logger log = Logger.getLogger(XMLParser.class.getName());

	public List<Server> parse(String fileName) throws FileNotFoundException, Exception {

		List<Server> servers = new ArrayList<Server>();
		
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputStream is = new FileInputStream(new File(fileName));
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();

			
			log.info("Root element :" + doc.getDocumentElement().getNodeName());
			
			NodeList n = doc.getElementsByTagName("server");
			
			log.info(n.getLength());
			
			
			for(int index=0; index < n.getLength(); index++) {
				
				if (n.item(index).getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) n.item(index);
					
					String id =  eElement.getElementsByTagName("id").item(0).getTextContent();
					String name = eElement.getElementsByTagName("name").item(0).getTextContent();
					log.info(id+","+name);
					servers.add(new Server(id, name));
				}
				
			}
			
			return servers;
			
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	
	

}
