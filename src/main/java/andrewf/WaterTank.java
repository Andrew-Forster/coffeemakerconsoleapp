package andrewf;
import static andrewf.Utils.*;

public class WaterTank {
    double waterLevel;
    double maxCups;

    
    public WaterTank() {
        waterLevel = 0;
        maxCups = 12;
    }

    /**
     * Drain the water tank to the desired amount
     * @param amount
     */
    public void drain(double amount) {
        waterLevel -= amount;
    }

    /**
     * Get the current water level in the tank
     * @return double waterLevel
     */
    public double getWaterLevel() {
        return waterLevel;
    }

    /**
     * Fill the water tank to the desired amount
     * @param desiredAmount
     * @return String message "Water Tank was filled to " + waterLevel + " cups"
     * @throws InterruptedException
     */
    public String fillTank(double desiredAmount) throws InterruptedException {
        int increment = 10;
        int numChars = 10;
        double time = 300;
        System.out.println("Filling water tank...");
        for (int i = 0; i < increment; i++) {
            waterLevel = round(waterLevel + (desiredAmount / increment), 1);
            String str = colorString("blue","█".repeat(i) + "░".repeat(numChars - i -1));
            System.out.print("\r" + str + " - " + waterLevel + " cups");
            Thread.sleep((long) time);
        }
        return success("Water Tank was filled to " + waterLevel + " cups"); 
    }
    
}
