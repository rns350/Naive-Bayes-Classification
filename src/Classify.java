import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.*;
import java.io.*;
/**Class to handle the main logic for the naive bayes classification.
 * This class handles the data reading, the training, and the testing.
 * 
 * @author Reed Nathaniel Schick
 */
public class Classify{

    static ArrayList<Data> dataSet = new ArrayList<Data>(); //set of data that the xlsx file contained
    static int trainingNum; //number of training data
    static int testingNum; //number of testing data
    static boolean verbose; //optional verbose output
    static Frequencies frequence = new Frequencies(); //Frequency of each attribute at any given value
    static LogProbability probs; //Logarithmic Probabilities calculated from the frequencies
    static double accuracy, precision, recall; //acccuracy of classification as a whole, precision and recall only for attribute a6 = 3

    public static void main(String [] args){
        readInput(args); //read input
        for(int i = 0; i < trainingNum; i ++){ //fill frequencies arrays
            frequence.fill(dataSet.get(i));
        }
        probs = new LogProbability(frequence, trainingNum); //calculate Logarithmic Probabilities
        test(); //Classification section
        if(verbose){ System.out.print("\n" + probs); } //optional verbose output
        System.out.printf("\nAccuracy = %.4f   Precision = %.4f   Recall = %.4f\n", accuracy, precision, recall); //final output
    }

    /**This method takes data from the training set and classifies their a6
     * value based on the values of a1 - a5
     * 
     */
    public static void test(){
        Data current; //current data being tested
        int classification; //classification of current
        int correct = 0; //number corectly classified
        int incorrect = 0; //number incorrectly classified
        int num3 = 0; //number of a6 = 3
        int classify3 = 0; //number of data classified as a6 = 3
        int correct3 = 0; //number of data correctly classified as a6 = 3
        //test the training set
        for(int i = dataSet.size() - testingNum; i < dataSet.size(); i ++){
            current = dataSet.get(i);
            classification = probs.classify(current);
            if(classification == current.getA6()){ correct ++; }
            else { incorrect ++; }
            if(classification == 3 && current.getA6() == 3){
                num3 ++;
                classify3 ++;
                correct3 ++;
            }
            else if(classification == 3){ classify3 ++; }
            else if(current.getA6() == 3){ num3 ++; }
        }
        //calculate accuracy, precision, and recall
        accuracy = (double) correct / (incorrect + correct);
        precision = (double) correct3 / classify3;
        recall = (double) correct3 / num3;
    }

    /**Method for parsing the arguments and reading the xlsx file.  The xlsx file
     * is read with help from the Apache POI libraries
     * 
     * @param args - command line arguments
     */
    public static void readInput(String [] args){
        if(args.length < 3){
            System.err.println("Error: 3 command line arguments expected:\n"
                                + "[Data Set File Location][Training Rows][Testing Rows]"
                                + "[Optionally: -v for verbose]\nTerminating...");
            System.exit(0);
        }

        int lineNum = 2;
        try{
            FileInputStream excelFile = new FileInputStream(args[0]); //excel file
            Workbook workbook = new XSSFWorkbook(excelFile); //open a workbook with it
            Sheet dataReader = workbook.getSheetAt(0); //take out the sheet
            Iterator<Row> lines = dataReader.iterator(); //iterator to go through the xlsx rows
            Row line; //current row
            int a1, a2, a3, a4, a5, a6; //classification attributes
            if(lines.hasNext()){ //skip first line (header)
                lines.next();
            }
            while(lines.hasNext()){
                line = lines.next(); //read a row
                if(line.getLastCellNum() != 6){ //there must be 6 and only 6 cells
                    System.err.println("Error at line " + lineNum + " of the input file.");
                    System.err.println("There are not 6 numbers on this line for the six attributes.");
                    System.err.println("Terminating...");
                    System.exit(0);
                }
                a1 = (int) line.getCell(0).getNumericCellValue(); //read a1
                a2 = (int) line.getCell(1).getNumericCellValue(); //read a2
                a3 = (int) line.getCell(2).getNumericCellValue(); //read a3
                a4 = (int) line.getCell(3).getNumericCellValue(); //read a4
                a5 = (int) line.getCell(4).getNumericCellValue(); //read a5
                a6 = (int) line.getCell(5).getNumericCellValue(); //read a6
                dataSet.add(new Data(a1, a2, a3, a4, a5, a6)); //make a new data piece and add it
                lineNum ++;
            }
        }catch(FileNotFoundException e){
            System.err.println("Error: File \"" + args[0] + "\" could not be found.\nTerminating...");
            System.exit(0);
        }catch(IOException e){
            System.err.println("Error: File \"" + args[0] + "\" could not be opened.\nTerminating...");
            System.exit(0);
        }catch(NumberFormatException | IllegalStateException e){
            System.err.println("Error at line " + lineNum + " of the input file.");
            System.err.println("There is a non-number at this line.");
            System.err.println("Terminating...");
            System.exit(0);
        }catch(IllegalArgumentException e){
            System.err.println("Error at line " + lineNum + " of the input file.");
            System.err.println(e.getMessage());
            System.err.println("Terminating...");
            System.exit(0);
        }

        //get the number of training data
        try{
            trainingNum = Integer.parseInt(args[1]);
            if(trainingNum <= 0){
                System.err.println("Error: Positive number expected for the number of training lines\nTerminating...");
                System.exit(0);
            }
        }catch(NumberFormatException e){
            System.err.println("Error: non-number given for the number of training lines\nTerminating...");
            System.exit(0);
        }

        //get the number of testing data
        try{
            testingNum = Integer.parseInt(args[2]);
            if(testingNum <= 0){
                System.err.println("Error: Positive number expected for the number of testing lines\nTerminating...");
                System.exit(0);
            }
        }catch(NumberFormatException e){
            System.err.println("Error: non-number given for the number of testing lines\nTerminating...");
            System.exit(0);
        }

        //total number of used data can not exceed total available data
        if(trainingNum + testingNum > dataSet.size()){
            System.err.println("Error: number of training lines + number of testing lines\n"
                                + "Cannot exceed the total number of lines: " + dataSet.size() + ".\nTerminating...");
            System.exit(0);
        }

        //if verbose, set to verbose
        if(args.length > 3 && args[3].equals("-v")){
            verbose = true;
        }
    }
}