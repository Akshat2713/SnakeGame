public class Tuple {
    public int x;
    public int y;

    Tuple(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void ChangeData(int x, int y){
        this.x=x;
        this.y=y;
    }
    public int getX(){return x;}
    public int getY(){return y;}
}
