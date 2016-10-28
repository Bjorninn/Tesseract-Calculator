package com.carefreewizard.tesseract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TesseractBuilder
{
    List<BinaryVectorCollection> binaryVectors = new ArrayList<>();
    List<Integer[]> ruleSet = new ArrayList<>();

    public TesseractBuilder()
    {
        Integer[] alpha = {0, 1, 2, 3};
        Integer[] beta = {4, 5, 6, 7};
        Integer[] gamma = {1, 3, 5, 7};
        Integer[] delta = {0, 2, 4, 6};
        Integer[] epsilon = {2, 3, 6, 7};
        Integer[] zeta = {0, 1, 4, 5};

        ruleSet.add(alpha);
        ruleSet.add(beta);
        ruleSet.add(gamma);
        ruleSet.add(delta);
        ruleSet.add(epsilon);
        ruleSet.add(zeta);
    }

    public Tesseract buildNewTesseract(BinaryVectorCollection centerCube)
    {
        binaryVectors.clear();

        BinaryVectorCollection antiCube = centerCube.clone();
        antiCube.flipMostSignificantBit();

        List<Cube> cubes = new ArrayList<>(Tesseract.CUBES_IN_TESSERACT);

        for (int i = 0; i < ruleSet.size(); i++)
        {
            BinaryVectorCollection vector = generate(centerCube, i);
            binaryVectors.add(vector);
        }

        binaryVectors.add(0, centerCube);
        binaryVectors.add(antiCube);

        for (BinaryVectorCollection vector : binaryVectors)
        {
            Cube cube = new Cube(vector.clone());
            cubes.add(cube);
        }

        return new Tesseract(cubes);
    }

    private BinaryVectorCollection generate(BinaryVectorCollection centerCube, int ruleSetIndex)
    {
        Integer[] rule = ruleSet.get(ruleSetIndex);

        List<Integer[]> vectors = new ArrayList<>();

        for (Integer vectorNumber : rule)
        {
            Integer[] vector = centerCube.getVector(vectorNumber);
            vectors.add(vector);
        }

        BinaryVectorCollection binaryVector = new BinaryVectorCollection(4);
        binaryVector.addVectors(vectors);
        binaryVector.flipMostSignificantBit();
        binaryVector.insertVectorsInFront(vectors);

        return binaryVector;
    }

    private BinaryVectorCollection getInverseCube(BinaryVectorCollection cube)
    {
        List<Integer[]> bitList = new ArrayList<>();

        for (int i = 0; i < 16; i++)
        {
            Integer[] binaryInt = convertDecimalToBinary(i);
            bitList.add(binaryInt);
        }

        for (int i = 0; i < cube.vectorTotal(); i++)
        {
            Integer[] vector = cube.getVector(i);


            for (int j = 0; j < bitList.size(); j++)
            {
                Integer[] integer = bitList.get(j);

                if (Arrays.equals(integer, vector))
                {
                    bitList.remove(j);
                }
            }
        }

        for (Integer[] integers : bitList)
        {
            System.out.print(Arrays.toString(integers));
        }

        BinaryVectorCollection binaryVector = new BinaryVectorCollection(4);
        binaryVector.setVectors(bitList);

        return binaryVector;
    }

    private Integer[] convertDecimalToBinary(int number)
    {
        String bitString = Integer.toBinaryString(number);
        Integer[] binaryArray = new Integer[4];

        while (bitString.length() < 4)
        {
            bitString = "0" + bitString;
        }

        for (int i = 0; i < binaryArray.length; i++)
        {
            binaryArray[i] = 0;
        }

        for (int i = 0; i < bitString.length(); i++)
        {
            binaryArray[i] = Character.digit(bitString.charAt(i), 10);
        }
        return binaryArray;
    }
}
