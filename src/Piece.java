package src;

import java.util.ArrayList;

public class Piece {
    ArrayList<Point> piece = new ArrayList<>();
    char alphabet;
    
    public Piece(char alphabet){
        this.alphabet = alphabet;
    }

    public char getAlphabet(){
        return this.alphabet;
    }

    public void addPoint(int x, int y){
        this.piece.add(new Point (x, y));
    }

    public void quarterRotate(){
        if (this.piece.size() > 1){
            int centerX = this.piece.get(0).getX();
            int centerY = this.piece.get(0).getY();

            for (Point p : piece){
                int temp = p.getX();
                p.setX( -(p.getY() - centerY) + centerX);
                p.setY( (temp - centerX) + centerY);
            }
        }
    }

    public void reflection(){
        if (this.piece.size() > 1){
            int centerY = this.piece.get(0).getY();

            for (Point p : piece){
                p.setY(2*centerY - p.getY());   
            }
        }
    }

    public void display(){
        if (this.piece.size() >= 1){

            int xLeft = this.piece.get(0).getX();
            int yLeft = this.piece.get(0).getY();

            for (Point p : this.piece) {
                if (p.getX() < xLeft) {
                    xLeft = p.getX(); 
                }
                if (p.getY() < yLeft) {
                    yLeft = p.getY(); 
                }
            }
            int offsetX =  -xLeft;
            int offsetY =  -yLeft;



            int maxX = this.piece.get(0).getX() + offsetX;
            int maxY = this.piece.get(0).getY() + offsetY;

            for (int i = 1; i < this.piece.size(); i++){
                if (maxX < this.piece.get(i).getX()  + offsetX){
                    maxX = this.piece.get(i).getX()  + offsetX;
                }

                if (maxY < this.piece.get(i).getY() + offsetY){
                    maxY = this.piece.get(i).getY() + offsetY;
                }
            }


            boolean[][] grid = new boolean[maxX+1][maxY+1];

            for (Point point: piece){
                grid[point.getX() + offsetX][point.getY() + offsetY] = true;
            }

            for (int i = 0; i < maxX+1; i++){
                for (int j = 0; j < maxY+1; j++){
                    if (grid[i][j] == true){
                        System.out.print(Mapping.findColor(this.alphabet) + this.alphabet + Mapping.RESET);
                    }else{
                        System.out.print("");
                    }
                }
                System.out.println("");
            }
        }
        else{
            System.out.println("Piece undefined");
        }
    }

    public void centralization(){
        boolean pointO = this.piece.get(0).getX() == 0 && this.piece.get(0).getY() == 0;

        if (this.piece.size() >= 1 && !pointO){
            int offsetX, offsetY;
            offsetX = this.piece.get(0).getX();
            offsetY = this.piece.get(0).getY();

            for (Point point: piece){
                point.setX(point.getX() - offsetX);
                point.setY(point.getY() - offsetY);
            }
        }
    }

    public boolean isValidPiece(){
        for (Point point: this.piece){
            boolean hasNeighbor = false;

            for (Point point2: this.piece){
                if (point.isNeighborPoint(point2)){
                    hasNeighbor = true; 
                    break;
                }
            }
            if (!hasNeighbor){
                return false;
            }
        }
        return true;
    }
}
