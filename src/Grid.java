package src;

import java.util.ArrayList;

public class Grid {
    public int n;
    public int m;
    public char[][] grid = null;
    ArrayList<Piece> onPieces = new ArrayList<>();
    ArrayList<Piece> offPieces = new ArrayList<>();
    String type;

    public Grid (int n, int m){
        this.n = n;
        this.m = m;
        this.grid = new char[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                this.grid[i][j] = '.';
            }
        }
    }

    public void addPiece(Piece piece){
        if (piece.piece.size() < 1){
            System.out.println("Sorry can't add empty piece");
            return;
        }
        if (isPieceExist(piece)){
            System.out.printf("Sorry piece %s already added in this grid. Failed to add piece", Mapping.findColor(piece.alphabet) + piece.alphabet + Mapping.RESET );
            return;
        }
        this.offPieces.add(piece);
    }

    public Piece onPieces(int idx){
        return this.onPieces.get(idx);
    }

    public Piece offPieces(int idx){
        return this.offPieces.get(idx);
    }

    public boolean canPlace(Piece p, int offsetX, int offsetY){
        for (Point point: p.piece){
            int x = point.getX()+offsetX;
            int y = point.getY()+offsetY;
            if (x < 0 || x >= this.m || y < 0 || y >= this.n || grid[y][x] != '.'){
                return false;
            }
        }
        return true;
    }

    public void placePiece(Piece p, int offsetX, int offsetY){

        if (!canPlace(p, offsetX, offsetY)){
            System.out.println("Sorry u can't place the piece in this condition");
            return;
        }
        for (Point point: p.piece){
            point.setX(point.getX()+offsetX);
            point.setY(point.getY()+offsetY);
        }

        for (Point point: p.piece){
            int x = point.getX();
            int y = point.getY();
            this.grid[y][x] = p.getAlphabet();
        }
        int idx = -1;
        for (Piece piece: offPieces){
            idx++;
            if (piece.alphabet == p.alphabet){
                break;
            }
        }
        if (idx != -1){
            this.offPieces.remove(idx);
        }
        this.onPieces.add(p); 
    }

    public void placePiece(Piece p){ // default condition
        placePiece(p,0,0);
    }

    public void unplacePiece(char alphabet){
        int idx = -1;
        for (Piece p: onPieces){
            idx++;

            if (p.alphabet == alphabet){
                int centerX = p.piece.get(0).getX();
                int centerY = p.piece.get(0).getY();
                for (Point point: p.piece){
                    grid[point.getY()][point.getX()] = '.';
                    point.setY(point.getY()-centerY);
                    point.setX(point.getX()-centerX);
                }
                this.offPieces.add(p);
                break;
            }
        }
        if (idx != -1) {
            this.onPieces.remove(idx);
        }
    }

    public void quarterRotate(char alphabet){
        for (Piece piece: offPieces){
            if (piece.alphabet == alphabet){
                for(Point point: piece.piece){
                    grid[point.getX()][point.getY()] = '.';
                }
                piece.quarterRotate();
                return;
            }
        }
        System.out.printf("Piece '%c' not in off pieces list\n", alphabet);
    }

    public void reflection(char alphabet){
        for (Piece piece: offPieces){
            if (piece.alphabet == alphabet){
                for(Point point: piece.piece){
                    grid[point.getX()][point.getY()] = '.';
                }
                piece.reflection();
                return;
            }
        }
        System.out.printf("Piece '%c' is not in off pieces list\n", alphabet);
    }

    public boolean isSolve(){
        if (!this.offPieces.isEmpty()){
            return false;
        }

        for (int i = 0; i < this.n; i++){
            for (int j = 0; j < this.m; j++){
                if (grid[i][j] == '.'){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPieceExist(Piece piece){
        for (Piece p: offPieces){
            if (p.alphabet == piece.alphabet){
                return true;
            }
        }
        for (Piece p: onPieces){
            if (p.alphabet == piece.alphabet){
                return true;
            }
        }
        return false;
    }

    public void displayGrid(){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                System.out.print(Mapping.findColor(grid[i][j]) + grid[i][j] + Mapping.RESET);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}
