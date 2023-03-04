package org.firstinspires.ftc.teamcode.newauto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Disabled
@Autonomous
public class Spiiiiiiin extends LinearOpMode {
    public static double spin = 2000;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence sequence = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .turn(-Math.toRadians(spin))
                .build();

        waitForStart();

        drive.followTrajectorySequenceAsync(sequence);

        while (opModeIsActive()) {
            drive.update();
        }

        drive.breakFollowing();

        drive.setWeightedDrivePower(new Pose2d());
    }
}
