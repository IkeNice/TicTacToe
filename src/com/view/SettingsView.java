package com.view;

import com.Main;
import com.controller.SettingsViewController;

import javax.swing.*;
import java.awt.*;

public class SettingsView extends Panel {

    private static final int MAX_ROW_LENGTH = 7;
    private static final int MIN_ROW_LENGTH = 3;
    private JSlider slider;
    private JButton playStandart;
    private SettingsViewController controller;

    public SettingsView(Main controller) {
        this.controller = new SettingsViewController(this, controller);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Panel topPanel = new Panel();
        topPanel.setLayout(new FlowLayout());

        Panel bottomPanel = new Panel();
        bottomPanel.setLayout(new FlowLayout());

        playStandart = new JButton("Player vs Player");
        playStandart.setActionCommand(SettingsViewController.PLAY_VS_PLAYER);
        playStandart.addActionListener(this.controller);

        topPanel.add(new JLabel("Row Length: "));

        slider = new JSlider(JSlider.HORIZONTAL, MIN_ROW_LENGTH, MAX_ROW_LENGTH, MIN_ROW_LENGTH);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
    }
}
