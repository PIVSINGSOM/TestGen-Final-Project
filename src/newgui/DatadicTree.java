//TestGen_Project V.1.0 :edit 17/7/2017 
package newgui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.tree.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author PIWROCK
 * @author SOSWEET
 * @author LINGNOI
 */
public class DatadicTree extends JInternalFrame {

    Home homeClass = new Home();
    //TestGenMatch mc = new TestGenMatch();

    String FlieLocation;
    public ArrayList InputVarName = new ArrayList<>();
    ArrayList OutputVarName = new ArrayList<>();
    ArrayList Dataset = new ArrayList<>();

    // JTree tree;
    JTree tree = new JTree();
    DefaultTreeModel treeModel;

    public DatadicTree() {
        //super("DataDicinary");

        setSize(240, 240);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void Readd() {

        ArrayList InputMin = new ArrayList<>();
        ArrayList InputMax = new ArrayList<>();
        ArrayList InputValue = new ArrayList<>();
        ArrayList OutputValue = new ArrayList<>();

        ArrayList Condition = new ArrayList<>();
        ArrayList Action = new ArrayList<>();

        int IntMin = 0;
        int IntMax = 0;
        int data = 0;
        try {
            String varType = "";
            String varName = "";
            String testType = "";
            String varData = "";
            //String FileData = "C:\\Users\\Benjawan Songmungsuk\\Documents\\TESTXML\\UseCaseDataDic3.xml";

            // TODO Auto-generated method stub
            File fXmlFile = new File(homeClass.getFileLocationData());
            //File fXmlFile = new File(FileData);

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
                                InputVarName.add(varName);
                                homeClass.InputVarName.add(varName);
                            }
                            if (nodeInputChild.getNodeName() == "Type") {
                                varType = nodeInputChild.getTextContent();
                                System.out.println("Variable Type: " + varType);
                                System.out.println("Test Type: " + testType);
                            }
                            if (nodeInputChild.getNodeName() == "Dataset") {
                                varData = nodeInputChild.getTextContent();
                                System.out.println("Dataset: " + varData);
                                Dataset.add(varData);

                            }

                            if (nodeInputChild.getNodeName() == "Condition") {
                                if (varType.equals("Range")) {
                                    System.out.print("id : " + eElement.getAttribute("id"));
                                    System.out.print(" min : " + eElement.getAttribute("min"));
                                    System.out.print(" max : " + eElement.getAttribute("max"));
                                    String Min = eElement.getAttribute("min");

                                    String Max = eElement.getAttribute("max");
                                    Condition.add("Min = " + Min + " : " + "Max = " + Max);
                                } else if (varType.equals("Ordinal")) {
                                    System.out.print("id : " + eElement.getAttribute("id"));
                                    System.out.print(" value : " + eElement.getAttribute("value"));
                                    String Value = eElement.getAttribute("value");
                                    Condition.add(Value);
                                }
                                //String Min = eElement.getAttribute("min");

                                //String Max = eElement.getAttribute("max");
                                //Condition.add(Max);
                                //String Value = eElement.getAttribute("value");
                                //Condition.add(Value);
                                data = Integer.parseInt(varData);

                                //Condition.add(Min);
                                //InputValue.add(Value);
                                //InputMin.add(Min);
                                //InputMax.add(Max);
                                //Condition.add(Max);
                                System.out.println("");

                            }

                        }
                    }

