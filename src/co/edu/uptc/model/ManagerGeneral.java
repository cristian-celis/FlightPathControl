package co.edu.uptc.model;

import co.edu.uptc.pojos.Plane;
import co.edu.uptc.pojos.Points;
import co.edu.uptc.presenter.ConnectionWithPanels;
import resources.config.properties.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ManagerGeneral implements ConnectionWithPanels.Model {
    private final ArrayList<Plane> planes;
    private ConnectionWithPanels.Presenter presenter;
    private final ManagerRoutes managerRoutes;
    private Timer timer;
    boolean isRunning;
    boolean isPause;
    int planesOnMap = 1;
    int landedPlanes = 0;
    Rectangle trackArea;


    public ManagerGeneral() {
        planes = new ArrayList<>();
        managerRoutes = new ManagerRoutes(this);
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    @Override
    public void setTrackArea(Rectangle trackArea) {
        this.trackArea = trackArea;
    }

    @Override
    public int getPlanesOnMap(){
        return landedPlanes;
    }

    @Override
    public String[] getColorTitles() {
        String[] colorTitles = new String[8];
        for (int i = 0; i < colorTitles.length; i++) {
            colorTitles[i] = UIConstants.getPlaneTitle(i);
        }
        return colorTitles;
    }

    @Override
    public int setDataGameOver(){
        return timer.getSecond();
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void setIsPause(boolean isPause) {
        this.isPause = isPause;
    }
    @Override
    public void addPlanes(String planesNumber) {
        isRunning = true;
        timer = new Timer(this);
        int amountPlanes = Integer.parseInt(planesNumber);
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    int i = 0;
                    while (i < amountPlanes) {
                        createAirPlanes(i);
                        sleep(5000);
                        i++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public int clickOnPlane(int x, int y) {
        int positionPlane = 0;
        for (Plane plane : this.planes) {
            if (plane.getPlaneArea().contains(x, y)) {
                return positionPlane;
            }
            positionPlane++;
        }
        return -1;
    }

    @Override
    public int searchPositionById(int id){
        int positionPlane = 0;
        for (Plane plane: this.planes){
            if (plane.getId() == id){
                return positionPlane;
            }
            positionPlane++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private void createAirPlanes(int idPlane) {
        ArrayList<Points> initialRoute = managerRoutes.generateInitialRoute();
        String randomColor = selectPlaneColor(1, new Random().nextInt(8), "");
        planes.add(new Plane(idPlane, initialRoute, 2, setSizeImage(selectPlaneColor(0, 0, randomColor)), initialRoute.get(0),
                new Rectangle(initialRoute.get(0).getCoorX() - 25, initialRoute.get(0).getCoorY() - 25, 50, 50), randomColor));
        presenter.setNumAirplaneOnMapLbl(planesOnMap++);
        new PlaneControl(this, planes.get(searchPositionById(idPlane))).start();
    }

    private Image setSizeImage(String imageAddress) {
        ImageIcon icon = new ImageIcon(imageAddress);
        int width = 60;
        int height = (int) (width / ((double) icon.getIconWidth() / icon.getIconHeight()));
        return icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    private String selectPlaneColor(int option, int randomColor, String nameColor) {
        return option == 0 ? UIConstants.getPlaneAddress(nameColor) : UIConstants.getPlaneTitle(randomColor);
    }

    @Override
    public void setPlaneColor(int idPlane, String nameColor) {
        Plane thisPlane = planes.get(idPlane);
        thisPlane.setPlaneImage(setSizeImage(UIConstants.getPlaneAddress(nameColor)));
        thisPlane.setPlaneColor(nameColor);
    }

    @Override
    public void setRoute(int idPlane, ArrayList<Integer> coorX, ArrayList<Integer> coorY) {
        ArrayList<Points> newRoute = new ArrayList<>();
        if (coorX.size() == coorY.size()) {
            for (int i = 0; i < coorX.size(); i++) {
                newRoute.add(new Points(coorX.get(i), coorY.get(i)));
            }
        }
        planes.get(idPlane).setRoute(managerRoutes.recalculateRoute(newRoute));
        planes.get(idPlane).setCurrentPoints(newRoute.get(0).getCoorX(), newRoute.get(0).getCoorY());
    }

    @Override
    public void setPresenter(ConnectionWithPanels.Presenter presenter) {
        this.presenter = presenter;
    }

    public ConnectionWithPanels.Presenter getPresenter() {
        return presenter;
    }
}