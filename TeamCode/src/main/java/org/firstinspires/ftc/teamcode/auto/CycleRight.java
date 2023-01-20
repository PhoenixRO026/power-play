package org.firstinspires.ftc.teamcode.auto;

import static org.firstinspires.ftc.teamcode.auto.AutoConst.topLiftHeight;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
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

    @Override
    public void runOpMode() throws InterruptedException {
        AutoConst consts = new AutoConst();
        drive = new AutoDrive(hardwareMap, consts.rightInit);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        lift = new Lift(hardwareMap);
        intake = hardwareMap.get(Servo.class, "intake");

        sequence = drive.trajectorySequenceBuilder(consts.rightInit)
                .addTemporalMarker(() -> liftHeight = topLiftHeight)
                .splineTo(consts.right1.vec, consts.right1.HEADING)
                .waitSeconds(0.25)
                .addTemporalMarker(() -> intakePos = SimpleDrive.intakeEnd)
                .waitSeconds(0.25)
                .addTemporalMarker(() -> liftHeight = 200)
                .setTangent(consts.right2.START_TANGENT)
                .splineToLinearHeading(consts.right2.poseTan, consts.right2.HEADING)
                .build();

        telemetry.addLine("ready");
        telemetry.update();

        waitForStart();

        drive.followTrajectorySequenceAsync(sequence);

        while (opModeIsActive()) {
            drive.update();
            lift.setHeightMM(liftHeight);
            intake.setPosition(intakePos);
            telemetry.addData("Lift height MM", lift.getHeightMM());
            telemetry.update();
        }
    }
}
