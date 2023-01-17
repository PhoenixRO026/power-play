package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Config
@Autonomous(name = "Cycle Right")
public class CycleRight extends LinearOpMode {
    AutoDrive drive;
    TrajectorySequence sequence;
    Lift lift;
    double liftHeight = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new AutoDrive(hardwareMap, AutoConst.rightPoseInit);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        lift = new Lift(hardwareMap);

        sequence = drive.trajectorySequenceBuilder(AutoConst.rightPoseInit)
                .addTemporalMarker(() -> liftHeight = AutoConst.topLiftHeight)
                .splineTo(AutoConst.rightVector1, AutoConst.rightHeading1)
                .build();

        telemetry.addLine("ready");
        telemetry.update();

        waitForStart();

        drive.followTrajectorySequenceAsync(sequence);

        while (opModeIsActive()) {
            drive.update();
            lift.setHeightMM(liftHeight);
            telemetry.addData("Lift height MM", lift.getHeightMM());
            telemetry.update();
        }
    }
}