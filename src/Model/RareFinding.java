package Model;

public class RareFinding extends Finding {

    public RareFinding(String name, int points, String imagePath) {
        super(name, points, imagePath);
    }

    @Override
    public String toString() {
        return "RareFinding{name='" + getName() + "', points=" + getPoints() + "}";
    }

}
