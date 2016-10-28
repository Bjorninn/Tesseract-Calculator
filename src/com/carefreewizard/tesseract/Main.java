package com.carefreewizard.tesseract;

public class Main
{

    public Main()
    {

    }

    public static void main(String[] args)
    {
        BinaryVectorCollection vec = new BinaryVectorCollection(4);
        vec.addVector("0000");
        vec.addVector("0001");
        vec.addVector("0010");
        vec.addVector("0011");
        vec.addVector("0100");
        vec.addVector("0101");
        vec.addVector("0110");
        vec.addVector("0111");

        TesseractBuilder tesseractBuilder = new TesseractBuilder();
        Tesseract tesseract = tesseractBuilder.buildNewTesseract(vec);
        tesseract.increaseBitSizeByOne();

        Tesseract antiTesseract = tesseract.clone();
        antiTesseract.flipMostSignificantBit();

        Penteract penteract = new Penteract();
        penteract.addFirstTesseract(tesseract);

        PenteractBuilder penteractBuilder = new PenteractBuilder(penteract);

        penteract.addAntiTesseract(antiTesseract);

        System.out.println(penteract.toString());

        penteractBuilder.findSharedCubes(penteract);
    }
}
