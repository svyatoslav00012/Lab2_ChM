package matrixSolving.impl;

import matrixSolving.api.MatrixSolver;

import java.util.Arrays;

public class LuDecomposition implements MatrixSolver {

    private static final String NAME = "LU decomposition method";

    private double[][] matrix;
    private double[][] l;
    private double[][] u;
    private double[] y;
    private double[] x;
    private int n;


    @Override
    public double[] solve(double[][] matrix) {
        init(matrix);
        calculateLAndU();
        calculateSolution();
        return x;
    }

    @Override
    public String getName() {
        return NAME;
    }

    private void init(double[][] matrix) {
        this.n = matrix.length;
        copyToThis(matrix);
        l = new double[n][n];
        u = new double[n][n];
        y = new double[n];
        x = new double[n];
    }

//    private void calculateLAndU() {
//
//        for (int s = 0; s < n; ++s) {
//            for (int j = s; j < n; ++j) {
//
//                double sum = 0;
//                for (int k = 0; k <= s - 1; ++k)
//                    sum += l[s][k] * u[k][j];
//
//                u[s][j] = matrix[s][j] - sum;
//            }
//
//            for (int i = s + 1; i < n; ++i) {
//
//                double sum = 0;
//                for (int k = 0; k <= s - 1; ++k)
//                    sum += l[i][k] * u[k][s];
//
//                l[i][s] = (matrix[i][s] - sum) / u[s][s];
//            }
//        }
//    }

    private void calculateLAndU() {

        for (int s = 0; s < n; ++s) {
            for (int j = s; j < n; ++j) {

                double sumForU = 0;
                for (int k = 0; k <= s - 1; ++k)
                    sumForU += l[s][k] * u[k][j];

                u[s][j] = matrix[s][j] - sumForU;

                if(j > s){
                    double sumForL = 0;
                    for (int k = 0; k <= s - 1; ++k)
                        sumForL += l[j][k] * u[k][s];

                    l[j][s] = (matrix[j][s] - sumForL) / u[s][s];
                }
            }
        }
    }

    private void calculateSolution(){
        calculateY();
        calculateX();
    }

    private void calculateY(){
        for(int i = 0; i < n; ++i){

            double b = matrix[i][n];

            double sum = 0;
            for(int s = 0; s < i -1; ++s)
                sum += l[i][s] * y[s];

            y[i] = b - sum;
        }
    }

    private void calculateX(){
        for(int i = n - 1; i >= 0; --i){

            double sum = 0;
            for(int s = i + 1; s < n; ++s)
                sum += u[i][s] * x[s];

            x[i] = (1 / u[i][i]) * (y[i] - sum);
        }
    }

    private void copyToThis(double[][] another){
        this.matrix = new double[n][n + 1];
        for(int i = 0; i < n; ++i)
            this.matrix[i] = Arrays.copyOf(another[i], n + 1);
    }
}
