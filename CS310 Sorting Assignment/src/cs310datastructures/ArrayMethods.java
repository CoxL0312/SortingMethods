/*
 * CS310 Assignment 13 & 14 Sorting Experimentation
 */
package cs310datastructures;

import java.util.Random;

/**
 * Contains utility methods for working with the array,
 * the constructor is private as this class cannot be instantiated
 * has methods to fill the array, with either sorted 1 through (whatever the
 * length of the array is) values, or random (unsorted) values
 * has a method to copy an array to another
 * has a method to swap two array values (in the same array)
 * has a method that just verifies if an array is sorted in ascending order
 * has a method to display the array as well
 *
 * @author Jeffrey LaMarche
 * 
 * @version 1.0 2020-Nov-11
 * Template Version
 *
 * @author Lindsey Cox
 * @version 1.1 2024-Apr-18 
 * Assignment13 Version
 */
public class ArrayMethods
{
    /**
     * Private default constructor, which means the class cannot be instantiated
     */
    private ArrayMethods()
    {
        // empty constructor, so that ArrayMethods cannnot be instantiated
    }

    /*
     * fills array with values from 1 to n, where n is the length of the array
     * if the array is null, throws an IllegalArgumentException instead
     */
    public static void fillArraySortedInts(int[] array)
    {
        try 
        {
            if (array == null)
            {
                throw new IllegalArgumentException
                ("Array Methods.fillArraySortedInts: array is null!");
            }
            else 
            {
                int n = array.length;
                for (int i = 1; i == n; i++) 
                {
                    //sets at the [i - 1] bc the indices go from 0 - n-1
                    array[i - 1] = i;
                }
            }

        }
        catch (IllegalArgumentException a)
        {
            System.out.println(a.getMessage());
        }

    }

    /**
     * fills array with random values
     * if the array is null, an IllegalArgumentException error is thrown
     * @param array
     */
    public static void fillArrayRandomInts(int[] array)
    {
        Random random = new Random();
        try
        {
            if (array == null)
            {
                 throw new IllegalArgumentException
                ("Array Methods.fillArraySortedInts: array is null!");
            }
            else
            {
                for (int i = 0; i < array.length - 1; i++)
                {
                    array[i] = random.nextInt();
                }
            }
        }
        catch (IllegalArgumentException b)
        {
            System.out.println(b.getMessage());
        }


    }

    /**
     * copies elements from arraySource to arrayDestination
     * if either array is null, or if the lengths don't match up, an 
     * IllegalArgumentException error is thrown
     * @param arraySource
     * @param arrayDestination
     */
    public static void copyArray(int[] arraySource, int[] arrayDestination)
    {
        try
        {
            if (arraySource == null)
            {
                throw new IllegalArgumentException
                ("ArrayMethods.copyArray: arraySource null");
            }
            else if (arrayDestination == null)
            {
                throw new IllegalArgumentException
                ("ArrayMethods.copyArray: arrayDestination null");
            }
            else if (arraySource.length != arrayDestination.length)
            {
                throw new IllegalArgumentException
                ("ArrayMethods.copyArray: array lengths different!");
            }
            else
            {
                for (int i = 0; i < arrayDestination.length; i++)
                {
                    arrayDestination[i] = arraySource[i];
                }
            }
        }
        catch (IllegalArgumentException c)
        {
            System.out.println(c.getMessage());
        }
    }

    /**
     * swaps two values at the specified indices in the array
     * if the array is null, or if either index is out of bounds of the array,
     * an IllegalArgumentException will be thrown
     * @param array
     * @param indexOne
     * @param indexTwo
     */
    public static void swapTwoArrayValues(int[] array, int indexOne, int indexTwo)
    {
        try
        {
            if (array == null)
            {
                throw new IllegalArgumentException
                ("ArrayMethods.swapTwoArrayValues: array is null!");
            }
            else if (indexOne < 0 || indexOne >= array.length)
            {
                throw new IllegalArgumentException
                ("ArrayMethods.swapTwoArrayValues: indexOne out of bounds!");
            }
            else if (indexTwo < 0 || indexTwo >= array.length)
            {
                throw new IllegalArgumentException
                ("ArrayMethods.swapTwoArrayValues: indexTwo out of bounds!");
            }
            else
            {
                Integer temp = array[indexOne];
                array[indexOne] = array[indexTwo];
                array[indexTwo] = temp;
            }
        }
        catch (IllegalArgumentException d)
        {
            System.out.println(d.getMessage());
        }
    }

    /**
     * verifies that the array is sorted by checking every contiguous pair to
     * see if the previous element is less than the following one
     * if the array is null, an IllegalArgumentException will be thrown
     * @param array
     * @return isArraySorted boolean if the array isn't null, and otherwise 
     * null
     */
    public static boolean verifySortedArray(int[] array)
    {

        try {
            if (array == null) 
            {
                throw new IllegalArgumentException("ArrayMethods.verifySortedArray: array is null!");
            }

            for (int i = 1; i < array.length; i++) 
            {
                if (array[i - 1] > array[i]) {
                    return false; // Unsorted pair found
                }
            }
            return true; // All pairs are sorted
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println(e.getMessage());
            return false; // Array was null
        }
    }

    /**
     * Displays the contents of the array from low index to high index, each
     * value separated by a comma. <br><br>
     * 
     * Fun Fact: You can create this array, which is empty with length zero. 
     * <br><br>
     * 
     * int[] emptyArray = new int[0]; <br><br>
     * 
     * It is not the same as the array reference being null. <br><br>
     *
     * @param array the array of integers to display
     * @param lowIndex the low index in the array being displayed (inclusive)
     * @param highIndex the high index in the array being displayed (inclusive)
     */
    public static void displayArray(int[] array, int lowIndex, int highIndex)
    {
        final String HEADING = "displayArray: ";
        String msg;

        if (array == null)
        {
            msg = String.format("%sArray is null", HEADING);
            throw new IllegalArgumentException(msg);
        }

        if (array.length > 0)   // allows an empty array to be printed
        {
            if (lowIndex < 0 || lowIndex >= array.length)
            {
                msg = String.format("%s", HEADING);
                msg = msg + String.format("Low index %d ", lowIndex);
                msg = msg + String.format("is out of bounds");
                throw new IllegalArgumentException(msg);
            }

            if (highIndex < 0 || highIndex >= array.length)
            {
                msg = String.format("%s", HEADING);
                msg = msg + String.format("High index %d ", highIndex);
                msg = msg + String.format("is out of bounds");
                throw new IllegalArgumentException(msg);
            }

            if (lowIndex > highIndex)
            {
                msg = String.format("%s", HEADING);
                msg = msg + String.format("Low index %d is greater ", lowIndex);
                msg = msg + String.format("than high index %d", highIndex);
                throw new IllegalArgumentException(msg);
            }
        }

        System.out.print("[");

        // if the array is not empty, display the contents
        if (array.length > 0)  
        {
            for (int i = lowIndex; i <= highIndex; i++)
            {
                // display everything but last value
                if (i < highIndex)
                {
                    System.out.print(array[i] + ", ");
                }
                // display the last value
                else
                {
                    System.out.print(array[i]);
                }
            }
        }

        System.out.println("]");
    }

}
