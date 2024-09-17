package andrewf;

import static andrewf.Utils.*;

//! Class Notes:
// Using updateInfo() after each method call is important to 
// keep the user informed of the state of the coffee maker

public class CoffeeMaker {

    private FilterBasket filterBasket;
    private WaterTank waterTank;
    private HeatingElement heatingElement;
    private boolean isBrewing;
    private int operatingTemp;
    private int cupsOfCoffeeBrewed;
    private int waterNeededForBrew = 2;

    public CoffeeMaker() {
        filterBasket = new FilterBasket();
        waterTank = new WaterTank();
        heatingElement = new HeatingElement();
        isBrewing = false;
        operatingTemp = 150;
        cupsOfCoffeeBrewed = 0;
    }

    // Heating Element

    /**
     * Return the status of the heating element
     * @return Boolean status
     */
    public Boolean heatingStatus() {
        return heatingElement.status();
    }

    /**
     * Return the temperature of the heating element
     * @return String message success or fail
     */
    public String heatUp() throws InterruptedException {
        if (heatingElement.getTemp() >= operatingTemp) {
            return warn("Heating element is already at operating temperature");
        }
        String res = heatingElement.heatUp(operatingTemp);
        updateInfo();
        return res;
    }

    // Water Tank

    /**
     * Fill the water tank
     * @param amount double amount of water to fill
     * @return String message success or fail
     * @throws InterruptedException
     */
    public String fillWaterTank(Double amount) throws InterruptedException {

        if (amount <= 0) {
            return fail("Amount must be greater than 0");
        }
        if (amount + waterTank.getWaterLevel() > waterTank.maxCups) {
            return fail("Amount exceeds tank capacity of " + waterTank.maxCups + " cups");
        }
        String res = waterTank.fillTank(amount);
        updateInfo();
        return res;
    }

    // Filter Basket

    /**
     * Load the filter into the coffee maker
     * @return String message success or fail
     */
    public String loadFilter() {
        String res = filterBasket.loadFilter();
        updateInfo();
        return res;
    }


    /**
     * Add grounds to the filter basket
     * @param amount amount of grounds to add
     * @return String message success or fail
     */
    public String addGrounds(double amount) {
        String res = filterBasket.addGrounds(amount);
        updateInfo();
        return res;
    }

    /**
     * Unload the filter from the coffee maker and clean it
     * @return String message success or fail
     */
    public String cleanFilter() {
        String res = filterBasket.cleanFilter();
        updateInfo();
        return res;
    }

    // Main functions

    /**
     * Turn on the coffee maker
     * @return String message success or fail
     */
    public String turnOnMaker() {
        if (heatingElement.status()) {
            return warn("Coffee maker is already on goofball");
        }
        heatingElement.turnOn();
        updateInfo();
        return success("Coffee maker is on");
    }

    /**
     * Turn off the coffee maker
     * @return String message success or fail
     */
    public String turnOffMaker() {
        if (!heatingElement.status()) {
            return warn("Coffee maker is already off goofball");
        }
        heatingElement.turnOff();
        heatingElement.setTemp(0);
        isBrewing = false;
        updateInfo();
        return success("Coffee maker is off");
    }

