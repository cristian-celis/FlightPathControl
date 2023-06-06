package co.edu.uptc.views;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JDialog {
    private JButton playBtt;
    private final JPanel panelInitialMenu;
    private JLabel timePlayedLbl, timePlayed, numberLandingsLbl, numberLandings, copyright;

    private final ManagerView frame;
    private AddAirplanesDialog addAirplanesDialog;

    public StartPanel(ManagerView frame){
        this.setBackground(new Color(180,180,180));
        setSize(700,700);
        setLocationRelativeTo(null);
        this.frame = frame;
        panelInitialMenu = new JPanel();
        this.setLayout(new BorderLayout());
        this.add(panelInitialMenu, BorderLayout.CENTER);
        initComponents();
    }

    private void initComponents(){
        playBtt = new JButton("Play");
        timePlayedLbl = new JLabel("Time played: ");
        timePlayed = new JLabel();
        numberLandingsLbl = new JLabel("Number of landings: ");
        numberLandings = new JLabel();
        copyright = new JLabel("By Cristian Celis, 2023");

        setComponents();
        addActionListeners();
    }

    private void setComponents(){
        panelInitialMenu.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Font font = new Font("Arial", Font.BOLD, 18);
        gbc.insets = new Insets(30,30,30,30);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        playBtt.setFont(font);
        panelInitialMenu.add(playBtt, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        timePlayedLbl.setFont(font);
        panelInitialMenu.add(timePlayedLbl, gbc);

        gbc.gridx = 1;
        timePlayed.setFont(font);
        panelInitialMenu.add(timePlayed, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        numberLandingsLbl.setFont(font);
        panelInitialMenu.add(numberLandingsLbl, gbc);

        gbc.gridx = 1;
        numberLandings.setFont(font);
        panelInitialMenu.add(numberLandings, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        copyright.setFont(new Font("Arial", Font.BOLD, 14));
        panelInitialMenu.add(copyright, gbc);
    }

    public void setData(String timePlayed, String numberLandings){
        this.timePlayed.setText(timePlayed + " seconds");
        this.numberLandings.setText(numberLandings);
    }

    private void addActionListeners(){
        playBtt.addActionListener(e->{
            this.setVisible(false);
            addAirplanesDialog = new AddAirplanesDialog(frame);
            addAirplanesDialog.setVisible(true);
        });
    }
}