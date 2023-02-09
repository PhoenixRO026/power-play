package org.firstinspires.ftc.teamcode.newteleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class JavaDrive extends LinearOpMode {
    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        robot = new Robot(hardwareMap, telemetry);

        waitForStart();

        while (!this.isStopRequested() && this.isStarted()) {
            robot.update();

            robot.intake.setPosition(gamepad1.right_trigger);

            robot.drive.driveFieldCentric(
                gamepad1.left_stick_x,
                gamepad1.left_stick_y,
                gamepad1.right_stick_x
            );

            if (gamepad1.right_bumper)
                robot.lift.setPower(1);
            else if (gamepad1.left_bumper)
                robot.lift.setPower(-1);
            else robot.lift.setPower(0);

            telemetry.update();
        }
    }
}
