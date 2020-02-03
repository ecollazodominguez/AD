/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlreader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import serializacion2.mclase2;

/**
 *
 * @author oracle
 */
public class XMLreader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        XMLInputFactory xof = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xof.createXMLStreamReader(new FileInputStream("/home/oracle/Desktop/products.xml"));
        ArrayList<mclase2> productos = new ArrayList<>();

        mclase2 n = new mclase2();
        int cont = 0;
        while (xsr.hasNext()) {
            xsr.next();
            if (xsr.getEventType() == XMLStreamConstants.START_ELEMENT) {
                if (xsr.getAttributeValue(0) != null) {
                    n.setCodigo(xsr.getAttributeValue(0));
                    cont++;
                } else if (xsr.getLocalName().equals("descricion")) {
                    n.setDescricion(xsr.getElementText());
                    cont++;
                } else if (xsr.getLocalName().equals("prezo")) {
                    n.setPrezo(Double.parseDouble(xsr.getElementText()));
                    cont++;
                }
            }
            if (cont >= 3) {
                productos.add(n);
                cont = 0;
                n = new mclase2();
            }
        }
        xsr.close();
        System.out.println(productos);
    }

}
