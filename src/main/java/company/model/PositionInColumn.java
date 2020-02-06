package company.model;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static company.model.GameManager.*;

public class PositionInColumn implements GameStrategy {

    @Override
    public void setPositionOfShip(ArrayList<String> allFieldsInGame, CopyOnWriteArrayList<String> fieldsForSingleShip) {

        boolean success = false;


        while (!success) {
            fieldsForSingleShip.clear();
            boolean neighbouringFieldIsEmpty = true;
            int fieldX = generateField();
            int fieldY = generateField();

            int j = 0;

            if ((fieldY + 2) <= TABLE_SIDE ) {
                while (j < LENGTH_OF_SHIP) {
                    if(checkNeighbouringFields(fieldY+j, fieldX, allFieldsInGame)){
                        fieldsForSingleShip.add(String.valueOf(fieldY+j) + fieldX);

                    }else{
                        neighbouringFieldIsEmpty = false;
                        break;
                    }
                    j++;
                }
            } else {
                while (j < LENGTH_OF_SHIP) {
                    if(checkNeighbouringFields(fieldY-j, fieldX, allFieldsInGame)){
                        fieldsForSingleShip.add(String.valueOf(fieldY-j) + fieldX);

                    }else{
                        neighbouringFieldIsEmpty = false;
                        break;
                    }
                    j++;
                }
            }

            success = checkIfAllFieldsAreLocateProperly(allFieldsInGame, fieldsForSingleShip, neighbouringFieldIsEmpty);

        }
    }
}

