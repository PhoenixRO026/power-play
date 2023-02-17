package com.example.meepmeeptesting;


import static com.example.meepmeeptesting.Consts.driveTrainLenght;
import static com.example.meepmeeptesting.Consts.fieldSize;

public class AutoPoses {
    public static ConfigPose startPose = new ConfigPose(
            fieldSize / 4,
            -fieldSize / 2 + driveTrainLenght / 2,
            Math.toRadians(90)
    );

    public static double liftUpStartWait = 0.3;

    public static ConfigPose pose1 = new ConfigPose(
            fieldSize / 4,
            -fieldSize / 12,
            Math.toRadians(90)
    );

    public static double pose1Turn = Math.toRadians(180 - 45);

    public static ConfigPose midPose = new ConfigPose(
            fieldSize / 6,
            -fieldSize / 6,
            Math.toRadians(270 - 45)
    ).offset(0, -driveTrainLenght / 2 - 3);

    public static double midLiftUpOffset = -2;
    public static double midIntake2DownWait = 0.1;
    public static double midIntakeOpenWait = 0.1;
    public static double midLeaveWait = 0.1;

    public static ConfigPose highPose = new ConfigPose(
            fieldSize / 6,
            0,
            Math.toRadians(90 + 45)
    ).offset(0, -driveTrainLenght / 2 - 3);

    public static double highPoseTurn = Math.toRadians(90 + 45);
    public static double highLiftUpOffset = -2;

    public static ConfigPose pose2 = new ConfigPose(
            fieldSize / 4,
            -fieldSize / 12,
            Math.toRadians(45)
    );

    public static double pose2Turn = Math.toRadians(90 + 45);

    public static ConfigPose stackPose = new ConfigPose(
            fieldSize / 2.4 - 0.7,
            -fieldSize / 12,
            Math.toRadians(0)
    );

    public static double stackIntakeCloseWait = 0.1;
    public static double stackLiftUpWait = 0.1;
    public static double stackLeaveWait = 0.1;

    public static  ConfigPose pose3 = new ConfigPose(
            fieldSize / 4,
            -fieldSize / 12,
            Math.toRadians(180)
    );

    public static double pose3Turn = Math.toRadians(-90 - 45);

    public static ConfigPose pose4 = new ConfigPose(
            fieldSize / 4,
            -fieldSize / 12,
            Math.toRadians(270 + 45)
    );

    public static double pose4Turn = Math.toRadians(-45);
}
