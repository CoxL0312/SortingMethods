/*
 * CS310 Assignment 13 & 14 Sorting Experimentation
 */
package cs310datastructures;

import java.io.File;
import java.io.PrintWriter;

/**
 * Provides the driver for the Sorting Methods program
 * Creates the arrays and their corresponding results arrays for the related
 * sorting methods
 * Calls the methods to sort the arrays, and then display the results, and 
 * create CSV files for each sorting method's array
 * Has a playground method
 *
 * @author Jeffrey LaMarche
 * 
 * @version 1.0 2021-Mar-31  
 * Template Version
 * 
 * @author Lindsey Cox
 * @version 1.1 2024-Apr-18
 * Assignment13 Version
 */
public class CS310AssignmentSorting
{

    /*
    The number of tests to perform per sort
     */
    private static final int NUM_TESTS = 10;

    /*
    The size of the arrays that will be sorted
     */
    private static final int[] ARRAY_SIZES =
    {
        10, 100, 1000, 10000, 25000, 50000, 100000
    };

    /*
    The number of nanoseconds in a second
     */
    private static final double NANO_IN_SEC = 1000000000.0;

    /*
    The filename where to store the generated CSV file
     */
    private static final String OUTPUT_FILENAME = "output/sortResults.csv";
    
    /*
    Whether the arrays in the tests contain random integer values
    */
    private static final boolean RANDOM_ARRAYS = true;
    
    /*
    Labels for the sorting methods
    */
    private static final String BUBBLE = "Bubble";
    private static final String SELECTION = "Selection";
    private static final String INSERTION = "Insertion";
    private static final String SHELL = "Shell";
    private static final String MERGE = "Merge";
    private static final String QUICK = "Quick";
    private static final String JAVA = "Java Arrays";

    
    public static void main(String[] args)
    {
        final int NUM_ARRAY_SIZES = ARRAY_SIZES.length;

        long[][] array2dOne = new long[NUM_TESTS][NUM_ARRAY_SIZES];
        long[][] array2dTwo = new long[NUM_TESTS][NUM_ARRAY_SIZES];
        long[][] array2dThree = new long[NUM_TESTS][NUM_ARRAY_SIZES];
        long[][] array2dFour = new long[NUM_TESTS][NUM_ARRAY_SIZES];
        long[][] array2dFive = new long[NUM_TESTS][NUM_ARRAY_SIZES];
        long[][] array2dSix = new long[NUM_TESTS][NUM_ARRAY_SIZES];
        long[][] array2dSeven = new long[NUM_TESTS][NUM_ARRAY_SIZES];

        boolean verified = true;

        //loop for num tests being performed and all sorts verified
        for (int i = 0; i < NUM_TESTS && verified; i++)
        {
            System.out.println("Starting sorting test " + (i + 1) + ":");
            //loop for diff sized arrays being tested and all sorts verified
            for (int j = 0; j < ARRAY_SIZES.length  && verified; j++)
            {
                int currSize = ARRAY_SIZES[j];
                System.out.println("     Starting test of size " + 
                currSize + ":");
                //create seven arrays for each of the seven sorting methods
                int[] Bubble = new int[currSize];
                int[] Selection = new int[currSize];
                int[] Insertion = new int[currSize];
                int[] Shell = new int[currSize];
                int[] Merge = new int[currSize];
                int[] Quick = new int[currSize];
                int[] Java = new int[currSize];

                //fill the bubble array with either random ints or sorted
                //from 1 through the array size
                if (RANDOM_ARRAYS)
                {
                    ArrayMethods.fillArrayRandomInts(Bubble);
                }
                else
                {
                    ArrayMethods.fillArraySortedInts(Bubble);
                }

                //copy the bubble sort array onto the other six
                ArrayMethods.copyArray(Bubble, Selection);
                ArrayMethods.copyArray(Bubble, Insertion);
                ArrayMethods.copyArray(Bubble, Shell);
                ArrayMethods.copyArray(Bubble, Merge);
                ArrayMethods.copyArray(Bubble, Quick);
                ArrayMethods.copyArray(Bubble, Java);

                //test each sort
            
                boolean bubbleTest = runSortTest(BUBBLE, i, j,
                        Bubble, array2dOne);
                boolean selectionTest = runSortTest(SELECTION, i,
                        j, Selection, array2dTwo);
                boolean insertionTest = runSortTest(INSERTION, i,
                        j, Insertion, array2dThree);
                boolean shellTest = runSortTest(SHELL, i, j,
                        Shell, array2dFour);
                boolean mergeTest = runSortTest(MERGE, i, j,
                        Merge, array2dFive);
                boolean quickTest = runSortTest(QUICK, i, j,
                        Quick, array2dSix);
                boolean javaTest = runSortTest(JAVA, i, j,
                        Java, array2dSeven);

                // Check each test condition, 
                //if any is false, set verified to false, and print out
                //appropriate statement
                //if verified is false, should end the loops
                if (!bubbleTest)
                {
                    System.out.println("Bubble test failed!");
                    verified = false;
                }
                if (!selectionTest)
                {
                    System.out.println("Selection test failed!");
                    verified = false;
                }
                if (!insertionTest)
                {
                    System.out.println("Insertion test failed!");
                    verified = false;
                }
                if (!shellTest)
                {
                    System.out.println("Shell test failed!");
                    verified = false;
                }
                if (!mergeTest)
                {
                    System.out.println("Merge test failed!");
                    verified = false;
                }
                if (!quickTest)
                {
                    System.out.println("Quick test failed!");
                    //verified = false;
                }
                if (!javaTest)
                {
                    System.out.println("Java test failed!");
                    verified = false;
                }
                if (verified == true) 
                {
                    displayTable(BUBBLE, array2dOne);
                    displayTable(SELECTION, array2dTwo);
                    displayTable(INSERTION, array2dThree);
                    displayTable(SHELL, array2dFour);
                    displayTable(MERGE, array2dFive);
                    displayTable(QUICK, array2dSix);
                    displayTable(JAVA, array2dSeven);

                    //create file with these results
                    createCSVFile(OUTPUT_FILENAME, array2dOne, array2dTwo, 
                    array2dThree, array2dFour, array2dFive, array2dSix, 
                    array2dSeven);
                }
            }
        }

    }

