package eat.neilson.Cafe;

public abstract class MenuItem {





    public abstract double price();

    @Override
    public abstract String toString();

    //@Override
    public abstract int compareTo(MenuItem item);

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();




}
