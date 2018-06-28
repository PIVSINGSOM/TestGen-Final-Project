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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author 5730213082
 */
public class Testcase {

    public static ArrayList RandomMatch = new ArrayList<>();

    ArrayList CoditionRange = new ArrayList<>();
    ArrayList CoditionRangeMax = new ArrayList<>();
    ArrayList CoditionOrdinal = new ArrayList<>();
    ArrayList Action = new ArrayList<>();
    ArrayList Patition = new ArrayList<>();
    ArrayList ArrInvalid = new ArrayList<>();
    ArrayList Varname = new ArrayList<>();
    ArrayList VarnameMatch = new ArrayList<>();

    ArrayList CoditionLength = new ArrayList<>();
    ArrayList CoditionID = new ArrayList<>();
    ArrayList ActionID = new ArrayList<>();

    ArrayList Columname = new ArrayList<>();

    int minTest;
    int maxTest;

    int IntMin = 0;
    int IntMax = 0;

    Home homeClass = new Home();

    public void Read() {
        try {
            ArrayList InputMin = new ArrayList<>();
            ArrayList InputMax = new ArrayList<>();
            ArrayList InputValue = new ArrayList<>();
            ArrayList OutputValue = new ArrayList<>();

            ArrayList DecistionID = new ArrayList<>();

            Varname.add("Testcase");
            homeClass.Varname.add("Testcase");
            VarnameMatch.add("Testcase");
            Varname.add("Partition");
            //homeClass.Varname.add("Partition");

            String varType = "";
            String id = "";
            String varName = "";
            String testType = "";
            String varData = "";
            String Decistion_ID = "";
            String Codition_ID = "";
            String Action_ID = "";
            String conID = "";
            String acID;

            int data = 0;

            //FileLocationData = FLData.getText();
            //FileLocationDis = FLD.getText();
            // TODO Auto-generated method stub
            File fXmlFile = new File(homeClass.getFileLocationData());
            File DisXmlFile = new File(homeClass.getFileLocationDis());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            DocumentBuilderFactory disFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder disBuilder = disFactory.newDocumentBuilder();
            Document dt = disBuilder.parse(DisXmlFile);
            dt.getDocumentElement().normalize();
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());	

            //access to node "Input"
            NodeList DECISION = dt.getElementsByTagName("Decision");
            // System.out.println(DECISION.getLength());

            /*System.out.println("***********************************************");
            System.out.println("                   Disition Tree               ");
            System.out.println("***********************************************");
             */
            for (int i = 0; i < DECISION.getLength(); i++) {
                Node node = DECISION.item(i);
                //System.out.print(node.getNodeName() + " : ");
                Element eElement = (Element) node;
                //System.out.println("" + eElement.getAttribute("id"));

                NodeList CONDITION = eElement.getElementsByTagName("Condition");
                Decistion_ID = eElement.getAttribute("id");

                int DId = Integer.parseInt(Decistion_ID);
                DecistionID.add(DId);
                //System.out.println("DID "+Decistion_ID);

                for (int j = 0; j < CONDITION.getLength(); j++) {
                    CoditionLength.add(CONDITION.getLength());
                    Node nodeChild = CONDITION.item(j);
                    Element eElementchild = (Element) nodeChild;
                    //System.out.println("  " + nodeChild.getNodeName() + " id : " + eElementchild.getAttribute("refid"));

                    NodeList ACTION = eElementchild.getElementsByTagName("ACTION");
                    Codition_ID = eElementchild.getAttribute("refid");

                    int CId = Integer.parseInt(Codition_ID);
                    CoditionID.add(CId);
                    //Patition.add(CId);

                    int k = 0;
                    if (j == CONDITION.getLength() - 1) {
                        Node nodeChild1 = ACTION.item(k);
                        Element eElementAction = (Element) nodeChild1;
                        //System.out.println("  " + nodeChild1.getNodeName() + "    id : " + eElementAction.getAttribute("refid"));
                        Action_ID = eElementAction.getAttribute("refid");

                        int AId = Integer.parseInt(Action_ID);
                        ActionID.add(AId);
                        // Patition.add(AId);
                        k++;
                    }

                }

            }

            NodeList ndInput = doc.getElementsByTagName("Input");
            NodeList ndOutput = doc.getElementsByTagName("Output");

            for (int c = 0; c < CoditionID.size(); c++) {

                int ConID = (int) CoditionID.get(c);
                conID = String.valueOf(ConID);

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
                                    // System.out.println("Variable Name: " + varName);

                                    if (c == 1) {

                                        Varname.add(varName);
                                        homeClass.Varname.add(varName);
                                        VarnameMatch.add(varName);

                                    }
                                }
                                if (nodeInputChild.getNodeName() == "Type") {
                                    varType = nodeInputChild.getTextContent();

                                }
                                if (nodeInputChild.getNodeName() == "Dataset") {
                                    varData = nodeInputChild.getTextContent();

                                }

                                if (nodeInputChild.getNodeName() == "Condition") {

                                    if (varType.equals("Range")) {
                                        if (eElement.getAttribute("id").equals(conID)) {

                                            String min = eElement.getAttribute("min");
                                            int Min = Integer.parseInt(min);
                                            CoditionRange.add(Min);

                                            String max = eElement.getAttribute("max");
                                            int Max = Integer.parseInt(max);
                                            CoditionRangeMax.add(Max);
                                        }
                                    } else if (varType.equals("Ordinal")) {

                                        if (eElement.getAttribute("id").equals(conID)) {

                                            String Value = eElement.getAttribute("value");

                                            CoditionOrdinal.add(Value);
                                            homeClass.CoditionOrdinal.add(Value);
                                            //System.out.println(CoditionOrdinal);

                                        }
                                        String Value = eElement.getAttribute("value");
                                        String Min = eElement.getAttribute("min");
                                        String Max = eElement.getAttribute("max");
                                        //String Var = nodeInputChild.getTextContent();
                                        data = Integer.parseInt(varData);
                                        InputValue.add(Value);
                                        InputMin.add(Min);
                                        InputMax.add(Max);

                                    }

                                }

                            }

                        }

                        InputValue.clear();
                        InputMin.clear();
                        InputMax.clear();

                    }
                }//end of for(int i=0;i<ndInput.getLength();i++)
            }
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

                                Varname.add(varName);
                                homeClass.Varname.add(varName);
                                VarnameMatch.add(varName);
                                Varname.add("Coverage");
                                homeClass.Varname.add("Coverage");
                                VarnameMatch.add("Coverage");
                                Varname.add("Comment");
                                homeClass.Varname.add("Comment");
                                Varname.add("Summary");
                                

                            }
                            if (nodeOutputChild.getNodeName() == "Type") {
                                varType = nodeOutputChild.getTextContent();

                            }

                            if (nodeOutputChild.getNodeName() == "Dataset") {
                                varData = nodeOutputChild.getTextContent();

                            }

                            for (int a = 0; a < ActionID.size(); a++) {

                                int AcID = (int) ActionID.get(a);
                                acID = String.valueOf(AcID);

                                if (nodeOutputChild.getNodeName() == "Action") {
                                    if (varType.equals("Range")) {
                                        System.out.print("id : " + eElement.getAttribute("id"));
                                        System.out.print(" min : " + eElement.getAttribute("min"));
                                        System.out.print(" max : " + eElement.getAttribute("max"));
                                    } else if (varType.equals("Ordinal")) {
                                        if (eElement.getAttribute("id").equals(acID)) {

                                            String value = eElement.getAttribute("value");
                                            int Value = Integer.parseInt(value);
                                            Action.add(Value);
                                            homeClass.Action.add(Value);

                                            data = Integer.parseInt(varData);
                                            OutputValue.add(Value);

                                        }
                                    }

                                }
                            }

                        }

                    }

                    if (data == 1) {
                        data = data + 1;
                    }
                    for (int p = 0; p < data + 1; p++) {
                        //System.out.print(a);
                    }
                    System.out.println("");
                    for (int p = 0; p < OutputValue.size(); p++) {

                        if (p == 0) {

                        }
                        if (data == 2) {

                        } else {

                        }

                    } //end of for (int p = 0; p < OutputValue.size(); p++)

                }
                OutputValue.clear();

            }

            //loop for เพื่อดึงค่าออกมาแสดงเป็น testcase
            for (int var = 1; var < VarnameMatch.size() + 1; var++) {

                if (var == 1) {
                    System.out.print(VarnameMatch.get(0));
                }
                if (var == VarnameMatch.size()) {
                    System.out.println();
                } else {
                    System.out.print("\t" + VarnameMatch.get(var));
                }

            }
            for (int r = 0; r < CoditionRange.size(); r++) {
            }
            for (int r = 0; r < CoditionRange.size(); r++) {  //System.out.println(CoditionRange);
                if (CoditionOrdinal.size() > 0) {

                    System.out.println((r + 1) + "\t" + RandomMatch.get(r) + "\t" + CoditionOrdinal.get(r) + "\t" + Action.get(r) + "\t" + 100 * (r + 1) / (CoditionRange.size() + 3) + "%");
                    //System.out.println(RandomMatch);
                } else {
                    System.out.println((r + 1) + "\t" + RandomMatch.get(r) + "\t" + Action.get(r) + "\t" + 100 * (r + 1) / (CoditionRange.size() + 3) + "%");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(RandomMatch);
        System.out.println("!!! This is not completed. !!!");
        System.out.println("!!! If you want to complete, click the Export to Excel button. !!!");
        
        
    }

    public void runTestcase() {
        //Read();
        try {

            PrintStream myconsole = new PrintStream(new File("src\\etc\\Dissistion.txt"));

            System.setOut(myconsole);
            Read();

        } catch (Exception e) {
            //jTextArea1.setText("ERROR2");
        }

        try {

            FileReader reader = new FileReader("src\\etc\\Dissistion.txt");
            BufferedReader br = new BufferedReader(reader);
            //jTextArea1.read(br, null);
            br.close();
            //jTextArea1.requestFocus();
            //jTextArea1.setSelectedTextColor(Color.YELLOW);
            //ShowECP.setBackground(Color.red);
        } catch (Exception e) {
            //jTextArea1.setText("ERROR3");
        }
    }

    public void testData() {

    }

    public void exportExcelFile() {
        JFileChooser savefile = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("xls", "xls");

        savefile.setFileFilter(filter);

        int returnVal = savefile.showSaveDialog(savefile);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            try {

                // FileWriter fw = new FileWriter(filter+".xls");
                //FileWriter fw;
                //fw = new FileWriter(savefile.getSelectedFile() + ".xls");
                String fileName = savefile.getSelectedFile() + ".xls".toString();

                WritableWorkbook workbook = Workbook.createWorkbook(new File(fileName));

                //*** Create Font ***//
                WritableFont fontBlue = new WritableFont(WritableFont.TIMES, 10);
                fontBlue.setColour(Colour.BLUE);

                WritableFont fontRed = new WritableFont(WritableFont.TIMES, 10);
                fontRed.setColour(Colour.RED);

                WritableFont.FontName wingdingsFont = WritableFont.createFont("Wingdings");
                WritableFont Fontwingdings = new WritableFont(wingdingsFont, 11);
                WritableCellFormat wingdings = new WritableCellFormat(Fontwingdings);
                Fontwingdings.setColour(Colour.BLUE);

                WritableFont.FontName wingdingsFont1 = WritableFont.createFont("Wingdings");
                WritableFont Fontwingdings1 = new WritableFont(wingdingsFont, 11);
                WritableCellFormat wingdings1 = new WritableCellFormat(Fontwingdings1);
                Fontwingdings1.setColour(Colour.RED);

                //*** Create Format ***//*
                WritableCellFormat cellFormat1 = new WritableCellFormat(fontBlue);
                cellFormat1.setWrap(true);
                cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
                cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
                cellFormat1.setWrap(true);
                cellFormat1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
                        jxl.format.Colour.BLACK);

                WritableCellFormat cellFormat2 = new WritableCellFormat(fontRed);

                cellFormat2.setBackground(Colour.ORANGE);
                cellFormat2.setAlignment(Alignment.CENTRE);
                cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
                cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);

                WritableCellFormat cellFormatWingding = new WritableCellFormat(wingdings);
                cellFormatWingding.setWrap(true);
                cellFormatWingding.setAlignment(jxl.format.Alignment.CENTRE);
                cellFormatWingding.setVerticalAlignment(VerticalAlignment.CENTRE);
                cellFormatWingding.setWrap(true);
                cellFormatWingding.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
                        jxl.format.Colour.BLACK);

                WritableCellFormat cellFormatWingding1 = new WritableCellFormat(wingdings1);
                cellFormatWingding1.setWrap(true);
                cellFormatWingding1.setAlignment(jxl.format.Alignment.CENTRE);
                cellFormatWingding1.setVerticalAlignment(VerticalAlignment.CENTRE);
                cellFormatWingding1.setWrap(true);
                cellFormatWingding1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
                        jxl.format.Colour.BLACK);

                int a = 0;
                int j = 0;
                int k = 1;

                //*** Sheet 1 ***//*
                WritableSheet ws1 = workbook.createSheet("mySheet1", 0);
                // Column 1

                //String minminmin = "" + CoditionRange.get(0);  //ดึงค่าMinที่น้อยที่สุด
                //String maxmaxmax = "" + CoditionRangeMax.get(CoditionRangeMax.size() - 1); //ดึงค่าMaxที่มากที่สุด
                //int minTest = Integer.parseInt(minminmin);
                //int maxTest = Integer.parseInt(maxmaxmax);
                //testData();
                String minminmin = "" + CoditionRange.get(0);  //ดึงค่าMinที่น้อยที่สุด
                String maxmaxmax = "" + CoditionRangeMax.get(CoditionRangeMax.size() - 1); //ดึงค่าMaxที่มากที่สุด

                int minTest = Integer.parseInt(minminmin);
                int maxTest = Integer.parseInt(maxmaxmax);

                homeClass.minTest = minTest;
                homeClass.maxTest = maxTest;

                if (minTest > 0) {

                    CoditionRange.add(-200);
                    CoditionRange.add(minTest / 2);
                    CoditionRange.add(maxTest + (maxTest / 2));

                    homeClass.CoditionRange.add(-200);
                    homeClass.CoditionRange.add(minTest / 2);
                    homeClass.CoditionRange.add(maxTest + (maxTest / 2));

                    CoditionID.add("-");
                    CoditionID.add("-");
                    CoditionID.add("-");

                    homeClass.CoditionID.add("-");
                    homeClass.CoditionID.add("-");
                    homeClass.CoditionID.add("-");

                    if (CoditionOrdinal.size() > 0) {
                        CoditionID.add("-");
                        CoditionID.add("-");
                        CoditionID.add("-");

                        homeClass.CoditionID.add("-");
                        homeClass.CoditionID.add("-");
                        homeClass.CoditionID.add("-");

                        CoditionOrdinal.add(CoditionOrdinal.get(0));
                        CoditionOrdinal.add(CoditionOrdinal.get(0));
                        CoditionOrdinal.add(CoditionOrdinal.get(0));

                        homeClass.CoditionOrdinal.add(CoditionOrdinal.get(0));
                        homeClass.CoditionOrdinal.add(CoditionOrdinal.get(0));
                        homeClass.CoditionOrdinal.add(CoditionOrdinal.get(0));
                    }

                    Action.add(Action.get(0));
                    Action.add(Action.get(0));
                    Action.add(Action.get(0));

                    homeClass.Action.add(Action.get(0));
                    homeClass.Action.add(Action.get(0));
                    homeClass.Action.add(Action.get(0));

                    ActionID.add("-");
                    ActionID.add("-");
                    ActionID.add("-");

                    homeClass.ActionID.add("-");
                    homeClass.ActionID.add("-");
                    homeClass.ActionID.add("-");

                    //CoditionID.add("-") and ActionID.add("-"); สร้างไว้ในกรณีที่ใช้เพื่อแสดงในpartitionที่เป็นinvalid
                } //if (minTest > 0) เพิ่มค่า invalid ในกรณีที่ค่าเริ่มต้นมากกว่า 0

                if (minTest < 0) {

                    CoditionRange.add(minTest - 20);
                    CoditionID.add("-");

                    homeClass.CoditionRange.add(minTest - 20);
                    homeClass.CoditionID.add("-");

                    if (maxTest < 0) {
                        CoditionRange.add(maxTest - (maxTest / 2));
                        CoditionID.add("-");

                        homeClass.CoditionRange.add(maxTest - (maxTest / 2));
                        homeClass.CoditionID.add("-");

                    }
                    if (maxTest > 0) {
                        CoditionRange.add(maxTest + (maxTest / 2));
                        CoditionID.add("-");

                        homeClass.CoditionRange.add(maxTest + (maxTest / 2));
                        homeClass.CoditionID.add("-");
                    }
                    CoditionOrdinal.add(CoditionOrdinal.get(0));
                    CoditionOrdinal.add(CoditionOrdinal.get(0));
                    CoditionID.add("-");
                    CoditionID.add("-");

                    homeClass.CoditionOrdinal.add(CoditionOrdinal.get(0));
                    homeClass.CoditionOrdinal.add(CoditionOrdinal.get(0));
                    homeClass.CoditionID.add("-");
                    homeClass.CoditionID.add("-");

                    Action.add(Action.get(0));
                    Action.add(Action.get(0));
                    ActionID.add("-");
                    ActionID.add("-");

                    homeClass.Action.add(Action.get(0));
                    homeClass.Action.add(Action.get(0));
                    homeClass.ActionID.add("-");
                    homeClass.ActionID.add("-");

                } //if (minTest < 0) เพิ่มค่า invalid ในกรณีที่ค่าเริ่มต้นน้อยกว่า 0

                if (minTest == 0) {

                    CoditionRange.add(minTest - 20);
                    CoditionRange.add(maxTest + (maxTest / 2));

                    homeClass.CoditionRange.add(minTest - 20);
                    homeClass.CoditionRange.add(maxTest + (maxTest / 2));

                    CoditionOrdinal.add(CoditionOrdinal.get(0));
                    CoditionOrdinal.add(CoditionOrdinal.get(0));
                    CoditionID.add("-");
                    CoditionID.add("-");
                    CoditionID.add("-");
                    CoditionID.add("-");

                    homeClass.CoditionOrdinal.add(CoditionOrdinal.get(0));
                    homeClass.CoditionOrdinal.add(CoditionOrdinal.get(0));
                    homeClass.CoditionID.add("-");
                    homeClass.CoditionID.add("-");
                    homeClass.CoditionID.add("-");
                    homeClass.CoditionID.add("-");

                    Action.add(Action.get(0));
                    Action.add(Action.get(0));
                    ActionID.add("-");
                    ActionID.add("-");

                    homeClass.Action.add(Action.get(0));
                    homeClass.Action.add(Action.get(0));
                    homeClass.ActionID.add("-");
                    homeClass.ActionID.add("-");

                } //if (minTest == 0) เพิ่มค่า invalid ในกรณีที่ค่าเริ่มต้นเท่ากับ 0

                for (int i = 0; i < CoditionRange.size(); i++) {

                    // for (int j = 0; j < CoditionOrdinal; j++) {
                    // ws1.setColumnView(j, 15);
                    int l = (int) CoditionLength.get(i);
                    for (int ws = 0; ws < Varname.size(); ws++) {
                        ws1.addCell(new Label(ws, 0, "" + Varname.get(ws), cellFormat1));
                        RandomMatch.add(Varname.get(ws));
                    }

                    //String minminmin = "" + CoditionRange.get(0);
                    // int minTest = Integer.parseInt(minminmin);
                    if (minTest > 0) {

                        ws1.addCell(new Label(0, a + 1, "" + (a + 1), cellFormat1));
                        ws1.addCell(new Label(1, a + 1, " " + CoditionID.get(j) + " ," + CoditionID.get(k) + " ," + ActionID.get(i), cellFormat1));

                        ws1.addCell(new Label(2, a + 1, "" + RandomMatch.get(i), cellFormat1));

                        if (CoditionOrdinal.size() > 0) {
                            ws1.addCell(new Label(3, a + 1, "" + CoditionOrdinal.get(i), cellFormat1));
                        }
                        ws1.addCell(new Label(4, a + 1, "" + Action.get(i), cellFormat1));
                        ws1.addCell(new Label(5, a + 1, "" + 100 * (a + 1) / CoditionRange.size() + "%", cellFormat1));

                        if (a < CoditionRange.size() - 3) {

                            ws1.addCell(new Label(6, a + 1, "Valid input", cellFormat1));
                            ws1.addCell(new Label(7, a + 1, "ü", cellFormatWingding));

                        }
                        if (a >= CoditionRange.size() - 3) {
                            ws1.addCell(new Label(6, a + 1, "InValid" + " " + Varname.get(2), cellFormat1));
                            ws1.addCell(new Label(7, a + 1, "û", cellFormatWingding1));
                        }

                        //  a++;
                    }//if (minTest > 0) สร้างตาราง excell ในกรณีที่ค่าน้อยที่สุดมากกว่า 0

                    if (minTest < 0) {

                        ws1.addCell(new Label(0, a + 1, "" + a, cellFormat1));
                        ws1.addCell(new Label(1, a + 1, " " + CoditionID.get(j) + " ," + CoditionID.get(k) + " ," + ActionID.get(i), cellFormat1));

                        ws1.addCell(new Label(2, a + 1, "" + CoditionRange.get(i), cellFormat1));
                        ws1.addCell(new Label(3, a + 1, "" + CoditionOrdinal.get(i), cellFormat1));
                        ws1.addCell(new Label(4, a + 1, "" + Action.get(i), cellFormat1));
                        ws1.addCell(new Label(5, a + 1, "" + 100 * (a + 1) / CoditionRange.size() + "%", cellFormat1));
                        if (a < CoditionRange.size() - 2) {
                            ws1.addCell(new Label(6, a + 1, "Valid input", cellFormat1));
                            ws1.addCell(new Label(7, a + 1, "ü", cellFormatWingding));
                        }
                        if (a >= CoditionRange.size() - 2) {
                            ws1.addCell(new Label(6, a + 1, "InValid" + " " + Varname.get(2), cellFormat1));
                            ws1.addCell(new Label(7, a + 1, "û", cellFormatWingding1));
                        }
                        //   a++;
                        //  j = j +l;
                        //   k=j+1;

                    }//if (minTest > 0) สร้างตาราง excell ในกรณีที่ค่าน้อยที่สุดน้อยกว่า 0

                    if (minTest == 0) {

                        ws1.addCell(new Label(0, a + 1, "" + a, cellFormat1));
                        //ws1.addCell(new Label(1, a+1, " " + CoditionID.get(j)+ " ," + CoditionID.get(k) +" ,"+ ActionID.get(i), cellFormat1));

                        ws1.addCell(new Label(2, a + 1, "" + CoditionRange.get(i), cellFormat1));
                        ws1.addCell(new Label(3, a + 1, "" + CoditionOrdinal.get(i), cellFormat1));
                        ws1.addCell(new Label(4, a + 1, "" + Action.get(i), cellFormat1));
                        ws1.addCell(new Label(5, a + 1, "" + 100 * (a + 1) / CoditionRange.size() + "%", cellFormat1));
                        if (a < CoditionRange.size() - 3) {
                            ws1.addCell(new Label(6, a + 1, "Valid input", cellFormat1));
                            ws1.addCell(new Label(7, a + 1, "ü", cellFormatWingding));
                        }
                        if (a >= CoditionRange.size() - 3) {
                            ws1.addCell(new Label(6, a + 1, "InValid" + " " + Varname.get(2), cellFormat1));
                            ws1.addCell(new Label(7, a + 1, "û", cellFormatWingding1));
                        }

                    }//if (minTest == 0) สร้างตาราง excell ในกรณีที่ค่าน้อยที่สุดเท่ากับ 0

                    a++;
                    j = j + l;
                    k = k + 1;
                }

                //** Merge Cell ***//*
                // ws1.mergeCells(0, 4, 1, 4); // Merge col[0-1] and row[4]
                //  Label lable = new Label(0, 4,"ThaiCreate.Com", cellFormat2);
                //  ws1.addCell(lable);
                workbook.write();
                workbook.close();

                System.out.println("Excel file created.");
                JOptionPane.showMessageDialog(null, "Save succeed");
                /*final ImageIcon icon = new ImageIcon(getClass().getResource("icon.ico"));
                JOptionPane.showMessageDialog(null,
                        "Eggs are not supposed to be green.",
                        "Inane custom dialog",
                        JOptionPane.INFORMATION_MESSAGE,
                        icon);*/

                //fw.close();
            } catch (Exception ex) {
                
                int n = JOptionPane.showConfirmDialog(
                        null,
                        "Excel file error you want to save?",
                        "Error",
                        JOptionPane.YES_NO_OPTION);

                /*JOptionPane.showMessageDialog(null,
                    "File not save",
                    "Error",
                    JOptionPane.ERROR_MESSAGE );*/
                ex.printStackTrace();
            }

        }
        System.out.println(CoditionRange);

    }

}
