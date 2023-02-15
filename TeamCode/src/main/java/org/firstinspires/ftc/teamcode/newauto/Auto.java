package org.firstinspires.ftc.teamcode.newauto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.newauto.Consts.*;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Config
@Autonomous(preselectTeleOp = "Java Drive")
public class Auto extends LinearOpMode {
    SampleMecanumDrive drive;
    Servo intake;
    Servo intake2;
    DcMotorEx lift;
    ElapsedTime time = new ElapsedTime();

    public static ConfigPose startPose = new ConfigPose(
            fieldSize / 4,
            -fieldSize / 2 + driveTrainLenght / 2,
            Math.toRadians(90)
    );

    public int lastLiftPos = 0;
    public int liftPos = 0;

    public double intakePos = intakeClose;
    public double lastIntakePos = intakeClose;

    public double intake2Pos = intake2Up;
    public double lastIntake2Pos = intake2Up;

    public static ConfigPose pose1 = new ConfigPose(
            fieldSize / 4,
            -fieldSize / 12,
            Math.toRadians(90)
    );

    public static ConfigPose midPose = new ConfigPose(
            fieldSize / 6,
            -fieldSize / 6,
            Math.toRadians(270 - 45)
    ).offset(0, -driveTrainLenght / 2 - 2);

    public static ConfigPose pose2 = new ConfigPose(
            fieldSize / 4,
            -fieldSize / 12,
            Math.toRadians(45)
    );

    public static ConfigPose stackPose = new ConfigPose(
            fieldSize / 2.4 - 1,
            -fieldSize / 12,
            Math.toRadians(0)
    );

    public static ConfigPose pose3 = new ConfigPose(
            fieldSize / 4,
            -fieldSize / 12,
            Math.toRadians(180)
    );

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(startPose.pose2d());
        intake = hardwareMap.get(Servo.class, "intake");
        intake2 = hardwareMap.get(Servo.class, "intake2");
        lift = hardwareMap.get(DcMotorEx.class, "lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);
        lift.setTargetPosition(0);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(1);

        TrajectorySequence test = drive.trajectorySequenceBuilder(startPose.pose2d())
                .splineTo(pose1.pose2d().vec(), pose1.pose2d().getHeading())
                .turn(Math.toRadians(180 - 45))
                .splineTo(midPose.pose2d().vec(), midPose.pose2d().getHeading())
                .UNSTABLE_addTemporalMarkerOffset(-2, () -> liftPos = midPos)
                .waitSeconds(0.5)
                .addTemporalMarker(() -> intake2Pos = intake2Down)
                .waitSeconds(0.2)
                .addTemporalMarker(() -> intakePos = intakeOpen)
                .waitSeconds(0.2)
                .addTemporalMarker(() -> liftPos = stackPos)
                .splineToConstantHeading(pose2.pose2d().vec(), pose2.pose2d().getHeading())
                .turn(Math.toRadians(90 + 45))
                .splineTo(stackPose.pose2d().vec(), stackPose.pose2d().getHeading())
                .waitSeconds(0.2)
                .addTemporalMarker(() -> intakePos = intakeClose)
                .waitSeconds(0.3)
                .addTemporalMarker(() -> liftPos = aboveStackPos)
                .waitSeconds(0.2)
                .addTemporalMarker(() -> intake2Pos = intake2Up)
                .splineToConstantHeading(pose3.pose2d().vec(), pose3.pose2d().getHeading())
                .build();

        intake.setPosition(intakeClose);
        intake2.setPosition(intake2Down);

        time.reset();

        while (opModeInInit() && time.seconds() <= 0.2) {
            sleep(20);
        }

        intake2.setPosition(intake2Pos);

        telemetry.addLine("Ready!");
        telemetry.update();

        waitForStart();

        drive.followTrajectorySequenceAsync(test);

        while (!isStopRequested() && isStarted()) {
            drive.update();
            if (liftPos != lastLiftPos) {
                lift.setTargetPosition(liftPos);
                lastLiftPos = liftPos;
            }
            if (intakePos != lastIntakePos) {
                intake.setPosition(intakePos);
                lastIntakePos = intakePos;
            }
            if (intake2Pos != lastIntake2Pos) {
                intake2.setPosition(intake2Pos);
                lastIntake2Pos = intake2Pos;
            }
        }
        lift.setPower(0);

        drive.breakFollowing();
    }
}