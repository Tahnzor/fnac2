package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XmlProcessor {

    public static Map<String, Producto> obtenerInformacionProductosDesdeXML(String archivoXML) {
        Map<String, Producto> productosInfo = new HashMap<>();

        try {
            File xmlFile = new File(archivoXML);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            XPathExpression codigoExpr = xpath.compile("/products/product/sku/text()");
            XPathExpression nombreExpr = xpath.compile("/products/product/titulo/text()");
            XPathExpression precioExpr = xpath.compile("/products/product/precio/text()");

            NodeList productosNodes = (NodeList) xpath.evaluate("/products/product", document, XPathConstants.NODESET);

            for (int i = 0; i < productosNodes.getLength(); i++) {
                Node productoNode = productosNodes.item(i);
                String codigo = codigoExpr.evaluate(productoNode);
                String nombre = nombreExpr.evaluate(productoNode);
                double precio = Double.parseDouble(precioExpr.evaluate(productoNode));

                Producto producto = new Producto();
                producto.setCodigo(codigo);
                producto.setNombre(nombre);
                producto.setPrecio(precio);

                productosInfo.put(codigo, producto);
            }

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return productosInfo;
    }
}
