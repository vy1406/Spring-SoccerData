package com.example.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.db.PostgreSQL_util;
import com.example.db.SQLite_util;
import com.example.model.League;
import com.example.util.PostgresReaderWriter;
import com.example.util.SqliteReader;
import com.example.util.XML_ReaderWriter;



public class LeagueDao implements SqliteReader, PostgresReaderWriter, XML_ReaderWriter{

	Connection connection = null;
	
	@Override
	public ArrayList<League> getAll() {


		connection = PostgreSQL_util.getConnection();
		ArrayList<League> leagues = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from League");
			while (rs.next()) {
				League current = new League();
				current.setId(rs.getString("id"));
				current.setName(rs.getString("name"));
				current.setCountry_id(rs.getString("country_id"));
				leagues.add(current);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return leagues;
	}


	@Override
	public void showAll() {
		ArrayList<League> leagues = this.getAll();
		for (int i = 0; i < leagues.size(); i++) {
			League league = leagues.get(i);
			System.out.println(league);
		}
	}
	
	@Override
	public void writeObjectsFromSQLiteToPostgreSQL() {
		ArrayList<League> arrListLeagues = getObjectsFromXML();
		try {
			createTableIfNotExist("league");
			for (League curLeague : arrListLeagues) {
				writeSingleObjectToPostgres(curLeague, "league");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public String dropDaoTable() {
		return dropTable("league");
	}


	@Override
	public String createDaoTable() {
		Connection con = PostgreSQL_util.getConnection();
		String message = "";
		try { 
			String sql1 = "CREATE TABLE league ( id SERIAL PRIMARY KEY , country_id SERIAL, "
					+ "name TEXT UNIQUE, FOREIGN KEY(country_id) REFERENCES country(id))";
			
			PreparedStatement stmt = con.prepareStatement(sql1);
			stmt.executeUpdate();
			message += "league table was created ";
			return message;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "league wasnt created, see stacktrace";
	}


	@Override
	public void writeSingleObjectToPostgres(Object argObj, String argTable) throws SQLException {
		League argLeague = (League)argObj;
		Connection conn = PostgreSQL_util.getConnection();
		String sql = "INSERT INTO " + argTable + " (id, country_id,  name) VALUES (?, ?, ?) ";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(argLeague.getId()));		
		st.setInt(2, Integer.parseInt(argLeague.getCountry_id()));
		st.setString(3, argLeague.getName());
		st.executeUpdate();
		st.close();
		
	}


	@Override
	public void closeConnection() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public ArrayList<League> getObjectsFromXML() {
		ArrayList<League> leagueList = new ArrayList<League>();
		String fileName = "leagues";
		File xmlFile = new File(path + fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("League");
			for (int i = 0; i < nodeList.getLength(); i++) {
				leagueList.add((League) getObject(nodeList.item(i)));
			}

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}

		return leagueList;
	}


	@Override
	public Object getObject(Node node) {
		League league = new League();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			league.setId(getTagValue("id", element));
			league.setCountry_id(getTagValue("country_id", element));
			league.setName(getTagValue("name", element));			
		}

		return league;
	}


	@Override
	public void showObjectsFromXML() {

		String fileName = "leagues";
		File xmlFile = new File(path + fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName("League");
			ArrayList<League> leagueList = new ArrayList<League>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				leagueList.add((League)getObject(nodeList.item(i)));
			}
			for (League current : leagueList) {
				System.out.println(current.toString());
			}
		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}
		
	}


	@Override
	public boolean createObjectXML() {

		String fileName = "leagues";
		ArrayList<League> arrLeagues = new ArrayList<>();
		LeagueDao leagueDao = new LeagueDao();
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Leagues");
			doc.appendChild(rootElement);

			arrLeagues = leagueDao.getAll();
			for (League curLeague : arrLeagues) {
				Element leagueElem = doc.createElement("League");
				rootElement.appendChild(leagueElem);

				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(curLeague.getId()));
				leagueElem.appendChild(id);

				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(curLeague.getName()));
				leagueElem.appendChild(name);

				Element country_id = doc.createElement("country_id");
				country_id.appendChild(doc.createTextNode(curLeague.getCountry_id()));
				leagueElem.appendChild(country_id);
			}

			// ----------------------------------
			// Save the file
			// ----------------------------------
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult streamResult = new StreamResult(new File(path + fileName));
			transformer.transform(source, streamResult);
			return true;

		} catch (Exception e) {
			System.out.println("Exception in createXML:");
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public String getTagValue(String argTag, Element element)  {
		NodeList nodeList = element.getElementsByTagName(argTag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}






	
}
