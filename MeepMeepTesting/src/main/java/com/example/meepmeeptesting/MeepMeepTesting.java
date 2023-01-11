package com.example.meepmeeptesting;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.SampleMecanumDrive;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {

    public static double MAX_VEL = 48.47814086141882;
    public static double MAX_ACCEL = 52.48180821614297;
    public static double MAX_ANG_VEL = Math.toRadians(243.95269725695957);
    public static double MAX_ANG_ACCEL = Math.toRadians(184.02607784577722);
    public static double TRACK_WIDTH = 12.18; // in
    public static void main(String[] args) {
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(670);

        Pose2d start = new Pose2d(0, 0, Math.toRadians(90));

        Pose2d highJunctionPose = new Pose2d(-7 - 23.4 + 2 + 9 + 1 + 0.5, -7 + 2 - 1 + 0.5, Math.toRadians(135));

        Pose2d initPose = new Pose2d(-36, -63.15, Math.toRadians(90));

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(14.17323, 16.92913)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(MAX_VEL, MAX_ACCEL, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(initPose)
                                .setTangent(Math.toRadians(10))
                                .addTemporalMarker(() -> {})
                                .splineToConstantHeading(new Vector2d(-10.2, -46.56), Math.toRadians(90))
                                .splineTo(new Vector2d(-10, -20), Math.toRadians(90))
                                .splineTo(
                                        new Vector2d(-24 + 5.985351, 0 - 5.985351),
                                        Math.toRadians(135),
                                        SampleMecanumDrive.getVelocityConstraint(MAX_VEL / 2, MAX_ANG_VEL, TRACK_WIDTH),
                                        SampleMecanumDrive.getAccelerationConstraint(MAX_ACCEL)
                                )
                                .addTemporalMarker(() -> {})
                                .waitSeconds(1)
                                .back(10)
                                .build()
                );

        /*RoadRunnerBotEntity myBot2 = new DefaultBotBuilder(meepMeep)
                .setDimensions(14.17323, 17.71654)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(48.47814086141882, 52.48180821614297, Math.toRadians(243.95269725695957), Math.toRadians(180), 12.18)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-35.5, -61.5, Math.toRadians(90)))
                                .setTangent(Math.toRadians(10))
                                .splineToConstantHeading(
                                        new Vector2d(-10, -47),
                                        Math.toRadians(90),
                                        SampleMecanumDrive.getVelocityConstraint(30, 40, 12.18),
                                        SampleMecanumDrive.getAccelerationConstraint(40)
                                )
                                .addTemporalMarker(0, () -> {
                                    //liftHeight = 800;
                                })
                                .splineToSplineHeading(
                                        new Pose2d(-7 - 23.4 + 2 + 9 + 1 + 0.5, -7 + 2 - 1 + 0.5, Math.toRadians(135)),
                                        Math.toRadians(135)
                                )
                                .addTemporalMarker(4, () -> {
                                    //intakePos = intakeEnd;
                                })
                                .waitSeconds(1)
                                .back(3)
                                .addTemporalMarker(4.5, () -> {
                                    //liftHeight = 0;
                                })
                                .splineToSplineHeading(
                                        new Pose2d(-11, -12, Math.toRadians(90)),
                                        Math.toRadians(-30)
                                )
                                .strafeLeft(45)
                                .build()
                );*/

        // Set field image
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                // Background opacity from 0-1
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                //.addEntity(myBot2)
                .start();
    }
}