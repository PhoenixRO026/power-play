package org.firstinspires.ftc.teamcode.auto;

import static com.example.constants.Constants.autoConeHeight;
import static org.firstinspires.ftc.teamcode.auto.AutoConst.topLiftHeight;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.teleop.SimpleDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Config
@Autonomous(name = "Cycle Right")
public class CycleRight extends LinearOpMode {
    AutoDrive drive;
    TrajectorySequence sequence;
    Lift lift;
    Servo intake;
    double liftHeight = 100;
    double intakePos = SimpleDrive.intakeStart;
    double coneHeight = autoConeHeight;

    enum STATE {
        PRELOAD,
        LOOP_1,
        LOOP_2,
        LOOP_3,
        LOOP_4,
        LOOP_5,
        PARK,
        STOP
    }

    STATE state = STATE.PRELOAD;

    @Override
    public void runOpMode() throws InterruptedException {
        AutoConst consts = new AutoConst();
        drive = new AutoDrive(hardwareMap, consts.rightInit);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        lift = new Lift(hardwareMap);
        intake = hardwareMap.get(Servo.class, "intake");

        TrajectorySequence preload = drive.trajectorySequenceBuilder(consts.rightInit)
                .addTemporalMarker(() -> liftHeight = topLiftHeight)
                .splineTo(consts.right1.vec, consts.right1.HEADING)
                .waitSeconds(0.1)
                .addTemporalMarker(() -> intakePos = SimpleDrive.intakeEnd)
                .waitSeconds(0.1)
                .addTemporalMarker(() -> liftHeight = coneHeight)
                .setTangent(consts.right2.START_TANGENT)
                .splineToLinearHeading(consts.right2.poseHed, consts.right2.END_TANGENT)
                .build();

        TrajectorySequence loop = drive.trajectorySequenceBuilder(consts.right2.poseHed)
                .waitSeconds(0.1)
                .addTemporalMarker(() -> intakePos = SimpleDrive.intakeStart)
                .waitSeconds(0.3)
                .addTemporalMarker(() -> liftHeight = topLiftHeight)
                .waitSeconds(0.1)
                .setTangent(consts.right3.START_TANGENT)
                .splineToLinearHeading(consts.right3.poseHed, consts.right3.END_TANGENT)
                .waitSeconds(0.1)
                .addTemporalMarker(() -> intakePos = SimpleDrive.intakeEnd)
                .waitSeconds(0.1)
                .addTemporalMarker(() -> liftHeight = coneHeight)
                .setTangent(consts.right4.START_TANGENT)
                .splineToLinearHeading(consts.right4.poseHed, consts.right4.END_TANGENT)
                .build();

        TrajectorySequence loop5 = drive.trajectorySequenceBuilder(consts.right2.poseHed)
                .waitSeconds(0.1)
                .addTemporalMarker(() -> intakePos = SimpleDrive.intakeStart)
                .waitSeconds(0.3)
                .addTemporalMarker(() -> liftHeight = topLiftHeight)
                .waitSeconds(0.1)
                .setTangent(consts.right3.START_TANGENT)
                .splineToLinearHeading(consts.right3.poseHed, consts.right3.END_TANGENT)
                .waitSeconds(0.1)
                .addTemporalMarker(() -> intakePos = SimpleDrive.intakeEnd)
                .waitSeconds(0.1)
                .setTangent(consts.rightPark.START_TANGENT)
                .splineToLinearHeading(consts.rightPark.poseHed, consts.rightPark.END_TANGENT)
                .build();

        Trajectory park1 = drive.trajectoryBuilder(consts.rightPark.poseHed)
                .strafeLeft(AutoConst.fieldSize / 6)
                .build();

        Trajectory park3 = drive.trajectoryBuilder(consts.rightPark.poseHed)
                .strafeRight(AutoConst.fieldSize / 6)
                .build();


        /*sequence = drive.trajectorySequenceBuilder(consts.rightInit)
                .addTemporalMarker(() -> liftHeight = topLiftHeight)
                .splineTo(consts.right1.vec, consts.right1.HEADING)
                .waitSeconds(0.25)
                .addTemporalMarker(() -> intakePos = SimpleDrive.intakeEnd)
                .waitSeconds(0.25)
                .addTemporalMarker(() -> liftHeight = AutoConst.autoConeHeight)
                .setTangent(consts.right2.START_TANGENT)
                .splineToLinearHeading(consts.right2.poseTan, consts.right2.HEADING)
                .waitSeconds(0.25)
                .addTemporalMarker(() -> intakePos = SimpleDrive.intakeStart)
                .waitSeconds(0.25)
                .addTemporalMarker(() -> liftHeight = topLiftHeight)
                .waitSeconds(0.25)
                .setTangent(consts.right3.START_TANGENT)
                .splineToLinearHeading(consts.right3.poseTan, consts.right3.HEADING)
                .waitSeconds(0.25)
                .addTemporalMarker(() -> intakePos = SimpleDrive.intakeEnd)
                .waitSeconds(0.25)
                .setTangent(consts.right4.START_TANGENT)
                .splineToLinearHeading(consts.right4.poseTan, consts.right4.HEADING)
                .build();*/

        telemetry.addLine("ready");
        telemetry.update();

        int result = 13;
        int tempResult;

        while (opModeInInit()) {
            tempResult = drive.getResult();
            if (tempResult != -1)
                result = tempResult;
            telemetry.addData("Detection", result);
            telemetry.update();
            sleep(20);
        }

        drive.followTrajectorySequenceAsync(preload);

        while (opModeIsActive()) {
            drive.update();
            lift.setHeightMM(liftHeight);
            intake.setPosition(intakePos);
            switch (state) {
                case PRELOAD:
                    if (!drive.isBusy()) {
                        coneHeight -= AutoConst.coneDecrease;
                        state = STATE.LOOP_5;
                        drive.setPoseEstimate(consts.right2.poseHed);
                        drive.followTrajectorySequenceAsync(loop5);
                    }
                    break;
                case LOOP_1:
                    if (!drive.isBusy()) {
                        coneHeight -= AutoConst.coneDecrease;
                        state = STATE.LOOP_2;
                        drive.setPoseEstimate(consts.right2.poseHed);
                        drive.followTrajectorySequenceAsync(loop);
                    }
                    break;
                case LOOP_2:
                    if (!drive.isBusy()) {
                        coneHeight -= AutoConst.coneDecrease;
                        state = STATE.LOOP_3;
                        drive.setPoseEstimate(consts.right2.poseHed);
                        drive.followTrajectorySequenceAsync(loop);
                    }
                    break;
                case LOOP_3:
                    if (!drive.isBusy()) {
                        coneHeight -= AutoConst.coneDecrease;
                        state = STATE.LOOP_5;
                        drive.setPoseEstimate(consts.right2.poseHed);
                        drive.followTrajectorySequenceAsync(loop5);
                    }
                    break;
                case LOOP_4:
                    if (!drive.isBusy()) {
                        state = STATE.LOOP_5;
                        drive.setPoseEstimate(consts.right2.poseHed);
                        drive.followTrajectorySequenceAsync(loop5);
                    }
                    break;
                case LOOP_5:
                    if (!drive.isBusy()) {
                        state = STATE.PARK;
                        switch (result) {
                            case 13:
                                drive.followTrajectoryAsync(park1);
                                break;
                            case 15:
                                drive.followTrajectory(park3);
                                break;
                        }
                    }
                    break;
                case PARK:
                    if (!drive.isBusy()) {
                        state = STATE.PARK;
                    }
                    break;
                case STOP:

            }
            telemetry.addData("Lift height MM", lift.getHeightMM());
            telemetry.addData("state", state);
            telemetry.update();
        }
    }
}
