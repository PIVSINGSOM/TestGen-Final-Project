/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newgui;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author 5730213082
 */
public class PartitionECP {

    Home homeClass = new Home();
    ExcelWrite excelW = new ExcelWrite();

    public ArrayList Random = new ArrayList<>();
    public static ArrayList ucID = new ArrayList<>();
    public static ArrayList ucName = new ArrayList<>();
    ArrayList Random1 = new ArrayList<>();
    ArrayList Type = new ArrayList<>();
    ArrayList ErrorP = new ArrayList<>();
    ArrayList PatitionECP = new ArrayList<>();
    ArrayList partitionSize = new ArrayList();

    public void Read() {
        homeClass.ucID.clear();
        homeClass.ucName.clear();
        excelW.ucName.clear();
        System.out.format("a Testing Generation Tool for Supporting System and Acceptance Testing Environment");

        //ArrayList<Integer> InputMin = new ArrayList<Integer>();
        //ArrayList<Integer> InputMax = new ArrayList<Integer>();
        ArrayList<Integer> ValMax = new ArrayList<>();
        ArrayList<Integer> ValMin = new ArrayList<>();
        ArrayList InputMin = new ArrayList<Integer>();
        ArrayList InputMax = new ArrayList<Integer>();
        ArrayList InputValue = new ArrayList<>();
        ArrayList OutputValue = new ArrayList<>();

        String aa = "|▓▓▓▓▓▓▓▓▓|";
        String a = "|█████████|";

        String b = "              ";
        String c = "            ";
        int IntMin = 0;
        int IntMax = 0;
        int data = 0;
        int randomMin;
        try {
            String varType = "";
            String varName = "";
            String testType = "";
            String varData = "";

            String Err = "";

            // TODO Auto-generated method stub
            File fXmlFile = new File(homeClass.getFileLocationData());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());	

            //access to node "Input"
            NodeList ndInput = doc.getElementsByTagName("Input");
            NodeList ndOutput = doc.getElementsByTagName("Output");
            NodeList ndUCname = doc.getElementsByTagName("UC");

            for (int uc = 0; uc < ndUCname.getLength(); uc++) {
                org.w3c.dom.Node node = ndUCname.item(uc);
                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    testType = node.getNodeName();
                    NodeList ndInputChild = node.getChildNodes();

                    //access to child nodes of node "Input"
                    for (int j = 0; j < ndInputChild.getLength(); j++) {
                        org.w3c.dom.Node nodeInputChild = ndInputChild.item(j);
                        if (nodeInputChild.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                            org.w3c.dom.Element eElement = (org.w3c.dom.Element) nodeInputChild;

                            if (nodeInputChild.getNodeName() == "Usecase") {
                                String usecaseID = eElement.getAttribute("id");

                                ucID.add(usecaseID);
                                homeClass.ucID.add(usecaseID);

                                String usecaseName = eElement.getAttribute("name");

                                ucName.add(usecaseName);
                                homeClass.ucName.add(usecaseName);
                                excelW.ucName.add(usecaseName);
                                
                            }
                        }
                    }
                }

            }
            System.out.println(ucID + "" + ucName);

