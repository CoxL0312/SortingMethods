/*
 * CS310 Assignment 13 & 14 Sorting Experimentation
 */
package cs310datastructures;

import java.util.Arrays;    // used in assignment 14

/**
 * Containse leleven total methods that each perform a one of seven sorts on an 
 * array,
 * There is a blank constructor as this clas will not be instantiated
 * There is a method for 
 * Bubble Sort
 * Selection Sort
 * Insertion Sort
 * Shell Sort
 * Merge Sort (public method and a private one that is called by the public)
 * Quick Sort (public)
 * Quick Sort (private that returns an int)
 * Quick Sort (private that returns nothing)
 * Java Sort
 *
 * @author Jeffrey LaMarche
 * 
 * @version 1.0 2020-Nov-11
 * Template Version
 * 
 * @author Lindsey Cox
 * @version 1.1 2024-Apr-18
 */
public class SortingMethods
{

    /*
     * constructor
     */
    private SortingMethods()
    {

    }


    /**
     * bubble sort method
     * @param int[] numbers
     */
    public static void bubbleSort(int[] numbers) 
    {
        boolean listSorted = false;
        boolean wasThereASwap = true;
        int length = numbers.length;

        //until either listSorted is set as true, or if there was Not a swap
        do 
        {
            wasThereASwap = false;
            listSorted = true;
            for (int i = 0; i < length - 1; i++) 
            {
                for (int j = 0; j < length - i - 1; j++) 
                {
                    if (numbers[j] > numbers[j + 1]) {
                        ArrayMethods.swapTwoArrayValues(numbers, j,
                                (j + 1));
                        listSorted = false;
                        wasThereASwap = true;
                    }
                }

            }
        } while (listSorted == false && wasThereASwap);

    }

    public static void selectionSort(int[] numbers) 
    {
        int length = numbers.length;

        // moving through each element in array
        for (int i = 0; i < length - 1; i++) 
        {
            int currMin = i;
            // finding if there are any more min numbers
            for (int j = i + 1; j < length; j++) 
            {
                // if new min is found, swap the two, and record the idx of the
                // new min
                if (numbers[j] < numbers[currMin]) 
                {
                    currMin = j;
                }
            }
            ArrayMethods.swapTwoArrayValues(numbers, currMin,
                    i);
        }
    }

    /**
     * insertion sort method
     * 
     * @param int[] numbers
     */
    public static void insertionSort(int[] numbers) 
    {
        int firstElement = -1;
        int length = numbers.length;
        int j = -1;

        for (int i = 1; i < length; i++) 
        {
            firstElement = numbers[i];
            j = i - 1; // in first loop this is idx 0
            // the in-place swap where we go backwards through the sorted list,
            // to repeatedly swap our item into the correct spot
            while (j >= 0 && numbers[j] > firstElement) 
            {
                numbers[j + 1] = numbers[j];
                j--;
            }
            // we have found the correct position for the element
            numbers[j + 1] = firstElement;
        }
    }

    /**
     * insertion sort method
     * 
     * @param int[] numbers
     */
    public static void shellSort(int[] numbers) 
    {
        int length = numbers.length;
        int gap = -1;
        int j, insertValue;

        for (gap = length / 2; gap > 0; gap /= 2) 
        {
            for (int i = gap; i < length; i++) 
            {
                insertValue = numbers[i];
                j = i;
                // go through the mini array to sort
                while ((j >= gap) && (numbers[j - gap] > insertValue)) 
                {
                    numbers[j] = numbers[j - gap];
                    j--;
                }
                numbers[j] = insertValue;
            }
        }
    }

    /**
     * merge sort method that calls the private method that does the stuff
     * @param int[] numbers
     */
    public static void mergeSort(int[] numbers)
    {
        mergeSort(numbers, 0, numbers.length - 1); 
    }

