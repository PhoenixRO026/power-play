package com.example.constants;

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

    public static double initPoseY = -fieldSize / 2 + ROBOT_LENGT / 2;
    public static double initPoseX = fieldSize / 4;

    public static double pose1Y = -fieldSize / 12;
    public static double pose1X = fieldSize / 4;
    public static double pose1Heading = Math.toRadians(90 + 45);

    public static class MMConsts {
        public static Pose2d rightInitPose = new Pose2d(Constants.initPoseX, Constants.initPoseY, Math.toRadians(90));
        public static Pose2d leftInitPose = new Pose2d(-Constants.initPoseX, Constants.initPoseY, Math.toRadians(90));

        public static Pose2d rightPose1 = new Pose2d(Constants.pose1X, Constants.pose1Y);
        public static Pose2d leftPose1 = new Pose2d(-Constants.pose1X, Constants.pose1Y);
    }

    public static Pose2d oppositeSide(Pose2d pose) {
        return new Pose2d(-pose.getX(), pose.getY(), pose.getHeading());
    }

    public static Vector2d oppositeSide(Vector2d pose) {
        return new Vector2d(-pose.getX(), pose.getY());
    }
}