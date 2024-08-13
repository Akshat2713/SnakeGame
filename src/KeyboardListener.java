import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// import javax.swing.JFrame;

public class KeyboardListener extends KeyAdapter{
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {

            case 40:
            if (ThreadsController.directionSnake!=3 && ThreadsController.ableToAcceptKeyPress){
                ThreadsController.directionSnake=4;
                ThreadsController.ableToAcceptKeyPress=false;
            }

            break;
            case 39:
                if ( ThreadsController.directionSnake!=2 &&ThreadsController.ableToAcceptKeyPress){
                    ThreadsController.directionSnake=1;
                    ThreadsController.ableToAcceptKeyPress=false;
                }

                break;
                case 38:
                if ( ThreadsController.directionSnake!=4 && ThreadsController.ableToAcceptKeyPress) {
                    ThreadsController.directionSnake=3;
                    ThreadsController.ableToAcceptKeyPress=false;
                }

                break;

                case 37:
                if ( ThreadsController.directionSnake!=1 && ThreadsController.ableToAcceptKeyPress){
                    ThreadsController.directionSnake=2;
                    ThreadsController.ableToAcceptKeyPress=false;
                }

                break;

            
        
            default:
                break;
        }
    }
}
