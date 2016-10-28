package com.carefreewizard.tesseract;

import java.util.List;


public class Cube
{
    private BinaryVectorCollection vectors;

    public Cube(BinaryVectorCollection vectors)
    {
        this.vectors = vectors;
    }

    public BinaryVectorCollection getVectors()
    {
        return vectors;
    }

    public Integer[] getVectorForIndex(int index)
    {
        return vectors.getVector(index);
    }

    public int size()
    {
        return vectors.vectorTotal();
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public void flipMostSignificantBit()
    {
        vectors.flipMostSignificantBit();
    }

    public void increaseBitSizeByOne()
    {
        final int newBitSize = vectors.getBitSize() + 1;
        BinaryVectorCollection newVectors = new BinaryVectorCollection(newBitSize);

        List<Integer[]> oldVectors = vectors.getAllVectors();

        for (Integer[] oldVector : oldVectors)
        {
            Integer[] newVector = new Integer[newBitSize];

            System.arraycopy(oldVector, 0, newVector, 0, oldVector.length);

            newVector[newVector.length - 1] = 0;
            newVectors.addVector(newVector);
        }
        vectors = newVectors;
    }

    public boolean compare(Cube cube)
    {
        BinaryVectorCollection compVector = cube.getVectors();
        return compVector.compare(vectors);
    }

    @Override
    public Cube clone()
    {
        BinaryVectorCollection newVectors = vectors.clone();

        return new Cube(newVectors);
    }

    @Override
    public String toString()
    {
        return vectors.toString();
    }
}
