package com.view;

import com.Main;
import com.controller.PlayViewController;

import javax.swing.*;
import java.awt.*;

public class PlayView extends Panel {

    private PlayViewController controller;

    private Panel cellsPanel;

    private JButton playAgain;
    private JButton settings;
    private JLabel comment;

    private Panel topPanel;

    public PlayView(Main parent) {
        // создаем экземпляр класса-слушателя
        controller = new PlayViewController(this, parent);

        // назначаем менеджер компоновки основноц панели
        this.setLayout(new BorderLayout());

        // создаем экземпляр дополнительных панелей
        topPanel = new Panel();
        cellsPanel = new Panel();

        playAgain = new JButton("Play Again");
        settings = new JButton("Settings");
        comment = new JLabel();

        // назначаем менеджеры компоновок дополнительным панелям
        topPanel.setLayout(new FlowLayout());
        cellsPanel.setLayout(new GridLayout(3, 3));

        // ассоциируем кнопки с определенным действием
        playAgain.setActionCommand(PlayViewController.PLAY_AGAIN_COMMAND);
        settings.setActionCommand(PlayViewController.GET_SETTING_COMMAND);

        // ассоциируем кнопки со слушателем
        playAgain.addActionListener(controller);
        settings.addActionListener(controller);

        // Добавляем кнопки на дополнительную панель
        topPanel.add(playAgain);
        topPanel.add(settings);
    } // end mconstructor PlayView
}
