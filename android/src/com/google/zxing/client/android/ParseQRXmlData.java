package com.google.zxing.client.android;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.AssetManager;
import android.util.Xml;

public class ParseQRXmlData {

	public ParseQRXmlData(){
		
	}
	private static final String ns = null;
	public  List<ProductNode> parseXml(AssetManager assetManager) throws XmlPullParserException{
		List<ProductNode> products;
		try{
			InputStream in = assetManager.open("data.xml");//TODO close?
			products = parse(in);
		}
		catch(IOException ex){
			return null;
		}
		
		return products;
	}
	
	private List<ProductNode> parse(InputStream in)throws XmlPullParserException, IOException{
		try{
			XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readNodes(parser);
		} finally {
            in.close();
        }
	}
	
	//nodes
	private List<ProductNode> readNodes(XmlPullParser parser) throws XmlPullParserException, IOException {
	    List<ProductNode> nodes = new ArrayList<ProductNode>();

	    parser.require(XmlPullParser.START_TAG, ns, "root");
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        // Starts by looking for the entry tag
	        if (name.equals("node")) {
	        	nodes.add(readNode(parser));
	        } 
	    }  
	    return nodes;
	}
	
	//node
	private ProductNode readNode(XmlPullParser parser) throws XmlPullParserException, IOException {
	    ProductNode node = new ProductNode() ;
	    parser.require(XmlPullParser.START_TAG, ns, "node");
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        // Starts by looking for the entry tag
	        if (name.equals("id")) {
	        	String id = readNodeId(parser);
	        	node.setId(id);
	        } 
	        else if(name.equals("entries")){
	        	List<Entry> entries = readEntries(parser);
	        	node.setEntries(entries);
	        }
	    }  
	    return node;
	}
	
	//node id
	private String readNodeId(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "id");
	    String id = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "id");
	    return id;
	}
	
	//entries
	private List<Entry> readEntries(XmlPullParser parser) throws XmlPullParserException, IOException {
		List<Entry> entries = new ArrayList<Entry>();
		
	    parser.require(XmlPullParser.START_TAG, ns, "entries");
	
	    Entry entry = null;
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("entry")) {
	            entry = readEntry(parser);
	            entries.add(entry);
	        } 
	    }
	    return entries;
	}
	//entry
	private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "entry");
//	    String index = null;
	    int index = 0;
	    String entryName = null;
	    String value = null;
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("index")) {
	            index = readIndex(parser);
	        } else if (name.equals("name")) {
	        	entryName = readName(parser);
	        } else if (name.equals("value")) {
	            value = readValue(parser);
	        }
	    }
	    return new Entry(index, entryName, value);
	}
	//index
	private int readIndex(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "index");
	    String title = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "index");
	    return Integer.parseInt(title);
	}
	//name
	private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "name");
	    String name = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "name");
	    return name;
	}
	//value
	private String readValue(XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "value");
	    String value = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "value");
	    return value;
	}
	
	
	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
	    String result = "";
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
	    }
	    return result;
	}
}