    /**
     * performs the sorting, and the record keeping for the testing, of all
     * the sorting methods, then displays test information
     * @param sortName
     * @param testNum
     * @param sizeNum
     * @param arrayToSort
     * @param sortResults
     * @return boolean verifySort
     */
    public static boolean runSortTest(String sortName, int testNum, int sizeNum, 
            int[] arrayToSort, long[][] sortResults)
    {

        //nanoseconds
        long NANOSECS = (long) NANO_IN_SEC;
        long startTime = 0;
        long endTime = 0;
        switch (sortName) 
        {
            case "Bubble":
            startTime = System.nanoTime();
            SortingMethods.bubbleSort(arrayToSort);
            endTime = System.nanoTime();
            break;
            case "Selection":
            startTime = System.nanoTime();
            SortingMethods.selectionSort(arrayToSort);
            endTime = System.nanoTime();
            break;
            case "Insertion":
            startTime = System.nanoTime();
            SortingMethods.insertionSort(arrayToSort);
            endTime = System.nanoTime();
            break;
            case "Shell":
            startTime = System.nanoTime();
            SortingMethods.insertionSort(arrayToSort);
            endTime = System.nanoTime();
            break;
            case "Merge":
            startTime = System.nanoTime();
            SortingMethods.mergeSort(arrayToSort);
            endTime = System.nanoTime();
            break;
            case "Quick":
            startTime = System.nanoTime();
            SortingMethods.quickSort(arrayToSort);
            endTime = System.nanoTime();
            break;
            case "Java Arrays":
            startTime = System.nanoTime();
            SortingMethods.javaSort(arrayToSort);
            endTime = System.nanoTime();
            break;
            default:
            System.out.println("How did we get here");
            break;
        }

        //what this method returns
        boolean verifySort = ArrayMethods.verifySortedArray(arrayToSort);
        //store the sorting time result in the results array
        endTime = (endTime - startTime);
        sortResults[testNum][sizeNum] = endTime;
        System.out.printf("%35s Sort Time:", sortName);
        System.out.print("    " + endTime);
        if (verifySort)
        {
            System.out.println(" (Validation Passed)");
        }
        else
        {
            System.out.println(" (Validation Failed)");
        }
        System.out.println();

        
        return verifySort;
    }
    
