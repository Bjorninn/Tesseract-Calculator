package com.carefreewizard.tesseract;

import java.util.ArrayList;
import java.util.List;

public class Penteract
{
    private static final int TESSERACTS_IN_PENTERACT = 10;
    private static final int FIRST_TESSERACTS_INDEX = 0;
    private static final int ANTI_TESSERACTS_INDEX = 9;

    private int index = 1;
    private List<Tesseract> tesseracts;


    public Penteract()
    {
        tesseracts = new ArrayList<Tesseract>(TESSERACTS_IN_PENTERACT);
    }

    public void addFirstTesseract(Tesseract centerTesseract)
    {
        tesseracts.add(FIRST_TESSERACTS_INDEX, centerTesseract);
    }

    public void addAntiTesseract(Tesseract antiTesseract)
    {
        tesseracts.add(ANTI_TESSERACTS_INDEX, antiTesseract);
    }

    public void addTesseract(Tesseract tesseract)
    {
        if (index >= ANTI_TESSERACTS_INDEX)
        {
            throw new IndexOutOfBoundsException();
        }

        tesseracts.add(index, tesseract);
        index++;
    }

    public void insertTesseract(Tesseract tesseract, int index)
    {
        tesseracts.add(index, tesseract);
    }

    public Tesseract getFirstTesseract()
    {
        return tesseracts.get(FIRST_TESSERACTS_INDEX);
    }

    public Tesseract getAntiTesseract()
    {
        return tesseracts.get(ANTI_TESSERACTS_INDEX);
    }

    public Tesseract getTesseractAtIndex(int index)
    {
        return tesseracts.get(index);
    }

    public List<Tesseract> getTesseracts()
    {
        return tesseracts;
    }

    @Override
    public String toString()
    {
        String output = "";
        String[] names = {"Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta", "Theta", "iota", "kappa"};

        for (int i = 0; i < tesseracts.size(); i++)
        {
            Tesseract tess = tesseracts.get(i);

            output += names[i] + "\n";
            output += "========\n";
            output += "========\n";
            output += tess.toString() + "\n" + "\n";
        }

        return output;
    }
}
