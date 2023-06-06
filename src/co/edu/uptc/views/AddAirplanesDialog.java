package co.edu.uptc.views;

import javax.swing.*;
import java.awt.*;

public class AddAirplanesDialog extends JDialog {

    private JButton acceptBtt;
    private JLabel addAirplaneLbl;
    private JSpinner spinner;
    private final ManagerView frame;

    public AddAirplanesDialog(ManagerView frame){
        setSize(450,300);
        this.frame = frame;
        this.setTitle("Select AirPlanes");
        this.setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents(){
        Font font = new Font("Arial", Font.BOLD, 15);

        spinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        acceptBtt = new JButton("Accept");
        acceptBtt.setFont(font);
        addAirplaneLbl = new JLabel("How much airplanes do you want to add ");
        addAirplaneLbl.setFont(font);
        setComponents();
        setActionListener();
    }

    private void setComponents(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        this.add(addAirplaneLbl, gbc);

        gbc.gridx = 1;
        this.add(spinner, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(acceptBtt, gbc);
    }

    private void setActionListener(){
        acceptBtt.addActionListener(e->{
            this.dispose();
            frame.setVisible(true);
            frame.startGamePanel();
            frame.getPresenter().addPlanes(spinner.getValue().toString());
        });
    }
}
