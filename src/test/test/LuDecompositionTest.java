package test.test;

import matrixSolving.api.MatrixSolver;
import matrixSolving.impl.LuDecomposition;
import org.junit.jupiter.api.Test;
import test.utils.TestUtils;

public class LuDecompositionTest {

    MatrixSolver lu = new LuDecomposition();

    @Test
    public void testFirstMatrix(){
        TestUtils.testMatrix(1, lu);
        assert true;
    }

    @Test
    public void testSecondMatrix(){
        TestUtils.testMatrix(2, lu);
    }

    @Test
    public void testThirdMatrix(){
        TestUtils.testMatrix(3, lu);
    }
}
