package eat.neilson.Cafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Donut extends MenuItem {

    private DonutType type;
    private DonutFlavor flavor;


    public Donut() {
    }

    public Donut(DonutType type, DonutFlavor flavor) {
        this.type = type;
        this.flavor = flavor;
    }


    @Override
    public double price() {
        return type.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donut donut = (Donut) o;
        return type == donut.type && flavor == donut.flavor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, flavor);
    }

    public static void main(String[] args) {
        Donut a = new Donut();
        a.type = DonutType.CAKE;
        a.flavor = DonutFlavor.CHOCOLATE;

        Donut b = new Donut();
        b.type = DonutType.CAKE;
        b.flavor = DonutFlavor.CHOCOLATE;

        System.out.println(a);
        System.out.println(b);

        System.out.println(a == b);
        System.out.println(a.hashCode() == b.hashCode());
        System.out.println(a.equals(b));
    }


}
