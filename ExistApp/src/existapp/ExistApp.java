/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package existapp;

import java.io.File;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XPathQueryService;

/**
 *
 * @author oracle
 */
public class ExistApp {

    final static String driver = "org.exist.xmldb.DatabaseImpl";

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, XMLDBException, ClassNotFoundException {
        Class<?> cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        DatabaseManager.registerDatabase((org.xmldb.api.base.Database) database);
        String coleccion = "/db";
        Collection col;
        String uri = "xmldb:exist://localhost:8080/exist/xmlrpc";
        col = (Collection) DatabaseManager.getCollection(uri + coleccion, "admin", "oracle");

        //lista las carpetas(colecciones)
        for (String arg : col.listChildCollections()) {
            System.out.println(arg);

        }

        System.out.println("******colecciones /db mostradas*******");
        //lista los recursos (generalmente XMLs)
        for (String arg : col.listResources()) {
            System.out.println(arg);

        }

        System.out.println("********recursos /db mostrados********");

        //Para crear colecciones
        crearColeccion("Prueba", col);

        //Borar colección
        borrarColeccion("Prueba", col);

        // subir recursos á colección cousas
        coleccion = "/db/cousas";
        col = (Collection) DatabaseManager.getCollection(uri + coleccion, "admin", "oracle");

        crearColeccion("cousas", col);
        createResource("proba.xml", col);
        createResource("proba2.xml", col);
        createResource("empleados.xml", col);

        for (String arg : col.listResources()) {
            System.out.println(arg);

        }
        System.out.println("*******recursos /db/cousas mostrados********");

        //borrar recursos
       // removeResource("proba2.xml", col);

        for (String arg : col.listResources()) {
            System.out.println(arg);

        }
        System.out.println("*******recursos /db/cousas mostrados********");

        //Para amosar contido recurso
        amosarContido("/EMPLEADOS/EMP_ROW[DEPT_NO=10]", col);

        System.out.println("************amosando empleados consulta****************");

        //para amosar contido recurso dun xml en concreto
        amosarContidoXML("proba.xml", "/PLAY/fm", col);

        System.out.println("********amosando proba consulta*******");
        
        /////////////////
        ///////XML///////
        ////////////////
        
        //para inserir un elemento nun XML en concreto       
        queryEleXML("empleados.xml","update insert <zona><cod_zona>50</cod_zona><nombre>Madrid-Oeste</nombre><director>Alicia Ramos Martin</director></zona> into /EMPLEADOS/ZONAS", col);
        
        //para actualizar un elemento nun XML en concreto
        queryEleXML("empleados.xml","update value /EMPLEADOS/EMP_ROW[EMP_NO=7369]/APELLIDO with 'RODROGUEZ'",col);
        
        //para borrar un elemento nun XML en concreto
        queryEleXML("empleados.xml","update delete /EMPLEADOS/ZONAS/zona[cod_zona=50]",col);
        
        ////////////////
        ///COLECCION////
        ///////////////
        
        //para inserir un elemento na COLECCION
        queryEle("update insert <zona><cod_zona>50</cod_zona><nombre>Madrid-Oeste</nombre><director>Alicia Ramos Martin</director></zona> into /EMPLEADOS/ZONAS", col);
        queryEle("update insert <autor><cod_autor>1</cod_autor><nombre>luis</nombre><edad>30</edad></autor> into /PLAY", col);
        
        //para actualizar un elemento na COLECCION
        queryEle("update value /EMPLEADOS/EMP_ROW[EMP_NO=7369]/APELLIDO with 'RODROGUEZ'", col);
        
        //para eliminar un elemento na COLECCION
        queryEle("update delete /EMPLEADOS/ZONAS/zona[cod_zona=50]", col);
    }

    public static void crearColeccion(String coleccion, Collection col) throws XMLDBException {
        //Para crear colecciones
        CollectionManagementService mgtService = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
        mgtService.createCollection(coleccion);
    }

    public static void borrarColeccion(String coleccion, Collection col) throws XMLDBException {
        //Para borrar colecciones
        CollectionManagementService mgtService = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
        mgtService.removeCollection(coleccion);
    }

    public static void createResource(String file, Collection col) throws XMLDBException {
        //Para crear recursos
        File arquivo1 = new File("/home/oracle/Desktop/compartido/eXist/" + file);

        Resource novoRecurso1 = col.createResource(arquivo1.getName(), "XMLResource");
        novoRecurso1.setContent(arquivo1);
        col.storeResource(novoRecurso1);
    }

    public static void removeResource(String resource, Collection col) throws XMLDBException {
        //borrar recursos
        Resource recursoaborrar = col.getResource(resource);
        col.removeResource(recursoaborrar);

    }

    public static void amosarContido(String query, Collection col) throws XMLDBException {
        //para amosar contido recurso en toda coleccion
        XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        ResourceSet resultado = servicio.query(query);

        ResourceIterator i = resultado.getIterator();

        if (i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String) r.getContent());
        }

    }

    public static void amosarContidoXML(String xml, String query, Collection col) throws XMLDBException {
        //para amosar contido recurso dun xml en concreto
        XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        ResourceSet resultado = servicio.queryResource(xml, query);

        ResourceIterator i = resultado.getIterator();

        if (i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String) r.getContent());
        }
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
