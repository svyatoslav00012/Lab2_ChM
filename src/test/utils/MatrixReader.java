package test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class MatrixReader {

    private static final String INPUT = "input";
    private static final String OUTPUT = "output";
    private static final String FILE_EXTENSION = ".txt";

    public static double[][] readExpandedMatrix(int index) {
        InputStream inStream = getInStream(index);
        Scanner in = new Scanner(inStream);

        int n = in.nextInt();
        double[][] matrix = new double[n][n + 1];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n + 1; ++j)
                matrix[i][j] = in.nextDouble();

        return matrix;
    }

    private static InputStream getInStream(int index) {
        try {
            return new FileInputStream(getInputFile(index));
        } catch (FileNotFoundException e) {
            System.out.println("error reading file");
            e.printStackTrace();
            System.out.println("Enter matrix from console:");
            return System.in;
        }
    }

    private static File getInputFile(int index) {
        return new File(INPUT + index + FILE_EXTENSION);
    }

}
