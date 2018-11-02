package com.example.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
import com.example.model.Game;
import com.example.model.Match;
import com.example.util.PostgresReaderWriter;
import com.example.util.SqliteReader;
import com.example.util.XML_ReaderWriter;

public class MatchDao implements PostgresReaderWriter, SqliteReader, XML_ReaderWriter {
	Connection connection;

	// while looping thru matches - remembering the seasons.
	public ArrayList<String> seasonList = new ArrayList<>();
	
	public ArrayList<String> getSeasonList(){
		return seasonList;
	}
	
	public ArrayList<Game> getGamesByLeagueId(String argLeague_id){
		connection = PostgreSQL_util.getConnection();
		ArrayList<Game> games = new ArrayList<>(); 
		
		int tempInt;
		String tempString;
		String sql = "SELECT  m.id, m.match_api_id, m.country_id, m.league_id, m.season, m.date, \r\n" + 
				"	m.home_team_api_id,  m.away_team_api_id, m.home_team_goal, \r\n" + 
				"	m.away_team_goal, m.stage,\r\n" + 
				"       	t1.name as home_team_long_name, t1.short_name as home_team_short_name, \r\n" + 
				"	t2.name as away_team_long_name, t2.short_name as away_team_short_name\r\n" + 
				"  FROM match AS m\r\n" + 
				"    JOIN team AS t1 ON m.home_team_api_id = t1.team_api_id\r\n" + 
				"	JOIN team AS t2 ON m.away_team_api_id = t2.team_api_id\r\n" + 
				"	where m.league_id = '" + argLeague_id + "'";
		Date date;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
//			ResultSet rs = statement.executeQuery("select * "
//					+ "from match as m "
//					+ " join team as t1 on m.home_team_api_id = t1.team_api_id "
//					+ "	join team as t2 on m.away_team_api_id = t2.team_api_id "
//					+ "										");
			while (rs.next()) {
				Game curGame = new Game();
				curGame.setId(rs.getInt("id"));
				tempInt = Integer.parseInt(rs.getString("match_api_id"));
				curGame.setMatch_api_id(tempInt);
				curGame.setCountry_id(rs.getString("country_id"));
				curGame.setLeague_id(rs.getString("league_id"));
				curGame.setSeason(rs.getString("season"));
				this.seasonList.add(rs.getString("season"));
				tempInt = Integer.parseInt(rs.getString("home_team_api_id"));
				curGame.setHome_team_api_id(tempInt);
				tempInt = Integer.parseInt(rs.getString("away_team_api_id"));
				curGame.setAway_team_api_id(tempInt);
				curGame.setHome_goal(rs.getString("home_team_goal"));
				curGame.setAway_goal(rs.getString("away_team_goal"));
				curGame.setStage(rs.getString("stage"));
				String s = rs.getString("date");
				date = convertToDate(s);
				curGame.setDate(date);
				
				curGame.setHome_team_name(rs.getString("home_team_long_name"));
				curGame.setAway_team_name(rs.getString("away_team_long_name"));
				curGame.setHome_team_short_name(rs.getString("home_team_short_name"));
				curGame.setAway_team_short_name(rs.getString("away_team_short_name"));
				games.add(curGame);
			}

		} catch (ParseException | SQLException e) {
			e.printStackTrace();
		}
	
