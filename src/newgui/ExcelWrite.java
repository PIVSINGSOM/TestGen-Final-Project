package newgui;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExcelWrite {

    public static ArrayList flow = new ArrayList<>();

    public static ArrayList Alternative = new ArrayList<>();
    public static ArrayList Exception = new ArrayList<>();
    public static ArrayList UC = new ArrayList<>();
    public static ArrayList Normalflow = new ArrayList<>();
    public static ArrayList AF_description = new ArrayList<>();
    public static ArrayList MF_description = new ArrayList<>();
    public static ArrayList EF_description = new ArrayList<>();
    
    public static ArrayList NorFlow = new ArrayList<>();
    public static ArrayList AlFlow = new ArrayList<>();
    public static ArrayList ExFlow = new ArrayList<>();
    
    
    public static ArrayList ucName = new ArrayList<>();

     public static ArrayList AF_description1 = new ArrayList<>();
     public static ArrayList EF_description1 = new ArrayList<>();
    

    public void excel() {
        // add 
        flow.add("Normal Flow");
        flow.add("Alternative Flow");
        flow.add("Exception Flow");

        
        
        JFileChooser savefile = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("xls", "xls");
        savefile.setFileFilter(filter);
        int returnVal = savefile.showSaveDialog(savefile);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //1. Create an Excel file
            WritableWorkbook myFirstWbook = null;
            try {
               String fileName = savefile.getSelectedFile() + ".xls".toString();

                 myFirstWbook = Workbook.createWorkbook(new File(fileName));
                //myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));

                // create an Excel sheet
                WritableSheet excelSheet = myFirstWbook.createSheet("Sheet 1", 0);

                // add something into the Excel sheet
                Label label = new Label(0, 0, "ScenarioID");
                excelSheet.addCell(label);
                excelSheet.setColumnView(0, 10);

                int z = 1;

                label = new Label(1, 0, "Name");
                excelSheet.addCell(label);
                excelSheet.setColumnView(1, 25);
                //int z = 1;
                for (int b = 0; b < ucName.size(); b++) {
                    for (int c = 0; c < flow.size(); c++) {

                        label = new Label(1, z, "" + ucName.get(0) + "_" + "" + flow.get(c));
                        excelSheet.addCell(label);

                        label = new Label(0, z, "" + (c + 1));
                        excelSheet.addCell(label);

                        double y = ((c + 1) * 100) / flow.size();

                        label = new Label(4, z, y + "%");
                        excelSheet.addCell(label);
                        if (c == 0) {
                            label = new Label(3, z, "Test Case 1-6");
                            excelSheet.addCell(label);
                            for (int d = 0; d < NorFlow.size(); d++) {
                                label = new Label(2, z, "" + NorFlow.get(d) + "" + ":" + MF_description.get(d));
                                excelSheet.addCell(label);

                                z++;

                            }
                        } else if (c == 1) {
                            label = new Label(3, z, "Test Case 1-6");
                            excelSheet.addCell(label);
                            for (int e = 0; e < AlFlow.size()-1; e++) {
                                label = new Label(2, z, "" + AlFlow.get(e) + "" + ":" + AF_description1.get(e));
                                excelSheet.addCell(label);

                                z++;

                            }
                        } else if (c == 2) {
                            label = new Label(3, z, "Test Case 7-9");
                            excelSheet.addCell(label);
                            for (int f = 0; f < ExFlow.size(); f++) {
                                label = new Label(2, z, "" + ExFlow.get(f) + "" + ":" + EF_description1.get(f));
                                excelSheet.addCell(label);

                                z++;

                            }
                        }

                    }

                }

                label = new Label(2, 0, "Test Step");
                excelSheet.addCell(label);
                excelSheet.setColumnView(2, 30);

                label = new Label(3, 0, "Test Case");
                excelSheet.addCell(label);
                excelSheet.setColumnView(3, 15);

                label = new Label(4, 0, "Coverage");
                excelSheet.addCell(label);
                excelSheet.setColumnView(3, 20);

                myFirstWbook.write();
                myFirstWbook.close();

                System.out.println("Excel file created.");
                JOptionPane.showMessageDialog(null, "Save succeed");

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
    }
}
