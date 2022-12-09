package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "SimpleAuto")
public class SimpleAuto extends LinearOpMode {

    Pose2d initPos = new Pose2d(35, -58, Math.toRadians(90));
    AutoDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new AutoDrive(hardwareMap, initPos);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        TrajectorySequence trajSeq3 = drive.trajectorySequenceBuilder(initPos)
                .forward(2)
                .strafeRight(23)
                .forward(23)
                .build();

        TrajectorySequence trajSeq2 = drive.trajectorySequenceBuilder(initPos)
                .forward(2)
                .strafeRight(23)
                .forward(43)
                .strafeLeft(23)
                .build();

        TrajectorySequence trajSeq1 = drive.trajectorySequenceBuilder(initPos)
                .forward(2)
                .strafeLeft(22)
                .forward(23)
                .build();

        int result = 13;
        int tempResult = -1;
        while (opModeInInit()) {
            tempResult = drive.getResult();
            if (tempResult != -1)
                result = tempResult;
            telemetry.addData("Detection", result);
            telemetry.update();
            sleep(20);
        }

        switch (result) {
            case 13:
                drive.followTrajectorySequenceAsync(trajSeq1);
                break;
            case 14:
                drive.followTrajectorySequenceAsync(trajSeq2);
                break;
            case 15:
                drive.followTrajectorySequenceAsync(trajSeq3);
                break;
        }
        boolean savedPose = false;
        while (opModeIsActive()) {
            drive.update();
            if (!drive.isBusy() && !savedPose) {
                StaticVars.currentPose = drive.getPoseEstimate();
                savedPose = true;
            }
        }
    }
}
