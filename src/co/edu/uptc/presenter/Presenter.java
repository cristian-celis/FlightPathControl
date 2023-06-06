package co.edu.uptc.presenter;

import co.edu.uptc.pojos.Plane;

import java.awt.*;
import java.util.ArrayList;

public class Presenter implements ConnectionWithPanels.Presenter{
    private ConnectionWithPanels.View view;
    private ConnectionWithPanels.Model model;

    @Override
    public void setView(ConnectionWithPanels.View view) {
        this.view = view;
    }

    @Override
    public void setModel(ConnectionWithPanels.Model model) {
        this.model = model;
    }

    @Override
    public ArrayList<Plane> getPlanes() {
        return model.getPlanes();
    }

    @Override
    public void setRoute(int idPlane, ArrayList<Integer> coorX, ArrayList<Integer> coorY) {
        model.setRoute(idPlane, coorX, coorY);
    }

    public void addPlanes(String amountPlanes){
        model.addPlanes(amountPlanes);
    }

    @Override
    public void repintar(){
        view.repintar();
    }

    public void setIsRunning(boolean isRunning){
        model.setIsRunning(isRunning);
    }

    @Override
    public int clickOnPlane(int x, int y) {
        return model.clickOnPlane(x, y);
    }

    @Override
    public String[] getColorTitles(){
        return model.getColorTitles();
    }

    @Override
    public void setPlaneColor(int plane, String nameColor){
        model.setPlaneColor(plane, nameColor);
    }

    @Override
    public void setNumAirplaneOnMapLbl(int numberPlanes) {
        view.setNumAirplaneOnMapLbl(numberPlanes);
    }

    @Override
    public void setLandedAirPlane(int numberPlanes) {
        view.setLandedAirPlane(numberPlanes);
    }

    @Override
    public void setIsPause(boolean isPause){
        model.setIsPause(isPause);
    }

    @Override
    public void setTrackArea(Rectangle trackArea){
        model.setTrackArea(trackArea);
    }

    @Override
    public int setDataGameOver(){
        return model.setDataGameOver();
    }
    @Override
    public void gameOver(){
        view.setDataGameOver();
    }

    @Override
    public void setTimer(String second) {
        view.setTimer(second);
    }

    public int getPlanesOnMap(){
        return model.getPlanesOnMap();
    }

    @Override
    public int searchPositionById(int id){
        return model.searchPositionById(id);
    }
}
