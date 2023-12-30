import java.util.concurrent.*;

public class Diner implements Runnable {
    private int id;
    private int numBurgers;
    private int numFries;
    private boolean hasCoke;

    public Diner(int id, int numBurgers, int numFries, boolean hasCoke) {
        this.id = id;
        this.numBurgers = numBurgers;
        this.numFries = numFries;
        this.hasCoke = hasCoke;
    }

    @Override
    public void run() {
        try {
            Utils.printTimeAndMessage(String.format("Diner %d arrives.", id));
            
            // Wait for table to be available
            int tableNum = Utils.getAvailableResource(Restaurant6431.tables);
            Utils.printTimeAndMessage(String.format("Diner %d is seated at table %d.", id, tableNum + 1));

            // Assigning a cook to the diner
            int cookNum = Utils.getAvailableResource(Restaurant6431.cooks);
            Utils.printTimeAndMessage(String.format("Cook %d processes Diner %d's order.", cookNum + 1, id));

            // Cooking burger
            Utils.useMachine(Restaurant6431.burgerMachine, "burger", cookNum, numBurgers);

            // Cooking fries if needed
            if (numFries > 0) {
                Utils.useMachine(Restaurant6431.friesMachine, "fries", cookNum, numFries);
            }

            // Getting coke if needed
            if (hasCoke) {
                Utils.useMachine(Restaurant6431.cokeMachine, "coke", cookNum, 1);
            }

            Utils.printTimeAndMessage(String.format("Diner %d's order is ready. Diner %d starts eating.", id, id));
            Thread.sleep(Utils.EATING_TIME);
            Utils.printTimeAndMessage(String.format("Diner %d finishes. Diner %d leaves the restaurant.", id, id));

            // Diner leaves, free the table and the cook
            Restaurant6431.tables[tableNum].release();
            Restaurant6431.cooks[cookNum].release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

