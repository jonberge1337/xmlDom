package xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class LecturaXmlSinConocerEstructura {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = factory.newDocumentBuilder();
			Document doc = dbBuilder.parse(new File("/home/jb/Deskargak/pedidos.xml"));
			doc.getDocumentElement().normalize();
			NodeList lista = doc.getElementsByTagName("albaran");
			System.out.println("Numero de albaranes" + lista.getLength());
			for (int i = 0; i < lista.getLength(); i++) {
				Node nodo = lista.item(i);
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) nodo;
					if (elemento.hasChildNodes()) {
						NodeList n1 = nodo.getChildNodes();
						for (int j = 0; j < n1.getLength(); j++) {
							Node nd = n1.item(j);
							System.out.println(nd.getNodeName() + " " + nd.getTextContent());
						}
					}
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
