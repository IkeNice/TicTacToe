package com.controller;

import com.Main;
import com.data.ApplicationData;
import com.view.PlayView;
import com.view.components.GameCell;

import java.awt.event.*;

public class PlayViewController extends MouseAdapter implements MouseListener, ActionListener {

    public static final String PLAY_AGAIN_COMMAND = "play again command";
    public static final String GET_SETTING_COMMAND = "get settings command";

    private static final String HOR_LINE = "horizontal line";
    private static final String VER_LINE = "vertical line";
    private static final String DIAG_LINE = "diagonal line";
    private static final String OPP_DIAG_LINE = "opposite diagonal line";

    private Main root;
    private PlayView parent;

    // получаем ссылки на родительский и основной классы
    public PlayViewController(PlayView parent, Main root) {
        this.parent = parent;
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // определяем значение ActionCommand в событии
        switch (e.getActionCommand()){
            case PLAY_AGAIN_COMMAND:
                reset();
                break;
            case GET_SETTING_COMMAND:
                reset();
                root.setting();
                break;

            default:
                break;
        }
    } // end actionPerformed

    @Override
    public void mouseClicked(MouseEvent e) {
        // переменная для хранения нажатой ячейки
        GameCell curerntCell = (GameCell) e.getComponent();

        // return, нажали на неактивную ячейку
        if (!curerntCell.isEnabled()) return;

        // если удалось установить значение в ячейку
        if (checkCell(curerntCell)){

        }
    }

    // установка значения в ячейку
    private boolean checkCell(GameCell curerntCell) {
        // если ячейка пуста
        if (curerntCell.getText() == ""){
            // установка в нее значение PLAYERX_VALUE либо PLAYERO_VALUE
            curerntCell.setText(ApplicationData.getPlayerValue());

            // если был обнаружен победитель
            if (checkForWinner(curerntCell)) return true;
        }
        return true;
    }

    // проверка наличия победителя
    private boolean checkForWinner(GameCell curerntCell) {
        // если строка заполнена одинаковыми ячейками
        if (findFullRow(curerntCell)){

        }
        return false;
    }

    // проверяем строки на равенство ячеек
    private boolean findFullRow(GameCell curerntCell) {
        // пмассив для хранения значения ячеек по одной линии
        GameCell[] line;

        // получаем содержимое ячеек строки по горизонтали
        line = getLine(curerntCell, HOR_LINE);
        return false;
    }

    // возвращение содержимого ячееек определенной строки
    private GameCell[] getLine(GameCell curerntCell, String lineType) {
        // создаем массив на 3 значения
        GameCell[] line = new GameCell[3];

        switch (lineType){
            case HOR_LINE:
                // добавляем в массив значения ячеек горизонтальной строки
                for (int i = 0; i < line.length; i++){
                    line[i] = parent.cells[curerntCell.cellX][i];
                }
                break;

            case VER_LINE:
                // добавляем в массив значения ячеек вертикальной строки
                for (int i = 0; i < line.length; i++){
                    line[i] = parent.cells[i][curerntCell.cellY];
                }
                break;

            case DIAG_LINE:
                // проверяем, равны ли координаты нажаатой ячейки по XY
                if (curerntCell.cellX == curerntCell.cellY){
                    // добавляем в массив значения ячеек (0, 0); (1, 1); (2, 2)
                    for (int i = 0; i < line.length; i++){
                        line[i] = parent.cells[i][i];
                    }
                } else {
                    return null;
                }
                break;

            case OPP_DIAG_LINE:
                // если (координата Х) равна (2 - координата Y)
                if (curerntCell.cellX == (2 - curerntCell.cellY)){
                    // добавляем в массив значения ячеек (0, 2); (1, 1); (2, 0)
                    for (int i = 0; i < line.length; i++){
                        line[i] = parent.cells[i][2 - i];
                    }
                } else {
                    return null;
                }
                break;
            default:
                break;
        } // end switch
        return line;
    } // end getLine

    private void reset() {
        ApplicationData.setDefaultSettings();
        parent.reset();
    }
}
