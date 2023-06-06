package co.edu.uptc.views;

import co.edu.uptc.pojos.Plane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;


public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
    private final ManagerView frame;
    private Image landingTrackImage;

    public GamePanel(ManagerView frame) {
        this.frame = frame;
        this.setBackground(new Color(240,240,240));
        initComponents();
        loadImages();
    }

    private void initComponents() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    private void loadImages() {
        ImageIcon icon = new ImageIcon("src/resources/assets/landingTrack.png");
        int width = 450;
        int height = (int) (width / ((double) icon.getIconWidth() / icon.getIconHeight()));
        landingTrackImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Rectangle trackArea = new Rectangle(550, 415, width, height);
        frame.getPresenter().setTrackArea(trackArea);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        g.drawImage(landingTrackImage, 550, 415, landingTrackImage.getWidth(null), landingTrackImage.getHeight(null), null);
        paintGame(graphics2D);
    }

    public void paintGame(Graphics g) {
        try{
            for (Plane plane : frame.getPresenter().getPlanes()) {
                int[] points = plane.getCurrentPoints();
                Image planeImage = plane.getPlaneImage();
                g.drawImage(planeImage, points[0]-30, points[1]-30, planeImage.getWidth(null), planeImage.getHeight(null), null);
            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }
    }

    private boolean isReading = false;

    public void mousePressed(MouseEvent e) {
        idClickedPlane = frame.getPresenter().clickOnPlane(e.getX(), e.getY());
        if (idClickedPlane != -1) {
            frame.getOptionsGame().setTextFields(frame.getPresenter().getPlanes().get(idClickedPlane).getId(), frame.getPresenter().getPlanes().get(idClickedPlane).getPlaneColor(), frame.getPresenter().getPlanes().get(idClickedPlane).getSpeed());
        }
        if (e.getButton() == MouseEvent.BUTTON1 && idClickedPlane != -1) {
            isReading = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (idClickedPlane != -1) {
            isReading = false;
            if (coorX.size() > 10 && coorY.size() > 10)
                frame.getPresenter().setRoute(idClickedPlane, coorX, coorY);
            coorX.clear();
            coorY.clear();
            idClickedPlane = -1;
        }
    }

    private int idClickedPlane = -1;

    private int lastX;
    private int lastY;
    private final ArrayList<Integer> coorX = new ArrayList<>();
    private final ArrayList<Integer> coorY = new ArrayList<>();

    public void mouseDragged(MouseEvent e) {
        if (isReading) {
            int x = e.getX();
            int y = e.getY();
            coorX.add(x);
            coorY.add(y);

            Graphics g = getGraphics();
            lastX = lastX == 0 ? x : lastX;
            lastY = lastY == 0 ? y : lastY;
            for (int i = 1; i < coorX.size(); i++){
                g.drawLine(coorX.get(i-1), coorY.get(i-1), coorX.get(i), coorY.get(i));
            }
            lastX = x;
            lastY = y;
        }
    }
    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }
}