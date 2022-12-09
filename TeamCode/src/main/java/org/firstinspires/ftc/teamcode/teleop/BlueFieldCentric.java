package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Blue Centric")
public class BlueFieldCentric extends SimpleDrive{

    @Override
    public void movement() {
        sniperMode = gamepad1.right_trigger >= 0.2;

        ultraSniperMode = gamepad1.left_trigger >= 0.2;

        if(sniperMode){
            driveSpeed = 0.5;
        }else driveSpeed = 1;

        if(ultraSniperMode)
            driveSpeed = 0.25;

        double leftX, leftY, rightX;

        if(gamepad1.dpad_left || gamepad1.dpad_right)
            leftX = -btoi(gamepad1.dpad_left) + btoi(gamepad1.dpad_right);
        else leftX = gamepad1.left_stick_x;

        if(gamepad1.dpad_down || gamepad1.dpad_up)
            leftY = -btoi(gamepad1.dpad_down) + btoi(gamepad1.dpad_up);
        else leftY = -gamepad1.left_stick_y;

        if(gamepad1.x || gamepad1.b)
            rightX = -btoi(gamepad1.x) + btoi(gamepad1.b);
        else rightX = gamepad1.right_stick_x;

        drive.update();

        Pose2d poseEstimate = drive.getPoseEstimate();

        Vector2d input = new Vector2d(
                leftY,
                -leftX
        ).rotated(-poseEstimate.getHeading());

        drive.setWeightedDrivePower(
                new Pose2d(
                        input.getX() * driveSpeed,  //left_stick_y
                        input.getY() * driveSpeed,  //left_stick_x
                        -rightX * driveSpeed  //right_stick_x
                )
        );
    }
}
