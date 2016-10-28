package com.carefreewizard.tesseract;

import java.util.ArrayList;
import java.util.List;

public class PenteractBuilder
{

    Penteract penteract;
    List<Integer[]> ruleSet = new ArrayList<>();

    public PenteractBuilder(Penteract penteract)
    {
        this.penteract = penteract;

        Integer[] alpha =
                {0, 1, 2, 3};
        Integer[] beta =
                {4, 5, 6, 7};
        Integer[] gamma =
                {1, 3, 5, 7};
        Integer[] delta =
                {0, 2, 4, 6};
        Integer[] epsilon =
                {2, 3, 6, 7};
        Integer[] zeta =
                {0, 1, 4, 5};

        ruleSet.add(alpha);
        ruleSet.add(beta);
        ruleSet.add(gamma);
        ruleSet.add(delta);
        ruleSet.add(epsilon);
        ruleSet.add(zeta);

		/* 	For each cube in the firstTesseract, ignoring the first and last one
         * 		clone the cube
		 * 		create a new tesseract
		 * 		create the other cubes using the rules, flipping the most significant bit on the opposite side
		 * 		stick the cubes in the tesseract
		 * 		store the tesseract in the penteract
		 * 
		 */

        Tesseract firstTesseract = penteract.getFirstTesseract();

        List<Cube> firstCubes = firstTesseract.getCubes();

        for (Cube startingCube : firstCubes)
        {
            Cube startingCubeClone = startingCube.clone();
            Tesseract tesseract = generate(startingCubeClone);
            penteract.addTesseract(tesseract);
        }
    }

    private Tesseract generate(Cube centerCube)
    {

        List<Cube> cubesInTess = new ArrayList<>(Tesseract.CUBES_IN_TESSERACT);
        cubesInTess.add(Tesseract.CENTER_CUBE_INDEX, centerCube);

        for (int i = 0; i < ruleSet.size(); i++)
        {
            Integer[] rule = ruleSet.get(i);
            List<Integer[]> vectors = new ArrayList<>();

            for (Integer vectorNumber : rule)
            {
                Integer[] vector = centerCube.getVectorForIndex(vectorNumber);
                vectors.add(vector);
            }

            BinaryVectorCollection binaryVector = new BinaryVectorCollection(5);
            binaryVector.addVectors(vectors);
            binaryVector.flipMostSignificantBit();
            binaryVector.insertVectorsInFront(vectors);

            Cube cube = new Cube(binaryVector);
            cubesInTess.add(i + 1, cube);
        }

        Cube antiCube = centerCube.clone();
        antiCube.flipMostSignificantBit();
        cubesInTess.add(antiCube);

        return new Tesseract(cubesInTess);
    }

    public int countUniqueTesseracts(Penteract pent)
    {
        List<Tesseract> tesseracts = pent.getTesseracts();
        List<BinaryVectorCollection> vectorCollections = new ArrayList<>();
        List<BinaryVectorCollection> uniqueCubes = new ArrayList<>();

        for (Tesseract tesseract : tesseracts)
        {
            List<Cube> cubes = tesseract.getCubes();

            for (Cube cube : cubes)
            {
                BinaryVectorCollection vector = cube.getVectors();
                vectorCollections.add(vector);
            }
        }

        for (BinaryVectorCollection binaryVectorCollection : vectorCollections)
        {
            boolean shouldAdd = true;

            for (BinaryVectorCollection cube : uniqueCubes)
            {
                if (binaryVectorCollection.compare(cube))
                {
                    shouldAdd = false;
                    break;
                }
            }

            if (shouldAdd)
            {
                uniqueCubes.add(binaryVectorCollection);
            }
        }

        return uniqueCubes.size();
    }

    public void findSharedCubes(Penteract pent)
    {
        String[] names =
                {"Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta", "Theta", "iota", "kappa"};

        List<Tesseract> tesseracts = pent.getTesseracts();

        for (int n = 0; n < tesseracts.size(); n++)
        {
            Tesseract tesseract = tesseracts.get(n);
            String tesseractName = names[n];
            List<Cube> cubes = tesseract.getCubes();

            for (int p = 0; p < cubes.size(); p++)
            {
                Cube cube = cubes.get(p);
                int cubeNumber = p + 1;

                for (int q = 0; q < tesseracts.size(); q++)
                {
                    if (q == n)
                    {
                        continue;
                    }

                    Tesseract comTesseract = tesseracts.get(q);
                    String compTesseractName = names[q];
                    List<Cube> compCubes = comTesseract.getCubes();

                    for (int r = 0; r < compCubes.size(); r++)
                    {
                        Cube compCube = compCubes.get(r);
                        int compCubeNumber = r + 1;

                        if (compCube.compare(cube))
                        {
                            System.out.println("( " + tesseractName + " - " + cubeNumber + " )" + " == " + "( " + compTesseractName + " - " + compCubeNumber + " )");
                        }
                    }
                }
            }
        }
    }
}
