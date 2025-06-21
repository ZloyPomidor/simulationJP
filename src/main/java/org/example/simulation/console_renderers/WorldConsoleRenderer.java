package org.example.simulation.console_renderers;

import org.example.simulation.entities.Entity;
import org.example.simulation.map.Coordinates;
import org.example.simulation.map.WorldMap;

public class WorldConsoleRenderer {

    public static final String BACKGROUND = "\uD83D\uDFEB";

    public static final String PREDATOR = "\uD83E\uDD81";

    public static final String GRASS = "\uD83C\uDF3F";

    public static final String TREE = "\uD83C\uDF33";

    public static final String HERBIVORE = "\uD83D\uDC34";

    public static final String ROCK = "⛰";

    public static String getTheSelectedSmiley(Entity obj){
        return switch (obj.getClass().getSimpleName()) {
            case "Grass" -> GRASS;
            case "Herbivore" -> HERBIVORE;
            case "Predator" -> PREDATOR;
            case "Tree" -> TREE;
            case "Rock" -> ROCK;
            default -> throw new IllegalArgumentException();
        };
    }

    private static String getLineOutPut(Coordinates cord, WorldMap worldMap){
        if (!worldMap.coordinatesIsEmpty(cord)){
            return getTheSelectedSmiley(worldMap.getEntity(cord));
        }else{
            return BACKGROUND;
        }
    }

    public static void render(WorldMap worldMap){
        for (int y = worldMap.column; y>=WorldMap.MIN_COORDINATES_OF_THE_WORLD; y--){
            StringBuilder line = new StringBuilder();
            for (int x = 0; x<= worldMap.row; x++){
                line.append(getLineOutPut(new Coordinates(x, y), worldMap));
            }
            System.out.println(line);
        }
    }

    public static void output(String message){
        System.out.println(message);
    }

}
