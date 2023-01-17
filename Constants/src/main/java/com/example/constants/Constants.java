package com.example.constants;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class Constants {
    public static double fieldSize = 141;

    public static double MAX_VEL = 52.48291908330528;
    public static double MAX_ACCEL = 52.48291908330528;
    public static double MAX_ANG_VEL = Math.toRadians(240.5639808);
    public static double MAX_ANG_ACCEL = Math.toRadians(240.5639808);
    public static double TRACK_WIDTH = 12.525;

    public static double topLiftHeight = 838;

    public static double ROBOT_WIDTH = 14.1732;
    public static double ROBOT_LENGT = 17.20472;

    public static Pose2d rightPoseInit = new Pose2d(
            fieldSize / 4,
            -fieldSize / 2 + ROBOT_LENGT / 2,
            Math.toRadians(90)
    );

    public static Vector2d rightVector1 = new Vector2d(
            fieldSize / 4 + sqrt(pow(ROBOT_LENGT / 2, 2) / 2),
            -fieldSize / 12 - sqrt(pow(ROBOT_LENGT / 2, 2) / 2)
    );
    public static double rightHeading1 = Math.toRadians(90 + 45);

    public static Vector2d flipVector(Vector2d pose) {
        return new Vector2d(-pose.getX(), pose.getY());
    }

    public static double flipHeading(double heading) {
        double headingDeg = Math.toDegrees(heading) % 360;
        return Math.toRadians((540 - headingDeg) % 360);
    }

    public static Pose2d flipPose(Pose2d pose) {
        return new Pose2d(-pose.getX(), pose.getY(), flipHeading(pose.getHeading()));
    }
}