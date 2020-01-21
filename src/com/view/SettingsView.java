package com.view;

import com.Main;
import com.controller.SettingsViewController;

import javax.swing.*;
import java.awt.*;

public class SettingsView extends Panel {

    private static final int MAX_ROW_LENGTH = 7;
    private static final int MIN_ROW_LENGTH = 3;
    private JButton playWithComputer;
    private JSlider slider;
    private JButton playStandart;
    private SettingsViewController controller;

    public SettingsView(Main parent) {
        controller = new SettingsViewController(this, parent);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Panel topPanel = new Panel();
        topPanel.setLayout(new FlowLayout());

        Panel bottomPanel = new Panel();
        bottomPanel.setLayout(new FlowLayout());

        playStandart = new JButton("Player vs Player");
        playStandart.setActionCommand(SettingsViewController.PLAY_VS_PLAYER);
        playStandart.addActionListener(controller);

        slider = new JSlider(JSlider.HORIZONTAL, MIN_ROW_LENGTH, MAX_ROW_LENGTH, MIN_ROW_LENGTH);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        playWithComputer = new JButton("Play vs Computer");
        playWithComputer.setActionCommand(SettingsViewController.PLAY_VS_COMPUTER);
        playWithComputer.addActionListener(controller);

        topPanel.add(new JLabel("Row Length: "));
        topPanel.add(slider);

        bottomPanel.add(playStandart);
        bottomPanel.add(playWithComputer);

        this.add(topPanel);
        this.add(bottomPanel);
    } // end constructor SettingsView

    public int getSliderValue() {
        return slider.getValue();
    }
}
