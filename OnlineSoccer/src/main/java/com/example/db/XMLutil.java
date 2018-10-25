package com.example.db;

import java.io.File;
import java.io.IOException;
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

import com.example.dao.CountryDao;
import com.example.dao.LeagueDao;
import com.example.model.Country;
import com.example.model.League;


public class XMLutil {

	public static String path = "C:\\Users\\USER\\MyDocs\\AfterStudy\\Java\\uDemyProjects\\FILES\\SoccerData\\";
	public static String fileName = "xmlTest";


	public static ArrayList<Country> getCountriesFromXML() {
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
				countryList.add(getCountry(nodeList.item(i)));
			}

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}
		
		return countryList;
	}

	public static void showCountriesFromXML() {
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
				countryList.add(getCountry(nodeList.item(i)));
			}
			for (Country curCountry : countryList) {
				System.out.println(curCountry.toString());
			}
		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Country getCountry( Node node) {
		
		Country country = new Country();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			country.setId(getTagValue("id", element));
			country.setName(getTagValue("name", element));
		}

		return country;
	}

	public static String getTagValue(String argTag, Element element) {
		NodeList nodeList = element.getElementsByTagName(argTag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

	public static boolean createLeagueXML() {
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

	// returns true if everything was ok
	public static boolean createCountryXML() {
		ArrayList<Country> arrCountries = new ArrayList<>();
		String fileName = "countries";
		CountryDao countryDao = new CountryDao();
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Countries");
			doc.appendChild(rootElement);

			arrCountries = countryDao.getAll();
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
