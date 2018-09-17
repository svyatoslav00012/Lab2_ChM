package main;

import matrixSolving.api.MatrixSolver;
import matrixSolving.impl.LuDecomposition;
import test.utils.MatrixReader;

import java.io.*;

public class MatrixSolving {

    private static final String INPUT = "input";
    private static final String OUTPUT = "output";
    private static final String FILE_EXTENSION = ".txt";

    private MatrixSolver[] solvers = {
            new LuDecomposition()
    };

    public void solveMatrixes(int n){
        for(int i = 1; i <= n; ++i)
            solveMatrix(i);
    }

    private void solveMatrix(int index){
        double[][] matrix = MatrixReader.readExpandedMatrix(index);
        double[][] solutions = solveUsingAllSolvers(matrix);
        writeResults(index, solutions);
    }

    private void writeResults(int index, double[][] solutions) {
        OutputStream outStream = getOutStream(index);
        OutputStreamWriter outSWriter = new OutputStreamWriter(outStream);
        
        try(BufferedWriter writer = new BufferedWriter(outSWriter)){
            for(int i = 0; i < solutions.length; ++i){
                writer.write(solvers[i].getName() + ":\n");
                for(int x = 0; x < solutions[i].length; ++x)
                    writer.write("x" + x  + "=" + solutions[i][x] + "\n");
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double[][] solveUsingAllSolvers(double[][] matrix){
        double[][] solutions = new double[solvers.length][matrix.length];
        for(int i = 0; i < solvers.length; ++i)
            solutions[i] = solvers[i].solve(matrix);

        return solutions;
    }

    private OutputStream getOutStream(int index){
        try {
            return new FileOutputStream(getOutputFile(index));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write to file. Writing to console...");
            e.printStackTrace();
            return System.out;
        }
    }

    private File getOutputFile(int index) {
        File out = new File(OUTPUT + index + FILE_EXTENSION);
        if (!out.exists()) {
            try {
                out.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out;
    }
}
