package com.example.constants;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class Constants {

    public static double MAX_VEL = 52.48291908330528;
    public static double MAX_ACCEL = 52.48291908330528;
    public static double MAX_ANG_VEL = Math.toRadians(240.5639808);
    public static double MAX_ANG_ACCEL = Math.toRadians(240.5639808);

    public static Pose2d leftInitPose = new Pose2d(-35.5, -63.5, Math.toRadians(90));
    public static Pose2d rightInitPose = new Pose2d(-leftInitPose.getX(), leftInitPose.getY(), leftInitPose.getHeading());

    Pose2d righthighPolePos = new Pose2d(7 + 23.4 - 2 - 9 - 1 - 0.5 - 1, -7 + 2 - 1 + 0.5 - 1, Math.toRadians(45));
}