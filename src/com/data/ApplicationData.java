package com.data;

public class ApplicationData {
    public static final String PLAYER_VS_PLAYER = "player";
    public static final String PLAYER_VS_COMPUTER = "computer";
    
    private static final String PLAYERX = "Player X";
    private static final String PLAYERO = "Player O";

    public static String playMode = PLAYER_VS_PLAYER;
    private static String currentPlayer;
    private static boolean gameOver;

    // возвращаем имя текущего игрока
    public static String getCurrentPlayerName() {
        if (currentPlayer == null) setDefaultSettings();
        return currentPlayer;
    }

    // установка настроек игры по умолчанию
    private static void setDefaultSettings() {
        currentPlayer = PLAYERX;
        gameOver = false;
    }
}
