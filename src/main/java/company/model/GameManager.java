package company.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class GameManager {

    final static int TABLE_SIDE = 8;
    final static int LENGTH_OF_SHIP = 3;
    final static int NUMBER_OF_SHIPS = 3;
    private ArrayList<String> computerShots = new ArrayList<>();
    private AbstractPlayer player;
    private AbstractPlayer computer;

    public LinkedList<String> prepareTheGame(){
        player = new Player();
        computer = new Computer();
        GameManager.generateShipsLocation(player);
        GameManager.generateShipsLocation(computer);

        return player.getAllFieldsAsList();
    }

    public HashMap<String, String> playTheGameAndReturnResult(String playerShot){

        HashMap<String, String> responseMessage = new HashMap<>();

        responseMessage.put("playerShotResult", GameManager.checkPlayerMove(computer, playerShot));

        String computerId = readComputerMove();
        responseMessage.put("computerShot", computerId);
        responseMessage.put("computerShotResult", GameManager.checkPlayerMove(player, computerId));

        if(responseMessage.containsValue("win")){
            computer = null;
            player= null;
        }
        return responseMessage;
    }

    private String readComputerMove(){

        String move;
        while(true){

            String fieldY = String.valueOf ((int)(Math.random() * TABLE_SIDE) + 1);
            String fieldX = String.valueOf ((int)(Math.random() * TABLE_SIDE) + 1);
            move = fieldY.concat(fieldX);
            if(!computerShots.contains(move)){
                computerShots.add(move);
                break;
            }
        }
        return move;
    }

    private static void helpLocateShipFields(GameStrategy strategy, ArrayList<String> fields, CopyOnWriteArrayList<String> fieldsForSingleShip){
        strategy.setPositionOfShip(fields,fieldsForSingleShip);
    }

    public static void generateShipsLocation(AbstractPlayer abstractPlayer) {

        ArrayList<String> allComputerFields = new ArrayList<>();
        CopyOnWriteArrayList<Ship> ships = new CopyOnWriteArrayList<>();
        int i = 0;
        while (i < NUMBER_OF_SHIPS) {

            if (i % 2 == 0) { // locate fields in column

                CopyOnWriteArrayList<String> fieldsForSingleShip = new CopyOnWriteArrayList<>();
                helpLocateShipFields(new PositionInColumn(),allComputerFields, fieldsForSingleShip);
                ships.add(new Ship());
                ships.get(i).setPositionsOfShip(fieldsForSingleShip);

            } else { // locate fields in row
                CopyOnWriteArrayList<String> fieldsForSingleShip = new CopyOnWriteArrayList<>();
                helpLocateShipFields(new PositionInRow(), allComputerFields, fieldsForSingleShip);
                ships.add(new Ship());
                ships.get(i).setPositionsOfShip(fieldsForSingleShip);

            }
            i++;
        }
        abstractPlayer.setShips(ships);
    }

    public static String checkPlayerMove(AbstractPlayer player,String move){

        AtomicReference<String> result = new AtomicReference<>("missed");

        player.getShips()
                .forEach(ship -> ship.getPositionsOfShip()
                        .forEach(field -> {
                            if (field.equals(move)) {
                                ship.getPositionsOfShip().remove(field);
                                result.set("hit");
                            }
                            if(ship.getPositionsOfShip().isEmpty()){
                                result.set("sunken");
                                player.getShips().remove(ship);
                            }

                        }));

        if(player.getShips().isEmpty()){
            result.set("win");
        }
        return result.get();
    }
}
