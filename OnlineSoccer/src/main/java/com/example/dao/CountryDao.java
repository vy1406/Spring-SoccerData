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
import com.example.model.Country;
import com.example.util.PostgresReaderWriter;
import com.example.util.SqliteReader;
import com.example.util.XML_ReaderWriter;



public class CountryDao implements SqliteReader, PostgresReaderWriter, XML_ReaderWriter {

	Connection connection;
	String fileName;
	// ---------------------------------
	// SQLreader Interface
	// returns arraylist of countries taken from sqlite db file
	// ---------------------------------
	@Override
	public ArrayList<Country> getAll() {

		connection = SQLite_util.getConnection();
		ArrayList<Country> countries = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from Country");
			while (rs.next()) {
				Country curCountry = new Country();
				curCountry.setId(rs.getString("id"));
				curCountry.setName(rs.getString("name"));

				countries.add(curCountry);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return countries;
	}

	// ---------------------------------
	// SQLreader Interface
	// prints all countries
	// ---------------------------------
	@Override
	public void showAll() {
		ArrayList<Country> countries = this.getAll();
		for (int i = 0; i < countries.size(); i++) {
			Country curCountry = countries.get(i);
			System.out.println(curCountry);
		}
	}

	// ---------------------------------
	// SQLreader Interface
	// public con.close();
	// ---------------------------------
	@Override
	public void closeConnection() {
		try {
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------
	// PostgresReaderWriter INTERFACE
	// write countries from xml to postgres
	// ------------------------------------
	public void writeObjectsFromSQLiteToPostgreSQL() {
		ArrayList<Country> arrListCountries = getObjectsFromXML();
		try {
			createTableIfNotExist("country");
			for (Country curCountry : arrListCountries) {
				writeSingleObjectToPostgres(curCountry, "country");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------
	// PostgresReaderWriter INTERFACE
	// dropTable - interface default
	// ---------------------------------
	public String dropDaoTable() {
		return dropTable("country");
	}

	// ---------------------------------
	// PostgresReaderWriter INTERFACE
	// copied from sqlite to postgres - watch out - the syntax is not the same
	// sometimes.
	// ---------------------------------
	public String createDaoTable() {
		
		Connection con = PostgreSQL_util.getConnection();
		String message = "";
		try {
			String sql2 = " CREATE TABLE country ( id SERIAL PRIMARY KEY  , name text UNIQUE ) ";
			PreparedStatement stmt = con.prepareStatement(sql2);
			stmt.executeUpdate();
			message += "country table was created ";
			return message;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "country wasnt created, see stacktrace";
	}

	// ----------------------------------
	// PostgresReaderWriter INTERFACE
	// FOR INSIDE USE
	// help function - easy reading
	// for writeCountriesFromSQLiteToPostgreSQL()
	// ----------------------------------
	public void writeSingleObjectToPostgres(Object argObj, String argTable) throws SQLException {
		
		Country argCountry = (Country)argObj;
		Connection conn = PostgreSQL_util.getConnection();
		String sql = "INSERT INTO " + argTable + " (id, Name) VALUES (?, ?) ";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, Integer.parseInt(argCountry.getId()));
		st.setString(2, argCountry.getName());
		st.executeUpdate();
		st.close();
	}
	
	// ------------------------------------
	//// XML_ReaderWriter INTERFACE
	// getting the objects to array list from xml file
	// ------------------------------------
	@Override
	public ArrayList<Country> getObjectsFromXML() {
		ArrayList<Country> countryList = new ArrayList<Country>();
		String fileName = "countries";
		File xmlFile = new File(path + fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("Country");
			for (int i = 0; i < nodeList.getLength(); i++) {
				countryList.add((Country) getObject(nodeList.item(i)));
			}

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}

		return countryList;
	}

	// ---------------------------------------
	// XML_ReaderWriter INTERFACE
	// FOR INSIDE USE
	// get single object - for readability
	// ---------------------------------------
	@Override
	public Object getObject(Node node) {
		Country country = new Country();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			country.setId(getTagValue("id", element));
			country.setName(getTagValue("name", element));
		}

		return country;
	}

	// ---------------------------------------
	// XML_ReaderWriter INTERFACE
	// helping function. for readability
	// ---------------------------------------
	
	@Override
	public String getTagValue(String argTag, Element element) {
		NodeList nodeList = element.getElementsByTagName(argTag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}
	// ---------------------------------------
	// XML_ReaderWriter INTERFACE
	// show... 
	// ---------------------------------------
	
	@Override
	public void showObjectsFromXML() {
		String fileName = "countries";
		File xmlFile = new File(path + fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName("Country");
			ArrayList<Country> countryList = new ArrayList<Country>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				countryList.add((Country) getObject(nodeList.item(i)));
			}
			for (Country current : countryList) {
				System.out.println(current.toString());
			}
		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}
	}
	
	// ---------------------------------------
	// XML_ReaderWriter INTERFACE
	// creating an xml file in the local disc. ( path is from XML_ReaderWriter interface
	// ---------------------------------------	
	@Override
	public boolean createObjectXML() {
		ArrayList<Country> arrCountries = new ArrayList<>();
		String fileName = "countries";
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Countries");
			doc.appendChild(rootElement);

			arrCountries = getAll();
			for (Country curCountry : arrCountries) {
				Element countryElem = doc.createElement("Country");
				rootElement.appendChild(countryElem);

				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(curCountry.getId()));
				countryElem.appendChild(id);

				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(curCountry.getName()));
				countryElem.appendChild(name);
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

	

}
