package org.firstinspires.ftc.teamcode.auto;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.teleop.SimpleDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Config
@Autonomous(name = "Cycle3Right Right")
public class AutoRight extends LinearOpMode {
    AutoDrive drive;
    Pose2d initPose = new Pose2d(36, -66.7, Math.toRadians(90));
    Lift lift;
    Servo intake;
    double intakeStart = SimpleDrive.intakeStart;
    double intakeEnd = SimpleDrive.intakeEnd;
    double liftHeight = 0;
    double intakePos = intakeStart;

    public static double maxAngVelMulti = 0.8;
    public static double maxVelMulti = 0.8;

    public static double robotLenght = 14.173;

    public static double diff = sqrt(pow(robotLenght / 2, 2) / 2);

    //public static double clawLenght = 2.5;

    public static double clawDiff = 4;

    public static double sideClawDiff = 1;

    Pose2d highPolePos = new Pose2d(24 - diff - clawDiff - sideClawDiff, -diff - clawDiff + sideClawDiff, Math.toRadians(90 - 48));

    Vector2d pose1 = new Vector2d(13.5, -47);
    
    Pose2d pose2 = new Pose2d(12, -12, Math.toRadians(90));

    double topHeight = 838;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new AutoDrive(hardwareMap, initPose);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        lift = new Lift(hardwareMap);
        intake = hardwareMap.get(Servo.class, "intake");

        TrajectorySequence trajSeq3 = drive.trajectorySequenceBuilder(initPose)
                .setTangent(Math.toRadians(90 + 80))
                .splineToConstantHeading(
                        pose1,
                        Math.toRadians(90),
                        SampleMecanumDrive.getVelocityConstraint(30, 40, 12.18),
                        SampleMecanumDrive.getAccelerationConstraint(40)
                )
                .addTemporalMarker(0, () -> {
                    liftHeight = topHeight;
                })
                .splineToSplineHeading(
                        highPolePos,
                        Math.toRadians(90 - 25),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL * maxVelMulti, DriveConstants.MAX_ANG_VEL * maxAngVelMulti, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL)
                )
                .addTemporalMarker(4, () -> {
                    intakePos = intakeEnd;
                })
                .waitSeconds(1.5)
                //.back(3)
                .addTemporalMarker(5, () -> {
                    liftHeight = 0;
                })
                .setTangent(Math.toRadians(270 - 45))
                .splineToLinearHeading(
                        pose2,
                        Math.toRadians(270 - 45)
                )
                .strafeRight(45)
                .build();

        TrajectorySequence trajSeq2 = drive.trajectorySequenceBuilder(initPose)
                .setTangent(Math.toRadians(90 + 80))
                .splineToConstantHeading(
                        pose1,
                        Math.toRadians(90),
                        SampleMecanumDrive.getVelocityConstraint(30, 40, 12.18),
                        SampleMecanumDrive.getAccelerationConstraint(40)
                )
                .addTemporalMarker(0, () -> {
                    liftHeight = topHeight;
                })
                .splineToSplineHeading(
                        highPolePos,
                        Math.toRadians(90 - 25),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL * maxVelMulti, DriveConstants.MAX_ANG_VEL * maxAngVelMulti, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL)
                )
                .addTemporalMarker(4, () -> {
                    intakePos = intakeEnd;
                })
                .waitSeconds(1.5)
                //.back(3)
                .addTemporalMarker(5, () -> {
                    liftHeight = 0;
                })
                .setTangent(Math.toRadians(270 - 45))
                .splineToLinearHeading(
                        pose2,
                        Math.toRadians(270 - 45)
                )
                .strafeRight(24)
                .build();

        TrajectorySequence trajSeq1 = drive.trajectorySequenceBuilder(initPose)
                .setTangent(Math.toRadians(90 + 80))
                .splineToConstantHeading(
                        pose1,
                        Math.toRadians(90),
                        SampleMecanumDrive.getVelocityConstraint(30, 40, 12.18),
                        SampleMecanumDrive.getAccelerationConstraint(40)
                )
                .addTemporalMarker(0, () -> {
                    liftHeight = topHeight;
                })
                .splineToSplineHeading(
                        highPolePos,
                        Math.toRadians(90 - 25),
                        SampleMecanumDrive.getVelocityConstraint(DriveConstants.MAX_VEL * maxVelMulti, DriveConstants.MAX_ANG_VEL * maxAngVelMulti, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL)
                )
                .addTemporalMarker(4, () -> {
                    intakePos = intakeEnd;
                })
                .waitSeconds(1.5)
                //.back(3)
                .addTemporalMarker(5, () -> {
                    liftHeight = 0;
                })
                .setTangent(Math.toRadians(270 - 45))
                .splineToLinearHeading(
                        pose2,
                        Math.toRadians(270 - 45)
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
