package com.view;

import com.Main;
import com.controller.PlayViewController;
import com.data.ApplicationData;
import com.sun.corba.se.impl.io.OptionalDataException;
import com.view.components.GameCell;

import javax.swing.*;
import java.awt.*;

public class PlayView extends Panel {

    private PlayViewController controller;

    private Panel topPanel;
    private Panel cellsPanel;

    private JButton playAgain;
    private JButton settings;
    private JLabel comment;

    public GameCell[][] cells;

    private Font buttonFont;

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
        // создаем экземпляр многомерного массива для кнопок-ячеек
        cells = new GameCell[3][3];

        buttonFont = new Font("Times New Roman", Font.PLAIN, 60);

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

        // создаем экземпляры кнопок-ячеек, добавляем слушателя и
        // размещаем их на дополнительной панели
        for (int i = 0; i < cells.length; i++){
            for (int j = 0; j < cells.length; j++){
                cells[i][j] = new GameCell(i, j);
                cells[i][j].addMouseListener(controller);
                cells[i][j].setFont(buttonFont);

                cellsPanel.add(cells[i][j]);
            }
        } // end for

        // задаем выравнивение для поля-комментария
        comment.setHorizontalAlignment(SwingConstants.CENTER);

        // размещаем все компоненты на основной панели
        this.add(topPanel, BorderLayout.NORTH);
        this.add(cellsPanel, BorderLayout.CENTER);
        this.add(comment, BorderLayout.SOUTH);

        // сбрасываем состояние кнопок-ячеек на начальное
        reset();
    } // end constructor PlayView

    // сброс состояния-ячеек на начальное
    public void reset() {
        // делаем ячейки активными
        setCellsEnabled(true);

        // делаем ячейки пустыми
        for (GameCell[] line : cells){
            for (GameCell item : line) {
                item.setText("");
                item.setBackground(null);
            }
        }
        // получаем имя текущего игрока и
        // устанавливаем его в поле комментария
        setCurrentPlayerNote(ApplicationData.getCurrentPlayerName());
    } // end method reset

    // устанавливаем примечание об очереди хода в поле комментария
    private void setCurrentPlayerNote(String currentPlayerName) {
        comment.setText(currentPlayerName + " your turn.");
    }

    // установка активности кнопок-ячеек формы
    private void setCellsEnabled(boolean b) {
        for (int i = 0; i < cells.length; i++){
            for (int j = 0; j < cells.length; j++){
                cells[i][j].setEnabled(b);
            }
        }
    } // end method setCellsEnabled
}
