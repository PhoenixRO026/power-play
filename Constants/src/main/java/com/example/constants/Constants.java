package com.example.constants;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

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
    public static double ROBOT_LENGT = ROBOT_WIDTH;
    public static double ROBOT_TRUE_LENGT = 16;
    public static double CLAW_OFFSET = 2;
    public static double ROBOT_LENGHT_DIFF = ROBOT_TRUE_LENGT - ROBOT_LENGT;

    public Pose rightInit = new Pose (
            fieldSize / 4,
            -fieldSize / 2 + ROBOT_LENGT / 2,
            90
    );

    private static final double RIGHT_1_HEADING_DEG = 90 + 30;
    public Pose right1 = new Pose(
            fieldSize / 6 - (ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) * cos(Math.toRadians(RIGHT_1_HEADING_DEG)) / 2 + CLAW_OFFSET * sin(Math.toRadians(RIGHT_1_HEADING_DEG)),
            -(ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) * sin(Math.toRadians(RIGHT_1_HEADING_DEG)) / 2 - CLAW_OFFSET * cos(Math.toRadians(RIGHT_1_HEADING_DEG)),
            RIGHT_1_HEADING_DEG
    );
    private static final double LEFT_1_HEADING_DEG = flipHeadingDeg(RIGHT_1_HEADING_DEG);
    public Pose left1 = new Pose(
            -(fieldSize / 6 - (ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) * -cos(Math.toRadians(LEFT_1_HEADING_DEG)) / 2) + CLAW_OFFSET * sin(Math.toRadians(LEFT_1_HEADING_DEG)),
            -(ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) * sin(Math.toRadians(LEFT_1_HEADING_DEG)) / 2 - CLAW_OFFSET * cos(Math.toRadians(LEFT_1_HEADING_DEG)),
            LEFT_1_HEADING_DEG
    );

    public static Vector2d flipVector(Vector2d pose) {
        return new Vector2d(-pose.getX(), pose.getY());
    }

    public static double flipHeadingRad(double heading) {
        double headingDeg = Math.toDegrees(heading) % 360;
        return Math.toRadians((540 - headingDeg) % 360);
    }

    public static double flipHeadingDeg(double heading) {
        double headingDeg = heading % 360;
        return (540 - headingDeg) % 360;
    }

    public static Pose2d flipPose(Pose2d pose) {
        return new Pose2d(-pose.getX(), pose.getY(), flipHeadingRad(pose.getHeading()));
    }
}