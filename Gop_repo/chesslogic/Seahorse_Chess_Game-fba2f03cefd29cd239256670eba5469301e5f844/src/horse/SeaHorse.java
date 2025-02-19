package horse;

import java.awt.image.BufferedImage;

public class SeaHorse {
    private BufferedImage image;
    private int currentIndex = 0;
    @SuppressWarnings("unchecked")
    private Pair<Integer, Integer>[] path = new Pair[] {
            new Pair<>(0, 6), new Pair<>(1, 6), new Pair<>(2, 6), new Pair<>(3, 6), new Pair<>(4, 6), new Pair<>(5, 6),
            new Pair<>(6, 6), new Pair<>(6, 5), new Pair<>(6, 4), new Pair<>(6, 3), new Pair<>(6, 2), new Pair<>(6, 1),
            new Pair<>(6, 0), new Pair<>(7, 0), new Pair<>(8, 0), new Pair<>(8, 1), new Pair<>(8, 2), new Pair<>(8, 3),
            new Pair<>(8, 4), new Pair<>(8, 5), new Pair<>(8, 6), new Pair<>(9, 6), new Pair<>(10, 6),
            new Pair<>(11, 6), new Pair<>(12, 6), new Pair<>(13, 6), new Pair<>(14, 6), new Pair<>(14, 7),
            new Pair<>(14, 8), new Pair<>(13, 8), new Pair<>(12, 8), new Pair<>(11, 8), new Pair<>(10, 8),
            new Pair<>(9, 8), new Pair<>(8, 8), new Pair<>(8, 9), new Pair<>(8, 10), new Pair<>(8, 11),
            new Pair<>(8, 12), new Pair<>(8, 13), new Pair<>(8, 14), new Pair<>(7, 14), new Pair<>(6, 14),
            new Pair<>(6, 13), new Pair<>(6, 12), new Pair<>(6, 11), new Pair<>(6, 10), new Pair<>(6, 9),
            new Pair<>(6, 8), new Pair<>(5, 8), new Pair<>(4, 8), new Pair<>(3, 8), new Pair<>(2, 8), new Pair<>(1, 8),
            new Pair<>(0, 8), new Pair<>(0, 7), new Pair<>(0, 6)
    };
    public SeaHorse(BufferedImage image) {
        this.image = image;
    }
    public Pair<Integer, Integer> next() {
        return path[currentIndex++];
    }
    public boolean hasNext() {
        return currentIndex < path.length;
    }
    public Pair<Integer, Integer> getPosition() {
        return path[currentIndex];
    }
    public BufferedImage getImage() {
        return image;
    }
    public void reset() {
        currentIndex = 0;
    }
    public void move() {
        if (currentIndex < path.length - 1) {
            currentIndex++;
        } else {
            reset();
        }
    }
    //path coordinates
    // {0, 6}, {1, 6}, {2,6}, {3, 6}, {4, 6}, {5, 6}, {6, 6}, {6, 5}, { 6, 4}, {6, 3} , { 6, 2}, {6, 1} , {6, 0}, {7, 0}, {8, 0},{8,1},{8,2},{8,3},{8,4},{8,5},{8,6},{9,6},{10,6},{11,6},{12,6},{13,6},{14,6},{14,7},{14,8},{13,8},{12,8},{11, 8}, {10,8}, {9,8}, {8,8},{8,9},{8,10},{8,11},{8,12},{8,13},{8,14},{7,14},{6,14},{6,13},{6,12},{6,11},{6,10},{6,9},{6,8},{5,8},{4,8},{3,8},{2,8},{1,8},{0,8},{0,7},{0,6}

    //spawn 
    //blue      {1,1},{1,4},{5, 1},{5, 4}
    //red       {10,1},{10,4},{13,1},{13,4}
    //green     {1, 10}, {1, 13}, {5, 10}, {5, 13}
    //yellow    {10,10}, {10,13}, {13,10}, {13,13}

    //win
    // Blue {1,7},{2,7},{3,7},{4,7},{5,7},{6,7}
    // Green {7,1},{7,2},{7,3},{7,4},{7,5},{7,6}
    // Yellow {8,7}, {9,7}, {10,7}, {11,7}, {12,7},{13,7}
    // Red {7,8}, {7,9}, {7,10}, {7,11}, {7,12}, {7,13}

}
