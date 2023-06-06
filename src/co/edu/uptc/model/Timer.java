package co.edu.uptc.model;

import co.edu.uptc.views.ManagerView;

public class Timer extends Thread{
    private int second = 0;
    private ManagerGeneral managerGeneral;

    public Timer(ManagerGeneral managerGeneral){
        this.start();
        this.managerGeneral = managerGeneral;
    }

    @Override
    public void run(){
        try{
            while(managerGeneral.isRunning){
                if (managerGeneral.isPause){
                    sleep(500);
                } else {
                    sleep(1000);
                    second += 1;
                    managerGeneral.getPresenter().setTimer(String.valueOf(second));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getSecond(){
        return second;
    }
}
