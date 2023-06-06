package co.edu.uptc.views;

import co.edu.uptc.presenter.ConnectionWithPanels;

import javax.swing.*;
import java.awt.*;

public class ManagerView extends JFrame implements ConnectionWithPanels.View {

    private StartPanel startPanel;
    private GamePanel gamePanel;
    private OptionsGame optionsGame;
    private GridBagConstraints gbc;
    private ConnectionWithPanels.Presenter presenter;

    public ManagerView(){
        super.setTitle("AirPlanes Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(214, 234, 248));
        initComponents();
    }

    private void initComponents(){
        gbc = new GridBagConstraints();
        setComponents();
        startPanel = new StartPanel(this);
        startPanel.setVisible(true);
    }

    public ConnectionWithPanels.Presenter getPresenter(){
        return presenter;
    }

    private void setComponents(){
        this.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        startPanel = new StartPanel(this);
    }

    @Override
    public void setDataGameOver(){
        presenter.setIsRunning(false);
        gamePanel.setVisible(false);
        optionsGame.setVisible(false);
        startPanel.setVisible(true);
        this.pack();
        startPanel.setData(String.valueOf(presenter.setDataGameOver()), String.valueOf(presenter.getPlanesOnMap()));
    }

    public void startGamePanel() {
        this.setBackground(new Color(214, 234, 248));
        gamePanel = new GamePanel(this);
        optionsGame = new OptionsGame(this);

        this.setLayout(new BorderLayout());

        gamePanel.setPreferredSize(new Dimension(1500, 920));
        gamePanel.setVisible(true);
        this.add(gamePanel, BorderLayout.CENTER);

        optionsGame.setPreferredSize(new Dimension(300, 920));
        optionsGame.setVisible(true);
        this.add(optionsGame, BorderLayout.EAST);

        this.setResizable(false);
        this.setSize(1800, 920);
    }

    public OptionsGame getOptionsGame(){
        return optionsGame;
    }

    @Override
    public void start() {
    }

    @Override
    public void repintar(){
        if (gamePanel != null)
            gamePanel.repaint();
    }

    @Override
    public void setNumAirplaneOnMapLbl(int numberPlanes) {
        optionsGame.setNumAirplaneOnMapLbl(numberPlanes);
    }

    @Override
    public void setLandedAirPlane(int numberPlanes) {
        optionsGame.setLandedAirPlane(numberPlanes);
    }

    @Override
    public void setTimer(String second) {
        optionsGame.setTimer(second);
    }

    @Override
    public void setPresenter(ConnectionWithPanels.Presenter presenter) {
        this.presenter = presenter;
    }
}