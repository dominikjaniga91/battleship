package company.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static company.model.GameManager.TABLE_SIDE;

public interface GameStrategy {

    void setPositionOfShip(ArrayList<String> allFieldsInGame, CopyOnWriteArrayList<String> helperTable);

    default int generateField(){

        return (int)(Math.random() * TABLE_SIDE) + 1;
    }

    default boolean checkNeighbouringFields(int fieldY, int fieldX, ArrayList<String> allFieldsInGame){

        List<String> listOfNeighbouringFields = Arrays.asList(fieldY - 1 + String.valueOf(fieldX),
                fieldY - 1 + String.valueOf(fieldX + 1),
                fieldY - 1 + String.valueOf(fieldX - 1),
                fieldY + String.valueOf(fieldX + 1),
                fieldY + String.valueOf(fieldX - 1),
                fieldY + 1 + String.valueOf(fieldX),
                fieldY + 1 + String.valueOf(fieldX + 1),
                fieldY + 1 + String.valueOf(fieldX - 1)
        );

        return allFieldsInGame.stream().noneMatch(listOfNeighbouringFields::contains);
    }

    default boolean checkIfAllFieldsAreLocateProperly(ArrayList<String> allFieldsInGame, CopyOnWriteArrayList<String> fieldsForSingleShip, boolean neighbouringFieldIsEmpty ){

        boolean success = false;
        boolean ifNotContains = fieldsForSingleShip.stream().noneMatch(allFieldsInGame::contains);

        if ( ifNotContains && neighbouringFieldIsEmpty) {
            allFieldsInGame.addAll(fieldsForSingleShip);
            success = true;
        }
        return success;
    }
}
