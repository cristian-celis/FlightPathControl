package co.edu.uptc.model;

import co.edu.uptc.pojos.Plane;
import co.edu.uptc.pojos.Points;

import java.util.ArrayList;
import java.util.Random;

public class ManagerRoutes {
    private ManagerGeneral managerGeneral;
    private int widthPanel = 1450;
    private int heightPanel = 830;

    public ManagerRoutes(ManagerGeneral managerGeneral){
        this.managerGeneral = managerGeneral;
    }
    private int[] initialPoint() {
        int[] possiblePoints = new int[2];
        boolean pointsFound = false;
        while(!pointsFound){
            possiblePoints = searchInitialPoints();
            if (checkCoordinates(possiblePoints[0], possiblePoints[1])){
                pointsFound = true;
            }
        }
        return possiblePoints;
    }

    private int[] searchInitialPoints(){
        Random random = new Random();
        int[] randomPoints = new int[2];
        int side = random.nextInt(4);
        switch (side) {
            case 0 -> {
                randomPoints[1] = random.nextInt(heightPanel);
            }
            case 1 -> {
                randomPoints[0] = random.nextInt(widthPanel);
            }
            case 2 -> {
                randomPoints[0] = widthPanel;
                randomPoints[1] = random.nextInt(heightPanel);
            }
            case 3 -> {
                randomPoints[1] = heightPanel;
                randomPoints[0] = random.nextInt(widthPanel);
            }
        }
        return randomPoints;
    }

    private boolean checkCoordinates(int x, int y){
        for (Plane plane : managerGeneral.getPlanes()) {
            if (distanceTo(plane.getRoute().get(0), x, y) < 150) {
                return  false;
            }
        }
        return true;
    }

    private int distanceTo(Points other, int x, int y) {
        int dx = x - other.getCoorX();
        int dy = y - other.getCoorY();
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    public ArrayList<Points> generateInitialRoute() {
        ArrayList<Points> initialRoute = new ArrayList<>();

        int centralPointX = 800;
        int centralPointY = 460;

        int[] initialPoint = initialPoint();
        int currentPointX = initialPoint[0];
        int currentPointY = initialPoint[1];

        if (Math.abs(centralPointX - currentPointX) < Math.abs(centralPointY - currentPointY)) {
            currentPointX = initialPoint[1];
            currentPointY = initialPoint[0];
            centralPointX = 460;
            centralPointY = 800;
        }
        currentPointY = Math.abs(currentPointY) == 0 ? currentPointY + 1 : currentPointY;
        int moveX = Math.abs((centralPointX - currentPointX)) / Math.abs(centralPointY - currentPointY);
        int moveY = Math.abs(centralPointY - currentPointY);

        for (int i = 0; i < moveY; i++) {
            for (int j = 0; j < moveX; j++) {
                initialRoute.add(centralPointX == 460 ? new Points(currentPointY, currentPointX) : new Points(currentPointX, currentPointY));
                currentPointX = currentPointX < centralPointX ? currentPointX + 1 : currentPointX - 1;
            }
            currentPointY = currentPointY < centralPointY ? currentPointY + 1 : currentPointY - 1;
        }

        if (centralPointX == 460) {
            int temp = currentPointX;
            currentPointX = currentPointY;
            currentPointY = temp;
            centralPointX = 800;
            centralPointY = 460;
        }

        while (currentPointX != centralPointX || currentPointY != centralPointY) {
            currentPointX = currentPointX != centralPointX ? currentPointX < centralPointX ? currentPointX + 1 : currentPointX - 1 : currentPointX;
            currentPointY = currentPointY != centralPointY ? currentPointY < centralPointY ? currentPointY + 1 : currentPointY - 1 : currentPointY;
            initialRoute.add(new Points(currentPointX, currentPointY));
        }
        return initialRoute;
    }

    public ArrayList<Points> fillRouteGaps(ArrayList<Points> route) {
        ArrayList<Points> filledRoute = new ArrayList<>();
        int lastPointX = route.get(route.size() - 1).getCoorX();
        int lastPointY = route.get(route.size() - 1).getCoorY();
        int currentX = route.get(0).getCoorX();
        int currentY = route.get(0).getCoorY();
        int nextX = route.get(1).getCoorX();
        int nextY = route.get(1).getCoorY();
        int count = 0;
        while (count < route.size()-1 && (currentX != lastPointX || currentY != lastPointY)) {
            while (currentX != nextX || currentY != nextY) {
                currentX = currentX == nextX? currentX : currentX < nextX ? currentX + 1 : currentX - 1;
                currentY = currentY == nextY? currentY : currentY < nextY ? currentY + 1 : currentY - 1;
                filledRoute.add(new Points(currentX, currentY));
            }
            count += 1;
            nextX = route.get(count).getCoorX();
            nextY = route.get(count).getCoorY();
        }
        return filledRoute;
    }

    public ArrayList<Points> recalculateRoute(ArrayList<Points> oldRoute) {
        ArrayList<Points> route = fillRouteGaps(oldRoute);
        int currentX = route.get(route.size() - 1).getCoorX();
        int currentY = route.get(route.size() - 1).getCoorY();
        int moveX = currentX - route.get(route.size() - 3).getCoorX() != 0 ? (currentX - route.get(route.size() - 3).getCoorX()) * -1 : 0;
        int moveY = currentY - route.get(route.size() - 3).getCoorY() != 0 ? (currentY - route.get(route.size() - 3).getCoorY()) * -1 : 0;
        while (currentX > 30 && currentX < 1550 && currentY > 30 && currentY < 900) {
            currentX -= moveX;
            currentY -= moveY;
            route.add(new Points(currentX, currentY));
        }
        return route;
    }
}