            for (int i = 0; i < ndInput.getLength(); i++) {
                Node node = ndInput.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    testType = node.getNodeName();
                    NodeList ndInputChild = node.getChildNodes();

                    //access to child nodes of node "Input"
                    for (int j = 0; j < ndInputChild.getLength(); j++) {
                        Node nodeInputChild = ndInputChild.item(j);
                        if (nodeInputChild.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nodeInputChild;

                            if (nodeInputChild.getNodeName() == "Varname") {
                                varName = nodeInputChild.getTextContent();
                                System.out.println("Variable Name: " + varName);

                            }
                            if (nodeInputChild.getNodeName() == "Type") {
                                varType = nodeInputChild.getTextContent();
                                String Value = eElement.getAttribute("value");

                                System.out.println("Variable Type: " + varType);
                                System.out.println("Test Type: " + testType);
                            }
                            if (nodeInputChild.getNodeName() == "Dataset") {
                                varData = nodeInputChild.getTextContent();
                                System.out.println("Dataset: " + varData);

                            }

                            if (nodeInputChild.getNodeName() == "Condition") {
                                if (varType.equals("Range")) {
                                    System.out.print("id : " + eElement.getAttribute("id"));
                                    System.out.print(" min : " + eElement.getAttribute("min"));
                                    System.out.print(" max : " + eElement.getAttribute("max"));
                                } else if (varType.equals("Ordinal")) {
                                    String varO = eElement.getAttribute("value");
                                    System.out.print("id : " + eElement.getAttribute("id"));
                                    System.out.print(" value : " + eElement.getAttribute("value"));
                                    Type.add(varO);
                                }
                                String Value = eElement.getAttribute("value");
                                String Min = eElement.getAttribute("min");
                                String Max = eElement.getAttribute("max");

                                String MINVAL = eElement.getAttribute("min");
                                String MAXVAL = eElement.getAttribute("max");
                                data = Integer.parseInt(varData);
                                InputValue.add(Value);
                                InputMin.add(Min);
                                InputMax.add(Max);
                                partitionSize.add(Min);
                                if (i < 1) {
                                    Integer min = Integer.parseInt(MINVAL);
                                    Integer max = Integer.parseInt(MAXVAL);

                                    ValMin.add(min);
                                    ValMax.add(max);
                                }
                                System.out.println("");

                            }

                        }
                    }

                    boolean error = false;
                    int index = 0;

                    for (int ip = 0; ip < ValMax.size(); ip++) {
                        if (ValMin.get(ip) > ValMax.get(ip)) {
                            error = true;
                        }

                    }

                    ErrorP.add("✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶");
                    ErrorP.add("                                          ");
                    ErrorP.add("Eror type 1: " + error);
                    ErrorP.add("#" + index + " :minVal" + "<" + (index) + "> =" + ValMin.get(index) + " maxVal" + "<" + (index) + "> =" + ValMax.get(index));
                    while (/*(error == false) && */(index < (ValMax.size() - 1))) {
                        if (ValMin.get(index + 1) < ValMax.get(index)) {
                            error = true;
                            ErrorP.add("Eror type 2: " + error);
                            ErrorP.add("#" + index + " :minVal" + "<" + (index + 1) + "> =" + ValMin.get(index + 1) + " maxVal" + "<" + index + "> =" + ValMax.get(index));
                        }

                        index = index + 1;
                    }

                    if (error == false) {
                        ErrorP.add("OK, valid partition range");
                        ErrorP.add("                                          ");
                        ErrorP.add("✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶");

                        /* for (int ip = 0; ip < InputMax.size(); ip++) {
                        if (Integer.parseInt((String) InputMin.get(ip)) > Integer.parseInt((String) InputMax.get(ip))) {
                            error = true;
                        }

                    }
                    System.out.println("==========================================");
                    System.out.println("                                          ");
                    System.out.println("Eror type 1: " + error);
                    while ((error == false) && (index < (InputMax.size() - 1))) {
                        if (Integer.parseInt((String) InputMin.get(index + 1)) < Integer.parseInt((String) InputMax.get(index))) {
                            error = true;
                        }
                        System.out.println("Eror type 2: " + error);
                        System.out.println("#" + index + " :minVal=" + InputMin.get(index + 1) + " maxVal=" + InputMax.get(index));
                        index = index + 1;
                        
                    }
                    if (error == false) {
                        System.out.println("OK, valid partition range");
                        System.out.println("                                          ");
                        System.out.println("==========================================");*/
                        if (varType.equals("Ordinal")) {
                            if (data == 1) {
                                data = data + 1;
                            }
                            for (int p = 0; p < data + 1; p++) {
                                System.out.print(a);
                            }
                            System.out.println("");
                            for (int p = 0; p < InputValue.size(); p++) {

                                if (p == 0) {
                                    System.out.print("      ");
                                }
                                if (data == 2) {
                                    System.out.print(InputValue.get(p) + c);
                                    //System.out.print(data);
                                } else {
                                    System.out.print(InputValue.get(p) + c);
                                }

                            } //end of for (int p = 0; p < OutputValue.size(); p++)
                            System.out.print("None");
                        } else if (varType.equals("Range")) {
                            if (data == 1) {
                                data = data + 1;
                            }
                            for (int p = 0; p < data + 1; p++) {
                                System.out.print(a);
                            }
                            System.out.println("");
                            for (int p = 0; p < InputMin.size(); p++) {

                                if (p == 0) {
                                    System.out.print("                ");
                                }
                                if (data == 2) {
                                    System.out.print(InputMax.get(p) + c);
                                    //System.out.print(data);
                                } else {
                                    System.out.print(InputMin.get(p) + c);
                                }

                            } //end of for (int p = 0; p < OutputValue.size(); p++)
                            //System.out.print("None");
                        }

                        InputValue.clear();
                        InputMin.clear();
                        InputMax.clear();
                        System.out.println("");
                    } else {

                        ErrorP.add("Not OK, invalid partition range");
                        ErrorP.add("                                          ");
                        ErrorP.add("✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶");

                    }
                    Err = "" + error;

                    System.out.println(Err);
                }
            }//end of for(int i=0;i<ndInput.getLength();i++)

            //access to node "Output"
            if (Err.equals("false")) {
                System.out.println(Err);

                for (int i = 0; i < ndOutput.getLength(); i++) {
                    Node node = ndOutput.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        testType = node.getNodeName();
                        NodeList ndOutputChild = node.getChildNodes();

                        //access to child nodes of node "Input"
                        for (int j = 0; j < ndOutputChild.getLength(); j++) {
                            Node nodeOutputChild = ndOutputChild.item(j);
                            if (nodeOutputChild.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElement = (Element) nodeOutputChild;

                                if (nodeOutputChild.getNodeName() == "Varname") {
                                    varName = nodeOutputChild.getTextContent();
                                    System.out.println("Variable Name: " + varName);

                                }
                                if (nodeOutputChild.getNodeName() == "Type") {
                                    varType = nodeOutputChild.getTextContent();
                                    System.out.println("Variable Type: " + varType);
                                    System.out.println("Test Type: " + testType);
                                }

                                if (nodeOutputChild.getNodeName() == "Dataset") {
                                    varData = nodeOutputChild.getTextContent();
                                    System.out.println("Dataset: " + varData);

                                }

                                if (nodeOutputChild.getNodeName() == "Action") {
                                    if (varType.equals("Range")) {
                                        System.out.print("id : " + eElement.getAttribute("id"));
                                        System.out.print(" min : " + eElement.getAttribute("min"));
                                        System.out.print(" max : " + eElement.getAttribute("max"));
                                    } else if (varType.equals("Ordinal")) {
                                        System.out.print("id : " + eElement.getAttribute("id"));
                                        System.out.print(" value : " + eElement.getAttribute("value"));
                                        String Value = eElement.getAttribute("value");

                                        data = Integer.parseInt(varData);
                                        OutputValue.add(Value);

                                    }

                                    System.out.println("");

                                }

                            }

                        }

                        if (data == 1) {
                            data = data + 1;
                        }
                        for (int p = 0; p < data + 1; p++) {
                            System.out.print(a);
                        }
                        System.out.println("");
                        for (int p = 0; p < OutputValue.size(); p++) {

                            if (p == 0) {
                                System.out.print("         ");
                            }
                            if (data == 2) {
                                System.out.print(OutputValue.get(p) + b);
                                //System.out.print(data);
                            } else {
                                System.out.print(OutputValue.get(p) + b);
                            }

                        } //end of for (int p = 0; p < OutputValue.size(); p++)
                        System.out.print("None");

                    }
                    OutputValue.clear();
                    System.out.println("");
                }//end of for(int i=0;i<ndOutput.getLength();i++)
            }
            System.out.println(Type);
            for (int ErrP = 0; ErrP < ErrorP.size() / 2; ErrP++) {
                System.out.println(ErrorP.get(ErrP));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    } //end of Read() Method

    public void Random() {
        ArrayList InputMin = new ArrayList<>();
        ArrayList InputMax = new ArrayList<>();
        ArrayList InputValue = new ArrayList<>();
        ArrayList OutputValue = new ArrayList<>();

        String aa = "|▒▒▒▒▒▒▒▒▒|";
        String asaa = "|█████████|";
        String a = "|█████████|";
        String b = "              ";
        String bb = "              ";
        String c = "   ";
        String d = "          ";
        String ddd = "             ";
        String dd = "      ";
        int IntMin = 0;
        int IntMax = 0;
        int data = 0;
        try {
            String varType = "";
            String varName = "";
            String testType = "";
            String varData = "";
            int randomMin;
            int randomMax;
            int random = 0;
            int random1 = 0;
            int random2 = 0;
            int random3 = 0;
            int PrintMax = 0;

            int randomECP;

            // TODO Auto-generated method stub
            File fXmlFile = new File(homeClass.getFileLocationData());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());	

            //access to node "Input"
            NodeList ndInput = doc.getElementsByTagName("Input");
            NodeList ndOutput = doc.getElementsByTagName("Output");

            for (int i = 0; i < ndInput.getLength(); i++) {
                Node node = ndInput.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    testType = node.getNodeName();
                    NodeList ndInputChild = node.getChildNodes();

                    //access to child nodes of node "Input"
                    for (int j = 0; j < ndInputChild.getLength(); j++) {
                        Node nodeInputChild = ndInputChild.item(j);
                        if (nodeInputChild.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nodeInputChild;

                            if (nodeInputChild.getNodeName() == "Varname") {
                                varName = nodeInputChild.getTextContent();
                                System.out.println("Variable Name: " + varName);

                            }
                            if (nodeInputChild.getNodeName() == "Type") {
                                varType = nodeInputChild.getTextContent();
                                System.out.println("Variable Type: " + varType);
                                System.out.println("Test Type: " + testType);
                            }
                            if (nodeInputChild.getNodeName() == "Dataset") {
                                varData = nodeInputChild.getTextContent();

                            }

                            if (nodeInputChild.getNodeName() == "Condition") {
                                if (varType.equals("Range")) {
                                    String Min = eElement.getAttribute("min");
                                    String Max = eElement.getAttribute("max");
                                    IntMin = Integer.parseInt(Min);
                                    IntMax = Integer.parseInt(Max);
                                } else if (varType.equals("Ordinal")) {
                                    String varO = eElement.getAttribute("value");
                                    System.out.print("id : " + eElement.getAttribute("id"));
                                    System.out.print(" value : " + eElement.getAttribute("value"));
                                    Type.add(varO);
                                }
                                String Value = eElement.getAttribute("value");
                                String Min = eElement.getAttribute("min");
                                String Max = eElement.getAttribute("max");
                                data = Integer.parseInt(varData);
                                InputValue.add(Value);
                                InputMin.add(IntMin);
                                InputMax.add(IntMax);

                                System.out.println("");

                            }

                        }
                    }
                    if (varType.equals("Range")) {
                        if ((int) InputMin.get(0) > 0) {
                            int RanMax = (int) InputMax.get(InputMax.size() - 1);
                            PatitionECP.add("-∞");
                            PatitionECP.add(0);
                            for (int im = 0; im < InputMin.size(); im++) {
                                PatitionECP.add(InputMin.get(im));
                            }
                            PatitionECP.add(RanMax);
                            PatitionECP.add("∞");
                            Random.add((int) (Math.random() * -200) - 1);

                            Random1.add((int) (Math.random() * -200) - 1);
                            if ((int) InputMin.get(0) > 1) {
                                Random1.add((int) (Math.random() * ((int) InputMin.get(0) - 1) + 1));
                                Random.add((int) (Math.random() * ((int) InputMin.get(0) - 1) + 1));
                            } else {
                                Random1.add(0.5);
                                Random.add(0.5);
                            }
                            //Random.add((int)InputMin.get(0)/2);
                            //System.out.println(InputMin);
                            if ((int) InputMin.get(0) == 1) {
                                Random.add((int) (Math.random() * (int) InputMax.get(0)) + (int) InputMin.get(0));
                                Random1.add((int) (Math.random() * (int) InputMax.get(0)) + (int) InputMin.get(0));
                                for (int ran = 1; ran < InputMin.size(); ran++) {

                                    //randomECP = (int) (Math.random() * ((int)InputMin.get(ran)-(int)InputMax.get(ran))) + (int)InputMin.get(ran);
                                    //System.out.println(randomECP);
                                    randomECP = (int) (Math.random() * (int) InputMin.get(ran)) + (int) InputMin.get(ran);
                                    Random.add(randomECP);
                                    Random1.add(randomECP);

                                }
                            } else {
                                for (int ran = 0; ran < InputMin.size(); ran++) {

                                    //randomECP = (int) (Math.random() * ((int)InputMin.get(ran)-(int)InputMax.get(ran))) + (int)InputMin.get(ran);
                                    randomECP = (int) (Math.random() * (int) InputMin.get(ran)) + (int) InputMin.get(ran);
                                    //System.out.println(randomECP);
                                    Random.add(randomECP);
                                    Random1.add(randomECP);

                                }

                            }
                            if ((int) InputMin.get(0) > 100) {
                                Random.add((int) (Math.random() * ((int) InputMin.get(0))) + RanMax);
                                Random1.add((int) (Math.random() * ((int) InputMin.get(0))) + RanMax);
                            } else {
                                Random.add((int) (Math.random() * 100) + RanMax);
                                Random1.add((int) (Math.random() * 100) + RanMax);
                            }
                            System.out.print(dd + "<" + Random1.get(0) + ">");

                            for (int test = 1; test < Random1.size(); test++) {

                                if (test == 1 && Random1.get(test).toString().length() <= 3) {

                                    d = "             ";
                                }

                                if (test != 1 && Random1.get(test).toString().length() < 3) {

                                    d = "                          ";
                                }
                                if (Random1.get(test).toString().length() == 1) {

                                    d = "                   ";
                                }
                                if (Random1.get(test).toString().length() > 3 && Random1.get(test).toString().length() < 6) {

                                    d = "           ";
                                }
                                if (Random1.get(test).toString().length() > 6) {

                                    d = "           ";
                                }

                                System.out.print(d + "<" + Random1.get(test) + ">");

                            }

                            System.out.println();

                        }

                    }
                    if (varType.equals("Ordinal")) {
                        if (data == 1) {
                            data = data + 1;
                        }
                        for (int p = 0; p < data + 1; p++) {
                            System.out.print(a);
                        }
                        System.out.println("");
                        for (int p = 0; p < InputValue.size(); p++) {

                            if (p == 0) {
                                System.out.print("      ");
                            }
                            if (data == 2) {
                                System.out.print(InputValue.get(p) + d);
                                //System.out.print(data);
                            } else {
                                System.out.print(InputValue.get(p) + d);
                            }

                        } //end of for (int p = 0; p < OutputValue.size(); p++)
                        System.out.print("None");
                        System.out.println("");
                        System.out.println("✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶");
                    } else if (varType.equals("Range")) {
                        if (data == 1) {
                            data = data + 1;
                        }
                        System.out.print(aa);
                        //System.out.print(data);

                        for (int p = 0; p < data + 1; p++) {
                            if (p > 0) {
                                System.out.print(a);
                            } else {
                                System.out.print(aa);
                            }
                        }

                        System.out.print(aa);

                        System.out.println("");

                        System.out.print(PatitionECP.get(0));
                        System.out.print(bb + PatitionECP.get(1));
                        for (int p = 2; p < PatitionECP.size(); p++) {

                            if (p == 2 && PatitionECP.get(p).toString().length() == 4) {

                                ddd = "                ";
                            }

                            if (p != 2 && PatitionECP.get(p).toString().length() == 4) {
                                ddd = "             ";
                            }

                            if (PatitionECP.get(p).toString().length() >= 5) {

                                ddd = "            ";
                            }
                            if (PatitionECP.get(p).toString().length() < 3) {

                                ddd = "              ";
                            }
                            if (PatitionECP.get(p).toString().length() == 1) {

                                ddd = "                ";
                            }
                            if (p == (PatitionECP.size() - 1)) {

                                ddd = "             ";
                            }

                            System.out.print(ddd + PatitionECP.get(p));
                        }

                        /*for (int p = 0; p < InputMin.size(); p++) {

                            if (p == 0) {
                                System.out.print("                " + "0");
                                System.out.print("     ");
                            }
                            if (data == 2) {
                                System.out.print(InputMax.get(p) + c);

                            } else {

                                randomMin = (int) InputMin.get(p);
                                randomMax = (int) InputMax.get(p);

                                if (randomMin > 0 && randomMax > 0) {
                                    //System.out.print("RANDOM: " + r);
                                    random1 = (int) (Math.random() * (randomMin));
                                    random2 = (int) (Math.random() * (randomMin)) + randomMin;

                                }

                                if (p == 0) {
                                    System.out.print("<" + random1 + ">" + c);

                                }
                                if (p == 2) {
                                    PrintMax = (int) InputMax.get(p);
                                    random3 = (int) (Math.random() * (randomMin)) + PrintMax;
                                }

                                System.out.print(InputMin.get(p) + "   <" + random2 + ">" + c);

                            } //end of for (int p = 0; p < OutputValue.size(); p++)

                        }//System.out.print("None");
                        System.out.print(PrintMax + " <" + random3 + ">");*/
                        System.out.println("");
                        System.out.println("");
                        System.out.println("✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶✶");
                    }
                    Random1.clear();
                    PatitionECP.clear();
                    InputValue.clear();
                    InputMin.clear();
                    InputMax.clear();
                    System.out.println("");

                }
                //Random.clear();
            }//end of for(int i=0;i<ndInput.getLength();i++)

            //access to node "Output"
            for (int i = 0; i < ndOutput.getLength(); i++) {
                Node node = ndOutput.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    testType = node.getNodeName();
                    NodeList ndOutputChild = node.getChildNodes();

                    //access to child nodes of node "Input"
                    for (int j = 0; j < ndOutputChild.getLength(); j++) {
                        Node nodeOutputChild = ndOutputChild.item(j);
                        if (nodeOutputChild.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nodeOutputChild;

                            if (nodeOutputChild.getNodeName() == "Varname") {
                                varName = nodeOutputChild.getTextContent();
                                System.out.println("Variable Name: " + varName);

                            }
                            if (nodeOutputChild.getNodeName() == "Type") {
                                varType = nodeOutputChild.getTextContent();
                                System.out.println("Variable Type: " + varType);
                                System.out.println("Test Type: " + testType);
                            }
                            if (nodeOutputChild.getNodeName() == "Dataset") {
                                varData = nodeOutputChild.getTextContent();

                            }

                            if (nodeOutputChild.getNodeName() == "Action") {
                                if (varType.equals("Range")) {

                                } else if (varType.equals("Ordinal")) {

                                    String Value = eElement.getAttribute("value");

                                    data = Integer.parseInt(varData);
                                    OutputValue.add(Value);

                                }

                            }

                        }

                    }

                    if (data == 1) {
                        data = data + 1;
                    }
                    for (int p = 0; p < data + 1; p++) {
                        System.out.print(a);
                    }
                    System.out.println("");
                    for (int p = 0; p < OutputValue.size(); p++) {

                        if (p == 0) {
                            System.out.print("         ");
                        }
                        if (data == 2) {
                            System.out.print(OutputValue.get(p) + b);
                            //System.out.print(data);
                        } else {
                            System.out.print(OutputValue.get(p) + b);
                        }

                    } //end of for (int p = 0; p < OutputValue.size(); p++)
                    System.out.print("None");
                }
                OutputValue.clear();
                System.out.println("");

            }//end of for(int i=0;i<ndOutput.getLength();i++)

        } catch (Exception e) {
            e.printStackTrace();
        }
    } //end of Method Random

    public void PatitionECP() {
        try {

            PrintStream myconsole = new PrintStream(new File("src\\etc\\TestGen_ECP.txt"));

            System.setOut(myconsole);
            Random();

        } catch (Exception e) {
        }

    }

    public void Patition() {
        try {

            PrintStream myconsole = new PrintStream(new File("src\\etc\\TestGen_Patition.txt"));

            System.setOut(myconsole);
            Read();
            //Testcase();

        } catch (Exception e) {
        }

    }

    public void Testcase() {
        // int ranSize = Random.size() - 1;
        
        homeClass.RandomMatch.clear();
        Testcase match = new Testcase();

        for (int RanM = 2; RanM < Random.size() - 1; RanM++) {

            if (Type.size() > 0) {
                for (int RanMM = 0; RanMM < Type.size(); RanMM++) {
                    match.RandomMatch.add(Random.get(RanM));
                    homeClass.RandomMatch.add(Random.get(RanM));

                }
            } else {
                match.RandomMatch.add(Random.get(RanM));
                homeClass.RandomMatch.add(Random.get(RanM));
            }

        }

        match.RandomMatch.add(Random.get(0));
        match.RandomMatch.add(Random.get(1));
        match.RandomMatch.add(Random.get(Random.size() - 1));

        homeClass.RandomMatch.add(Random.get(0));
        homeClass.RandomMatch.add(Random.get(1));
        homeClass.RandomMatch.add(Random.get(Random.size() - 1));

    }

    public void Matrix() {
        Read();

    }

}
