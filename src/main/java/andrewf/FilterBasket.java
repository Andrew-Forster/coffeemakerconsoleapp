package andrewf;
import static andrewf.Utils.*;

public class FilterBasket {
    private boolean filled;
    private boolean filterPresent;
    private double fillLevel;
    private Boolean isClean;

    public FilterBasket() {
        filled = false;
        filterPresent = false;
        fillLevel = 0;
        isClean = true;
    }

    /**
     * Load the filter into the coffee maker
     * @return String message
     */
    public String loadFilter() {
        if (filterPresent) {
            return warn("Filter is already loaded, you goofball");
        }
        if (!isClean) {
            return warn("You need to clean the filter first, you slob");
        }
        filterPresent = true;
        return info("Filter loaded (:");
    }

    public void unloadFilter() {
        filterPresent = false;
    }

    /**
     * Add grounds to the filter basket
     * @param amount double amount of grounds to add
     */
    public String addGrounds(double amount) {
        if (!filterPresent) {
            return warn("Where's your filter bro? You don't want to drink that sludge... do you? ...");
        }

        if (!isClean) {
            return warn("You need to clean the filter first, you slob");
        }

        if (filled) {
            return warn("Filter is already filled, you trying to make a mess?");
        }
        

        if (amount > 40 && amount < 300) {
            fillLevel += amount;
            filled = true;
            return success(amount + " grams of coffee grounds added");
        } else if (amount < 40) {
            return warn("You call that a coffee? Add at least 40 grams of coffee grounds");
        } else {
            return fail("WOAH! You trying to get a heart attack? Add less than 300 grams of coffee grounds!!!");
        }
    }

    /**
     * Clean the filter basket
     * @return String message
     */
    public String cleanFilter() {
        if (!filterPresent) {
            return warn("There's no filter to clean goofball"); 
        }
        if (!filled) {
            return warn("Filter is already clean dude, add some grounds and lets get cookin'!");
        }
        fillLevel = 0;
        filled = false;
        filterPresent = false;
        isClean = true;
        return success("Aww did your mommy clean the filter for you? It's all clean now!");
    }
    

    public boolean isFilled() {
        return filled;
    }

    public boolean filterPresent() {
        return filterPresent;
    }

    public double getFillLevel() {
        return fillLevel;
    }

    public Boolean isClean() {
        return isClean;
    }

    public void setClean(Boolean clean) {
        isClean = clean;
    }
}