                    InputValue.clear();
                    InputMin.clear();
                    InputMax.clear();
                    System.out.println("");
                }

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
                                OutputVarName.add(varName);
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
                                    Action.add(Value);
                                }

                                System.out.println("");

                            }

                        }

                    }

                }
                OutputValue.clear();
                System.out.println(InputVarName);
                System.out.println(OutputVarName);
                System.out.println(Condition);
                System.out.println(Action);
            }//end of for(int i=0;i<ndOutput.getLength();i++)

        } catch (Exception e) {
            e.printStackTrace();
        }

    } //end of Read() Method

    public void Read() {

        ArrayList InputMin = new ArrayList<>();
        ArrayList InputMax = new ArrayList<>();
        ArrayList InputValue = new ArrayList<>();
        ArrayList OutputValue = new ArrayList<>();

        ArrayList Condition = new ArrayList<>();
        ArrayList Action = new ArrayList<>();

        int IntMin = 0;
        int IntMax = 0;
        int data = 0;

        try {
            String varType = "";
            String varName = "";
            String testType = "";
            String varData = "";
            String FileData = "C:\\Users\\Benjawan Songmungsuk\\Documents\\TESTXML\\UseCaseDataDic3.xml";

            // TODO Auto-generated method stub
            File fXmlFile = new File(homeClass.getFileLocationData());
            //File fXmlFile = new File(FileData);

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
                                InputVarName.add(varName);
                            }
                            if (nodeInputChild.getNodeName() == "Type") {
                                varType = nodeInputChild.getTextContent();
                                System.out.println("Variable Type: " + varType);
                                System.out.println("Test Type: " + testType);
                            }
                            if (nodeInputChild.getNodeName() == "Dataset") {
                                varData = nodeInputChild.getTextContent();
                                System.out.println("Dataset: " + varData);
                                int Data = Integer.parseInt(varData);
                                Dataset.add(Data);

                            }

                            if (nodeInputChild.getNodeName() == "Condition") {
                                if (varType.equals("Range")) {
                                    System.out.print("id : " + eElement.getAttribute("id"));
                                    System.out.print(" min : " + eElement.getAttribute("min"));
                                    System.out.print(" max : " + eElement.getAttribute("max"));
                                    String Min = eElement.getAttribute("min");

                                    String Max = eElement.getAttribute("max");
                                    Condition.add("Min = " + Min + " : " + "Max = " + Max);
                                } else if (varType.equals("Ordinal")) {
                                    System.out.print("id : " + eElement.getAttribute("id"));
                                    System.out.print(" value : " + eElement.getAttribute("value"));
                                    String Value = eElement.getAttribute("value");
                                    Condition.add(Value);
                                }
                                //String Min = eElement.getAttribute("min");

                                //String Max = eElement.getAttribute("max");
                                //Condition.add(Max);
                                //String Value = eElement.getAttribute("value");
                                //Condition.add(Value);
                                data = Integer.parseInt(varData);

                                //Condition.add(Min);
                                //InputValue.add(Value);
                                //InputMin.add(Min);
                                //InputMax.add(Max);
                                //Condition.add(Max);
                                System.out.println("");

                            }

                        }
                    }

                    InputValue.clear();
                    InputMin.clear();
                    InputMax.clear();
                    System.out.println("");
                }

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
                                OutputVarName.add(varName);
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
                                    Action.add(Value);
                                }

                                System.out.println("");

                            }

                        }

                    }

                }
                OutputValue.clear();
                System.out.println("#1 :" + InputVarName);
                System.out.println("#2 :" + OutputVarName);
                System.out.println("#3 :" + Condition);
                System.out.println("#4 :" + Action);
                System.out.println("#5 :" + Dataset);
            }//end of for(int i=0;i<ndOutput.getLength();i++)

            DefaultMutableTreeNode root = new DefaultMutableTreeNode("DataDicnary");
            DefaultMutableTreeNode input = new DefaultMutableTreeNode("Input");
            DefaultMutableTreeNode output = new DefaultMutableTreeNode("Output");

            for (int i = 0; i < InputVarName.size(); i++) {

                DefaultMutableTreeNode subRoot
                        = new DefaultMutableTreeNode(InputVarName.get(i));
                input.add(subRoot);

                for (int ra = 0; ra < InputVarName.size(); ra++) {
                    if (i == ra) {
                        if (ra == 0) {

                            for (int j = 0; j < (int) Dataset.get(ra); j++) {
                                System.out.println("#" + InputVarName.get(ra) + " :" + Condition.get(j));
                                DefaultMutableTreeNode node
                                        = new DefaultMutableTreeNode(Condition.get(j));
                                subRoot.add(node);
                            }
                        } else {
                            int ara = 0;
                            for (int j = (int) Dataset.get(ra - 1); j < ((int) Dataset.get(ra) + (int) Dataset.get(ra - 1)); j++) {

                                System.out.println("#" + InputVarName.get(ra) + " :" + Condition.get(j + ara));

                                DefaultMutableTreeNode node
                                        = new DefaultMutableTreeNode(Condition.get(j + ara));

                                subRoot.add(node);

                            }

                        }
                    }
                }
                /*
                if (i == 0) {
                    for (int j = 0; j < 3; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(Condition.get(j));
                        subRoot.add(node);
                    }
                }
                if (i == 1) {
                    for (int j = 3; j < 5; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(Condition.get(j));
                        subRoot.add(node);
                    }
                }*/
            }
            for (int i = 0; i < OutputVarName.size(); i++) {
                DefaultMutableTreeNode subRoot
                        = new DefaultMutableTreeNode(OutputVarName.get(i));
                output.add(subRoot);

                for (int j = 0; j < Action.size(); j++) {
                    DefaultMutableTreeNode node
                            = new DefaultMutableTreeNode(Action.get(j));
                    subRoot.add(node);

                }

            }

            // Build our tree model starting at the root node, and then make a JTree out
            // of it.
            treeModel = new DefaultTreeModel(root);
            tree = new JTree(treeModel);
            JScrollPane scrollPane = new JScrollPane(tree);

            // Build the tree up from the nodes we created.
            treeModel.insertNodeInto(input, root, 0);
            treeModel.insertNodeInto(output, root, 1);
            // Or, more succinctly:

            //root.add(subroot1);
            // Display it.
            getContentPane().add(scrollPane, BorderLayout.CENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }

    } //end of Read() Method

    public void ReadDe() {
        Readd();
        ArrayList InputMin = new ArrayList<>();
        ArrayList InputMax = new ArrayList<>();
        ArrayList InputValue = new ArrayList<>();
        ArrayList OutputValue = new ArrayList<>();

        ArrayList DecistionID = new ArrayList<>();
        ArrayList CoditionID = new ArrayList<>();
        ArrayList ActionID = new ArrayList<>();

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
        String FileDis = "C:\\Users\\Benjawan Songmungsuk\\Documents\\TESTXML\\DecisionTree2.xml";
        int IntMin = 0;
        int IntMax = 0;
        int data = 0;
        int VarSize = (InputVarName.size() + OutputVarName.size()) - 1;
        int VarSize1 = (InputVarName.size() + OutputVarName.size()) - InputVarName.size();

        try {
            // TODO Auto-generated method stub

            File DisXmlFile = new File(homeClass.getFileLocationDis());
            //File DisXmlFile = new File(FileDis);
            DocumentBuilderFactory disFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder disBuilder = disFactory.newDocumentBuilder();
            Document dt = disBuilder.parse(DisXmlFile);
            dt.getDocumentElement().normalize();
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());	

            //access to node "Input"
            NodeList DECISION = dt.getElementsByTagName("Decision");
            // System.out.println(DECISION.getLength());

            System.out.println("***********************************************");
            System.out.println("                   Disition Tree               ");
            System.out.println("***********************************************");
            System.out.println(InputVarName);
            System.out.println(OutputVarName);
            for (int i = 0; i < DECISION.getLength(); i++) {
                Node node = DECISION.item(i);
                System.out.print(node.getNodeName() + " : ");
                Element eElement = (Element) node;
                System.out.println("" + eElement.getAttribute("id"));

                NodeList CONDITION = eElement.getElementsByTagName("Condition");
                Decistion_ID = eElement.getAttribute("id");

                int DId = Integer.parseInt(Decistion_ID);
                DecistionID.add(DId);
                //System.out.println("DID "+Decistion_ID);

                for (int j = 0; j < CONDITION.getLength(); j++) {

                    Node nodeChild = CONDITION.item(j);
                    Element eElementchild = (Element) nodeChild;
                    System.out.println("  " + nodeChild.getNodeName() + " id : " + eElementchild.getAttribute("refid"));

                    NodeList ACTION = eElementchild.getElementsByTagName("ACTION");
                    Codition_ID = eElementchild.getAttribute("refid");

                    int CId = Integer.parseInt(Codition_ID);
                    CoditionID.add(CId);

                    int k = 0;
                    if (j == CONDITION.getLength() - 1) {
                        Node nodeChild1 = ACTION.item(k);
                        Element eElementAction = (Element) nodeChild1;
                        System.out.println("  " + nodeChild1.getNodeName() + "    id : " + eElementAction.getAttribute("refid"));
                        Action_ID = eElementAction.getAttribute("refid");

                        int AId = Integer.parseInt(Action_ID);
                        ActionID.add(AId);

                        k++;
                    }

                }

            }
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("DisciionTree");
            // DefaultMutableTreeNode input = new DefaultMutableTreeNode("Input");
            // DefaultMutableTreeNode output = new DefaultMutableTreeNode("Output");
            System.out.println(DecistionID);
            for (int i = 0; i < DecistionID.size(); i++) {
                DefaultMutableTreeNode subRoot
                        = new DefaultMutableTreeNode(DecistionID.get(i));
                root.add(subRoot);
                for (int ro = 0; ro < DecistionID.size(); ro++) {
                    if (i == ro) {
                        for (int j = 0 + (InputVarName.size() * ro); j < VarSize + (InputVarName.size() * ro); j++) {
                            DefaultMutableTreeNode node
                                    = new DefaultMutableTreeNode(CoditionID.get(j));
                            subRoot.add(node);
                        }
                        for (int j = ro; j < VarSize1 + ((InputVarName.size() * ro) / InputVarName.size()); j++) {
                            DefaultMutableTreeNode node
                                    = new DefaultMutableTreeNode(ActionID.get(j));
                            subRoot.add(node);

                        }
                    }
                }

                /* for (int i = 0; i < DecistionID.size(); i++) {
                DefaultMutableTreeNode subRoot
                        = new DefaultMutableTreeNode(DecistionID.get(i));
                root.add(subRoot);
                if (i == 0) {
                    for (int j = 0; j < 2; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(CoditionID.get(j));
                        subRoot.add(node);
                    }
                    for (int j = 0; j < 1; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(ActionID.get(j));
                        subRoot.add(node);

                    }
                }
                if (i == 1) {
                    for (int j = 2; j < 4; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(CoditionID.get(j));
                        subRoot.add(node);
                    }
                    for (int j = 1; j < 2; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(ActionID.get(j));
                        subRoot.add(node);

                    }

                }
                if (i == 2) {
                    for (int j = 4; j < 6; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(CoditionID.get(j));
                        subRoot.add(node);
                    }
                    for (int j = 2; j < 3; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(ActionID.get(j));
                        subRoot.add(node);

                    }

                }
                if (i == 3) {
                    for (int j = 6; j < 8; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(CoditionID.get(j));
                        subRoot.add(node);
                    }
                    for (int j = 3; j < 4; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(ActionID.get(j));
                        subRoot.add(node);

                    }
                }
                if (i == 4) {
                    for (int j = 8; j < 10; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(CoditionID.get(j));
                        subRoot.add(node);
                    }
                    for (int j = 4; j < 5; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(ActionID.get(j));
                        subRoot.add(node);

                    }
                }
                if (i == 5) {
                    for (int j = 10; j < 12; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(CoditionID.get(j));
                        subRoot.add(node);
                    }
                    for (int j = 5; j < 6; j++) {
                        DefaultMutableTreeNode node
                                = new DefaultMutableTreeNode(ActionID.get(j));
                        subRoot.add(node);

                    }
                }*/
            }
            /*for (int i = 0; i < OutputVarName.size(); i++) {
                DefaultMutableTreeNode subRoot
                        = new DefaultMutableTreeNode(OutputVarName.get(i));
                output.add(subRoot);

                for (int j = 0; j < Action.size(); j++) {
                    DefaultMutableTreeNode node
                            = new DefaultMutableTreeNode(Action.get(j));
                    subRoot.add(node);

                }

            }*/

            // Build our tree model starting at the root node, and then make a JTree out
            // of it.
            treeModel = new DefaultTreeModel(root);
            tree = new JTree(treeModel);
            JScrollPane scrollPane = new JScrollPane(tree);

            // Build the tree up from the nodes we created.
            //treeModel.insertNodeInto(input, root, 0);
            // treeModel.insertNodeInto(output, root, 1);
            // Or, more succinctly:
            //root.add(subroot1);
            // Display it.
            getContentPane().add(scrollPane, BorderLayout.CENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }

    } //end of Read() Method


    /* public void init() {
        // Build up a bunch of TreeNodes. We use DefaultMutableTreeNode because the
        // DefaultTreeModel can use it to build a complete tree.
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("DataDicnary");
        DefaultMutableTreeNode input = new DefaultMutableTreeNode("Input");
        DefaultMutableTreeNode output = new DefaultMutableTreeNode("Output");
        DefaultMutableTreeNode leaf1 = new DefaultMutableTreeNode("Leaf 1");
        DefaultMutableTreeNode leaf2 = new DefaultMutableTreeNode("Leaf 2");

        for (int i = 0; i < 3; i++) {
            DefaultMutableTreeNode leaf
                    = new DefaultMutableTreeNode(("leaf") + i);
            input.add(leaf);

        }

        // Build our tree model starting at the root node, and then make a JTree out
        // of it.
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);

        // Build the tree up from the nodes we created.
        treeModel.insertNodeInto(input, root, 0);
        treeModel.insertNodeInto(output, root, 1);
        // Or, more succinctly:
        output.add(leaf1);
        output.add(leaf2);
        //root.add(subroot1);

        // Display it.
        getContentPane().add(tree, BorderLayout.CENTER);
    }*/
    //public static void main(String args[]) throws SAXException, ParserConfigurationException, IOException {
    // DatadicTree dt = new DatadicTree();
    //dt.ReadDe();
    // dt.Read();
    // }
}
