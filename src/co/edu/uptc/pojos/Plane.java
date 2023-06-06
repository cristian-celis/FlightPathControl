package co.edu.uptc.pojos;

import resources.config.properties.UIConstants;

import java.awt.*;
import java.util.ArrayList;

public class Plane {
    int id;
    Points currentPoints;
    ArrayList<Points> route;
    int speed;
    Image planeImage;
    String planeColor;
    Rectangle planeArea;

    public Plane(int id, ArrayList<Points> route, int speed, Image planeImage, Points currentPoints, Rectangle planeArea, String planeColor){
        this.id = id;
        this.route = route;
        this.speed = speed;
        this.planeImage = planeImage;
        this.currentPoints = currentPoints;
        this.planeArea = planeArea;
        this.planeColor = planeColor;
    }

    public Rectangle getPlaneArea() {
        return planeArea;
    }

    public void setPlaneArea(Rectangle planeArea) {
        this.planeArea = planeArea;
    }

    public int[] getCurrentPoints(){
        int[] currentPoints = new int[2];
        currentPoints[0] = this.currentPoints.getCoorX();
        currentPoints[1] = this.currentPoints.getCoorY();
        return currentPoints;
    }
    public void setCurrentPoints(int coorX, int coorY){
        currentPoints = new Points(coorX, coorY);
    }

    public int getId() {
        return id;
    }

    public ArrayList<Points> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Points> route) {
        this.route = route;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getPlaneImage() {
        return planeImage;
    }

    public void setPlaneImage(Image color) {
        this.planeImage = color;
    }

    public String getPlaneColor(){
        return planeColor;
    }

    public void setPlaneColor(String color){
        this.planeColor = color;
    }
}
