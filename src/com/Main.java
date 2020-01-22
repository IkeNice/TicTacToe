package com;

import com.view.PlayView;
import com.view.SettingsView;

import javax.swing.*;
import java.awt.*;

public class Main extends JApplet {

    private Panel currentView;

    // инициализация апплета
    public void init (){
        try {
            // инициализация компонентов апплета в потоке
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    setting();
                }
            });

        } catch (Exception e){
            System.err.println("Can't init GUI: " + e.toString());
        }
    } // end method init

    // инициализация панели выбора режима игры
    private void setting() {
        this.setSize(300, 40);
        manageView(new SettingsView(this));
    }

    // установка передаваемой панели и обновление корневой
    private void manageView(Panel view) {
        if (currentView != null)
            remove(currentView);

        currentView = view;
        add(currentView);

        rootPane.updateUI();
    } // end method manageView

    // инициализация панели игры
    public void play() {
        this.setSize(430, 450);
        manageView(new PlayView(this));
    } // end method play
}
