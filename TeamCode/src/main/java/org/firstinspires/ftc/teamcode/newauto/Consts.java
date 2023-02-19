package org.firstinspires.ftc.teamcode.newauto;

import com.acmerobotics.dashboard.config.Config;

@Config
public class Consts {
    public static double fieldSize = 141.1;
    public static double driveTrainLenght = 14.1;

    public static double intakeClose = 0.30;
    public static double intakeOpen = 0.06;

    public static double intake2Down = 0.615;

    private static final double intake2Diff = 0.2;
    public static double intake2Up = intake2Down + intake2Diff;

    public static double startSpeed = 50;

    public static int midPos = 1495;
    public static int highPos = 2355;
    public static int stackPos = 410;
    public static int aboveStackPos = 770;

    public static double coneStackDifMM = 29.05;

    public static double mmPerRev = 120;
    public static double tickPerRev = (1.0 + (46.0 / 17.0)) * (1.0 + (46.0 / 17.0)) * 28.0;

    public static int toTicks(double heightMM) {
        return (int) (heightMM * tickPerRev / mmPerRev);
    }
}
