package andrewf;
import static andrewf.Utils.*;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * Control the flow of the program
     * @param args
     * @throws InterruptedException
     */
    public static void main( String[] args ) throws InterruptedException
    {

        CoffeeMaker coffeeMaker = new CoffeeMaker();

        // How to make Coffee
        // 1. Fill water tank
        // 2. Load filter
        // 3. Add grounds
        // 4. Turn on coffee maker & heat up
        // 5. Brew coffee
        // 6. Turn off coffee maker
        // 7. Enjoy coffee
        // 8. Empty filter
        // 9. Repeat

        displayOptions(coffeeMaker, "");


        while (true) {
            String input = System.console().readLine();
            displayOptions(coffeeMaker, input);
            double d;
            switch (input.toLowerCase()) {
                // Fill water tank
                case "1":
                    System.out.println("How many cups of water would you like to add?");
                    d = validateDouble(System.console().readLine());
                    System.out.println(coffeeMaker.fillWaterTank(d));
                    break;
                // Load filter
                case "2":
                    System.out.println(coffeeMaker.loadFilter());
                    break;
                // Add grounds
                case "3":
                    System.out.println("How many grams of coffee grounds would you like to add?");
                    d = validateDouble(System.console().readLine());
                    System.out.println(coffeeMaker.addGrounds(d));
                    break;
                // Turn on coffee maker & heat up
                case "4":
                    System.out.println(coffeeMaker.turnOnMaker());
                    System.out.println(coffeeMaker.heatUp());
                    break;
                // Brew coffee
                case "5":
                    try {
                        System.out.println(coffeeMaker.brew());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                // Turn off coffee maker
                case "6":
                    System.out.println(coffeeMaker.turnOffMaker());
                    break;
                // Empty filter
                case "7":
                    System.out.println(coffeeMaker.cleanFilter());
                    break;
                case "e":
                    if (coffeeMaker.heatingStatus()) {
                            System.out.println(fail("DUDE YOU LEFT THE COFFEE MAKER ON! LOOK WHAT YOU DID! YOU BURNED THE HOUSE DOWN!"));
                            burntHouseArt();
                    } else {
                        System.out.println(info("Cya, nerd."));
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input, please try again");
                    break;
            }
            System.out.println("Make a New Selection: ");
        }
    
    }
}
