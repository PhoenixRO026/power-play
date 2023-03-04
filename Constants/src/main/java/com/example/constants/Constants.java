package com.example.constants;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class Constants {
    public static double fieldSize = 141.1;
    public static double MAX_VEL = 50;
    public static double MAX_ACCEL = 50;
    public static double MAX_ANG_VEL = Math.toRadians(240.5639808);
    public static double MAX_ANG_ACCEL = Math.toRadians(240.5639808);
    public static double TRACK_WIDTH = 12.525;
    public static double topLiftHeight = 860;
    public static double ROBOT_WIDTH = 14.1732;
    public static double ROBOT_LENGTH = ROBOT_WIDTH;
    //public static double ROBOT_TRUE_LENGTH = 16;
    public static final double HORIZONTAL_LOW_CLAW_OFFSET = -2;
    public static final double VERTICAL_LOW_CLAW_OFFSET = -2;
    public static final double HORIZONTAL_HIGH_CLAW_OFFSET = -1;
    public static final double VERTICAL_HIGH_CLAW_OFFSET = -1.5;
    public static double autoConeHeight = 130;
    public static double coneDecrease = 20;

    public static double CONE_RADIUS = 4;

    public Pose2d rightInit = new Pose2d (
            fieldSize / 4,
            -fieldSize / 2 + ROBOT_LENGTH / 2,
            Math.toRadians(90)
    );

    /*public static double RIGHT_1_HEADING = Math.toRadians(90 + 30);
    public static double RIGHT_1_VERTICAL_OFFSET = -1.5;
    public static double RIGHT_1_HORIZONTAL_OFFSET = -1;*/
    public static double RIGHT_1_HEADING = Math.toRadians(90 + 30);
    public static double RIGHT_1_VERTICAL_OFFSET = -4;
    public static double RIGHT_1_HORIZONTAL_OFFSET = -1;
    public Pose right1 = new Pose(
            fieldSize / 6,
            0,
            RIGHT_1_HEADING,
            0,
            RIGHT_1_HEADING,
            RIGHT_1_VERTICAL_OFFSET,
            RIGHT_1_HORIZONTAL_OFFSET
    );

    private static final double LEFT_1_HEADING = flipHeadingRad(RIGHT_1_HEADING);
    public Pose left1 = new Pose(
            -fieldSize / 6,
            0,
            LEFT_1_HEADING,
            0,
            LEFT_1_HEADING,
            Pose.CLAW_OFFSET.HIGH_OFFSET
    );

    /*public static final double RIGHT_2_HEADING = Math.toRadians(0);
    public static final double RIGHT_2_TANGENT = Math.toRadians(330);
    public static double RIGHT_2_VERTICAL_OFFSET = -2;
    public static double RIGHT_2_HORIZONTAL_OFFSET = -2;*/
    public static final double RIGHT_2_HEADING = Math.toRadians(0);
    public static final double RIGHT_2_TANGENT = Math.toRadians(330);
    public static double RIGHT_2_VERTICAL_OFFSET = 0;
    public static double RIGHT_2_HORIZONTAL_OFFSET = -1.3;
    public Pose right2 = new Pose(
            (fieldSize - CONE_RADIUS) / 2,
            -fieldSize / 12,
            RIGHT_2_HEADING,
            RIGHT_2_TANGENT,
            RIGHT_2_HEADING,
            RIGHT_2_VERTICAL_OFFSET,
            RIGHT_2_HORIZONTAL_OFFSET
    );

    private static final double LEFT_2_HEADING = flipHeadingRad(RIGHT_2_HEADING);
    private static final double LEFT_2_TANGENT = flipHeadingRad(RIGHT_2_TANGENT);
    public Pose left2 = new Pose(
            -(fieldSize - CONE_RADIUS) / 2,
            -fieldSize / 12,
            LEFT_2_HEADING,
            LEFT_2_TANGENT,
            LEFT_2_HEADING,
            Pose.CLAW_OFFSET.LOW_OFFSET
    );

    /*private static final double RIGHT_3_HEADING = Math.toRadians(90 + 45);
    private static final double RIGHT_3_TANGENT = Math.toRadians(180);
    public static double RIGHT_3_VERTICAL_OFFSET = -1.5;
    public static double RIGHT_3_HORIZONTAL_OFFSET = 1.5;*/
    private static final double RIGHT_3_HEADING = Math.toRadians(90 + 45);
    private static final double RIGHT_3_TANGENT = Math.toRadians(180);
    public static double RIGHT_3_VERTICAL_OFFSET = -3.5;
    public static double RIGHT_3_HORIZONTAL_OFFSET = 2.5;
    public Pose right3 = new Pose(
            fieldSize / 6,
            0,
            RIGHT_3_HEADING,
            RIGHT_3_TANGENT,
            RIGHT_3_HEADING,
            RIGHT_3_VERTICAL_OFFSET,
            RIGHT_3_HORIZONTAL_OFFSET
    );

    private static final double LEFT_3_HEADING = flipHeadingRad(RIGHT_3_HEADING);
    private static final double LEFT_3_TANGENT = flipHeadingRad(RIGHT_3_TANGENT);
    public Pose left3 = new Pose(
            -fieldSize / 6,
            0,
            LEFT_3_HEADING,
            LEFT_3_TANGENT,
            LEFT_3_HEADING,
            Pose.CLAW_OFFSET.HIGH_OFFSET
    );

    /*public static final double RIGHT_4_HEADING = Math.toRadians(0);
    public static final double RIGHT_4_TANGENT = Math.toRadians(270 + 45);
    public static double RIGHT_4_VERTICAL_OFFSET = -2;
    public static double RIGHT_4_HORIZONTAL_OFFSET = -4;*/
    public static final double RIGHT_4_HEADING = Math.toRadians(0);
    public static final double RIGHT_4_TANGENT = Math.toRadians(270 + 45);
    public static double RIGHT_4_VERTICAL_OFFSET = -1;
    public static double RIGHT_4_HORIZONTAL_OFFSET = -4;
    public Pose right4 = new Pose(
            (fieldSize - CONE_RADIUS) / 2,
            -fieldSize / 12,
            RIGHT_4_HEADING,
            RIGHT_4_TANGENT,
            RIGHT_4_HEADING,
            RIGHT_4_VERTICAL_OFFSET,
            RIGHT_4_HORIZONTAL_OFFSET
    );

    private static final double LEFT_4_HEADING = flipHeadingRad(RIGHT_4_HEADING);
    private static final double LEFT_4_TANGENT = flipHeadingRad(RIGHT_4_TANGENT);
    public Pose left4 = new Pose(
            -(fieldSize - CONE_RADIUS) / 2,
            -fieldSize / 12,
            LEFT_4_HEADING,
            LEFT_4_TANGENT,
            LEFT_4_HEADING,
            Pose.CLAW_OFFSET.LOW_OFFSET
    );

    private static final double RIGHT_PARK_HEADING = Math.toRadians(90);
    private static final double RIGHT_PARK_START_TANGENT = Math.toRadians(270 + 45);
    private static final double RIGHT_PARK_END_TANGENT = Math.toRadians(270 + 45);
    public static double RIGHT_PARK_HORIZONTAL_OFFSET = 0;
    public static double RIGHT_PARK_VERTICAL_OFFSET = 0;

    public Pose rightPark = new Pose(
            fieldSize / 4,
            -fieldSize / 12,
            RIGHT_PARK_HEADING,
            RIGHT_PARK_START_TANGENT,
            RIGHT_PARK_END_TANGENT,
            RIGHT_PARK_VERTICAL_OFFSET,
            RIGHT_PARK_HORIZONTAL_OFFSET,
            Pose.OFFSET.NO_ADD
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