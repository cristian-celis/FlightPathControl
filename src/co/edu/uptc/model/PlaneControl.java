package co.edu.uptc.model;

import co.edu.uptc.pojos.Plane;
import resources.config.properties.UIConstants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlaneControl extends Thread {
    private final ManagerGeneral managerGeneral;
    private final Plane plane;
    private boolean planeArrived = false;
    private boolean inArea = false;
    private int onRunwayPoints = 0;

    public PlaneControl(ManagerGeneral managerGeneral, Plane plane) {
        this.managerGeneral = managerGeneral;
        this.plane = plane;
    }

    private int delay(){
        return (int) (1000 / (Math.pow(2, plane.getSpeed() -1 )));
    }

    @Override
    public void run() {
        try {
            while (managerGeneral.isRunning && !checkCrash() && !planeArrived) {
                if (managerGeneral.isPause){
                    sleep(500);
                }else {
                    arriveToTrack();
                    move();
                    sleep(delay());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void arriveToTrack(){
        if (managerGeneral.trackArea.contains(plane.getPlaneArea())){
            onRunwayPoints += 1;
            if (onRunwayPoints == 5){
                if (plane.getPlaneArea().contains((managerGeneral.trackArea.x + managerGeneral.trackArea.getWidth())-20, managerGeneral.trackArea.y + (managerGeneral.trackArea.getHeight() / 2))){
                    inArea = true;
                }
            }
            if (inArea && onRunwayPoints >= 200){
                planeArrived = true;
                managerGeneral.getPlanes().remove(this.plane);
                managerGeneral.planesOnMap = managerGeneral.getPlanes().size();
                managerGeneral.getPresenter().setNumAirplaneOnMapLbl(managerGeneral.planesOnMap);
                managerGeneral.landedPlanes += 1;
                managerGeneral.getPresenter().setLandedAirPlane(managerGeneral.landedPlanes);
                if (managerGeneral.getPlanes().isEmpty()){
                    managerGeneral.getPresenter().gameOver();
                }
            }
        }else{
            inArea = false;
            onRunwayPoints = 0;
        }
    }

    private boolean checkCrash() {
        for (Plane planes : managerGeneral.getPlanes()) {
            if (plane.getId() != planes.getId() && plane.getPlaneArea().intersects(planes.getPlaneArea())) {
                managerGeneral.getPresenter().gameOver();
                managerGeneral.isRunning = false;
                return true;
            }
        }
        return false;
    }

    private void move() {
        if (plane.getRoute().size() > 4) {
            plane.getRoute().remove(0);
            managerGeneral.getPresenter().repintar();
            plane.setCurrentPoints(plane.getRoute().get(0).getCoorX(), plane.getRoute().get(0).getCoorY());
            int[] currentPoint = plane.getCurrentPoints();
            plane.setPlaneArea(new Rectangle(currentPoint[0]-20, currentPoint[1]-20, 40, 40));
        }
    }
}