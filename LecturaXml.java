package xml;

import java.io.File;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class LecturaXml {

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File("/home/jb/Deskargak/pedidos.xml"));

            NodeList listaAlbaran = documento.getElementsByTagName("albaran");
            System.out.println(listaAlbaran.getLength());

            for (int i = 0; i < listaAlbaran.getLength(); i++) {
                Node nodo = listaAlbaran.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;

                    System.out.println("Albaran " + e.getAttribute("id"));
                    System.out.println("FechaAlbaran " + e.getElementsByTagName("fechaAlbaran").item(0).getTextContent());

                    System.out.println("Precio" + e.getElementsByTagName("precio").item(0).getTextContent());
                    System.out.println("Precio" + e.getElementsByTagName("precio").item(1).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
