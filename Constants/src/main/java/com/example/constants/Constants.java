package com.example.constants;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class Constants {
    public static double fieldSize = 141.1;
    public static double MAX_VEL = 52.48291908330528;
    public static double MAX_ACCEL = 52.48291908330528;
    public static double MAX_ANG_VEL = Math.toRadians(240.5639808);
    public static double MAX_ANG_ACCEL = Math.toRadians(240.5639808);
    public static double TRACK_WIDTH = 12.525;
    public static double topLiftHeight = 838;
    public static double ROBOT_WIDTH = 14.1732;
    public static double ROBOT_LENGTH = ROBOT_WIDTH;
    //public static double ROBOT_TRUE_LENGTH = 16;
    public static double HORIZONTAL_LOW_CLAW_OFFSET = 0;
    public static double VERTICAL_LOW_CLAW_OFFSET = 0;
    public static double HORIZONTAL_HIGH_CLAW_OFFSET = 0;
    public static double VERTICAL_HIGH_CLAW_OFFSET = 0;
    //public static double ROBOT_LENGHT_DIFF = ROBOT_TRUE_LENGTH - ROBOT_LENGTH;

    public static double CONE_RADIUS = 4;

    public Pose2d rightInit = new Pose2d (
            fieldSize / 4,
            -fieldSize / 2 + ROBOT_LENGTH / 2,
            Math.toRadians(90)
    );

    private static final double RIGHT_1_HEADING = Math.toRadians(90 + 30);
    public Pose right1 = new Pose(
            fieldSize / 6,
            0,
            RIGHT_1_HEADING,
            0,
            RIGHT_1_HEADING,
            Pose.OFFSET.HIGH_OFFSET
    );

    private static final double LEFT_1_HEADING = flipHeadingRad(RIGHT_1_HEADING);
    public Pose left1 = new Pose(
            -fieldSize / 6,
            0,
            LEFT_1_HEADING,
            0,
            LEFT_1_HEADING,
            Pose.OFFSET.HIGH_OFFSET
    );

    private static final double RIGHT_2_HEADING = Math.toRadians(0);
    private static final double RIGHT_2_TANGENT = Math.toRadians(330);
    public Pose right2 = new Pose(
            (fieldSize - CONE_RADIUS) / 2,
            -fieldSize / 12,
            RIGHT_2_HEADING,
            RIGHT_2_TANGENT,
            RIGHT_2_HEADING,
            Pose.OFFSET.LOW_OFFSET
    );

    private static final double LEFT_2_HEADING = flipHeadingRad(RIGHT_2_HEADING);
    private static final double LEFT_2_TANGENT = flipHeadingRad(RIGHT_2_TANGENT);
    public Pose left2 = new Pose(
            -(fieldSize - CONE_RADIUS) / 2,
            -fieldSize / 12,
            LEFT_2_HEADING,
            LEFT_2_TANGENT,
            LEFT_2_HEADING,
            Pose.OFFSET.LOW_OFFSET
    );

    private static final double RIGHT_3_HEADING = Math.toRadians(90 + 45);
    private static final double RIGHT_3_TANGENT = Math.toRadians(180);
    public Pose right3 = new Pose(
            fieldSize / 6,
            0,
            RIGHT_3_HEADING,
            RIGHT_3_TANGENT,
            RIGHT_3_HEADING,
            Pose.OFFSET.HIGH_OFFSET
    );

    private static final double LEFT_3_HEADING = flipHeadingRad(RIGHT_3_HEADING);
    private static final double LEFT_3_TANGENT = flipHeadingRad(RIGHT_3_TANGENT);
    public Pose left3 = new Pose(
            -fieldSize / 6,
            0,
            LEFT_3_HEADING,
            LEFT_3_TANGENT,
            LEFT_3_HEADING,
            Pose.OFFSET.HIGH_OFFSET
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