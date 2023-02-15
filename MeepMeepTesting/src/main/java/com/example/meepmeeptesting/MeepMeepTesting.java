package com.example.meepmeeptesting;

import static com.example.constants.Constants.MAX_ACCEL;
import static com.example.constants.Constants.MAX_ANG_ACCEL;
import static com.example.constants.Constants.MAX_ANG_VEL;
import static com.example.constants.Constants.MAX_VEL;
import static com.example.constants.Constants.ROBOT_LENGTH;
import static com.example.constants.Constants.ROBOT_WIDTH;
import static com.example.constants.Constants.TRACK_WIDTH;
import static com.example.constants.Constants.flipPose;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.example.constants.Constants;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        meep();
    }

    public static void meep() {
        Constants constants = new Constants();

        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(650);

        double field = 141.1;

        RoadRunnerBotEntity myBot3 = new DefaultBotBuilder(meepMeep)
                .setDimensions(14.1, 14.1)
                .setConstraints(MAX_VEL, MAX_ACCEL, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                                .build()
                );

        RoadRunnerBotEntity myBot1 = new DefaultBotBuilder(meepMeep)
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
                );

        // Set field image
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                // Background opacity from 0-1
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot1)
                .addEntity(myBot2)
                .start();
    }
}