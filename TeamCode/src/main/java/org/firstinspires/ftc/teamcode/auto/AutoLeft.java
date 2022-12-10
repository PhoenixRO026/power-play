package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.teleop.SimpleDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "Auto Left")
public class AutoLeft extends LinearOpMode {
    AutoDrive drive;
    Pose2d initPose = new Pose2d(-35.5, -61.5, Math.toRadians(90));
    Lift lift;
    Servo intake;
    double intakeStart = SimpleDrive.intakeStart;
    double intakeEnd = SimpleDrive.intakeEnd;
    double liftHeight = 0;
    double intakePos = intakeStart;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new AutoDrive(hardwareMap, initPose);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        lift = new Lift(hardwareMap);
        intake = hardwareMap.get(Servo.class, "intake");

        TrajectorySequence trajSeq3 = drive.trajectorySequenceBuilder(initPose)
                .waitSeconds(0.5)
                .forward(40)
                .splineTo(new Vector2d(-7 - 23.4, -7), Math.toRadians(45))
                .waitSeconds(0.5)
                .addTemporalMarker(3, () -> {
                    intakePos = intakeEnd;
                })
                .build();

        int result = 13;
        int tempResult;

        intake.setPosition(intakeStart);

        while (opModeInInit()) {
            tempResult = drive.getResult();
            if (tempResult != -1)
                result = tempResult;
            telemetry.addData("Detection", result);
            telemetry.update();
            sleep(20);
        }

        /*switch (result) {
            case 13:
                drive.followTrajectorySequenceAsync(trajSeq1);
                break;
            case 14:
                drive.followTrajectorySequenceAsync(trajSeq2);
                break;
            case 15:
                drive.followTrajectorySequenceAsync(trajSeq3);
                break;
        }*/

        drive.followTrajectorySequenceAsync(trajSeq3);
        liftHeight = 900;
        intakePos = intakeStart;

        boolean savedPose = false;
        while (opModeIsActive()) {
            drive.update();
            lift.setHeightMM(liftHeight);
            intake.setPosition(intakePos);
            if (!drive.isBusy() && !savedPose) {
                StaticVars.currentPose = drive.getPoseEstimate();
                savedPose = true;
            }
            telemetry.addData("Lift height MM", lift.getHeightMM());
            telemetry.update();
        }
    }
}
