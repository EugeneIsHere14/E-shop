package com.epam.preproduction.koshevyi.util.parser;

import com.epam.preproduction.koshevyi.entity.PageAccess;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains method for parsing
 * XML-file of certain structure.
 */
public class SecurityParser {

    private static final String CONSTRAINT_ELEMENT = "constraint";
    private static final String ROLE_ELEMENT = "role";

    /**
     * Parses specified XML-file and returns
     * List instance with parsed data.
     *
     * @param file path to the file which is must be parsed
     * @return returns List instance with parsed data
     * @throws ParserConfigurationException when configuration error occurred
     * @throws IOException when problems with file path or file access occurred
     * @throws SAXException when problems with parsing file occurred
     */
    public static List<PageAccess.Constraint> parseXML(String file)
            throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        List<PageAccess.Constraint> constraints = new ArrayList<>();

        NodeList nodeList = document.getElementsByTagName(CONSTRAINT_ELEMENT);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            NodeList childList = node.getChildNodes();
            List<String> urlPatterns = new ArrayList<>();
            List<Integer> roles = new ArrayList<>();

            for (int n = 0; n < childList.getLength(); n++) {
                Node childNode = childList.item(n);
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    if (childNode.getNodeName().equals(ROLE_ELEMENT)) {
                        roles.add(Integer.valueOf(childNode.getTextContent()));
                    } else urlPatterns.add(childNode.getTextContent());
                }
            }
            constraints.add(new PageAccess.Constraint(roles, urlPatterns));
        }

        return constraints;
    }
}