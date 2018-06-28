/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newgui;

/**
 *
 * @author 5730213082
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.graphicGraph.GraphicElement;
import org.graphstream.ui.graphicGraph.GraphicGraph;
import org.graphstream.ui.view.Camera;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.util.MouseManager;
import org.graphstream.ui.view.util.ShortcutManager;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
//import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ControlFlowGraph {

    Home homeClass = new Home();
    ExcelWrite excel = new ExcelWrite();
    public void Read() {
        System.out.format("a Testing Generation Tool for Supporting System and Acceptance Testing Environment");

        //ArrayList<Integer> InputMin = new ArrayList<Integer>();
        //ArrayList<Integer> InputMax = new ArrayList<Integer>();
    } //end of Read() Method

    public void ControlFG() {
        //public static void main(String args[]){

        String mainflow[] = {"Buying", "Usecase", "Get card discount", "Price > 1000", "Get discount", "End"};
        String exceptionflow[] = {"No card", "Price < 1000", "No discount"};
        String alternativeflow[] = {"No card1"};

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
                                excel.MF_description.add(mainDescription);
                                Mainflow.add(flowID);
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
                                excel.AF_description.add(altDescription);
                                Alternativeflow.add(altID);
                                // System.out.println("ALT ID : " + altID);
                                //System.out.println("Description: " + altDescription);
                                //Mainflow.add(varName);

                            }

                            if (nodeInputChild.getNodeName() == "ref") {

                                ref_altID = eElement.getAttribute("ref_id");
                                altREF.add(ref_altID);
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
                                        // System.out.println("REF OUT: " + altRef_out);
                                    }
                                }
                                for (int out = 0; out < refInList.getLength(); out++) {
                                    org.w3c.dom.Node node1 = refInList.item(out);
                                    if (node1.getNodeType() == node1.ELEMENT_NODE) {
                                        Element refOut = (Element) node1;
                                        altRef_in = refOut.getAttribute("at-step");
                                        altRefIN.add(altRef_in);
                                        
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
                                excel.EF_description.add(extDescription);
                                Exceptionflow.add(extID);
                                // System.out.println("EXT ID : " + extID);
                                // System.out.println("Description: " + extDescription);
                                //Mainflow.add(varName);

                            }
                            if (nodeInputChild.getNodeName() == "ref") {

                                ref_extID = eElement.getAttribute("ref_id");
                                extREF.add(ref_extID);
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

        SingleGraph graph = new SingleGraph("ControlFlowGraph");

        //graph.setStrict(true);
        //graph.setAutoCreate(false);
        for (int a = 0; a < Mainflow.size(); a++) {
            graph.addNode((String) Mainflow.get(a));

            Node e1 = graph.getNode("" + Mainflow.get(a));

            e1.addAttribute("ui.style", "fill-color: gray;size: 40px;"); //ตั้งค่าสีและขนาดของ node
            e1.addAttribute("ui.label", "" + Mainflow.get(a) + " ("+MF_description.get(a)+")");  //ตั้งชื่อ node

            e1.addAttribute("layout.frozen");
            e1.addAttribute("xy", 0, -a);   //กำหนดตำแหน่ง node 

        }

        for (int be = 0; be < Exceptionflow.size(); be++) {
            graph.addNode((String) Exceptionflow.get(be));

            Node e1 = graph.getNode("" + Exceptionflow.get(be));
            e1.addAttribute("ui.style", "fill-color: gray;size: 40px;");  //ตั้งค่าสีและขนาดของ node
            e1.addAttribute("ui.label", "" + Exceptionflow.get(be)+ " ("+EF_description.get(be)+")");
            e1.addAttribute("layout.frozen");
            e1.addAttribute("xy", 1.5, -be - 2);   //กำหนดตำแหน่ง node
        }

        for (int n = 0; n < Alternativeflow.size(); n++) {
            graph.addNode((String) Alternativeflow.get(n));

            Node e1 = graph.getNode("" + Alternativeflow.get(n));
            e1.addAttribute("ui.style", "fill-color: gray;size: 40px;");  //ตั้งค่าสีและขนาดของ node
            e1.addAttribute("ui.label", "" + Alternativeflow.get(n)+ " ("+AF_description.get(n)+")");
            e1.addAttribute("layout.frozen");
            e1.addAttribute("xy", -1.5, -n - 2);  //กำหนดตำแหน่ง node

        }

        for (int mf = 0;
                mf < Mainflow.size();
                mf++) {
            if (mf < Mainflow.size() - 1) {

                graph.addEdge(Mainflow.get(mf) + "" + Mainflow.get(mf + 1) + "", "" + Mainflow.get(mf) + "", "" + Mainflow.get(mf + 1), true);   //ใส่ true เพื่อให้มีหัวลูกศร  

            }

        } //สร้างกราฟ Main flow

        for (int ef = 0; ef < Exceptionflow.size(); ef++) {
            if (ef < Exceptionflow.size() - 1) {
                graph.addEdge(Exceptionflow.get(ef) + "", "" + Exceptionflow.get(ef) + "", "" + Exceptionflow.get(ef), true);   //ใส่ true เพื่อให้มีหัวลูกศร  

            }

        } //สร้างกราฟ Exception flow

        for (int af = 0; af < Alternativeflow.size(); af++) {
            if (af < Alternativeflow.size() - 1) {
                graph.addEdge(Alternativeflow.get(af) + "", "" + Alternativeflow.get(af) + "", "" + Alternativeflow.get(af), true);   //ใส่ true เพื่อให้มีหัวลูกศร  

            }

        } //สร้างกราฟ Alternative flow

        for (int afRef = 0; afRef < altREF.size(); afRef++) {

            graph.addEdge(altRefOut.get(afRef) + "" + altREF.get(afRef) + "", "" + altRefOut.get(afRef) + "", "" + altREF.get(afRef), true);   // จับคู่ main flow กับ alternative flow 
            graph.addEdge(altREF.get(afRef) + "" + altRefIN.get(afRef) + "", "" + altREF.get(afRef) + "", "" + altRefIN.get(afRef), true);     // จับคู่ alternative flow กับ main flow

        } //เชื่อมกราฟ main flow กับ alternativeflow

        for (int efRef = 0; efRef < extREF.size(); efRef++) {

            graph.addEdge(altRefOut.get(efRef) + "" + extREF.get(efRef) + "", "" + altRefOut.get(efRef) + "", "" + extREF.get(efRef), true);   // จับคู่ main flow กับ alternative flow 
            // graph.addEdge(altREF.get(afRef) + "" + altRefIN.get(afRef) + "", "" + altREF.get(afRef) + "", "" + altRefIN.get(afRef), true);     // จับคู่ alternative flow กับ main flow

        } //เชื่อมกราฟ main flow กับ exceptionflow
        /*
        graph.addEdge("Usecase", "Usecase", "No card", true);               // จับคู่ main flow กับ exception flow
        graph.addEdge("UsecaseNo card1", "Usecase", "No card1", true);      // จับคู่ main flow กับ alternative flow      
        graph.addEdge("No card1", "No card1", "Price > 1000", true);        // จับคู่ alternative flow กับ main flow */

        //graph.display(false);  //ตั้ง false เพื่อให้ไม่กราฟเคลื่อนที่
        Viewer viewer = graph.display(false);
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
        graph.addAttribute("ui.screenshot", "C:/Users/5730213082/Desktop/graph.png");

    }

}
