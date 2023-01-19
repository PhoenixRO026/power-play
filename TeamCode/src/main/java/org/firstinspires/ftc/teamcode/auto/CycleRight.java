package org.firstinspires.ftc.teamcode.auto;

import static org.firstinspires.ftc.teamcode.auto.AutoConst.topLiftHeight;

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
        AutoConst consts = new AutoConst();
        drive = new AutoDrive(hardwareMap, consts.rightInit);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        lift = new Lift(hardwareMap);

        sequence = drive.trajectorySequenceBuilder(consts.rightInit)
                .addTemporalMarker(() -> liftHeight = topLiftHeight)
                .splineTo(consts.right1.vec, consts.right1.HEADING)
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
