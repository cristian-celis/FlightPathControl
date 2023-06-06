package co.edu.uptc.views;

import javax.swing.*;
import java.awt.*;

public class OptionsGame extends JPanel {
    private JLabel colorLbl, speedLbl, idLbl, numAirplaneOnMapLbl, airPlaneOnMap, landedAirplaneLbl, landedAirPlane;
    private JTextField id;
    private JComboBox<String> planeColor;
    private JSpinner speed;
    private JButton save;
    private JButton offTrafficControlBtt, continueBtt, pauseBtt;
    private JPanel timerPanel;
    private JLabel timer;

    private final ManagerView frame;
    public OptionsGame(ManagerView frame){
        this.setBackground(new Color(120, 120, 120));
        this.frame = frame;
        initComponents();
    }

    private void initComponents(){
        idLbl = new JLabel("Id plane: ");
        id = new JTextField("", 20);
        id.setEditable(false);
        colorLbl = new JLabel("Color: ");

        DefaultComboBoxModel<String> defaultComboBox = new DefaultComboBoxModel<>(frame.getPresenter().getColorTitles());
        planeColor = new JComboBox<>(defaultComboBox);

        speed = new JSpinner(new SpinnerNumberModel(2, 2, 6, 1));
        speedLbl = new JLabel("Speed: ");

        save = new JButton("Save");
        offTrafficControlBtt = new JButton("Off traffic Control");
        continueBtt = new JButton("Continue");
        pauseBtt = new JButton("Pause");

        numAirplaneOnMapLbl = new JLabel("AirPlanes on the map: ");
        airPlaneOnMap = new JLabel();
        landedAirplaneLbl = new JLabel("Landed airplanes: ");
        landedAirPlane = new JLabel();

        timerPanel = new JPanel();

        setComponents();
        addActionListeners();
    }

    public void setTextFields(int id, String planeColor, int speed){
        this.id.setText(String.valueOf(id));
        this.planeColor.setSelectedItem(planeColor);
        this.speed.setValue(speed);
        speedLbl.setForeground(Color.BLACK);
    }

    private void setComponents(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Font font = new Font("Arial", Font.BOLD, 15);

        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.BOTH;
        idLbl.setFont(font);
        add(idLbl, gbc);
        gbc.gridx = 1;
        id.setFont(font);
        add(id, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        colorLbl.setFont(font);
        add(colorLbl, gbc);
        gbc.gridx = 1;
        planeColor.setFont(font);
        add(planeColor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        speedLbl.setFont(font);
        add(speedLbl, gbc);
        gbc.gridx = 1;
        speed.setFont(font);
        add(speed, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        save.setFont(font);
        add(save, gbc);
        gbc.insets = new Insets(150,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 4;
        offTrafficControlBtt.setFont(font);
        add(offTrafficControlBtt, gbc);
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridy = 5;
        continueBtt.setFont(font);
        add(continueBtt, gbc);
        gbc.gridy = 6;
        pauseBtt.setFont(font);
        add(pauseBtt, gbc);

        gbc.insets = new Insets(150, 10,10,10);
        gbc.gridwidth = 1;
        gbc.gridy = 7;
        numAirplaneOnMapLbl.setFont(font);
        add(numAirplaneOnMapLbl, gbc);
        gbc.gridx = 1;
        airPlaneOnMap.setFont(font);
        add(airPlaneOnMap, gbc);
        gbc.insets = new Insets(10, 10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 8;
        landedAirplaneLbl.setFont(font);
        add(landedAirplaneLbl, gbc);
        gbc.gridx = 1;
        landedAirPlane.setFont(font);
        add(landedAirPlane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.BOTH;
        timer = new JLabel("0");
        timer.setForeground(Color.white);
        timer.setFont(font);
        timerPanel.setBackground(Color.BLACK);
        timerPanel.add(timer);
        this.add(timerPanel, gbc);
    }

    public void setTimer(String second){
        timer.setText(second + " seconds");
    }

    private void addActionListeners(){
        save.addActionListener(e->{
            int id = frame.getPresenter().searchPositionById(Integer.parseInt(this.id.getText()));
            int newSpeed = Integer.parseInt(speed.getValue().toString());
            if (newSpeed > 9 || newSpeed < 1){
                speedLbl.setForeground(Color.RED);
            }else{
                frame.getPresenter().getPlanes().get(id).setSpeed(Integer.parseInt(speed.getValue().toString()));
                frame.getPresenter().setPlaneColor(id, planeColor.getSelectedItem().toString());
            }
        });
        offTrafficControlBtt.addActionListener(e->{
            frame.setDataGameOver();
        });
        continueBtt.addActionListener(e->{
            frame.getPresenter().setIsPause(false);
        });
        pauseBtt.addActionListener(e->{
            frame.getPresenter().setIsPause(true);
        });
    }
    public void setNumAirplaneOnMapLbl(int numberPlanes){
        airPlaneOnMap.setText(String.valueOf(numberPlanes));
    }

    public void setLandedAirPlane(int numberPlanes){
        landedAirPlane.setText(String.valueOf(numberPlanes));
    }
}