    /**
     * merge sort overloaded method
     * @param int[] numbers
     * @param int leftIndex
     * @param int rightIndex
     */
    private static void mergeSort(int[] numbers, int leftIndex, int rightIndex)
    {

        int inputLength = numbers.length;

        //base case, array of 0 or 1 element
        if (inputLength < 2)
        {
            return;
        }

        //divide array into 2
        int midIndex = inputLength / 2;
        int[] leftHalf = new int[midIndex];
        //not just a length of midindex bc that wouldn't work for arrays with
        //odd num of elements
        int[] rightHalf = new int[inputLength - midIndex];

        //copy over elements
        for (int i = 0; i < midIndex; i++)
        {
            leftHalf[i] = numbers[i];
        }
        for (int i = midIndex; i < inputLength; i++)
        {
            rightHalf[i - midIndex] = numbers[i];
        }

        //recursively call
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        //merge time

        //new variables for improved readability
        int leftSize = midIndex;
        int rightSize = rightHalf.length;

        //i is for left half, j is for right half, k is for the merged array
        int i = 0, j = 0, k = 0;

        //until one side runs out of items
        while (i < leftSize && j < rightSize)
        {
            //if the item from left half is smaller
            if (leftHalf[i] <= rightHalf[j])
            {
                numbers[k] = leftHalf[i];
                i++;
            }
            else //if item from right half is smaller
            {
                numbers[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        //since one half will likely run out of elements before the other,
        //this is clean up
        while (i < leftSize)
        {
            numbers[k++] = leftHalf[i++];
        }
        while (j < rightSize)
        {
            numbers[k++] = rightHalf[j++];
        }

    }

    /**
     * quick sort method
     * @param int[] numbers
     */
    public static void quickSort(int[] numbers)
    {
        quickSort(numbers, 0, numbers.length - 1);
    }

    /**
     * quicksort overloaded method
     * @param int[] numbers
     * @param int lowIndex
     * @param int highIndex
     */
    private static void quickSort(int[] numbers, int lowIndex, int highIndex)
    {
        //base case, if the array is only one item
        if (lowIndex >= highIndex)
        {
            return;
        }



        int pointer = quickSortPartition(numbers, lowIndex, highIndex);

        //recursive call
        quickSort(numbers, lowIndex, (pointer - 1));
        quickSort(numbers, (pointer + 1), highIndex);


    }

    /**
     * quicksort partition method,
     * picks pivot based on median of 3 method
     * @param int[] numbers
     * @param int lowIndex
     * @param int highIndex
     */
    private static int quickSortPartition(int[] numbers, int lowIndex, 
    int highIndex)
    {
        //used for swaps
        int temp;

        //pivot time
        int midIndex = lowIndex + (highIndex - lowIndex) / 2;
        int pivotIndex = -1;
        //compact way to check if low number is in the middle
        if ((numbers[lowIndex] - numbers[midIndex]) * 
        (numbers[highIndex] - numbers[lowIndex]) >= 0) 
        {
            pivotIndex = lowIndex;
        } 
        //checks if the midIndex number is the median
        else if ((numbers[midIndex] - numbers[lowIndex]) *
        (numbers[highIndex] - numbers[midIndex]) >= 0) 
        {
            pivotIndex = midIndex;
        }
         else 
        {
            pivotIndex = highIndex;
        }

        int pivot = numbers[pivotIndex];
        // put the pivot value at the back of the array
        temp = numbers[pivotIndex];
        numbers[pivotIndex] = numbers[highIndex];
        numbers[highIndex] = temp;

        //partition

        int leftPointer = lowIndex;
        int rightPointer = highIndex - 1;

        while (leftPointer < rightPointer)
        {
            //keep going from left until bigger number is found or left pointer
            //crosses over
            while (numbers[leftPointer] <= pivot && leftPointer < rightPointer)
            {
                leftPointer++;
            }
            //from the right
            while (numbers[rightPointer] >= pivot && rightPointer > 
            leftPointer)
            {
                rightPointer--;
            }

            // swap the right and left values only if leftPointer is less than
            // rightPointer
            if (leftPointer < rightPointer) 
            {
                temp = numbers[leftPointer];
                numbers[leftPointer] = numbers[rightPointer];
                numbers[rightPointer] = temp;
            }
        }

        if (numbers[leftPointer] > numbers[highIndex])
        {
            temp = numbers[leftPointer];
            numbers[leftPointer] = numbers[highIndex];
            numbers[highIndex] = temp;
        }

        return leftPointer;
    }


    /**
     * java arrays sort method
     * @param int[] numbers
     */
    public static void javaSort(int[] numbers)
    {
        Arrays.sort(numbers);
    }


}
