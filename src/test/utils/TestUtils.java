package test.utils;

import matrixSolving.api.MatrixSolver;

import java.util.Arrays;

public class TestUtils {

    private static final double EPS = 1e-12;

    public static void testMatrix(int index, MatrixSolver solver) {
        double[][] matrix = MatrixReader.readExpandedMatrix(index);
        double[] solution = solver.solve(matrix);
        double[] myB = countMyB(matrix, solution);
        double[] actualB = extractB(matrix);
        System.out.println(Arrays.toString(myB));
        System.out.println(Arrays.toString(actualB));
        double error = countError(myB, actualB);
        System.out.println(solver.getName() + ":");
        System.out.println("Matrix="+index);
        System.out.println("error="+error);
        assert error < EPS;
    }



    private static double[] countMyB(double[][] matrix, double[] solution) {
        double[] myB = new double[solution.length];
        for (int i = 0; i < matrix.length; ++i)
            myB[i] = multiplyVectors(matrix[i], solution);
        return myB;
    }

    private static double multiplyVectors(double[] v1, double[] v2) {
        double ans = 0;
        int len = Math.min(v1.length, v2.length);
        for (int i = 0; i < len; ++i)
            ans += v1[i] * v2[i];
        return ans;
    }

    private static double[] extractB(double[][] matrix) {
        double[] b = new double[matrix.length];
        for (int i = 0; i < b.length; ++i)
            b[i] = matrix[i][matrix.length];
        return b;
    }

    private static double countError(double[] v1, double[] v2) {
        double error = 0;
        for (int i = 0; i < v1.length; ++i) {
            double currentErr = Math.abs(v1[i] - v2[i]);
            if(currentErr > error)
                error = currentErr;
        }
        return error;
    }

}
