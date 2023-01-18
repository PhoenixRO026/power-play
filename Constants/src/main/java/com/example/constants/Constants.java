package com.example.constants;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

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
    public static double ROBOT_LENGT = ROBOT_WIDTH;
    public static double ROBOT_TRUE_LENGT = 16;
    public static double CLAW_OFFSET = 2;
    public static double ROBOT_LENGHT_DIFF = ROBOT_TRUE_LENGT - ROBOT_LENGT;

    public static double CONE_RADIUS = 4;

    public Pose2d rightInit = new Pose2d (
            fieldSize / 4,
            -fieldSize / 2 + ROBOT_LENGT / 2,
            Math.toRadians(90)
    );

    private static final double RIGHT_1_HEADING = Math.toRadians(90 + 30);
    public Pose2d right1 = new Pose2d(
            fieldSize / 6 - (ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) * cos(RIGHT_1_HEADING) / 2 + CLAW_OFFSET * sin(RIGHT_1_HEADING),
            -(ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) * sin(RIGHT_1_HEADING) / 2 - CLAW_OFFSET * cos(RIGHT_1_HEADING),
            RIGHT_1_HEADING
    );
    private static final double LEFT_1_HEADING = flipHeadingRad(RIGHT_1_HEADING);
    public Pose2d left1 = new Pose2d(
            -(fieldSize / 6 - (ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) * -cos(LEFT_1_HEADING) / 2) + CLAW_OFFSET * sin(LEFT_1_HEADING),
            -(ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) * sin(LEFT_1_HEADING) / 2 - CLAW_OFFSET * cos(LEFT_1_HEADING),
            LEFT_1_HEADING
    );

    public static final double RIGHT_2_TANGENT = Math.toRadians(330);
    public Pose2d right2 = new Pose2d(
            fieldSize / 2 - (ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) / 2 - CONE_RADIUS / 2,
            -fieldSize / 12 - CLAW_OFFSET,
            Math.toRadians(0)
    );

    public static final double LEFT_2_TANGENT = flipHeadingRad(RIGHT_2_TANGENT);
    public Pose2d left2 = new Pose2d(
            -right2.getX(),
            -fieldSize / 12 + CLAW_OFFSET,
            Math.toRadians(180)
    );

    private static final double RIGHT_3_HEADING = Math.toRadians(90 + 45);
    public static final double RIGHT_3_TANGENT = Math.toRadians(180);
    public Pose2d right3 = new Pose2d(
            fieldSize / 6 - (ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) * cos(RIGHT_3_HEADING) / 2 + CLAW_OFFSET * sin(RIGHT_3_HEADING),
            -(ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) * sin(RIGHT_3_HEADING) / 2 - CLAW_OFFSET * cos(RIGHT_3_HEADING),
            RIGHT_3_HEADING
    );
    private static final double LEFT_3_HEADING = flipHeadingRad(RIGHT_3_HEADING);
    public static final double LEFT_3_TANGENT = flipHeadingRad(RIGHT_3_TANGENT);
    public Pose2d left3 = new Pose2d(
            -(fieldSize / 6 - (ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) * -cos(LEFT_3_HEADING) / 2) + CLAW_OFFSET * sin(LEFT_3_HEADING),
            -(ROBOT_TRUE_LENGT + ROBOT_LENGHT_DIFF) * sin(LEFT_3_HEADING) / 2 - CLAW_OFFSET * cos(LEFT_3_HEADING),
            LEFT_3_HEADING
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