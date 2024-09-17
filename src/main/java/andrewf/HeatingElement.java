package andrewf;
import static andrewf.Utils.*;

public class HeatingElement {
    private boolean status;
    private int temp;

    public HeatingElement() {
        status = false;
        temp = 0;
    }

    /**
     * Turn on the heating element
     * @return String message success or fail
     */
    public String turnOn() {
        if (status) {
            return fail("Heating element is already on");
        }
        status = true;
        return success("Heating element is on");
    }
    // Should refactor this to be more like turnOn() and return a string message
    public void turnOff() {
        status = false;
    }

    public boolean status() {
        return status;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getTemp() {
        return temp;
    }

    /**
     * Heat up the heating element to the desired temperature
     * @param desiredTemp
     * @return String message "Heating set to " + temp + " degrees"
     * @throws InterruptedException
     */
    public String heatUp(double desiredTemp) throws InterruptedException {
        if (!status) {
            return fail("Heating element isn't even on bro, how many brain cells do you even have?");
        }
        // Increase temperature by 10 degrees every 300 milliseconds
        // For a total time of 3 seconds
        int increment = 10;
        double time = 300;
        System.out.println("Heating up...");
        for (int i = 0; i < increment; i++) {
            temp += (desiredTemp / increment);
            String str = colorString("red","█".repeat(i) + "░".repeat(increment - i - 1));
            System.out.print("\r" + str + " - " + temp + " degrees");
            Thread.sleep((long) time);
        }
        return success("Heating set to " + temp + " degrees"); 
    }
}
