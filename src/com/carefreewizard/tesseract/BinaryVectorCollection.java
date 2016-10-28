package com.carefreewizard.tesseract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinaryVectorCollection
{
    private List<Integer[]> vectors;
    private final int bitSize;

    public BinaryVectorCollection(int bitSize)
    {
        vectors = new ArrayList<>();
        this.bitSize = bitSize;
    }

    public void addVector(String binaryString)
    {
        if (binaryString.length() > bitSize)
        {
            throw new IllegalArgumentException("Binary String is to long. This vector can handle strings of max " + bitSize + " bits. Given String " + binaryString + " has " + binaryString.length() + " characters");
        }

        String[] splitBinaryString = binaryString.split("(?!^)");
        splitBinaryString = reverse(splitBinaryString);

        Integer[] binaryVector = new Integer[bitSize];

        for (int i = 0; i < binaryVector.length; i++)
        {
            binaryVector[i] = Integer.parseInt(splitBinaryString[i]);
        }

        vectors.add(binaryVector);
    }

    public void addVector(Integer[] vector)
    {
        Integer[] newVector = new Integer[bitSize];

        System.arraycopy(vector, 0, newVector, 0, vector.length);

        vectors.add(newVector);
    }

    public void addVectors(List<Integer[]> vectors)
    {
        for (Integer[] integers : vectors)
        {
            addVector(integers);
        }
    }

    public void insertVector(Integer[] vector, int index)
    {
        Integer[] newVector = new Integer[bitSize];

        System.arraycopy(vector, 0, newVector, 0, vector.length);

        vectors.add(index, newVector);
    }

    public void insertVectorsInFront(List<Integer[]> vectors)
    {
        for (int i = vectors.size() - 1; i >= 0; i--)
        {
            Integer[] integers = vectors.get(i);
            insertVector(integers, 0);
        }
    }

    public void flipMostSignificantBit()
    {
        for (Integer[] vector : vectors)
        {
            Integer mostSignificantBit = vector[vector.length - 1];
            Integer flippedBit = flipBit(mostSignificantBit);
            vector[vector.length - 1] = flippedBit;
        }
    }

    private Integer flipBit(Integer bit)
    {
        if (bit == 0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }


    private String[] reverse(String[] arr)
    {
        List<String> list = Arrays.asList(arr);
        Collections.reverse(list);
        return (String[]) list.toArray();
    }

    public String toString()
    {
        String vectorString = "";
        for (Integer[] vector : vectors)
        {
            String line = "[" + intLineToString(vector) + "]";
            vectorString += line + "\n";
        }
        return vectorString;
    }

    @Override
    public BinaryVectorCollection clone()
    {
        BinaryVectorCollection clone = new BinaryVectorCollection(bitSize);
        List<Integer[]> newList = new ArrayList<>();

        for (Integer[] integers : vectors)
        {
            Integer[] newArray = new Integer[integers.length];

            System.arraycopy(integers, 0, newArray, 0, integers.length);

            newList.add(newArray);
        }

        clone.setVectors(newList);

        return clone;
    }

    public void setVectors(List<Integer[]> vectors)
    {
        this.vectors = vectors;
    }

    public Integer[] getVector(int vectorIndex)
    {
        Integer[] newVector = new Integer[bitSize];
        Integer[] vector = vectors.get(vectorIndex);

        System.arraycopy(vector, 0, newVector, 0, vector.length);

        return newVector;
    }

    private String intLineToString(Integer[] vector)
    {
        String line = "";

        for (int i = vector.length - 1; i >= 0; i--)
        {
            line += String.valueOf(vector[i]);
        }
        return line;
    }

    public int vectorTotal()
    {
        return vectors.size();
    }

    public int getBitSize()
    {
        return bitSize;
    }

    public List<Integer[]> getAllVectors()
    {
        return vectors;
    }

    public boolean compare(BinaryVectorCollection otherVectors)
    {
        List<Integer[]> otherIntegers = otherVectors.getAllVectors();

        for (Integer[] otherInt : otherIntegers)
        {
            boolean hasMatch = false;

            for (Integer[] myInt : vectors)
            {
                if (Arrays.equals(otherInt, myInt))
                {
                    hasMatch = true;
                    break;
                }
            }

            if (!hasMatch)
            {
                return false;
            }
        }

        return true;
    }
}
