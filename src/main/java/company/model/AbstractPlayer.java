package company.model;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class AbstractPlayer {

    private CopyOnWriteArrayList<Ship> ships;


    public CopyOnWriteArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(CopyOnWriteArrayList<Ship> ships) {
        this.ships = ships;
    }


    public LinkedList<String> getAllFieldsAsList() {

        LinkedList<String> fields = new LinkedList<>();
        ships.forEach(name -> fields.addAll(name.getPositionsOfShip()));

        return fields;
    }
}

