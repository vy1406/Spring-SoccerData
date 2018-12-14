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

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.db.PostgreSQL_util;
import com.example.db.SQLite_util;
import com.example.model.Team;
import com.example.util.PostgresReaderWriter;
import com.example.util.SqliteReader;
import com.example.util.XML_ReaderWriter;

@Component
public class TeamDao implements SqliteReader, PostgresReaderWriter, XML_ReaderWriter {
	Connection connection;

	@Override
	public ArrayList<Team> getObjectsFromXML() {
		System.out.println("here");
		ArrayList<Team> teamList = new ArrayList<Team>();
		String fileName = "teams";
		File xmlFile = new File(path + fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("Team");
			for (int i = 0; i < nodeList.getLength(); i++) {
				teamList.add((Team)getObject(nodeList.item(i)));
			}

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}

		return teamList;
	}

	@Override
	public Object getObject(Node node) {
		Team team = new Team();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			team.setId(getTagValue("id", element));
			team.setName(getTagValue("name", element));
			team.setShort_name(getTagValue("short_name", element));

		}

		return team;
	}

	@Override
	public void showObjectsFromXML() {
		String fileName = "teams";
		File xmlFile = new File(path + fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName("Team");
			ArrayList<Team> teamList = new ArrayList<Team>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				teamList.add((Team)getObject(nodeList.item(i)));
			}
			for (Team current : teamList) {
				System.out.println(current.toString());
			}
		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public boolean createObjectXML() {

		ArrayList<Team> arrTeams = new ArrayList<>();
		String fileName = "teams";
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Teams");
			doc.appendChild(rootElement);

			arrTeams = getAll();
			for (Team curTeam : arrTeams) {
				Element countryElem = doc.createElement("Team");
				rootElement.appendChild(countryElem);

				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(curTeam.getId()));
				countryElem.appendChild(id);

				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(curTeam.getName()));
				countryElem.appendChild(name);

				Element short_name = doc.createElement("short_name");
				short_name.appendChild(doc.createTextNode(curTeam.getShort_name()));
				countryElem.appendChild(short_name);
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
	public String getTagValue(String argTag, Element element) {	
		NodeList nodeList = element.getElementsByTagName(argTag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

	@Override
	public void writeObjectsFromSQLiteToPostgreSQL() {
		ArrayList<Team> arrListTeams = getObjectsFromXML();
		try {
			createTableIfNotExist("team");
			for (Team curTeam: arrListTeams) {
				writeSingleObjectToPostgres(curTeam, "team");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String dropDaoTable() {
		return dropTable("team");
	}

	@Override
	public String createDaoTable() {
		Connection con = PostgreSQL_util.getConnection();
		String message = "";
		try {
			String sql2 = "CREATE TABLE team ( id SERIAL PRIMARY KEY ,  name TEXT, short_name TEXT )";
			PreparedStatement stmt = con.prepareStatement(sql2);
			stmt.executeUpdate();
			message += "team table was created ";
			return message;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "team wasnt created, see stacktrace";
	}

	@Override
	public void writeSingleObjectToPostgres(Object argObject, String argTable) throws SQLException {

		Team argTeam = (Team)argObject;
		Connection conn = PostgreSQL_util.getConnection();
		String sql = "INSERT INTO " + argTable + " (id, name, short_name) VALUES (?, ?, ? ) ";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(argTeam.getId()));
		st.setString(2, argTeam.getName());
		st.setString(3, argTeam.getShort_name());
		st.executeUpdate();
		st.close();

	}

	@Override
	public ArrayList<Team> getAll() {

		connection = SQLite_util.getConnection();
		ArrayList<Team> teams = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from Team");
			while (rs.next()) {
				Team curTeam = new Team();
				curTeam.setId(rs.getString("id"));
				curTeam.setName(rs.getString("team_long_name"));
				curTeam.setShort_name(rs.getString("team_short_name"));
				teams.add(curTeam);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teams;
	}

	@Override
	public void showAll() {
		ArrayList<Team> teams = this.getAll();
		for (int i = 0; i < teams.size(); i++) {
			Team curTeam = teams.get(i);
			System.out.println(curTeam);
		}

	}

	@Override
	public void closeConnection()  {
		try {
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