    /*
     * displays the results of a single sorting method, for all the tests
     * that were performed on it (aka the long[][] that holds the results
     * for said tests)
     * if either the sortName or the long[][] is null, an 
     * IllegalArgumentException will be thrown
     * @param String sortName
     * @param long[][] results
     */
    public static void displayTable(String sortName, long[][] results)
    {
        try
        {
            if (sortName == null)
            {
                throw new IllegalArgumentException
                ("CS310AssignmentSorting.displayTable: sortName null");
            }
            else if (results == null)
            {
                throw new IllegalArgumentException
                ("CS310AssignmentSorting.displayTable: results null");
            }
            else
            {
                double sum = 0;
                System.out.println(sortName + " Sort Results (In Seconds)");
                //print out the headers for the test array sizes,
                //10, 100, 1000, 10000, 25000, 50000, 100000
                System.out.print(" ".repeat(17) + 10);
                System.out.print(" ".repeat(8) + 100);
                System.out.print(" ".repeat(6) + 1000);
                System.out.print(" ".repeat(5) + 10000);
                System.out.print(" ".repeat(5) + 25000);
                System.out.print(" ".repeat(5) + 50000);
                System.out.print(" ".repeat(5) + 100000 + "\n" );
                

                System.out.println(("-").repeat(80));

                //loop through every test in the results array
                double timeInSeconds;
                for (int i = 0; i < NUM_TESTS; i++)
                {
                    System.out.print("Test   " + (i + 1) + "  ");
                    for (int j = 0; j < ARRAY_SIZES.length; j++)
                    {
                        timeInSeconds = results[i][j] / NANO_IN_SEC;
                        if (i < 9) 
                        {
                            if (j == 0) 
                            {
                                System.out.printf("%10.4f", 
                                timeInSeconds);
                            } 
                            else 
                            {
                                System.out.printf("%10.4f", 
                                timeInSeconds);
                            }

                            sum = +timeInSeconds;
                        }
                        else
                        {
                            if (j == 0) 
                            {
                                System.out.printf("%9.4f", 
                                timeInSeconds);
                            } 
                            else 
                            {
                                System.out.printf("%10.4f", 
                                timeInSeconds);
                            }

                            sum = +timeInSeconds;
                        }

                    }
                    System.out.println();
                }

                System.out.println(("-").repeat(80));

                //calculate averages
                System.out.print("Averages");
                for (int k = 0; k < ARRAY_SIZES.length; k++)
                {
                    if (k == 0)
                    {
                        System.out.printf("%12.4f", 
                        (double) sum / ARRAY_SIZES[k]);
                    }
                    else
                    {
                        System.out.printf("%10.4f",
                        (double) sum / ARRAY_SIZES[k]);
                    }
                    
                     
                }
                System.out.println();
                System.out.println();

            }
        }
        catch (IllegalArgumentException a)
        {
            System.out.println("CS310.AssignmentSorting.DisplayTable");
        }
        

    }

    /**
     * calls the private createCSVTable to create a CSV file for each of the 
     * sorting method results arrays
     * if any of the params are null, throws an IllegalArgumentException,
     * additional potential of a fileNotFoundException for when creating the 
     * PrintWriter object
     * @param filename
     * @param bubbleResults
     * @param selectionResults
     * @param insertionResults
     * @param shellResults
     * @param mergeResults
     * @param quickResults
     * @param javaResults
     * @return true if file is successfully created, else false
     */
    public static boolean createCSVFile(String filename, 
            long[][] bubbleResults, long[][] selectionResults, 
            long[][] insertionResults, long[][] shellResults,
            long[][] mergeResults, long[][] quickResults, long[][] javaResults)
    {
        PrintWriter output = null;
        try
        {
            //if any params are null
            if (filename == null || bubbleResults == null || 
            selectionResults == null || insertionResults == null || 
            shellResults == null ||mergeResults == null || 
            quickResults == null || javaResults == null)
            {
                throw new IllegalArgumentException
                ("CS310AssignmentSorting.createCSVFile: Something is null!");
            }
            else
            {
                output = new PrintWriter(filename);
                createCSVTable(output, BUBBLE, bubbleResults);
                createCSVTable(output, SELECTION, selectionResults);
                createCSVTable(output, INSERTION, insertionResults);
                createCSVTable(output, SHELL, shellResults);
                createCSVTable(output, MERGE, mergeResults);
                createCSVTable(output, QUICK, quickResults);
                createCSVTable(output, JAVA, javaResults);
            }
            return true;

        }
        catch (Exception b)
        {
            System.out.println(b.getMessage());
            //either a FileNotFoundException from creating the PrintWriter 
            //or one of the params was null
            return false;
        }
        finally 
        {
            if (output != null)
            {
                output.close();
            }
        }
    }

    
    private static void createCSVTable(PrintWriter writer, String sortName, 
            long[][] sortResults)
    {
        double timeInSeconds;
        String formattedTime;
        double sum = 0;
        writer.write(sortName + " Sort Results (In Seconds)\n");
        writer.write(",10,100,1000,10000,25000,50000,100000\n");
        for (int r = 0; r < sortResults.length; r++)
        {
            writer.write("Test " + (r + 1) + ",");
            for (int c = 0; c < 7; c++)
            {
                timeInSeconds = sortResults[r][c] / NANO_IN_SEC;
                sum += timeInSeconds;
                if (c != sortResults.length - 1)
                {
                    formattedTime = String.format("%.8f", timeInSeconds);
                    writer.write(formattedTime + ",");
                }
            }
            writer.write("\n");
        }

        StringBuilder aveOutput = new StringBuilder();
        double ave;
        for (int k = 0; k < ARRAY_SIZES.length; k++)
        {
            ave = (double) sum / ARRAY_SIZES[k];
            formattedTime = String.format("%.8f", ave);
            aveOutput.append(formattedTime);
            if (k != ARRAY_SIZES.length - 1)
            aveOutput.append(",");
        }
        writer.write("Averages " + aveOutput);

        writer.write("\n");
        writer.write("\n");
        writer.flush();

    }
    
    /**
     * Playground for exercising your FlutteroidFarm class (i.e., this means
     * testing)
     */
    public static void playground()
    {
        // TODO or Not TODO, that is the question
        // Professor Note: I recommend the TODO option
        
        // except that it must compile, follow the course coding standards,
        // not cause exceptions...
        System.out.println("There ain't no rulez here!");
    }
}
