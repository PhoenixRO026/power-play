package org.firstinspires.ftc.teamcode.newauto;

import static com.example.constants.Constants.MAX_ANG_VEL;
import static com.example.constants.Constants.TRACK_WIDTH;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.highLiftUpOffset;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.highPose;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.highPoseTurn;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.liftUpStartWait;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.midIntake2DownWait;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.midIntakeOpenWait;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.midLeaveWait;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.midLiftUpOffset;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.midPose;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose1;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose1Turn;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose1TurnIntake2UpOffset;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose2;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose2Turn;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose3;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose3Turn;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose4;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose4LiftOffsetWait;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose4Turn;
import static org.firstinspires.ftc.teamcode.newauto.AutoPoses.pose5;
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
import static org.firstinspires.ftc.teamcode.newauto.Consts.startSpeed;
import static org.firstinspires.ftc.teamcode.newauto.Consts.toTicks;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Config
//@Autonomous(preselectTeleOp = "New Drive", name = "3 Cone Right")
public abstract class Cycle3Right extends LinearOpMode {
    SampleMecanumDrive drive;
    Servo intake;
    Servo intake2;
    DcMotorEx lift;
    public Detect detect;

    public int lastLiftPos = 0;
    public int liftPos = 0;

    public double intakePos = intakeOpen;
    public double lastIntakePos = intakeOpen;

    public double intake2Pos = intake2Down;
    public double lastIntake2Pos = intake2Down;

    public STATE state = STATE.DRIVE;

    public enum STATE {
        DRIVE,
        PARK
    }

    public abstract void initCamera();

    public abstract int getAutoCase();

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
        lift.setDirection(DcMotorSimple.Direction.FORWARD);
        lift.setTargetPosition(0);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(1);
        //detect = new Detect(hardwareMap);
        initCamera();

        TrajectorySequence base = drive.trajectorySequenceBuilder(startPose.pose2d())
                .addTemporalMarker(() -> intakePos = intakeClose)
                .addTemporalMarker(liftUpStartWait, () -> liftPos = aboveStackPos)
                .splineTo(
                        pose1.pose2d().vec(),
                        pose1.pose2d().getHeading(),
                        SampleMecanumDrive.getVelocityConstraint(startSpeed, MAX_ANG_VEL, TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(startSpeed)
                )
                .turn(pose1Turn)
                .UNSTABLE_addTemporalMarkerOffset(pose1TurnIntake2UpOffset, () -> intake2Pos = intake2Up)
                .splineTo(midPose.pose2d().vec(), midPose.pose2d().getHeading())
                .UNSTABLE_addTemporalMarkerOffset(midLiftUpOffset, () -> liftPos = midPos)
                .waitSeconds(midIntake2DownWait)
                .addTemporalMarker(() -> intake2Pos = intake2Down)
                .waitSeconds(midIntakeOpenWait)
                .addTemporalMarker(() -> intakePos = intakeOpen)
                .waitSeconds(midLeaveWait)
                .addTemporalMarker(() -> liftPos = stackPos)
                .setReversed(true)
                .splineTo(pose2.pose2d().vec(), pose2.pose2d().getHeading())
                .setReversed(false)
                .turn(pose2Turn)
                .splineTo(stackPose.pose2d().vec(), stackPose.pose2d().getHeading())
                .waitSeconds(stackIntakeCloseWait)
                .addTemporalMarker(() -> intakePos = intakeClose)
                .waitSeconds(stackLiftUpWait)
                .addTemporalMarker(() -> liftPos = aboveStackPos)
                .waitSeconds(stackLeaveWait)
                .setReversed(true)
                .splineTo(pose3.pose2d().vec(), pose3.pose2d().getHeading())
                .setReversed(false)
                .turn(pose3Turn)
                .splineTo(midPose.pose2d().vec(), midPose.pose2d().getHeading())
                .UNSTABLE_addTemporalMarkerOffset(midLiftUpOffset, () -> {
                    liftPos = midPos;
                    intake2Pos = intake2Up;
                })
                .waitSeconds(midIntake2DownWait)
                .addTemporalMarker(() -> intake2Pos = intake2Down)
                .waitSeconds(midIntakeOpenWait)
                .addTemporalMarker(() -> intakePos = intakeOpen)
                .waitSeconds(midLeaveWait)
                .setReversed(true)
                .splineTo(pose2.pose2d().vec(), pose2.pose2d().getHeading())
                .setReversed(false)
                .turn(pose2Turn)
                .splineTo(stackPose.pose2d().vec(), stackPose.pose2d().getHeading())
                .UNSTABLE_addTemporalMarkerOffset(midLiftUpOffset, () -> liftPos = stackPos - toTicks(coneStackDifMM))
                .waitSeconds(stackIntakeCloseWait)
                .addTemporalMarker(() -> intakePos = intakeClose)
                .waitSeconds(stackLiftUpWait)
                .addTemporalMarker(() -> liftPos = aboveStackPos - toTicks(coneStackDifMM))
                .waitSeconds(stackLeaveWait)
                .setReversed(true)
                .splineTo(pose3.pose2d().vec(), pose3.pose2d().getHeading())
                .setReversed(false)
                .turn(highPoseTurn)
                .lineToConstantHeading(highPose.pose2d().vec())
                .UNSTABLE_addTemporalMarkerOffset(highLiftUpOffset, () -> {
                    liftPos = highPos;
                    intake2Pos = intake2Up;
                })
                .waitSeconds(midIntake2DownWait)
                .addTemporalMarker(() -> intake2Pos = intake2Down)
                .waitSeconds(midIntakeOpenWait)
                .addTemporalMarker(() -> intakePos = intakeOpen)
                .waitSeconds(midLeaveWait)
                .UNSTABLE_addTemporalMarkerOffset(pose4LiftOffsetWait, () -> liftPos = 0)
                .lineToConstantHeading(pose4.pose2d().vec())
                .turn(pose4Turn)
                .build();

        TrajectorySequence case13 = drive.trajectorySequenceBuilder(pose5.pose2d())
                .strafeLeft(fieldSize / 6)
                .build();
        TrajectorySequence case15 = drive.trajectorySequenceBuilder(pose5.pose2d())
                .strafeRight(fieldSize / 6)
                .build();

        /*TrajectorySequence case13 = base.strafeLeft(fieldSize / 6).build();
        TrajectorySequence case14 = base.build();
        TrajectorySequence case15 = base.strafeRight(fieldSize / 6).build+();*/

        intake.setPosition(intakePos);
        intake2.setPosition(intake2Pos);

        int result = 13;

        while (opModeInInit()) {
            result = getAutoCase();
            telemetry.addData("Detection", result);
            telemetry.update();
            sleep(20);
        }

        drive.followTrajectorySequenceAsync(base);

        while (!isStopRequested() && isStarted()) {
            drive.update();
            switch (state) {
                case DRIVE:
                    if (!drive.isBusy()) {
                        switch (result) {
                            case 13:
                                drive.followTrajectorySequenceAsync(case13);
                                break;
                            case 15:
                                drive.followTrajectorySequenceAsync(case15);
                                break;
                            default:
                        }
                        state = STATE.PARK;
                    }
                    break;
                case PARK:
            }
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
        drive.setWeightedDrivePower(new Pose2d(0, 0, 0));
    }
}