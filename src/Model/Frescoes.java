package Model;

public class Frescoes extends Finding {

    public Frescoes(String name, int points, String imagePath) {
        super(name, points, imagePath);
    }


    @Override
    public String toString() {
        return "Fresco{name='" + getName() + "', points=" + getPoints() + "}";
    }

}
