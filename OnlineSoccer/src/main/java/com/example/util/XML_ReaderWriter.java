package com.example.util;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public interface XML_ReaderWriter {

	String path = "C:\\Users\\USER\\MyDocs\\AfterStudy\\Java\\uDemyProjects\\FILES\\SoccerData\\";

	ArrayList<?> getObjectsFromXML();

	Object getObject(Node node);

	void showObjectsFromXML();

	boolean createObjectXML();
	
	String getTagValue(String argTag, Element element);
}
