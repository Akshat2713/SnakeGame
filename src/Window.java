import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Window extends JFrame{
    ThreadsController c;
    private static final long serialVersionUID= -2542001418764869760L;
    public static ArrayList<ArrayList<DataOfSquare>> Grid;
    public static int width=20;
    public static int height=20;

    public Window(){
        
        Grid=  new ArrayList<ArrayList<DataOfSquare>>();

        ArrayList<DataOfSquare> data;

        for(int i=0;i<width;i++){
            data=new ArrayList<DataOfSquare>();
            for (int j = 0; j < height; j++) {

                DataOfSquare d= new DataOfSquare(2);
                data.add(d);
                
            }
            Grid.add(data);
        }

        getContentPane().setLayout(new GridLayout(20,20,0,0));

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                getContentPane().add(Grid.get(i).get(j).square);
            }
        }


        Tuple position = new Tuple(5, 5);


        c= new ThreadsController(position);


        c.start();

        this.addKeyListener((KeyListener) new KeyboardListener());

    }
    public void waitForGameEnd() {
		try {
			c.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
