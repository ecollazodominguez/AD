/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package existapp2;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

/**
 *
 * @author oracle
 */
public class ExistApp2 {

    final static String driver = "org.exist.xmldb.DatabaseImpl";

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        Class<?> cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        DatabaseManager.registerDatabase((org.xmldb.api.base.Database) database);
        String coleccion = "/db/cousas";
        Collection col;
        String uri = "xmldb:exist://localhost:8080/exist/xmlrpc";
        col = (Collection) DatabaseManager.getCollection(uri + coleccion, "admin", "oracle");
        
        //inserir dende un XML
        queryEleXML("proba.xml", "update insert <PERSONA>alexia</PERSONA> into /PLAY/PERSONAE",col);
        
        //inserir dende a COLECCION
        queryEle("update insert <PERSONA>sara</PERSONA> into /PLAY/PERSONAE",col);
        
        // actualizar dende a COLECCION
        queryEle("update value /EMPLEADOS/EMP_ROW[EMP_NO=7521]/APELLIDO with 'BIEITEZ'",col);
       
        // borrar dende a COLECCION
        queryEle("update delete /EMPLEADOS/EMP_ROW[EMP_NO=7698]", col);
        
        // modificar dende un XML
        queryEleXML("proba2.xml","update value /PLAY/PERSONAE/PERSONA[text() = 'pedro'] with 'xoan'",col);
        queryEleXML("proba.xml","update value /PLAY/PERSONAE/PERSONA[text() = 'luis'] with 'xulio'",col);
        queryEle("update value /PLAY/PERSONAE/PERSONA[text() = 'xoan'] with 'sara'",col);
        queryEleXML("proba.xml","update value /PLAY/fm/p[@id=2]/nome[text() = 'ana'] with 'xulia'",col);
        
    }

    public static void queryEleXML(String xml, String query, Collection col) throws XMLDBException {
        //para inserir/actualizar/borrar un elemento nun xml en concreto
        XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        ResourceSet result = servicio.queryResource(xml, query);
        System.out.println("inserido/actualizado/borrado nun XML");
    }

    public static void queryEle(String query, Collection col) throws XMLDBException {
        //para inserir/actualizar/borrar un elemento na colección
        XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        ResourceSet result = servicio.query(query);
        System.out.println("inserido/actualizado/borrado na colección");
    }

}
