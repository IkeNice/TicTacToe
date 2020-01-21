package com;

import com.view.SettingsView;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

public class Main extends Applet {

    private Panel currentView;

    public void init (){

        try {

            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    initComponents();
                }
            });

        } catch (Exception e){
            System.err.println("Can't init GUI: " + e.toString());
        }
    }

    private void initComponents() {
        setting();
    }

    private void setting() {
        this.setSize(400, 500);
        manageView(new SettingsView(this));
    }

    private void manageView(Panel view) {
        if (currentView != null){
            remove(currentView);
        }
        currentView = view;
        add(currentView);

        rootPane.updateUI();
    }

}
