import java.util.ArrayList;

public class ThreadsController extends Thread {
    ArrayList<ArrayList<DataOfSquare>> Squares= new ArrayList<ArrayList<DataOfSquare>>();
    Tuple headSnakePos;
    int sizeSnake = 2;
    long speed=75;
    
    public static int directionSnake;

    ArrayList<Tuple> positions = new ArrayList<Tuple>();
    Tuple foodPosition;

    public static int score;
    public static boolean ableToAcceptKeyPress;
    public static boolean gameEnd;

    ThreadsController(Tuple positionDepart){
        score=0;
        Squares=Window.Grid;
        ableToAcceptKeyPress=true;
        gameEnd=false;

        headSnakePos= new Tuple(positionDepart.x, positionDepart.y);
        directionSnake=1;

        Tuple headPos= new Tuple(headSnakePos.getX(), headSnakePos.getY());
        positions.add(headPos);

        foodPosition= getValNotInSnake();
        spawnFood(foodPosition);

    }

    public void run(){
        while (!gameEnd) {
                ableToAcceptKeyPress=true;
                moveInternal(directionSnake);
                checkCollision();
                moveExternal();
                deleteTail();
                pauser();

        }

    }

    private void pauser(){
        try{
            sleep(speed);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    private void checkCollision(){
        Tuple posCritique = positions.get(positions.size()-1);

        for(int i =0; i<=positions.size()-2; i++ ){
            boolean biteItself= posCritique.getX()== positions.get(i).getX() && posCritique.getY()== positions.get(i).getY();
            if (biteItself) {
                stopTheGame();
            }
        }

        // Check to change x and y after execution.
        boolean eatingFood= posCritique.getX()== foodPosition.y && posCritique.getY()==foodPosition.x;
        if (eatingFood) {
            // System.out.println("स्वादिष्ट !!!");
            score++;
            sizeSnake = sizeSnake+1;
            foodPosition=getValNotInSnake();

            spawnFood(foodPosition);
        }
    }

    private void stopTheGame(){
        gameEnd=true;
    }


    private void spawnFood(Tuple foodPositionIn){
        Squares.get(foodPositionIn.x).get(foodPositionIn.y).lightMeUp(1);
    }


    private Tuple getValNotInSnake(){
        Tuple p;
        int ranX=0+ (int)(Math.random()*19);
        int ranY=0+ (int)(Math.random()*19);
        p=new Tuple(ranX, ranY);

        for( int i=0;i<=positions.size()-1;i++ ){
            if (p.getY()== positions.get(i).getX() && p.getX()== positions.get(i).getY()) {
                ranX=0+ (int)(Math.random()*19);
                ranY=0+ (int)(Math.random()*19);
                p=new Tuple(ranX, ranY);
                i=0;
            }
        }
        return p;
    }


    private void moveInternal(int dir){
        switch (dir) {
            case 4:
                headSnakePos.ChangeData(headSnakePos.x,(headSnakePos.y+1)%20);
                positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
                break;

            case 3:
                if (headSnakePos.y-1<0) {
                    headSnakePos.ChangeData(headSnakePos.x, 19);
                }
                else{
                    headSnakePos.ChangeData(headSnakePos.x, Math.abs(headSnakePos.y-1)%20);

                }
                positions.add(new Tuple (headSnakePos.x,headSnakePos.y));
                break;

            case 2:
                if(headSnakePos.x-1<0){
                    headSnakePos.ChangeData(19, headSnakePos.y);
                }
                else{
                    headSnakePos.ChangeData(Math.abs(headSnakePos.x-1)%20, headSnakePos.y);
                }
                positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
                break;
                
            case 1:
                headSnakePos.ChangeData((headSnakePos.x+1)%20, headSnakePos.y);
                positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
                break;
            
        }
    }

    private void moveExternal(){
        for(Tuple t: positions){
            int y= t.getX();
            int x= t.getY();
            try {
            Squares.get(x).get(y).lightMeUp(0);
            } catch (IndexOutOfBoundsException e) {
                stopTheGame();
            }
        }
    }

    private void deleteTail(){
        int cmpt= sizeSnake;
        for(int i=positions.size()-1;i>=0;i--){
            if(cmpt==0){
                Tuple t=positions.get(i);
                Squares.get(t.y).get(t.x).lightMeUp(2);
            }
            else{
                cmpt--;
            }
        }

        cmpt=sizeSnake;
        for(int i=positions.size()-1;i>=0;i--){
            if (cmpt==0) {
                positions.remove(i);
    
            }
    
            else{
                cmpt--;
            }

        }
        
    }
}
