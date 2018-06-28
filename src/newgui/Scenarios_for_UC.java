/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newgui;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author 5730213082
 */
public class Scenarios_for_UC {

    Home homeClass = new Home();
    
    ArrayList Columname = new ArrayList<>();
    ArrayList<Integer> ValMax = new ArrayList<>();
    ArrayList<Integer> ValMin = new ArrayList<>();
    ArrayList InputMin = new ArrayList<Integer>();
    ArrayList InputMax = new ArrayList<Integer>();
    ArrayList InputValue = new ArrayList<>();
    ArrayList OutputValue = new ArrayList<>();
    ArrayList Des = new ArrayList<>();
    ArrayList varnameSize = new ArrayList<>();

    ArrayList Mainflow = new ArrayList<>();
    ArrayList MF_description = new ArrayList<>();

    ArrayList Exceptionflow = new ArrayList<>();
    ArrayList EF_description = new ArrayList<>();
    ArrayList extREF = new ArrayList<>();
    ArrayList extRefOut = new ArrayList<>();

    ArrayList Alternativeflow = new ArrayList<>();
    ArrayList AF_description = new ArrayList<>();
    ArrayList altREF = new ArrayList<>();
    ArrayList altRefIN = new ArrayList<>();
    ArrayList altRefOut = new ArrayList<>();

    ArrayList tableColumname = new ArrayList<>();
    ArrayList ScenarioID = new ArrayList<>();

