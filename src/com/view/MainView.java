package com.view;

import com.Main;
import com.controller.MainViewController;
import com.data.ApplicationData;

import javax.swing.*;
import java.awt.*;

public class MainView extends Panel {

    private MainViewController controller;
    private Panel buttonsPanel;
    private JLabel comment;
    private JButton playAgain;

    public MainView(Main parent) {

        controller = new MainViewController(this, parent);

        this.setLayout(new BorderLayout());
        buttonsPanel = new Panel();
        comment = new JLabel();

        buttonsPanel.setLayout(new GridLayout(ApplicationData.getLineSize(), ApplicationData.getLineSize()));

        Panel topLayout = new Panel();
        topLayout.setLayout(new FlowLayout());

        playAgain = new JButton("Play Again");
        playAgain.setActionCommand(MainViewController.PLAY_AGAIN_COMMAND);
        playAgain.addActionListener(controller);

        topLayout.add(playAgain);
    }
}
