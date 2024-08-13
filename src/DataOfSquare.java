import java.awt.Color;
import java.util.ArrayList;

public class DataOfSquare {
    
    ArrayList<Color> C= new ArrayList<Color>();
    int color;
    SquarePanel square;

    public DataOfSquare(int col){
        C.add(Color.BLUE);  //for empty 
        C.add(Color.RED);
        C.add(Color.BLACK);

        color=col;
        square=new SquarePanel(C.get(color));
    }
    public void lightMeUp(int c){
        square.ChangeColour(C.get(c));
    }
}
