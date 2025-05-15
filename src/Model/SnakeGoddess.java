package Model;

public class SnakeGoddess extends Finding {

    public SnakeGoddess(String name, int points, String imagePath) {
        super(name, points, imagePath);
    }

    @Override
    public String toString() {
        return "SnakeGoddess{name='" + getName() + "', points=" + getPoints() + "}";
    }

}
