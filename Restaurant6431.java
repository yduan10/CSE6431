import java.util.Scanner;
import java.util.concurrent.*;

public class Restaurant6431 {
    public static Semaphore[] cooks;
    public static Semaphore[] tables;
    public static Semaphore burgerMachine = new Semaphore(1);
    public static Semaphore friesMachine = new Semaphore(1);
    public static Semaphore cokeMachine = new Semaphore(1);
    public static long startTime;

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            int numDiners = sc.nextInt();
            int numTables = sc.nextInt();
            int numCooks = sc.nextInt();

            cooks = new Semaphore[numCooks];
            tables = new Semaphore[numTables];
            for (int i = 0; i < numCooks; i++) {
                cooks[i] = new Semaphore(1);
            }
            for (int i = 0; i < numTables; i++) {
                tables[i] = new Semaphore(1);
            }

            ScheduledExecutorService executor = Executors.newScheduledThreadPool(numDiners);

            startTime = System.currentTimeMillis();

            for (int dinerId = 1; dinerId <= numDiners; dinerId++) {
                String[] dinerDetails = sc.next().split(",");
                int arrivalTime = Integer.parseInt(dinerDetails[0]);
                int numBurgers = Integer.parseInt(dinerDetails[1]);
                int numFries = Integer.parseInt(dinerDetails[2]);
                boolean hasCoke = Integer.parseInt(dinerDetails[3]) == 1;

                Diner diner = new Diner(dinerId, numBurgers, numFries, hasCoke);
                executor.schedule(diner, arrivalTime * 1000, TimeUnit.MILLISECONDS);
            }

            executor.shutdown();
            executor.awaitTermination(2, TimeUnit.HOURS);

            Utils.printTimeAndMessage("The last diner leaves the restaurant.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

