package org.firstinspires.ftc.teamcode.newteleop

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.arcrobotics.ftclib.gamepad.ButtonReader
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@Disabled
@TeleOp
class KotlinDrive : LinearOpMode() {
    private lateinit var robot: Robot
    private lateinit var liftLimitButton: ButtonReader

    override fun runOpMode() {
        telemetry = MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().telemetry)
        robot = Robot(hardwareMap, telemetry)
        liftLimitButton = ButtonReader { gamepad1.x || gamepad2.x }

        waitForStart()

        while (!this.isStopRequested && this.isStarted) {
            robot.update()
            liftLimitButton.readValue()

            robot.intake.position = gamepad1.right_trigger

            robot.drive.sniperMode = gamepad1.left_trigger >= 0.2

            robot.drive.driveFieldCentric(
                gamepad1.left_stick_x,
                gamepad1.left_stick_y,
                gamepad1.right_stick_x
            )

            if (liftLimitButton.wasJustPressed()) {
                robot.lift.limitsDisabled = robot.lift.limitsDisabled.not()
                gamepad1.rumble(1.0, 1.0, 20)
                gamepad2.rumble(1.0, 1.0, 20)
            }

            robot.lift.power = if (gamepad1.right_bumper) 1
            else if (gamepad1.left_bumper) -1
            else 0

            telemetry.update()
        }
    }
}