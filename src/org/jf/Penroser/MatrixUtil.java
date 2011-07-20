package org.jf.Penroser;

import android.graphics.Matrix;

public class MatrixUtil {
    public static void convertMatrix(float[] androidMatrix, float[] glMatrix) {
        assert androidMatrix.length>=9;
        assert glMatrix.length >= 16;

        glMatrix[0] = androidMatrix[0];     glMatrix[4] = androidMatrix[1];     glMatrix[8] = 0;        glMatrix[12] = androidMatrix[2];
        glMatrix[1] = androidMatrix[3];     glMatrix[5] = androidMatrix[4];     glMatrix[9] = 0;        glMatrix[13] = androidMatrix[5];
        glMatrix[2] = 0;                    glMatrix[6] = 0;                    glMatrix[10] = 1;       glMatrix[14] = 0;
        glMatrix[3] = androidMatrix[6];     glMatrix[7] = androidMatrix[7];     glMatrix[11] = 0;       glMatrix[15] = androidMatrix[8];
    }
}