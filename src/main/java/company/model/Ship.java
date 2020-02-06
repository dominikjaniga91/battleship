package company.model;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ship {


    private CopyOnWriteArrayList<String> positionsOfShip = new CopyOnWriteArrayList<>();

    public Ship(){ }

    public void setPositionsOfShip(CopyOnWriteArrayList<String> positionsOfShip) {  this.positionsOfShip = positionsOfShip;  }

    public CopyOnWriteArrayList<String> getPositionsOfShip() {  return this.positionsOfShip; }

}
