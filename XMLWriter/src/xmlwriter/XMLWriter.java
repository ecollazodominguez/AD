/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlwriter;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import serializacion2.mclase2;

/**
 *
 * @author oracle
 */
public class XMLWriter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, XMLStreamException, ClassNotFoundException {
     ObjectInputStream ois = new ObjectInputStream(
                                 new FileInputStream("/home/oracle/Desktop/serializable2.dat"));
     
     XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xsw = xof.createXMLStreamWriter(new FileWriter("/home/oracle/Desktop/products.xml"));
        
        xsw.writeStartDocument("1.0");
        xsw.writeStartElement("Productos");
        
        mclase2 n = null;
        n = (mclase2)ois.readObject();
        while(n != null){
            
            xsw.writeStartElement("producto");
            xsw.writeAttribute("codigo",n.getCodigo()); 
            
                xsw.writeStartElement("descricion");
                    xsw.writeCharacters(n.getDescricion());
                xsw.writeEndElement();
                
                xsw.writeStartElement("prezo");
                    xsw.writeCharacters(String.valueOf(n.getPrezo()));
                xsw.writeEndElement();
                
            xsw.writeEndElement();
        
        n= (mclase2)ois.readObject();
        }
        
        xsw.writeEndElement();
        xsw.writeEndDocument();
        
        ois.close();
        xsw.close();
        
        
    }
    
}
