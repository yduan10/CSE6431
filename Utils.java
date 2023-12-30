import java.util.concurrent.Semaphore;

public class Utils {
    public static final int BURGER_TIME = 5 * 1000;  // 5 minutes
    public static final int FRIES_TIME = 3 * 1000;   // 3 minutes
    public static final int COKE_TIME = 1 * 1000;    // 1 minute
    public static final int EATING_TIME = 30 * 1000; // 30 minutes

    public static void useMachine(Semaphore machine, String machineName, int cookNum, int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            machine.acquire();
            printTimeAndMessage(String.format("Cook %d uses the %s machine.", cookNum + 1, machineName));
            Thread.sleep(getMachineTime(machineName));
            machine.release();
        }
    }

    public static int getMachineTime(String machineName) {
        switch (machineName) {
            case "burger": return BURGER_TIME;
            case "fries": return FRIES_TIME;
            case "coke": return COKE_TIME;
            default: throw new IllegalArgumentException("Invalid machine name");
        }
    }

    public static int getAvailableResource(Semaphore[] resources) throws InterruptedException {
        int index = -1;
        boolean acquired = false;
        while (!acquired) {
            for (int i = 0; i < resources.length; i++) {
                if (resources[i].tryAcquire()) {
                    index = i;
                    acquired = true;
                    break;
                }
            }
            if (!acquired) {
                Thread.sleep(1000); // Check again after 1 second
            }
        }
        return index;
    }

    public static void printTimeAndMessage(String message) {
        long elapsedTime = (System.currentTimeMillis() - Restaurant6431.startTime) / 1000;
        System.out.println(String.format("%02d:%02d - %s", elapsedTime / 60, elapsedTime % 60, message));
    }
}

