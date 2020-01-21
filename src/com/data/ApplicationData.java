package com.data;

public class ApplicationData {
    public static final String PLAYER_VS_PLAYER = "player";
    public static final String PLAYER_VS_COMPUTER = "computer";

    public static String playMode = PLAYER_VS_PLAYER;

    private static int lineSize;

    public static int getLineSize() {
        return lineSize;
    }

    public static void setLineSize(int rowLength) {
        lineSize = rowLength;
    }


}
