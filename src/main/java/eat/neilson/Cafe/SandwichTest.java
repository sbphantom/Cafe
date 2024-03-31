package eat.neilson.Cafe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SandwichTest {

    @Test
    public void testSandwichA(){
        Sandwich sandwich = new Sandwich();
        sandwich.setBread();

        assertEquals(0.00, sandwich.price(), 0.0);

    }

    @Test
    public void testSandwichB(){
        Sandwich sandwich = new Sandwich();


        assertEquals(0.00, sandwich.price(), 0.0);
    }

    @Test
    public void testSandwichC(){
        Sandwich sandwich = new Sandwich();


        assertEquals(0.00, sandwich.price(), 0.0);
    }

    @Test
    public void testSandwichD(){
        Sandwich sandwich = new Sandwich();


        assertEquals(0.00, sandwich.price(), 0.0);
    }

    @Test
    public void testSandwichE(){
        Sandwich sandwich = new Sandwich();


        assertEquals(0.00, sandwich.price(), 0.0);
    }

}
