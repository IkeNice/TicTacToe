package com.controller;

import com.Main;
import com.data.ApplicationData;
import com.view.PlayView;
import com.view.components.GameCell;

import java.awt.event.*;
import java.util.Random;

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
            // return, если игра оконена
            if (ApplicationData.gameOver) return;

            // ход компьютера, если режим игры PLAYER_VS_COMPUTER
            if (ApplicationData.playMode == ApplicationData.PLAYER_VS_COMPUTER) computerTurn();
        }
    } // end mouseClicked


    // ход компьютера
    private void computerTurn() {
        boolean success = true;
        int x, y;
        GameCell currentCell;

        do {
            // генерация координат случайным образом
            x = new Random().nextInt(3);
            y = new Random().nextInt(3);

            // назначаем ячейку по сгенирированным кооридинатам
            currentCell = parent.cells[x][y];

            // проверяем, удалось ли установить значение в ячейку
            success = checkCell(currentCell);
        // если установить значение не удалось, цикл повторяется
        } while(!success);
    } // end computerTurn

    // установка значения в ячейку
    private boolean checkCell(GameCell curerntCell) {
        // если ячейка пуста
        if (curerntCell.getText() == ""){
            // установка в нее значение PLAYERX_VALUE либо PLAYERO_VALUE
            curerntCell.setText(ApplicationData.getPlayerValue());

            // true, если был обнаружен победитель
            if (checkForWinner(curerntCell)) return true;
            // true, если было зафиксирована ничья
            if (checkStalemate()) return true;

            // если победитель не обнаружен и остались
            // пустые ячейки, меняем ход игрока
            ApplicationData.switchCurrentPlayer();

            // получаем значение переменной текущего игрока
            // устанавливаем примечание об очереди хода в поле комментариев
            parent.setCurrentPlayerNote(ApplicationData.getCurrentPlayerName());

            return true;
        }
        // false, так как установить значение в ячейку не удалось
        return false;
    } // end checkCell

    // проверка статуса игры на ничью
    private boolean checkStalemate() {
        // false, если есть пустые ячейки
        for (int i = 0; i < parent.cells.length; i++){
            for (int j = 0; j < parent.cells[i].length; j++){
                if (parent.cells[i][j].getText().equals("")) return false;
            }
        }

        // делаем ячейки формы неактивными
        parent.setBlockState();
        // устанавливаем текст о ничье в поле комментария
        parent.setStalemateText();

        // изменение значения переменной статуса окончания игры
        ApplicationData.gameOver = true;

        return true;
    } // end checkStalemate

    // проверка наличия победителя
    private boolean checkForWinner(GameCell curerntCell) {
        // если строка заполнена одинаковыми ячейками
        if (findFullRow(curerntCell)){
            // делаем ячейки формы неактивными
            parent.setBlockState();

            // получаем имя текущего игрока
            // устанавливаеем текст с поздравлением в поле комментария
            parent.setWinnerText(ApplicationData.getCurrentPlayerName());

            // изменяем значение переменной статуса окончания игры
            ApplicationData.gameOver = true;

            return true;
        }
        //false, если строка с одинаковыми ячейками отсутствует
        return false;
    } // end checkForWinner

    // проверяем строки на равенство ячеек
    private boolean findFullRow(GameCell curerntCell) {
        // пмассив для хранения значения ячеек по одной линии
        GameCell[] line;

        // получаем содержимое ячеек строки по горизонтали
        line = getLine(curerntCell, HOR_LINE);
        // true, если значения ячеек горизонтальной строки равны
        if (checkLine(line)) return true;

        // получаем содержимое ячеек строки по вертикали
        line = getLine(curerntCell, VER_LINE);
        // true, если значения ячеек вертикальной строки равны
        if (checkLine(line)) return true;

        // получаем содержимое ячеек (0, 0); (1, 1); (2, 2)
        line = getLine(curerntCell, DIAG_LINE);
        // true, если значения ячеек (0, 0); (1, 1); (2, 2) равны
        if (checkLine(line)) return true;

        // получаем содержимое ячеек (0, 2); (1, 1); (2, 0)
        line = getLine(curerntCell, OPP_DIAG_LINE);
        // true, если значения ячеек (0, 2); (1, 1); (2, 0) равны
        if (checkLine(line)) return true;

        // false, если значения ячеек не равны ни по одной из строк
        return false;
    } // end findFullRow

    // проверка конкретной строки на равенство ячеек
    private boolean checkLine(GameCell[] line) {
        // false, если строка отсутствует
        if (line == null) return false;

        // получаем значение первой ячейки в строке
        String symbol = line[0].getText();

        // false, если первая ячейка в строке пустая
        if (symbol.equals("")) return false;

        // false, если ячейки строки имеют разные значения
        for (int i = 0; i < line.length; i++){
            if (line[i].getText() != symbol) return false;
        }

        // задаем фон строке с одинаковыми ячейками
        parent.setLineColor(line);

        // true, если значения ячеек равны и не пустые
        return true;
    } // end checkLine

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
