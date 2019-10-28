package simple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Conveyors {
    private String name;
    private static ArrayList<Conveyors> activeconveyors = new ArrayList<>();

    public Conveyors(String name) {
        Conveyors newconveyor = new Conveyors(name);
        newconveyor.setName(name);
        activeconveyors.add(newconveyor);

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static ArrayList<Conveyors> getActiveconveyors() {
        return activeconveyors;
    }
    public static Conveyors getactiveconveyor(String name) {
        ListIterator conveyoriterator = getActiveconveyors().listIterator();
        for(ListIterator it = conveyoriterator; it.hasNext();){
            Conveyors conveyor =(Conveyors)it.next();
            if(conveyor.name.equals(name)){
                return conveyor;
            }
        }
        return null;
    }
}
