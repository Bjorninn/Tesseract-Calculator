package com.carefreewizard.tesseract;

import java.util.ArrayList;
import java.util.List;

public class Tesseract
{
    public static final int CUBES_IN_TESSERACT = 8;
    public static final int CENTER_CUBE_INDEX = 0;
    public static final int ANTI_CUBE_INDEX = 7;

    List<Cube> cubes = new ArrayList<>(CUBES_IN_TESSERACT);

    public Tesseract(List<Cube> tesseractCubes)
    {
        for (Cube cube : tesseractCubes)
        {
            Cube newCube = cube.clone();
            cubes.add(newCube);
        }
    }

    public void increaseBitSizeByOne()
    {
        for (Cube cube : cubes)
        {
            cube.increaseBitSizeByOne();
        }
    }

    public void flipMostSignificantBit()
    {
        for (Cube cube : cubes)
        {
            cube.flipMostSignificantBit();
        }
    }

    public void addCenterCube(Cube centerCube)
    {
        cubes.add(CENTER_CUBE_INDEX, centerCube);
    }

    public void addAntiCube(Cube antiCube)
    {
        cubes.add(ANTI_CUBE_INDEX, antiCube);
    }

    public void insertCube(Cube cube, int index)
    {
        cubes.add(index, cube);
    }

    public Cube getCenterCube()
    {
        return cubes.get(CENTER_CUBE_INDEX);
    }

    public Cube getAntiCube()
    {
        return cubes.get(ANTI_CUBE_INDEX);
    }

    public Cube getCubeAtIndex(int index)
    {
        return cubes.get(index);
    }

    public List<Cube> getCubes()
    {
        return cubes;
    }

    public Tesseract clone()
    {
        List<Cube> cloneCubes = new ArrayList<>(CUBES_IN_TESSERACT);

        for (int i = 0; i < cubes.size(); i++)
        {
            Cube cube = cubes.get(i);
            Cube cloneCube = cube.clone();
            cloneCubes.add(i, cloneCube);
        }

        return new Tesseract(cloneCubes);
    }

    @Override
    public String toString()
    {
        String output = "";

        for (int i = 0; i < cubes.size(); i++)
        {
            Cube cube = cubes.get(i);
            output += i + 1 + "\n";
            output += "-------\n";
            output += cube.toString();
            output += "\n";
        }

        return output;
    }
}
