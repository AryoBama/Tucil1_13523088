package src;

public class Point{
    private int x;
    private int y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int newX){
        this.x = newX;
    }

    public void setY(int newY){
        this.y = newY;
    }


    public void translation(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }

    public boolean isNeighborPoint(Point targetPoint){
        return Math.abs(this.x - targetPoint.getX()) == 1 
        || Math.abs(this.x - targetPoint.getY()) == 1 
        || Math.abs(this.y - targetPoint.getX()) == 1 
        || Math.abs(this.y - targetPoint.getY()) == 1;
    }
}
