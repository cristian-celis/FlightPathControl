package co.edu.uptc.presenter;

import co.edu.uptc.pojos.Plane;

import java.awt.*;
import java.util.ArrayList;

public interface ConnectionWithPanels {
    interface Model {
        void setPresenter(Presenter presenter);
        ArrayList<Plane> getPlanes();
        void setRoute(int idPlane, ArrayList<Integer> coorX, ArrayList<Integer> coorY);
        void addPlanes(String amountPlanes);
        void setIsRunning(boolean isRunning);
        int clickOnPlane(int x, int y);
        String[] getColorTitles();
        void setPlaneColor(int plane, String nameColor);
        void setIsPause(boolean isPause);
        void setTrackArea(Rectangle trackArea);
        int setDataGameOver();
        int getPlanesOnMap();
        int searchPositionById(int id);
    }

    interface View {
        void start();
        void setPresenter(Presenter presenter);
        void repintar();
        void setNumAirplaneOnMapLbl(int numberPlanes);
        void setLandedAirPlane(int numberPlanes);
        void setTimer(String second);
        void setDataGameOver();
    }

    interface Presenter {
        void setView(View view);

        void setModel(Model model);
        void repintar();
        ArrayList<Plane> getPlanes();
        void setRoute(int idPlane, ArrayList<Integer> coorX, ArrayList<Integer> coorY);
        void addPlanes(String amountPlanes);
        void setIsRunning(boolean isRunning);
        int clickOnPlane(int x, int y);
        String[] getColorTitles();
        void setPlaneColor(int plane, String nameColor);
        void setNumAirplaneOnMapLbl(int numberPlanes);
        void setLandedAirPlane(int numberPlanes);
        void setIsPause(boolean isPause);
        void setTrackArea(Rectangle trackArea);
        int setDataGameOver();
        void setTimer(String second);
        void gameOver();
        int getPlanesOnMap();
        int searchPositionById(int id);
    }
}
