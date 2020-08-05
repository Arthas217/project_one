package org.com.zlk.leedcode;

import org.junit.Test;

import static org.com.zlk.leedcode.MatrixSolution.*;
import static org.junit.Assert.*;

/**
 * @Author zc217
 * @Date 2020/8/5
 */
public class MatrixSolutionTest {

    @Test
    public void testPrintMatrix() {
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[] value = printMatrix(a);
        for (int num : value) {
            System.out.println(num);
        }
    }

    @Test
    public void testPrintMatrixZLK() {
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[] value = printMatrixZLK(a);
        for (int num : value) {
            System.out.println(num);
        }

    }

    @Test
    public void testFindNumberIn2DArray() {
        int[][] matrix = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        System.out.println(findNumberIn2DArray(matrix, 7));
    }
}