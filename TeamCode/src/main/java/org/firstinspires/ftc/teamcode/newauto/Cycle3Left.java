package org.firstinspires.ftc.teamcode.newauto;

import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.highLiftUpOffset;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.highPose;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.highPoseTurn;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.intake2UpStartWait;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.midIntake2DownWait;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.midIntakeOpenWait;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.midLeaveWait;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.midLiftUpOffset;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.midPose;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose1;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose1Turn;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose2;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose2Turn;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose3;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose3Turn;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose4;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose4Turn;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.stackIntakeCloseWait;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.stackLeaveWait;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.stackLiftUpWait;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.stackPose;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.startPose;
import static org.firstinspires.ftc.teamcode.newauto.Consts.aboveStackPos;
import static org.firstinspires.ftc.teamcode.newauto.Consts.coneStackDifMM;
import static org.firstinspires.ftc.teamcode.newauto.Consts.fieldSize;
import static org.firstinspires.ftc.teamcode.newauto.Consts.highPos;
import static org.firstinspires.ftc.teamcode.newauto.Consts.intake2Down;
import static org.firstinspires.ftc.teamcode.newauto.Consts.intake2Up;
import static org.firstinspires.ftc.teamcode.newauto.Consts.intakeClose;
import static org.firstinspires.ftc.teamcode.newauto.Consts.intakeOpen;
import static org.firstinspires.ftc.teamcode.newauto.Consts.midPos;
import static org.firstinspires.ftc.teamcode.newauto.Consts.stackPos;
import static org.firstinspires.ftc.teamcode.newauto.Consts.toTicks;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

@Config
@Autonomous(preselectTeleOp = "New Drive", name = "3 Cone Left")
public class Cycle3Left extends LinearOpMode {
    SampleMecanumDrive drive;
    Servo intake;
    Servo intake2;
    DcMotorEx lift;
    Detect detect;

    public int lastLiftPos = 0;
    public int liftPos = 0;

    public double intakePos = intakeOpen;
    public double lastIntakePos = intakeOpen;

    public double intake2Pos = intake2Down;
    public double lastIntake2Pos = intake2Down;

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
        detect = new Detect(hardwareMap);

        TrajectorySequenceBuilder base = drive.trajectorySequenceBuilder(startPose.reversed().pose2d())
                .addTemporalMarker(() -> intakePos = intakeClose)
                //.addTemporalMarker(intake2UpStartWait, () -> intake2Pos = intake2Up)
                .splineTo(pose1.reversed().pose2d().vec(), pose1.reversed().pose2d().getHeading())
                .turn(-pose1Turn)
                .splineTo(midPose.reversed().pose2d().vec(), midPose.reversed().pose2d().getHeading())
                .UNSTABLE_addTemporalMarkerOffset(midLiftUpOffset, () -> {
                    liftPos = midPos;
                    intake2Pos = intake2Up;
                })
                .waitSeconds(midIntake2DownWait)
                .addTemporalMarker(() -> intake2Pos = intake2Down)
                .waitSeconds(midIntakeOpenWait)
                .addTemporalMarker(() -> intakePos = intakeOpen)
                .waitSeconds(midLeaveWait)
                .addTemporalMarker(() -> liftPos = stackPos)
                .setReversed(true)
                .splineTo(pose2.reversed().pose2d().vec(), pose2.reversed().pose2d().getHeading())
                .setReversed(false)
                .turn(-pose2Turn)
                .splineTo(stackPose.reversed().pose2d().vec(), stackPose.reversed().pose2d().getHeading())
                .waitSeconds(stackIntakeCloseWait)
                .addTemporalMarker(() -> intakePos = intakeClose)
                .waitSeconds(stackLiftUpWait)
                .addTemporalMarker(() -> liftPos = aboveStackPos)
                .waitSeconds(stackLeaveWait)
                .setReversed(true)
                .splineTo(pose3.reversed().pose2d().vec(), pose3.reversed().pose2d().getHeading())
                .setReversed(false)
                .turn(-pose3Turn)
                .splineTo(midPose.reversed().pose2d().vec(), midPose.reversed().pose2d().getHeading())
                .UNSTABLE_addTemporalMarkerOffset(midLiftUpOffset, () -> {
                    liftPos = midPos;
                    intake2Pos = intake2Up;
                })
                .waitSeconds(midIntake2DownWait)
                .addTemporalMarker(() -> intake2Pos = intake2Down)
                .waitSeconds(midIntakeOpenWait)
                .addTemporalMarker(() -> intakePos = intakeOpen)
                .waitSeconds(midLeaveWait)
                .addTemporalMarker(() -> liftPos = stackPos - toTicks(coneStackDifMM))
                .setReversed(true)
                .splineTo(pose2.reversed().pose2d().vec(), pose2.reversed().pose2d().getHeading())
                .setReversed(false)
                .turn(-pose2Turn)
                .splineTo(stackPose.reversed().pose2d().vec(), stackPose.reversed().pose2d().getHeading())
                .waitSeconds(stackIntakeCloseWait)
                .addTemporalMarker(() -> intakePos = intakeClose)
                .waitSeconds(stackLiftUpWait)
                .addTemporalMarker(() -> liftPos = aboveStackPos - toTicks(coneStackDifMM))
                .waitSeconds(stackLeaveWait)
                .setReversed(true)
                .splineTo(pose3.reversed().pose2d().vec(), pose3.reversed().pose2d().getHeading())
                .setReversed(false)
                .turn(-highPoseTurn)
                .splineTo(highPose.reversed().pose2d().vec(), highPose.reversed().pose2d().getHeading())
                .UNSTABLE_addTemporalMarkerOffset(highLiftUpOffset, () -> {
                    liftPos = highPos;
                    intake2Pos = intake2Up;
                })
                .waitSeconds(midIntake2DownWait)
                .addTemporalMarker(() -> intake2Pos = intake2Down)
                .waitSeconds(midIntakeOpenWait)
                .addTemporalMarker(() -> intakePos = intakeOpen)
                .waitSeconds(midLeaveWait)
                .addTemporalMarker(() -> liftPos = 0)
                .setReversed(true)
                .splineTo(pose4.reversed().pose2d().vec(), pose4.reversed().pose2d().getHeading())
                .setReversed(false)
                .turn(-pose4Turn);

        TrajectorySequence case13 = base.strafeLeft(fieldSize / 6).build();
        TrajectorySequence case14 = base.build();
        TrajectorySequence case15 = base.strafeRight(fieldSize / 6).build();

        intake.setPosition(intakePos);
        intake2.setPosition(intake2Pos);

        int result = 13;

        while (opModeInInit()) {
            result = detect.getResult();
            telemetry.addData("Detection", result);
            telemetry.update();
            sleep(20);
        }

        switch (result) {
            case 13:
                drive.followTrajectorySequenceAsync(case13);
                break;
            case 14:
                drive.followTrajectorySequenceAsync(case14);
                break;
            default:
                drive.followTrajectorySequenceAsync(case15);
        }

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