		return games;
	}
	@Override
	public ArrayList<Match> getObjectsFromXML() {
		ArrayList<Match> list = new ArrayList<Match>();
		String fileName = "matches";
		File xmlFile = new File(path + fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("Match");
			for (int i = 0; i < nodeList.getLength(); i++) {
				list.add((Match)getObject(nodeList.item(i)));
			}

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}

		return list;
	}

	@Override
	public Object getObject(Node node)  {
		Match match = new Match();
		String s;
		int tempInt;
		Date date;
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			
			Element element = (Element) node;
			match.setId(getTagValue("id", element));
			tempInt = Integer.parseInt(getTagValue("match_api_id", element));
			match.setMatch_api_id(tempInt);
			match.setCountry_id(getTagValue("country_id", element));
			match.setLeague_id(getTagValue("league_id", element));		
			match.setSeason(getTagValue("season", element));
			match.setStage(getTagValue("stage", element));	
			tempInt = Integer.parseInt(getTagValue("home_team_api_id", element));
			match.setHome_team_api_id(tempInt);
			tempInt = Integer.parseInt(getTagValue("away_team_api_id", element));
			match.setAway_team_api_id(tempInt);
			match.setHome_goal(getTagValue("home_goal", element));
			match.setAway_goal(getTagValue("away_goal", element));
			
			s = getTagValue("date", element);
			try {
				date = convertToDate(s);
				match.setDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}

		return match;
	}

	@Override
	public void showObjectsFromXML() {
		String fileName = "matches";
		File xmlFile = new File(path + fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName("Match");
			ArrayList<Match> list = new ArrayList<Match>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				list.add((Match) getObject(nodeList.item(i)));
			}
			for (Match current : list) {
				System.out.println(current.toString());
			}
		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public boolean createObjectXML() {

		ArrayList<Match> arrMatches = new ArrayList<>();
		String fileName = "matches";
		String tempParsedString;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Matches");
			doc.appendChild(rootElement);

			arrMatches = getAll();
			Date matchDate; 
			for (Match curMatch : arrMatches) {
				Element countryElem = doc.createElement("Match");
				rootElement.appendChild(countryElem);

				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(curMatch.getId()));
				countryElem.appendChild(id);
				
				Element match_api_id = doc.createElement("match_api_id");
				tempParsedString = curMatch.getMatch_api_id() +"";
				match_api_id.appendChild(doc.createTextNode(tempParsedString));
				countryElem.appendChild(match_api_id);

				Element country_id = doc.createElement("country_id");
				country_id.appendChild(doc.createTextNode(curMatch.getCountry_id()));
				countryElem.appendChild(country_id);

				Element league_id = doc.createElement("league_id");
				league_id.appendChild(doc.createTextNode(curMatch.getLeague_id()));
				countryElem.appendChild(league_id);

				Element season = doc.createElement("season");
				season.appendChild(doc.createTextNode(curMatch.getSeason()));
				countryElem.appendChild(season);
								
				Element home_team_api_id = doc.createElement("home_team_api_id");
				tempParsedString = curMatch.getHome_team_api_id() +"";
				home_team_api_id.appendChild(doc.createTextNode(tempParsedString));
				countryElem.appendChild(home_team_api_id);
				
				Element away_team_api_id = doc.createElement("away_team_api_id");
				tempParsedString = curMatch.getAway_team_api_id() +"";
				away_team_api_id.appendChild(doc.createTextNode(tempParsedString));
				countryElem.appendChild(away_team_api_id);
				
				Element home_goal = doc.createElement("home_goal");
				home_goal.appendChild(doc.createTextNode(curMatch.getHome_goal()));
				countryElem.appendChild(home_goal);
				
				Element away_goal = doc.createElement("away_goal");
				away_goal.appendChild(doc.createTextNode(curMatch.getAway_goal()));
				countryElem.appendChild(away_goal);
				
				Element stage = doc.createElement("stage");
				stage.appendChild(doc.createTextNode(curMatch.getStage()));
				countryElem.appendChild(stage);
				
				Element date = doc.createElement("date");
				matchDate = curMatch.getDate();
				date.appendChild(doc.createTextNode(makeDateString(matchDate)));
				countryElem.appendChild(date);

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

	private static String makeDateString(Date argDate) {
		String s = "";
		int year = argDate.getYear() + 1900;
		s += year + "-" + argDate.getMonth() + "-" +argDate.getDate();	
		return s;

	}

	@Override
	public String getTagValue(String argTag, Element element) {
		
		NodeList nodeList = element.getElementsByTagName(argTag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

	@Override
	public ArrayList<Match> getAll() {

		connection = SQLite_util.getConnection();
		ArrayList<Match> matches = new ArrayList<>();
		int tempInt;
		Date date;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from Match");
			while (rs.next()) {
				Match curMatch = new Match();
				curMatch.setId(rs.getString("id"));
				tempInt = Integer.parseInt(rs.getString("match_api_id"));
				curMatch.setMatch_api_id(tempInt);
				curMatch.setCountry_id(rs.getString("country_id"));
				curMatch.setLeague_id(rs.getString("league_id"));
				curMatch.setSeason(rs.getString("season"));
				tempInt = Integer.parseInt(rs.getString("home_team_api_id"));
				curMatch.setHome_team_api_id(tempInt);
				tempInt = Integer.parseInt(rs.getString("away_team_api_id"));
				curMatch.setAway_team_api_id(tempInt);
				curMatch.setHome_goal(rs.getString("home_team_goal"));
				curMatch.setAway_goal(rs.getString("away_team_goal"));
				curMatch.setStage(rs.getString("stage"));
				String s = rs.getString("date");
				date = convertToDate(s);
				curMatch.setDate(date);
				matches.add(curMatch);
			}

		} catch (ParseException | SQLException e) {
			e.printStackTrace();
		}
		return matches;
	}

	private Date convertToDate(String receivedDate) throws ParseException {
		DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		Date date = df.parse(receivedDate);
		return date;
	}

	@Override
	public void showAll() {
		ArrayList<Match> matches = this.getAll();
		for (int i = 0; i < matches.size(); i++) {
			Match curMatch = matches.get(i);
			System.out.println(curMatch);
		}
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
	public void writeObjectsFromSQLiteToPostgreSQL() {
		
		ArrayList<Match> arrList = getObjectsFromXML();
		
		try {
			createTableIfNotExist("match");
			for (Match curMatch : arrList) {
				writeSingleObjectToPostgres(curMatch, "match");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String dropDaoTable() {
		return dropTable("match");
	}

	@Override
	public String createDaoTable() {

		Connection con = PostgreSQL_util.getConnection();
		String message = "";
		try {
			String sql2 = " CREATE TABLE match ( id SERIAL PRIMARY KEY  , match_api_id integer, country_id text , league_id text , "
					+ "season text , date text , home_team_api_id integer, away_team_api_id integer,"
					+ " home_team_goal text, away_team_goal text, stage text ) ";
			PreparedStatement stmt = con.prepareStatement(sql2);
			stmt.executeUpdate();
			message += "match table was created ";
			return message;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "match wasnt created, see stacktrace";
	}

	@Override
	public void writeSingleObjectToPostgres(Object argObject, String argTable) throws SQLException {

		Match match = (Match) argObject;
		Connection conn = PostgreSQL_util.getConnection();
		String sql = "INSERT INTO " + argTable + " (id, match_api_id, country_id, league_id, season, date, home_team_api_id, away_team_api_id, home_team_goal, away_team_goal, stage) VALUES (?, ?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, Integer.parseInt(match.getId()));
		st.setInt(2, match.getMatch_api_id());
		st.setString(3, match.getCountry_id());
		st.setString(4, match.getLeague_id());
		st.setString(5, match.getSeason());
		st.setString(6, match.getDate().toString());
		st.setInt(7, match.getHome_team_api_id());
		st.setInt(8, match.getAway_team_api_id());
		st.setString(9, match.getHome_goal());
		st.setString(10, match.getAway_goal());
		st.setString(11, match.getStage());
		
		st.executeUpdate();
		st.close();
	}

}
