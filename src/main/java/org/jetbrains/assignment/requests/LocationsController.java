package org.jetbrains.assignment.requests;

import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.assignment.objects.Coords;
import org.jetbrains.assignment.objects.Location;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LocationsController {
    @PostMapping(value = "/locations", consumes = "application/json", produces = "application/json")
    public Coords[] getLocations(@RequestBody Location[] locations, HttpServletResponse response) {
        ArrayList<Coords> coordsArrayList = new ArrayList<Coords>();

        int currentX = 0;
        int currentY = 0;
        coordsArrayList.add(new Coords(currentX, currentY));

        for (Location location : locations) {
            if (location.getDirection().equalsIgnoreCase("north")) {
                currentY += location.getSteps();
            }
            if (location.getDirection().equalsIgnoreCase("south")) {
                currentY -= location.getSteps();
            }
            if (location.getDirection().equalsIgnoreCase("east")) {
                currentX += location.getSteps();
            }
            if (location.getDirection().equalsIgnoreCase("west")) {
                currentX -= location.getSteps();
            }
            coordsArrayList.add(new Coords(currentX, currentY));
        }

        return coordsArrayList.toArray(new Coords[0]);
    }

    @PostMapping(value = "/moves", consumes = "application/json", produces = "application/json")
    public Location[] getMoves(@RequestBody Coords[] coords, HttpServletResponse response) {
        ArrayList<Location> arrayList = new ArrayList<Location>();

        int currentX = 0;
        int currentY = 0;
        int index = 0;
        for (Coords coord : coords) {
            if (index == 0) {
                currentX = coord.getX();
                currentY = coord.getY();
            } else {
                String direction = "";
                int steps = 0;
                if (coord.getY() > currentY) {
                    direction = "north";
                    steps = (coord.getY() - currentY);
                }
                if (coord.getX() > currentX) {
                    direction = "east";
                    steps = (coord.getX() - currentX);
                }
                if (coord.getY() < currentY) {
                    direction = "south";
                    steps = (currentY - coord.getY());
                }
                if (coord.getX() < currentX) {
                    direction = "west";
                    steps = (currentX - coord.getX());
                }

                if (direction.equalsIgnoreCase("north")) {
                    currentY += steps;
                }

                if (direction.equalsIgnoreCase("east")) {
                    currentX += steps;
                }

                if (direction.equalsIgnoreCase("south")) {
                    currentY -= steps;
                }

                if (direction.equalsIgnoreCase("west")) {
                    currentX -= steps;
                }

                arrayList.add(new Location(direction, steps));
            }
            index++;

        }

        return arrayList.toArray(new Location[0]);
    }
}