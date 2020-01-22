package com.view;

import com.Main;
import com.controller.SettingsViewController;

import javax.swing.*;
import java.awt.*;

public class SettingsView extends Panel {

    private JButton playWithComputer;
    private JButton playWithPlayer;
    private SettingsViewController controller;

    public SettingsView(Main parent) {
        // создаем экземпляр класса-слушателя
        controller = new SettingsViewController(this, parent);

        // назаначаем менеджер компановки панели
        this.setLayout(new FlowLayout());

        // создаем экземпляр кнопок
        playWithPlayer = new JButton("Player vs Player");
        playWithComputer = new JButton("Play vs Computer");

        // ассоциируем кнопки с определенным действием
        playWithPlayer.setActionCommand(SettingsViewController.PLAYER_VS_PLAYER);
        playWithComputer.setActionCommand(SettingsViewController.PLAYER_VS_COMPUTER);

        // добавляем слушатель для кнопок
        playWithPlayer.addActionListener(controller);
        playWithComputer.addActionListener(controller);

        // добавлем кнопки на панель
        this.add(playWithPlayer);
        this.add(playWithComputer);
    } // end constructor SettingsView

}
