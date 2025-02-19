package dice;

import java.util.Random;

public class Dice {
    private Random random;
    private int lastRoll;

    public Dice(){
        random = new Random();
        lastRoll = 1;
    }
    public int roll(){
        lastRoll = random.nextInt(6) + 1;
        return lastRoll;
    }
    public int getLastRoll(){
        return lastRoll;
    }

}
