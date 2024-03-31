package eat.neilson.Cafe;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SandwichTest {

    @Test
    public void testSandwichA() {
        Sandwich sandwich = new Sandwich();
        sandwich.setBread(SandwichBread.BAGEL);
        sandwich.setProtein(SandwichProtein.BEEF);
        ArrayList<SandwichAddOn> addOns = new ArrayList<>();
        sandwich.setSandwichAddOn(addOns);
        assertEquals(10.99, sandwich.price(), 0.01);
    }

    @Test
    public void testSandwichB() {
        Sandwich sandwich = new Sandwich();
        sandwich.setBread(SandwichBread.SOUR_DOUGH);
        sandwich.setProtein(SandwichProtein.FISH);
        ArrayList<SandwichAddOn> addOns = new ArrayList<>(Arrays.asList(SandwichAddOn.values()));
        sandwich.setSandwichAddOn(addOns);
        assertEquals(11.89, sandwich.price(), 0.01);
    }

    @Test
    public void testSandwichCToString() {
        Sandwich sandwich = new Sandwich();
        sandwich.setBread(SandwichBread.BAGEL);
        sandwich.setProtein(SandwichProtein.CHICKEN);
        ArrayList<SandwichAddOn> addOns = new ArrayList<>(Arrays.asList(SandwichAddOn.values()));
        sandwich.setSandwichAddOn(addOns);
        String expectedString = SandwichProtein.CHICKEN + " " + SandwichBread.BAGEL + " sandwich with (" + addOns + ")";
        String actuallString = sandwich.toString();
        assertEquals(expectedString, actuallString);
    }

    @Test
    public void testSandwichDAddOnCount() {
        Sandwich sandwich = new Sandwich();
        sandwich.setBread(SandwichBread.BAGEL);
        sandwich.setProtein(SandwichProtein.CHICKEN);
        sandwich.addAddOn(SandwichAddOn.CHEESE);
        sandwich.addAddOn(SandwichAddOn.ONIONS);

        int expectedCount = 2;
        int actualCount = sandwich.addOnCount();


        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void testSandwichEEquals() {
        Sandwich sandwich1 = new Sandwich();
        sandwich1.setBread(SandwichBread.BAGEL);
        sandwich1.setProtein(SandwichProtein.BEEF);
        sandwich1.addAddOn(SandwichAddOn.CHEESE);

        Sandwich sandwich2 = new Sandwich();
        sandwich2.setBread(SandwichBread.BAGEL);
        sandwich2.setProtein(SandwichProtein.BEEF);
        sandwich2.addAddOn(SandwichAddOn.CHEESE);

        Sandwich sandwich3 = new Sandwich();
        sandwich3.setBread(SandwichBread.SOUR_DOUGH);
        sandwich3.setProtein(SandwichProtein.CHICKEN);
        sandwich3.addAddOn(SandwichAddOn.CHEESE);
        sandwich3.addAddOn(SandwichAddOn.ONIONS);

        // Check if the sandwiches are equal
        assertTrue(sandwich1.equals(sandwich2));
        assertFalse(sandwich1.equals(sandwich3));

    }
    @Test
    public void testSandwichFHashCode() {
        Sandwich sandwich1 = new Sandwich();
        sandwich1.setBread(SandwichBread.BAGEL);
        sandwich1.setProtein(SandwichProtein.BEEF);

        Sandwich sandwich2 = new Sandwich();
        sandwich2.setBread(SandwichBread.BAGEL);
        sandwich2.setProtein(SandwichProtein.BEEF);

        assertEquals(sandwich1.hashCode(), sandwich2.hashCode());
    }

}
