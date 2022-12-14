package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.teleop.SimpleDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "Auto Right")
public class AutoRight extends LinearOpMode {
    AutoDrive drive;
    Pose2d initPose = new Pose2d(35.5, -61.5, Math.toRadians(90));
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

        Pose2d highPolePos = new Pose2d(7 + 23.4 - 2 - 9 - 1 - 0.5, -7 + 2 - 1 + 0.5, Math.toRadians(45));
        TrajectorySequence trajSeq3 = drive.trajectorySequenceBuilder(initPose)
                .setTangent(Math.toRadians(-10 + 180))
                .splineToConstantHeading(
                        new Vector2d(10, -47),
                        Math.toRadians(90),
                        SampleMecanumDrive.getVelocityConstraint(30, 40, 12.18),
                        SampleMecanumDrive.getAccelerationConstraint(40)
                )
                .addTemporalMarker(0, () -> {
                    liftHeight = 820;
                })
                .splineToSplineHeading(
                        highPolePos,
                        Math.toRadians(45)
                )
                .addTemporalMarker(4, () -> {
                    intakePos = intakeEnd;
                })
                .waitSeconds(1)
                .back(3)
                .addTemporalMarker(4.5, () -> {
                    liftHeight = 0;
                })
                .splineToSplineHeading(
                        new Pose2d(11, -12, Math.toRadians(90)),
                        Math.toRadians(30 - 180)
                )
                .strafeRight(45)
                .build();

        TrajectorySequence trajSeq2 = drive.trajectorySequenceBuilder(initPose)
                .setTangent(Math.toRadians(-10 + 180))
                .splineToConstantHeading(
                        new Vector2d(10, -47),
                        Math.toRadians(90),
                        SampleMecanumDrive.getVelocityConstraint(30, 40, 12.18),
                        SampleMecanumDrive.getAccelerationConstraint(40)
                )
                .addTemporalMarker(0, () -> {
                    liftHeight = 820;
                })
                .splineToSplineHeading(
                        highPolePos,
                        Math.toRadians(45)
                )
                .addTemporalMarker(4, () -> {
                    intakePos = intakeEnd;
                })
                .waitSeconds(1)
                .back(3)
                .addTemporalMarker(4.5, () -> {
                    liftHeight = 0;
                })
                .splineToSplineHeading(
                        new Pose2d(11, -12, Math.toRadians(90)),
                        Math.toRadians(30 - 180)
                )
                .strafeRight(24)
                .build();

        TrajectorySequence trajSeq1 = drive.trajectorySequenceBuilder(initPose)
                .setTangent(Math.toRadians(-10 + 180))
                .splineToConstantHeading(
                        new Vector2d(10, -47),
                        Math.toRadians(90),
                        SampleMecanumDrive.getVelocityConstraint(30, 40, 12.18),
                        SampleMecanumDrive.getAccelerationConstraint(40)
                )
                .addTemporalMarker(0, () -> {
                    liftHeight = 820;
                })
                .splineToSplineHeading(
                        highPolePos,
                        Math.toRadians(45)
                )
                .addTemporalMarker(4, () -> {
                    intakePos = intakeEnd;
                })
                .waitSeconds(1)
                .back(3)
                .addTemporalMarker(4.5, () -> {
                    liftHeight = 0;
                })
                .splineToSplineHeading(
                        new Pose2d(11, -12, Math.toRadians(90)),
                        Math.toRadians(30 - 180)
                )
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

        //drive.followTrajectorySequenceAsync(trajSeq3);

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
