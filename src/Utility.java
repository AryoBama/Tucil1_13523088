package src;

public class Utility {
    public static boolean isEqualMatrix(char[][] mat1, char[][] mat2) {
        if (mat1.length != mat2.length || mat1[0].length != mat2[0].length) {
            return false;
        }

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[i].length; j++) {
                if (mat1[i][j] != mat2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void copyMatrix(char[][] m1, char[][] mCopy){
        for (int i = 0; i < m1.length; i++){
            for (int j = 0; j < m1[i].length; j++){
                mCopy[i][j] = m1[i][j];
            }
        }
    }
}
