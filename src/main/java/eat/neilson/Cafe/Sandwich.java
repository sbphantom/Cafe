package eat.neilson.Cafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Subclass of MenuItem for Sandwiches
 *
 * @author Danny Onuorah
 */
public class Sandwich extends MenuItem {
    private double price = 0;
    private SandwichBread bread;
    private SandwichProtein protein;
    private ArrayList<SandwichAddOn> addOns = new ArrayList<>();

    /**
     * Default sandwich constructor
     */
    public Sandwich() {
    }

    /**
     * Sandwich constructor with bread, protein and ArrayList<SandwichAddOn>
     */
    public Sandwich(SandwichBread bread, SandwichProtein protein, ArrayList<SandwichAddOn> addOns) {
        this.bread = bread;
        this.protein = protein;
        this.addOns = addOns;
    }

    /**
     * Get sandwich bread
     *
     * @return sandwich bread
     */
    public SandwichBread getBread() {
        return this.bread;
    }

    /**
     * Get sandwich protein
     *
     * @return sandwich protein
     */
    public SandwichProtein getProtein() {
        return this.protein;
    }

    /**
     * Get number of addOns on the sandwich
     *
     * @return number of addOns
     */
    public int addOnCount() {
        return addOns.size();
    }

    /**
     * Returns sandwich name
     *
     * @return sandwich name.
     */
    @Override
    public String name() {
        return protein + " " + bread + " Sandwich";
    }

    /**
     * Returns string contain sandwich addOns.
     *
     * @return formatted string of addOns.
     */
    @Override
    public String addOnString() {
        return addOns.toString().strip();
    }

    /**
     * Calculates and returns subtotal price of sandwich
     *
     * @return sandwich price
     */
    @Override
    public double price() {

        double price = protein.price;

        for (SandwichAddOn addOn : addOns) {
            price += addOn.price;
        }
        return price;
    }

    /**
     * Set sandwich bread
     *
     * @param bread new bread selection
     */
    public void setBread(SandwichBread bread) {
        this.bread = bread;
    }

    /**
     * Set sandwich protein
     *
     * @param protein new protein selection
     */
    public void setProtein(SandwichProtein protein) {
        this.protein = protein;
    }

    /**
     * Add addOn to sandwich
     *
     * @param addOn addOn to add to the sandwich.
     */
    public void addAddOn(SandwichAddOn addOn) {
        addOns.add(addOn);
    }

    /**
     * Removes a addOn from the sandwich
     *
     * @param addOn addOn to remove from sandwich
     */
    public void removeAddOn(SandwichAddOn addOn) {
        addOns.remove(addOn);
    }

    /**
     * Set addOns on sandwich
     *
     * @param addOns an array list of sandwich addOns.
     */
    public void setSandwichAddOn(ArrayList<SandwichAddOn> addOns) {
        this.addOns = addOns;
    }

    /**
     * Compares two sandwich by bread then protein
     *
     * @param item against sandwich to compare
     * @return > 0 if source is less
     * = 0 if source is equal
     * < 0 if source is greater
     */
    @Override
    public int compareTo(MenuItem item) {
        if (item instanceof Sandwich sandwich) {
            if (this.bread.compareTo(sandwich.bread) != 0) {
                return this.bread.compareTo(sandwich.bread);
            } else if (this.protein.compareTo(sandwich.protein) != 0) {
                return this.protein.compareTo(sandwich.protein);
            } else {
                // return(this.addOns.compareTo(sandwich.addOns));
                return 0;
            }
        } else return this.getClass().getSimpleName().compareTo(item.getClass().getSimpleName());
    }

    /**
     * Returns whether a sandwich is equal
     *
     * @param o object
     * @return true if object is equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sandwich sandwich = (Sandwich) o;
        return bread == sandwich.bread &&
                protein == sandwich.protein &&
                Objects.equals(addOns, sandwich.addOns);
    }

    /**
     * Calculates hashcode based on sandwich fields
     *
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        Collections.sort(addOns);
        return Objects.hash(getClass(), bread, protein, addOns);
    }

    /**
     * Returns formatted sandwich string
     *
     * @return formatted sandwich string
     */
    @Override
    public String toString() {
        return protein + " " + bread + " sandwich with (" + addOns + ")";
    }
}