    public void ScenariosForUC() {

        String mainflow[] = {"Buying", "Usecase", "Get card discount", "Price > 1000", "Get discount", "End"};
        String exceptionflow[] = {"No card", "Price < 1000", "No discount"};
        String alternativeflow[] = {"No card1"};

        String b = "              ";
        String c = "            ";
        int IntMin = 0;
        int IntMax = 0;
        int data = 0;
        int randomMin;
        try {

            String testType = "";
            String flowID = "";
            String mainDescription = "";

            String altID = "";
            String altDescription = "";
            String ref_altID = "";
            String altRef_in = "";
            String altRef_out = "";
            String refType = "";

            String extID = "";
            String extDescription = "";
            String ref_extID = "";
            String extRef_out = "";

            String varDes = "";
            String Err = "";

            // TODO Auto-generated method stub
            File fXmlFile = new File(homeClass.getFileLocationCFG());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());	

            //access to node "Input"
            NodeList ndMain = doc.getElementsByTagName("flow_of_event");
            NodeList ndAlternative = doc.getElementsByTagName("alternative_flow");
            NodeList ndException = doc.getElementsByTagName("exception_flow");
            NodeList ndRef = doc.getElementsByTagName("ref");

            for (int i = 0; i < ndMain.getLength(); i++) {
                org.w3c.dom.Node node = ndMain.item(i);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    testType = node.getNodeName();
                    NodeList ndInputChild = node.getChildNodes();

                    //access to child nodes of node "Input"
                    for (int j = 0; j < ndInputChild.getLength(); j++) {
                        org.w3c.dom.Node nodeInputChild = ndInputChild.item(j);
                        if (nodeInputChild.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                            org.w3c.dom.Element eElement = (org.w3c.dom.Element) nodeInputChild;

                            if (nodeInputChild.getNodeName() == "value") {
                                mainDescription = nodeInputChild.getTextContent();
                                flowID = eElement.getAttribute("flow_id");

                                MF_description.add(mainDescription);
                                homeClass.MF_description.add(mainDescription);
                                Mainflow.add(flowID);
                                homeClass.Mainflow.add(flowID);

                                //ScenarioID.add("Scenario ");
                                //System.out.println("Flow ID : " + flowID);
                                //System.out.println("Description: " + mainDescription);
                                //Mainflow.add(varName);
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < ndAlternative.getLength(); i++) {
                org.w3c.dom.Node node = ndAlternative.item(i);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    testType = node.getNodeName();
                    NodeList ndInputChild = node.getChildNodes();

                    //access to child nodes of node "Input"
                    for (int j = 0; j < ndInputChild.getLength(); j++) {
                        org.w3c.dom.Node nodeInputChild = ndInputChild.item(j);
                        if (nodeInputChild.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                            org.w3c.dom.Element eElement = (org.w3c.dom.Element) nodeInputChild;

                            if (nodeInputChild.getNodeName() == "value") {
                                altDescription = nodeInputChild.getTextContent();
                                altID = eElement.getAttribute("alt_id");

                                AF_description.add(altDescription);
                                Alternativeflow.add(altID);
                                
                                homeClass.AF_description.add(altDescription);
                                homeClass.Alternativeflow.add(altID);
                                // System.out.println("ALT ID : " + altID);
                                //System.out.println("Description: " + altDescription);
                                //Mainflow.add(varName);

                            }

                            if (nodeInputChild.getNodeName() == "ref") {

                                ref_altID = eElement.getAttribute("ref_id");
                                altREF.add(ref_altID);
                                homeClass.altREF.add(ref_altID);
                                // System.out.println("REF: " + ref_altID);

                            }

                            if (nodeInputChild.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                                NodeList refOutList = eElement.getElementsByTagName("flow-ref_out");
                                NodeList refInList = eElement.getElementsByTagName("flow-ref_in");
                                for (int out = 0; out < refOutList.getLength(); out++) {
                                    org.w3c.dom.Node node1 = refOutList.item(out);
                                    if (node1.getNodeType() == node1.ELEMENT_NODE) {
                                        Element refOut = (Element) node1;
                                        altRef_out = refOut.getAttribute("at-step");
                                        altRefOut.add(altRef_out);
                                        homeClass.altRefOut.add(altRef_out);
                                        // System.out.println("REF OUT: " + altRef_out);
                                    }
                                }
                                for (int out = 0; out < refInList.getLength(); out++) {
                                    org.w3c.dom.Node node1 = refInList.item(out);
                                    if (node1.getNodeType() == node1.ELEMENT_NODE) {
                                        Element refOut = (Element) node1;
                                        altRef_in = refOut.getAttribute("at-step");
                                        altRefIN.add(altRef_in);
                                        homeClass.altRefIN.add(altRef_in);
                                        // System.out.println("REF IN: " + altRef_in);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < ndException.getLength(); i++) {
                org.w3c.dom.Node node = ndException.item(i);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    testType = node.getNodeName();
                    NodeList ndInputChild = node.getChildNodes();

                    //access to child nodes of node "Input"
                    for (int j = 0; j < ndInputChild.getLength(); j++) {
                        org.w3c.dom.Node nodeInputChild = ndInputChild.item(j);
                        if (nodeInputChild.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                            org.w3c.dom.Element eElement = (org.w3c.dom.Element) nodeInputChild;

                            if (nodeInputChild.getNodeName() == "value") {
                                extDescription = nodeInputChild.getTextContent();
                                extID = eElement.getAttribute("ext_id");

                                EF_description.add(extDescription);
                                Exceptionflow.add(extID);
                                
                                homeClass.EF_description.add(extDescription);
                                homeClass.Exceptionflow.add(extID);
                                // System.out.println("EXT ID : " + extID);
                                // System.out.println("Description: " + extDescription);
                                //Mainflow.add(varName);

                            }
                            if (nodeInputChild.getNodeName() == "ref") {

                                ref_extID = eElement.getAttribute("ref_id");
                                extREF.add(ref_extID);
                                homeClass.extREF.add(ref_extID);
                                // System.out.println("REF: " + ref_extID);

                            }

                            if (nodeInputChild.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                                NodeList refOutList = eElement.getElementsByTagName("flow-ref");

                                for (int out = 0; out < refOutList.getLength(); out++) {
                                    org.w3c.dom.Node node1 = refOutList.item(out);
                                    if (node1.getNodeType() == node1.ELEMENT_NODE) {
                                        Element refOut = (Element) node1;
                                        extRef_out = refOut.getAttribute("at-step");
                                        extRefOut.add(extRef_out);
                                        homeClass.extRefOut.add(extRef_out);
                                        
                                        //System.out.println("REF OUT: " + extRef_out);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 1; i < MF_description.size() + 1; i++) {
            ScenarioID.add(i);
            homeClass.ScenarioID.add(i);

        }

        System.out.print("Mainflow : " + Mainflow);
        System.out.println();
        System.out.print("MF_description : " + MF_description);
        System.out.println();
        System.out.print("Exceptionflow : " + Exceptionflow);
        System.out.println();
        System.out.print("EF_description : " + EF_description);
        System.out.println();
        System.out.print("extREF : " + extREF);
        System.out.println();
        System.out.print("extRefOut : " + extRefOut);
        System.out.println();
        System.out.print("Alternativeflow : " + Alternativeflow);
        System.out.println();
        System.out.print("AF_description : " + AF_description);
        System.out.println();
        System.out.print("altREF : " + altREF);
        System.out.println();
        System.out.print("altRefIN : " + altRefIN);
        System.out.println();
        System.out.print("altRefOut : " + altRefOut);
        System.out.println();
        System.out.print("ScenarioID : " + ScenarioID);
        System.out.println();
    }

    public void ReadXML() {

        try {

            PrintStream myconsole = new PrintStream(new File("src\\etc\\Scenarios_for_UC.txt"));

            System.setOut(myconsole);
            ScenariosForUC();

        } catch (Exception e) {

        }

    }

}
