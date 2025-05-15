package Model;

public abstract class Finding {
    private final String name;
    private final int points;
    private final String imagePath;

    public Finding(String name, int points, String imagePath) {
        this.name = name;
        this.points = points;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return "Finding{name='" + name + "', points=" + points + "}";
    }

}
