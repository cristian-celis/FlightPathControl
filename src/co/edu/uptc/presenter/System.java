package co.edu.uptc.presenter;

import co.edu.uptc.model.ManagerGeneral;
import co.edu.uptc.views.ManagerView;

import javax.swing.*;

public class System {
    private ConnectionWithPanels.Presenter presenter;
    private ConnectionWithPanels.Model model;
    private ConnectionWithPanels.View view;

    public System(){
        presenter = new Presenter();
        model = new ManagerGeneral();
        view = new ManagerView();
        start();
    }

    public void start(){
        presenter.setModel(model);
        presenter.setView(view);
        model.setPresenter(presenter);
        view.setPresenter(presenter);
        view.start();
    }
}
