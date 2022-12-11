package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.SampleMecanumDrive;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(14.17323, 17.71654)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(48.47814086141882, 52.48180821614297, Math.toRadians(243.95269725695957), Math.toRadians(180), 12.18)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(35.5, -61.5, Math.toRadians(90)))
                                .setTangent(Math.toRadians(-10 + 180))
                                .splineToConstantHeading(
                                        new Vector2d(10, -47),
                                        Math.toRadians(90),
                                        SampleMecanumDrive.getVelocityConstraint(30, 40, 12.18),
                                        SampleMecanumDrive.getAccelerationConstraint(40)
                                )
                                .addTemporalMarker(0, () -> {
                                    //liftHeight = 800;
                                })
                                .splineToSplineHeading(
                                        new Pose2d(7 + 23.4 - 2 - 9 - 1, -7 + 2 - 1, Math.toRadians(45)),
                                        Math.toRadians(45)
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
                                        new Pose2d(11, -12, Math.toRadians(90)),
                                        Math.toRadians(30 - 180)
                                )
                                .strafeRight(45)
                                .build()
                );

        RoadRunnerBotEntity myBot2 = new DefaultBotBuilder(meepMeep)
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
                                        new Pose2d(-7 - 23.4 + 2 + 9 + 1, -7 + 2 - 1, Math.toRadians(135)),
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
                );

        // Set field image
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                // Background opacity from 0-1
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .addEntity(myBot2)
                .start();
    }
}