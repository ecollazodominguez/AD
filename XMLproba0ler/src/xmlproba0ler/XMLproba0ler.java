/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlproba0ler;

import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author oracle
 */
public class XMLproba0ler {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, XMLStreamException {
        XMLInputFactory xof = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xof.createXMLStreamReader(new FileInputStream("/home/oracle/Desktop/xmlproba0.xml"));

        while (xsr.hasNext()) {
            xsr.next();             
            if (xsr.getEventType() == XMLStreamConstants.START_ELEMENT) {
                if (xsr.getAttributeValue(0) != null) {
                    System.out.print(xsr.getAttributeValue(0) + " ");
                }
                if (xsr.getLocalName().equals("nome") || xsr.getLocalName().equals("titulo")) {
                    System.out.print(xsr.getElementText() + " ");

                }
                System.out.println("\n");
            }

        }

    }
}
