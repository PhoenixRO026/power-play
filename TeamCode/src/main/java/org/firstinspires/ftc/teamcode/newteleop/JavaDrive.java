package org.firstinspires.ftc.teamcode.newteleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class JavaDrive extends LinearOpMode {
    Robot robot;
    ButtonReader liftLimitButton;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        robot = new Robot(hardwareMap, telemetry);
        liftLimitButton = new ButtonReader(() -> gamepad1.x || gamepad2.x);

        waitForStart();

        while (!this.isStopRequested() && this.isStarted()) {
            robot.update();
            liftLimitButton.readValue();

            robot.intake.setPosition(gamepad1.right_trigger);

            robot.drive.setSniperMode(gamepad1.left_trigger >= 0.2);

            robot.drive.driveFieldCentric(
                gamepad1.left_stick_x,
                gamepad1.left_stick_y,
                gamepad1.right_stick_x
            );

            if (liftLimitButton.wasJustPressed()) {
                robot.lift.setLimitsDisabled(!robot.lift.getLimitsDisabled());
                gamepad1.rumble(1.0, 1.0, 20);
                gamepad2.rumble(1.0, 1.0, 20);
            }

            if (gamepad1.right_bumper) robot.lift.setPower(1);
            else if (gamepad1.left_bumper) robot.lift.setPower(-1);
            else robot.lift.setPower(0);

            telemetry.update();
        }
    }
}
