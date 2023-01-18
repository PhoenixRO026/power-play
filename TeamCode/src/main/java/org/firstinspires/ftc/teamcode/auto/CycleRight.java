package org.firstinspires.ftc.teamcode.auto;

import static org.firstinspires.ftc.teamcode.auto.AutoConst.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
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
        drive = new AutoDrive(hardwareMap, new Pose2d(consts.rightInit.X, consts.rightInit.Y, consts.rightInit.HEADING));
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        lift = new Lift(hardwareMap);

        sequence = drive.trajectorySequenceBuilder(new Pose2d(consts.rightInit.X, consts.rightInit.Y, consts.rightInit.HEADING))
                .addTemporalMarker(() -> liftHeight = topLiftHeight)
                .splineTo(new Vector2d(consts.right1.X, consts.right1.Y), consts.right1.HEADING)
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
