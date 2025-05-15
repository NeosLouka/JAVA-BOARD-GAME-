package Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Path> paths;

    public Board() {
        paths = new ArrayList<>();
        paths.add(new Path("Knossos"));
        paths.add(new Path("Malia"));
        paths.add(new Path("Phaistos"));
        paths.add(new Path("Zakros"));
    }

    public List<Path> getPaths() {
        return paths;
    }

    public Path getPathByName(String pathName) {
        for (Path path : paths) { // Υποθέτοντας ότι το board έχει λίστα paths
            if (path.getName().equals(pathName)) {
                return path;
            }
        }
        return null; // Αν δεν βρεθεί, επιστρέφει null
    }


}
