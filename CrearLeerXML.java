package xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CrearLeerXML {

	public static void main(String[] args) {
		Alumno personas[] = new Alumno[10];
		personas[0] = new Alumno("1DM3", "Jon", "Bergerandi", "Loidi", "Zarautz", "Masculino");
		personas[1] = new Alumno("1DM3", "Kevin", "Blanco", "Matamoros", "Irun", "Masculino");
		personas[2] = new Alumno("1DM3", "Unax", "Arretxe", "Egaña", "Irun", "Masculino");
		personas[3] = new Alumno("1DM3", "Mikel", "Seara", "Garcia", "Andoain", "Masculino");
		personas[4] = new Alumno("1DM3", "Gloria", "Nieves", "Del Olmo", "Huelva", "Femenino");
		personas[5] = new Alumno("2DM3", "Lander", "Ucin", "Olazabal", "Getaria", "Masculino");
		personas[6] = new Alumno("2DM3", "Diego", "Sanchez", "Aguirregomezcorta", "Guayakil", "Masculino");
		personas[7] = new Alumno("2DM3", "Ander", "Almandoz", "Keregeta", "San Sebastian", "Masculino");
		personas[8] = new Alumno("2DM3", "Luis", "Garcia", "Lezertua", "Pasajes", "Masculino");
		personas[9] = new Alumno("2DM3", "Iñaki", "Alzaga", "Berasategui", "Trintxerpe", "Masculino");

		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factoria.newDocumentBuilder();
			DOMImplementation dom = builder.getDOMImplementation();
			Document documento = dom.createDocument(null, "alumnos", null);
			File archivo = new File("/home/jb/Deskargak/alumnos.xml");
			
			for (Alumno alumno : personas) {
				Element alum = documento.createElement("curso");
				Attr atributo = documento.createAttribute("id");
				atributo.setValue(alumno.getCurso());
				alum.setAttributeNode(atributo);
				documento.getDocumentElement().appendChild(alum);
				
				Element nombre = documento.createElement("nombre");
				nombre.appendChild(documento.createTextNode(alumno.getNombre()));
				alum.appendChild(nombre);
				
				Element apellido1 = documento.createElement("apellido1");
				apellido1.appendChild(documento.createTextNode(alumno.getApellido1()));
				alum.appendChild(apellido1);
				
				Element apellido2 = documento.createElement("apellido2");
				apellido2.appendChild(documento.createTextNode(alumno.getApellido2()));
				alum.appendChild(apellido2);
				
				Element nacimiento = documento.createElement("lugarNacimiento");
				nacimiento.appendChild(documento.createTextNode(alumno.getLugarNacimiento()));
				alum.appendChild(nacimiento);
				
				Element genero = documento.createElement("genero");
				genero.appendChild(documento.createTextNode(alumno.getGenero()));
				alum.appendChild(genero);
				
				TransformerFactory factoriaTrans = TransformerFactory.newInstance();
				Transformer transformador = factoriaTrans.newTransformer();
				
				Source source = new DOMSource(documento);
				Result resultado = new StreamResult(archivo);
				
				transformador.setOutputProperty(OutputKeys.INDENT, "yes");
				transformador.transform(source, resultado);
			}
			
			documento = builder.parse(archivo);
			NodeList listaAlumnos = documento.getElementsByTagName("curso");
			for (int i = 0; i < listaAlumnos.getLength(); i++) {
				Node nodo = listaAlumnos.item(i);
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) nodo;
					System.out.println("Curso: " + elemento.getAttribute("id"));
					System.out.println("Nombre: " + elemento.getElementsByTagName("nombre").item(0).getTextContent());
					System.out.println("Primer apellido: " + elemento.getElementsByTagName("apellido1").item(0).getTextContent());
					System.out.println("Segundo apellido: " + elemento.getElementsByTagName("apellido2").item(0).getTextContent());
					System.out.println("Lugar de nacimiento: " + elemento.getElementsByTagName("lugarNacimiento").item(0).getTextContent());
					System.out.println("Genero: " + elemento.getElementsByTagName("genero").item(0).getTextContent() + "\n");
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Alumno{
	private String curso;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String lugarNacimiento;
	private String genero;

	public Alumno(String curso, String nombre, String apellido1, String apellido2, String lugarNacimiento, String genero) {
		this.curso = curso;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.lugarNacimiento = lugarNacimiento;
		this.genero = genero;
	}

	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getLugarNacimiento() {
		return lugarNacimiento;
	}
	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	
}