package com.example.meepmeeptesting;

import static com.example.constants.Constants.*;

import com.example.constants.Constants;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {

    public static void main(String[] args) {
        Constants constants = new Constants();

        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(680);

        RoadRunnerBotEntity myBot1 = new DefaultBotBuilder(meepMeep)
                .setDimensions(ROBOT_WIDTH, ROBOT_LENGT)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(MAX_VEL, MAX_ACCEL, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(constants.rightInit)
                                .splineTo(constants.right1.vec(), constants.right1.getHeading())
                                .setTangent(RIGHT_2_TANGENT)        //1
                                .splineToLinearHeading(constants.right2, Math.toRadians(0))
                                .setTangent(RIGHT_3_TANGENT)
                                .splineToLinearHeading(constants.right3, constants.right3.getHeading())
                                .setTangent(RIGHT_2_TANGENT)        //2
                                .splineToLinearHeading(constants.right2, Math.toRadians(0))
                                .setTangent(RIGHT_3_TANGENT)
                                .splineToLinearHeading(constants.right3, constants.right3.getHeading())
                                .setTangent(RIGHT_2_TANGENT)        //3
                                .splineToLinearHeading(constants.right2, Math.toRadians(0))
                                .setTangent(RIGHT_3_TANGENT)
                                .splineToLinearHeading(constants.right3, constants.right3.getHeading())
                                .setTangent(RIGHT_2_TANGENT)        //4
                                .splineToLinearHeading(constants.right2, Math.toRadians(0))
                                .setTangent(RIGHT_3_TANGENT)
                                .splineToLinearHeading(constants.right3, constants.right3.getHeading())
                                .setTangent(RIGHT_2_TANGENT)        //5
                                .splineToLinearHeading(constants.right2, Math.toRadians(0))
                                .setTangent(RIGHT_3_TANGENT)
                                .splineToLinearHeading(constants.right3, constants.right3.getHeading())
                                .build()
                );

        RoadRunnerBotEntity myBot2 = new DefaultBotBuilder(meepMeep)
                .setDimensions(ROBOT_WIDTH, ROBOT_LENGT)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(MAX_VEL, MAX_ACCEL, MAX_ANG_VEL, MAX_ANG_ACCEL, TRACK_WIDTH)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(flipPose(constants.rightInit))
                                .splineTo(constants.left1.vec(), constants.left1.getHeading())
                                .setTangent(LEFT_2_TANGENT)         //1
                                .splineToLinearHeading(constants.left2, Math.toRadians(180))
                                .setTangent(LEFT_3_TANGENT)
                                .splineToLinearHeading(constants.left3, constants.left3.getHeading())
                                .setTangent(LEFT_2_TANGENT)         //2
                                .splineToLinearHeading(constants.left2, Math.toRadians(180))
                                .setTangent(LEFT_3_TANGENT)
                                .splineToLinearHeading(constants.left3, constants.left3.getHeading())
                                .setTangent(LEFT_2_TANGENT)         //3
                                .splineToLinearHeading(constants.left2, Math.toRadians(180))
                                .setTangent(LEFT_3_TANGENT)
                                .splineToLinearHeading(constants.left3, constants.left3.getHeading())
                                .setTangent(LEFT_2_TANGENT)         //4
                                .splineToLinearHeading(constants.left2, Math.toRadians(180))
                                .setTangent(LEFT_3_TANGENT)
                                .splineToLinearHeading(constants.left3, constants.left3.getHeading())
                                .setTangent(LEFT_2_TANGENT)         //5
                                .splineToLinearHeading(constants.left2, Math.toRadians(180))
                                .setTangent(LEFT_3_TANGENT)
                                .splineToLinearHeading(constants.left3, constants.left3.getHeading())
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