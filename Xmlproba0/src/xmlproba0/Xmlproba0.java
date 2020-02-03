/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlproba0;

import java.io.FileWriter;
import java.io.IOException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author oracle
 */
public class Xmlproba0 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, XMLStreamException {
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xsw = xof.createXMLStreamWriter(new FileWriter("/home/oracle/Desktop/xmlproba0.xml"));
        
        
        xsw.writeStartDocument("1.0");
        xsw.writeStartElement("autores");
        
            xsw.writeStartElement("autor");
            xsw.writeAttribute("codigo", "a1");    
                xsw.writeStartElement("nome");
                    xsw.writeCharacters("Alexandre Dumas");
                xsw.writeEndElement();
                
                xsw.writeStartElement("titulo");
                    xsw.writeCharacters("El conde de montecristo");
                xsw.writeEndElement();
                
                xsw.writeStartElement("titulo");
                    xsw.writeCharacters("Los miserables");
                xsw.writeEndElement();
            xsw.writeEndElement();
            
            xsw.writeStartElement("autor");
            xsw.writeAttribute("codigo", "a2");;        
                xsw.writeStartElement("nome");
                    xsw.writeCharacters("Fiodor Dovstoyeski");
                xsw.writeEndElement();   
                
                xsw.writeStartElement("titulo");
                    xsw.writeCharacters("El idiota");
                xsw.writeEndElement();
                
                xsw.writeStartElement("titulo");
                    xsw.writeCharacters("Noches blancas");
                xsw.writeEndElement();
                
            xsw.writeEndElement();
            
        xsw.writeEndElement();
        xsw.writeEndDocument();
        
        xsw.close();
    }
    
}
