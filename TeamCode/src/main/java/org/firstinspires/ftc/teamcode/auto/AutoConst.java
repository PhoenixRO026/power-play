package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.example.constants.Constants;

@Config
public class AutoConst {
    public static double MAX_VEL = Constants.MAX_VEL;
    public static double MAX_ACCEL = Constants.MAX_ACCEL;
    public static double MAX_ANG_VEL = Constants.MAX_ANG_VEL;
    public static double MAX_ANG_ACCEL = Constants.MAX_ANG_ACCEL;
    public static double TRACK_WIDTH = Constants.TRACK_WIDTH;

    public static double ROBOT_WIDTH = Constants.ROBOT_WIDTH;
    public static double ROBOT_LENGT = Constants.ROBOT_LENGT;

    public static double topLiftHeight = Constants.topLiftHeight;

    public static Pose2d2 rightInitPose = new Pose2d2(Constants.initPoseX, Constants.initPoseY, Math.toRadians(90));
    public static Pose2d2 leftInitPose = new Pose2d2(-Constants.initPoseX, Constants.initPoseY, Math.toRadians(90));

    public static Vector2d2 rightPose1 = new Vector2d2(Constants.pose1X, Constants.pose1Y);
    public static Vector2d2 leftPose1 = new Vector2d2(-Constants.pose1X, Constants.pose1Y);
}