    /**
     * Brew coffee
     * @return String message success or fail
     * @throws InterruptedException
     */
    public String brew() throws InterruptedException {
        if (filterBasket.isFilled() && filterBasket.filterPresent() && waterTank.getWaterLevel() >= waterNeededForBrew && !isBrewing
                && heatingElement.status() && heatingElement.getTemp() >= operatingTemp && filterBasket.isClean()) {
            isBrewing = true;
            cupsOfCoffeeBrewed++;
            filterBasket.setClean(false);
            waterTank.drain(waterNeededForBrew);
            // Progress bar
            int increment = 10;
            double time = 500;
            int progress = 0;
            System.out.println("Brewing...");
            for (int i = 0; i < increment; i++) {
                progress += (100 / increment);
                String str = colorString("yellow","█".repeat(i) + "░".repeat(increment - i - 1));
                System.out.print("\r" + str + " - " + progress + "%");
                Thread.sleep((long) time);
            }
            updateInfo();
            return (success("Coffee is brewed!") 
            + "\n-----------------------------------"
            + warn("If you would like to make more coffee, please empty the filter and repeat the process")
            + fail("Don't forget to turn off the coffee maker when leaving!!! Or else...")
            + info("Used " + waterNeededForBrew + " cups of water")
            + "\n-----------------------------------\n"
            ); 
        }
        // If brewing conditions are not met
        return ("Brewing conditions Not Met \n"
                + (filterBasket.isFilled() ? success("Filter is filled\n") : fail("Filter MUST be filled\n"))
                + (filterBasket.filterPresent() ? success("Filter is present\n") : fail("Filter MUST be present\n"))
                + (filterBasket.isClean() ? success("Filter is clean\n") : fail("Filter MUST be clean\n"))
                + (waterTank.getWaterLevel() >= waterNeededForBrew ? success("Water tank is filled\n") : fail("Water tank MUST have " + waterNeededForBrew + " cups of water MINIMUM\n"))
                + (heatingElement.status() ? success("Heating element is on\n") : fail("Heating element MUST be on\n"))
                + (heatingElement.getTemp() >= operatingTemp ? success("Heating element is at operating temperature\n") : fail("Heating element MUST be at 150 degrees\n"))
                + "\n-----------------------------------\n"
                + "Please correct the above conditions and try again"
                + "\n-----------------------------------\n"

        );

    }

    // Coffee Maker Info

    // Return how full the water tank is
    // Return if the filter is present
    // Return if the filter is filled
    // Return if the heating element is on
    // Return if the heating element is at operating temp

    /**
     * Return the coffee maker status of the water tank, filter, heating element, and operating temp, etc.
     * @return String message
     */
    public String coffeeMakerInfo() {
        return ("-----------------------------------\n"
                + colorString("magenta","Coffee Maker Info\n")
                + "Coffee's Brewed: " + colorString("cyan", String.valueOf(cupsOfCoffeeBrewed)) + "\n"
                + "-----------------------------------\n"
                + waterLevel() + "\n"
                + filterPresent() + "\n"
                + (filterBasket.filterPresent() ? (filterClean() + "\n") : "")
                + filterFilled() + "\n"
                + isOn() + "\n"
                + isAtTemp() + "\n"
                + "-----------------------------------\n");
    }

    // Private methods

    // Return if the coffee maker filter is filled

    private String filterFilled() {
        String filled = filterBasket.isFilled() ? colorString("green", "√") : colorString("red", "X");
        return "Filter Filled? " + filled;
    }

    // Return if the coffee maker filter is present

    private String filterPresent() {
        String present = filterBasket.filterPresent() ? colorString("green", "√") : colorString("red", "X");
        return "Filter Present? " + present;
    }

    // Return if the coffee maker filter is clean

    private String filterClean() {
        String clean = filterBasket.isClean() ? colorString("green", "√") : colorString("red", "X");
        return "Filter Clean? " + clean;
    }

    // Return if coffee maker is on

    private String isOn() {
        String on = heatingElement.status() ? colorString("green", "√") : colorString("red", "X");
        return "Coffee Maker On? " + on;
    }

    // Return if temperature is at operating temp

    private String isAtTemp() {
        String atTemp = heatingElement.getTemp() >= operatingTemp ? colorString("green", "√") : colorString("red", "X");
        return "Heated to 150? " + atTemp;
    }

    // Return water level

    private String waterLevel() {
        // Return only 10 characters
        int numChars = 12;
        double capacity = waterTank.maxCups;
        double level = waterTank.getWaterLevel();
        int fillRatio = (int) (level / capacity * numChars);

        return ("Water Level: " +
            colorString("blue","█".repeat(fillRatio) + "░".repeat((int) (numChars - fillRatio))) 
            + " " + level + " / " + capacity + " cups");
    }

    private void updateInfo() {
        clear();
        System.out.println(coffeeMakerInfo());
        displayOptions(this, "");
    }
}
