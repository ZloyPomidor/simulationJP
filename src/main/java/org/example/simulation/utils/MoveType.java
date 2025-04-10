package org.example.simulation.utils;

import org.example.simulation.map.Coordinates;

import java.util.HashSet;
import java.util.Set;

public final class MoveType {

    private MoveType() {
    }

    public static Set<Coordinates> steps() {
        Set<Coordinates> result = new HashSet<>();

        for (int row = -1; row<= 1;row++){
            for (int column = -1; column<= 1;column++){
                if((row==0) &&(column==0)){
                    continue;
                }

                result.add(new Coordinates(row,  column));
            }
        }
        return result;

    }

}
