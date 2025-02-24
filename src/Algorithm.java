package src;

import java.util.ArrayList;

public class Algorithm {
    public static long counter = 0;
    public static boolean found = false;

    public static boolean existSolution(ArrayList<char[][]> solutions, char[][] solution){
        for (char[][] s: solutions){
            if(Utility.isEqualMatrix(s, solution)){
                return true;
            }
        }

        return false;
    }

    public static void allSolution(ArrayList<char[][]> solutions, Grid grid, ArrayList<Piece> allPieces, int start, int end){

        if (start > end){

            if (grid.isSolve() && !existSolution(solutions, grid.grid)){
                char[][] copy = new char[grid.grid.length][grid.grid[0].length];
                found = true;
                Utility.copyMatrix(grid.grid, copy);
                solutions.add(copy);
            }

        }else{
            for (int i = 0; i < grid.n; i ++){
                for (int j = 0; j < grid.m; j++)
                {
                    if (found){
                        break;
                    }
                    for (int k = 0; k < 4; k++){
                        counter++;
                        if(grid.canPlace(allPieces.get(start),j,i)){
                            grid.placePiece(allPieces.get(start),j,i);
                            char alphabet = allPieces.get(start).alphabet;
                            allSolution(solutions, grid, allPieces, start + 1,  end);
                            grid.unplacePiece(alphabet);
                        }
                        allPieces.get(start).quarterRotate();
                    }

                    allPieces.get(start).reflection();
                    
                    for (int k = 0; k < 4; k++){
                        counter++;
                        if(grid.canPlace(allPieces.get(start),j,i)){
                            grid.placePiece(allPieces.get(start),j,i);
                            char alphabet = allPieces.get(start).alphabet;
                            allSolution(solutions, grid, allPieces, start + 1,  end);
                            grid.unplacePiece(alphabet);
                        }
                        allPieces.get(start).quarterRotate();
                    }
                    allPieces.get(start).reflection();

                }
            }
        }
    }
}
