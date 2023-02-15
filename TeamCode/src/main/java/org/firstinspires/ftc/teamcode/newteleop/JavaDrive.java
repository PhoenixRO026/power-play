package org.firstinspires.ftc.teamcode.newteleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Java Drive")
public class JavaDrive extends LinearOpMode {
    Robot robot;
    ButtonReader liftLimitButton;
    ButtonReader resetHeadingButton;
    ButtonReader dpadUsed;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        robot = new Robot(hardwareMap, telemetry);
        liftLimitButton = new ButtonReader(() -> gamepad1.x || gamepad2.x);
        resetHeadingButton = new ButtonReader(() -> gamepad1.b);
        dpadUsed = new ButtonReader(() -> gamepad1.dpad_up || gamepad1.dpad_down || gamepad1.dpad_left || gamepad1.dpad_right);

        waitForStart();

        while (!this.isStopRequested() && this.isStarted()) {
            robot.update();
            liftLimitButton.readValue();
            resetHeadingButton.readValue();

            robot.intake.setPosition(Math.max(gamepad1.right_trigger, gamepad2.right_trigger));

            robot.drive.setSniperMode(gamepad1.left_trigger >= 0.2);

            if (resetHeadingButton.wasJustPressed()) {
                robot.drive.resetFieldCentric();
                gamepad1.rumble(1.0, 1.0, 300);
            }

            if (dpadUsed.isDown())
                robot.drive.driveRobotCentric(
                        btoi(gamepad1.dpad_right) - btoi(gamepad1.dpad_down),
                        btoi(gamepad1.dpad_up) - btoi(gamepad1.dpad_down),
                        gamepad1.right_stick_x
                );
            else
                robot.drive.driveFieldCentric(
                    gamepad1.left_stick_x,
                    -gamepad1.left_stick_y,
                    gamepad1.right_stick_x
                );

            if (liftLimitButton.wasJustPressed()) {
                robot.lift.setLimitsDisabled(!robot.lift.getLimitsDisabled());
                gamepad1.rumble(1.0, 1.0, 300);
                gamepad2.rumble(1.0, 1.0, 300);
            }

            if (gamepad1.right_bumper) robot.lift.setPower(1);
            else if (gamepad1.left_bumper) robot.lift.setPower(-1);
            else robot.lift.setPower(0);

            telemetry.update();
        }
    }

    private int btoi(boolean bool) {
        if (bool)
            return 1;
        else
            return 0;
    }
}
