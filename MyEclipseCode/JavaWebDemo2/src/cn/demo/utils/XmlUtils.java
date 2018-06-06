package cn.demo.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtils {
	
	private static String filepath;
	static{
		//类装载器获取xml文件目录
		filepath = XmlUtils.class.getClassLoader().getResource("users.xml").getPath();
	}

	public static Document getDocument() throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(filepath));
		
		return document;
	}
	
	public static void write2Xml(Document document) throws Exception{
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
	    XMLWriter writer = new XMLWriter(new FileOutputStream(filepath), format);
	    writer.write(document);
	    
	    writer.close();
	    
	}
}
