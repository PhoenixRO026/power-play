package com.example.meepmeeptesting;

import static com.example.constants.Constants.MAX_ANG_ACCEL;
import static com.example.constants.Constants.MAX_ANG_VEL;
import static com.example.constants.Constants.TRACK_WIDTH;
import static com.example.meepmeeptesting.AutoPoses.*;
import static com.example.meepmeeptesting.Consts.*;

import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.SampleMecanumDrive;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        meep3();
    }

    public static void meep3() {
        MeepMeep meepMeep = new MeepMeep(650);

        RoadRunnerBotEntity bot = new DefaultBotBuilder(meepMeep)
            .setDimensions(driveTrainLenght, driveTrainLenght)
            .setConstraints(50, 50, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
            .setColorScheme(new ColorSchemeRedDark())
            .followTrajectorySequence(drive ->
                drive.trajectorySequenceBuilder(startPose.pose2d())
                    //.addTemporalMarker(() -> intakePos = intakeClose)
                    //.addTemporalMarker(liftUpStartWait, () -> liftPos = aboveStackPos)
                    .splineTo(
                        pose1.pose2d().vec(),
                        pose1.pose2d().getHeading(),
                        SampleMecanumDrive.getVelocityConstraint(startSpeed, MAX_ANG_VEL, TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(startSpeed)
                    )
                    .turn(pose1Turn)
                    //.addTemporalMarker(() -> intake2Pos = intake2Up)
                    .splineTo(midPose.pose2d().vec(), midPose.pose2d().getHeading())
                    //.UNSTABLE_addTemporalMarkerOffset(midLiftUpOffset, () -> liftPos = midPos)
                    .waitSeconds(midIntake2DownWait)
                    //.addTemporalMarker(() -> intake2Pos = intake2Down)
                    .waitSeconds(midIntakeOpenWait)
                    //.addTemporalMarker(() -> intakePos = intakeOpen)
                    .waitSeconds(midLeaveWait)
                    //.addTemporalMarker(() -> liftPos = stackPos)
                    .setReversed(true)
                    .splineTo(pose2.pose2d().vec(), pose2.pose2d().getHeading())
                    .setReversed(false)
                    .turn(pose2Turn)
                    .splineTo(stackPose.pose2d().vec(), stackPose.pose2d().getHeading())
                    //.waitSeconds(stackIntakeCloseWait)
                    //.addTemporalMarker(() -> intakePos = intakeClose)
                    .waitSeconds(stackLiftUpWait)
                    //.addTemporalMarker(() -> liftPos = aboveStackPos)
                    .waitSeconds(stackLeaveWait)
                    .setReversed(true)
                    .splineTo(pose3.pose2d().vec(), pose3.pose2d().getHeading())
                    .setReversed(false)
                    .turn(pose3Turn)
                    .splineTo(midPose.pose2d().vec(), midPose.pose2d().getHeading())
                    /*.UNSTABLE_addTemporalMarkerOffset(midLiftUpOffset, () -> {
                        liftPos = midPos;
                        intake2Pos = intake2Up;
                    })*/
                    .waitSeconds(midIntake2DownWait)
                    //.addTemporalMarker(() -> intake2Pos = intake2Down)
                    .waitSeconds(midIntakeOpenWait)
                    //.addTemporalMarker(() -> intakePos = intakeOpen)
                    .waitSeconds(midLeaveWait)
                    //.addTemporalMarker(() -> liftPos = stackPos - toTicks(coneStackDifMM))
                    .setReversed(true)
                    .splineTo(pose2.pose2d().vec(), pose2.pose2d().getHeading())
                    .setReversed(false)
                    .turn(pose2Turn)
                    .splineTo(stackPose.pose2d().vec(), stackPose.pose2d().getHeading())
                    //.waitSeconds(stackIntakeCloseWait)
                    //.addTemporalMarker(() -> intakePos = intakeClose)
                    .waitSeconds(stackLiftUpWait)
                    //.addTemporalMarker(() -> liftPos = aboveStackPos)
                    .waitSeconds(stackLeaveWait)
                    .setReversed(true)
                    .splineTo(pose3.pose2d().vec(), pose3.pose2d().getHeading())
                    .setReversed(false)
                    .turn(pose3Turn)
                    .splineTo(midPose.pose2d().vec(), midPose.pose2d().getHeading())
                    /*.UNSTABLE_addTemporalMarkerOffset(midLiftUpOffset, () -> {
                        liftPos = midPos;
                        intake2Pos = intake2Up;
                    })*/
                    .waitSeconds(midIntake2DownWait)
                    //.addTemporalMarker(() -> intake2Pos = intake2Down)
                    .waitSeconds(midIntakeOpenWait)
                    //.addTemporalMarker(() -> intakePos = intakeOpen)
                    .waitSeconds(midLeaveWait)
                    //.addTemporalMarker(() -> liftPos = stackPos - toTicks(coneStackDifMM))
                    .setReversed(true)
                    .splineTo(pose2.pose2d().vec(), pose2.pose2d().getHeading())
                    .setReversed(false)
                    .turn(pose2Turn)
                    .splineTo(stackPose.pose2d().vec(), stackPose.pose2d().getHeading())
                    .waitSeconds(stackIntakeCloseWait)
                    //.addTemporalMarker(() -> intakePos = intakeClose)
                    .waitSeconds(stackLiftUpWait)
                    //.addTemporalMarker(() -> liftPos = aboveStackPos - toTicks(coneStackDifMM))
                    .waitSeconds(stackLeaveWait)
                    .setReversed(true)
                    .splineTo(pose3.pose2d().vec(), pose3.pose2d().getHeading())
                    .setReversed(false)
                    .turn(highPoseTurn)
                    .splineTo(highPose.pose2d().vec(), highPose.pose2d().getHeading())
                    /*.UNSTABLE_addTemporalMarkerOffset(highLiftUpOffset, () -> {
                        liftPos = highPos;
                        intake2Pos = intake2Up;
                    })*/
                    .waitSeconds(midIntake2DownWait)
                    //.addTemporalMarker(() -> intake2Pos = intake2Down)
                    .waitSeconds(midIntakeOpenWait)
                    //.addTemporalMarker(() -> intakePos = intakeOpen)
                    .waitSeconds(midLeaveWait)
                    //.addTemporalMarker(() -> liftPos = 0)
                    .setReversed(true)
                    .splineTo(pose4.pose2d().vec(), pose4.pose2d().getHeading())
                    .setReversed(false)
                    .turn(pose4Turn)
                    .strafeLeft(fieldSize / 6)
                    .build()
            );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                // Background opacity from 0-1
                .setBackgroundAlpha(0.95f)
                .addEntity(bot)
                .start();
    }

   /* public static void meep2() {
        MeepMeep meepMeep = new MeepMeep(650);

        double fieldSize = 141.1;
        double driveTrainLenght = 14.1;

        ConfigPose startPose = new ConfigPose(
                fieldSize / 4,
                -fieldSize / 2 + driveTrainLenght / 2,
                Math.toRadians(90)
        );

        ConfigPose pose1 = new ConfigPose(
                fieldSize / 4,
                -fieldSize / 12,
                Math.toRadians(180)
        );

        ConfigPose midPose = new ConfigPose(
                fieldSize / 6,
                -fieldSize / 6,
                Math.toRadians(270 - 45)
        ).offset(0, -driveTrainLenght / 2 - 2);

        ConfigPose highPose = new ConfigPose(
                fieldSize / 6,
                0,
                Math.toRadians(90 + 45)
        ).offset(0, -driveTrainLenght / 2 - 2);

        ConfigPose pose2 = new ConfigPose(
                fieldSize / 4,
                -fieldSize / 12,
                Math.toRadians(270)
        );

        ConfigPose stackPose = new ConfigPose(
                fieldSize / 2.4 - 1,
                -fieldSize / 12,
                Math.toRadians(0)
        );

        ConfigPose pose3 = new ConfigPose(
                fieldSize / 4,
                -fieldSize / 12,
                Math.toRadians(270)
        );

        ConfigPose pose4 = new ConfigPose(
                fieldSize / 4,
                -fieldSize / 12,
                Math.toRadians(90)
        );

        RoadRunnerBotEntity myBot3 = new DefaultBotBuilder(meepMeep)
                .setDimensions(driveTrainLenght, driveTrainLenght)
                .setConstraints(50, 50, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose.pose2d())
                                .lineToLinearHeading(pose1.pose2d())
                                .lineToLinearHeading(midPose.pose2d())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose2.pose2d())
                                .lineToLinearHeading(stackPose.pose2d())
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.3)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose3.pose2d())
                                .lineToLinearHeading(midPose.pose2d())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose2.pose2d())
                                .lineToLinearHeading(stackPose.pose2d())
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.3)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose3.pose2d())
                                .lineToLinearHeading(midPose.pose2d())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose2.pose2d())
                                .lineToLinearHeading(stackPose.pose2d())
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.3)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose3.pose2d())
                                .lineToLinearHeading(midPose.pose2d())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose2.pose2d())
                                .lineToLinearHeading(stackPose.pose2d())
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.3)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose4.pose2d())
                                .lineToLinearHeading(highPose.pose2d())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose4.pose2d())
                                .strafeRight(fieldSize / 6)
                                .build()
                );

        RoadRunnerBotEntity myBot4 = new DefaultBotBuilder(meepMeep)
                .setDimensions(driveTrainLenght, driveTrainLenght)
                .setConstraints(MAX_VEL, MAX_ACCEL, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose.reversed().pose2d())
                                .lineToLinearHeading(pose1.reversed().pose2d())
                                .lineToLinearHeading(midPose.reversed().pose2d())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose2.reversed().pose2d())
                                .lineToLinearHeading(stackPose.reversed().pose2d())
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.3)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose3.reversed().pose2d())
                                .lineToLinearHeading(midPose.reversed().pose2d())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose2.reversed().pose2d())
                                .lineToLinearHeading(stackPose.reversed().pose2d())
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.3)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose3.reversed().pose2d())
                                .lineToLinearHeading(midPose.reversed().pose2d())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose2.reversed().pose2d())
                                .lineToLinearHeading(stackPose.reversed().pose2d())
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.3)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose4.reversed().pose2d())
                                .lineToLinearHeading(highPose.reversed().pose2d())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .lineToLinearHeading(pose4.reversed().pose2d())
                                .strafeLeft(fieldSize / 6)
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                // Background opacity from 0-1
                .setBackgroundAlpha(0.95f)
                //.addEntity(myBot1)
                //.addEntity(myBot2)
                .addEntity(myBot3)
                .addEntity(myBot4)
                //.addEntity(myBot4)
                .start();
    }

    public static void meep() {
        //Constants constants = new Constants();

        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(650);

        double fieldSize = 141.1;
        double driveTrainLenght = 14.1;

        ConfigPose startPose = new ConfigPose(
                fieldSize / 4,
                -fieldSize / 2 + driveTrainLenght / 2,
                Math.toRadians(90)
        );

        ConfigPose pose1 = new ConfigPose(
                fieldSize / 4,
                -fieldSize / 12,
                Math.toRadians(90)
        );

        ConfigPose midPose = new ConfigPose(
                fieldSize / 6,
                -fieldSize / 6,
                Math.toRadians(270 - 45)
        ).offset(0, -driveTrainLenght / 2 - 2);

        ConfigPose highPose = new ConfigPose(
                fieldSize / 6,
                0,
                Math.toRadians(90 + 45)
        ).offset(0, -driveTrainLenght / 2 - 2);

        ConfigPose pose2 = new ConfigPose(
                fieldSize / 4,
                -fieldSize / 12,
                Math.toRadians(45)
        );

        ConfigPose stackPose = new ConfigPose(
                fieldSize / 2.4 - 1,
                -fieldSize / 12,
                Math.toRadians(0)
        );

        ConfigPose pose3 = new ConfigPose(
                fieldSize / 4,
                -fieldSize / 12,
                Math.toRadians(180)
        );

        ConfigPose pose4 = new ConfigPose(
                fieldSize / 4,
                -fieldSize / 12,
                Math.toRadians(270 + 45)
        );

        RoadRunnerBotEntity myBot3 = new DefaultBotBuilder(meepMeep)
                .setDimensions(driveTrainLenght, driveTrainLenght)
                .setConstraints(50, 50, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose.pose2d())
                                .splineTo(pose1.pose2d().vec(), pose1.pose2d().getHeading())
                                .turn(Math.toRadians(180 - 45))
                                .splineTo(midPose.pose2d().vec(), midPose.pose2d().getHeading())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .setReversed(true)
                                .splineTo(pose2.pose2d().vec(), pose2.pose2d().getHeading())
                                .setReversed(false)
                                .turn(Math.toRadians(90 + 45))
                                .splineTo(stackPose.pose2d().vec(), stackPose.pose2d().getHeading())
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.3)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .setReversed(true)
                                .splineTo(pose3.pose2d().vec(), pose3.pose2d().getHeading())
                                .setReversed(false)
                                .turn(Math.toRadians(-90 - 45))
                                .splineTo(midPose.pose2d().vec(), midPose.pose2d().getHeading())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .setReversed(true)
                                .splineTo(pose2.pose2d().vec(), pose2.pose2d().getHeading())
                                .setReversed(false)
                                .turn(Math.toRadians(90 + 45))
                                .splineTo(stackPose.pose2d().vec(), stackPose.pose2d().getHeading())
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.3)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .setReversed(true)
                                .splineTo(pose3.pose2d().vec(), pose3.pose2d().getHeading())
                                .setReversed(false)
                                .turn(Math.toRadians(-90 - 45))
                                .splineTo(midPose.pose2d().vec(), midPose.pose2d().getHeading())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .setReversed(true)
                                .splineTo(pose2.pose2d().vec(), pose2.pose2d().getHeading())
                                .setReversed(false)
                                .turn(Math.toRadians(90 + 45))
                                .splineTo(stackPose.pose2d().vec(), stackPose.pose2d().getHeading())
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.3)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .setReversed(true)
                                .splineTo(pose3.pose2d().vec(), pose3.pose2d().getHeading())
                                .setReversed(false)
                                .turn(Math.toRadians(90 + 45))
                                .splineTo(highPose.pose2d().vec(), highPose.pose2d().getHeading())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .setReversed(true)
                                .splineTo(pose4.pose2d().vec(), pose4.pose2d().getHeading())
                                .setReversed(false)
                                .turn(Math.toRadians(-45))
                                .strafeRight(fieldSize / 6)
                                .build()
                );

        RoadRunnerBotEntity myBot4 = new DefaultBotBuilder(meepMeep)
                .setDimensions(driveTrainLenght, driveTrainLenght)
                .setConstraints(MAX_VEL, MAX_ACCEL, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose.reversed().pose2d())
                                .splineTo(pose1.reversed().pose2d().vec(), pose1.reversed().pose2d().getHeading())
                                .turn(Math.toRadians(-180 + 45))
                                .splineTo(midPose.reversed().pose2d().vec(), midPose.reversed().pose2d().getHeading())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .setReversed(true)
                                .splineTo(pose2.reversed().pose2d().vec(), pose2.reversed().pose2d().getHeading())
                                .setReversed(false)
                                .turn(Math.toRadians(-90 - 45))
                                .splineTo(stackPose.reversed().pose2d().vec(), stackPose.reversed().pose2d().getHeading())
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.3)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .setReversed(true)
                                .splineTo(pose3.reversed().pose2d().vec(), pose3.reversed().pose2d().getHeading())
                                .setReversed(false)
                                .turn(Math.toRadians(90 + 45))
                                .splineTo(midPose.reversed().pose2d().vec(), midPose.reversed().pose2d().getHeading())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .setReversed(true)
                                .splineTo(pose2.reversed().pose2d().vec(), pose2.reversed().pose2d().getHeading())
                                .setReversed(false)
                                .turn(Math.toRadians(-90 - 45))
                                .splineTo(stackPose.reversed().pose2d().vec(), stackPose.reversed().pose2d().getHeading())
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.3)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .setReversed(true)
                                .splineTo(pose3.reversed().pose2d().vec(), pose3.reversed().pose2d().getHeading())
                                .setReversed(false)
                                .turn(Math.toRadians(-90 - 45))
                                .splineTo(highPose.reversed().pose2d().vec(), highPose.reversed().pose2d().getHeading())
                                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {})
                                .waitSeconds(0.5)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .waitSeconds(0.2)
                                .addTemporalMarker(() -> {})
                                .setReversed(true)
                                .splineTo(pose4.reversed().pose2d().vec(), pose4.reversed().pose2d().getHeading())
                                .setReversed(false)
                                .turn(Math.toRadians(45))
                                .strafeLeft(fieldSize / 6)
                                .build()
                );

        *//*RoadRunnerBotEntity myBot1 = new DefaultBotBuilder(meepMeep)
                .setDimensions(ROBOT_WIDTH, ROBOT_LENGTH)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(MAX_VEL, MAX_ACCEL, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(constants.rightInit)
                                .splineTo(constants.right1.vec, constants.right1.END_TANGENT)
                                .waitSeconds(0.5)
                                .setTangent(constants.right2.START_TANGENT)        //1
                                .splineToLinearHeading(constants.right2.poseTan, constants.right2.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.right3.START_TANGENT)
                                .splineToLinearHeading(constants.right3.poseTan, constants.right3.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.right4.START_TANGENT)        //2
                                .splineToLinearHeading(constants.right4.poseTan, constants.right4.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.right3.START_TANGENT)
                                .splineToLinearHeading(constants.right3.poseTan, constants.right3.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.right4.START_TANGENT)        //3
                                .splineToLinearHeading(constants.right4.poseTan, constants.right4.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.right3.START_TANGENT)
                                .splineToLinearHeading(constants.right3.poseTan, constants.right3.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.right4.START_TANGENT)        //4
                                .splineToLinearHeading(constants.right4.poseTan, constants.right4.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.right3.START_TANGENT)
                                .splineToLinearHeading(constants.right3.poseTan, constants.right3.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.right4.START_TANGENT)        //5
                                .splineToLinearHeading(constants.right4.poseTan, constants.right4.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.right3.START_TANGENT)
                                .splineToLinearHeading(constants.right3.poseTan, constants.right3.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.rightPark.START_TANGENT)
                                .splineToLinearHeading(constants.rightPark.poseHed, constants.rightPark.END_TANGENT)
                                .strafeLeft(24)
                                .build()
                );

        RoadRunnerBotEntity myBot2 = new DefaultBotBuilder(meepMeep)
                .setDimensions(ROBOT_WIDTH, ROBOT_LENGTH)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(MAX_VEL, MAX_ACCEL, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(flipPose(constants.rightInit))
                                .splineTo(constants.left1.vec, constants.left1.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.left2.START_TANGENT)         //1
                                .splineToLinearHeading(constants.left2.poseTan, constants.left2.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.left3.START_TANGENT)
                                .splineToLinearHeading(constants.left3.poseTan, constants.left3.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.left2.START_TANGENT)         //2
                                .splineToLinearHeading(constants.left2.poseTan, constants.left2.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.left3.START_TANGENT)
                                .splineToLinearHeading(constants.left3.poseTan, constants.left3.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.left2.START_TANGENT)         //3
                                .splineToLinearHeading(constants.left2.poseTan, constants.left2.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.left3.START_TANGENT)
                                .splineToLinearHeading(constants.left3.poseTan, constants.left3.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.left2.START_TANGENT)         //4
                                .splineToLinearHeading(constants.left2.poseTan, constants.left2.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.left3.START_TANGENT)
                                .splineToLinearHeading(constants.left3.poseTan, constants.left3.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.left2.START_TANGENT)         //5
                                .splineToLinearHeading(constants.left2.poseTan, constants.left2.HEADING)
                                .waitSeconds(0.5)
                                .setTangent(constants.left3.START_TANGENT)
                                .splineToLinearHeading(constants.left3.poseTan, constants.left3.HEADING)
                                .waitSeconds(0.5)
                                .build()
                );*//*

        // Set field image
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                // Background opacity from 0-1
                .setBackgroundAlpha(0.95f)
                //.addEntity(myBot1)
                //.addEntity(myBot2)
                .addEntity(myBot3)
                .addEntity(myBot4)
                .start();
    }*/
}