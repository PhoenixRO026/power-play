package org.firstinspires.ftc.teamcode.newteleop

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.arcrobotics.ftclib.gamepad.ButtonReader
import com.arcrobotics.ftclib.gamepad.GamepadEx
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@Disabled
@TeleOp
class KotlinDrive : LinearOpMode() {
    lateinit var robot: Robot
    lateinit var pad1: GamepadEx
    lateinit var pad2: GamepadEx

    override fun runOpMode() {
        telemetry = MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().telemetry)
        robot = Robot(hardwareMap, telemetry)
        pad1 = GamepadEx(gamepad1)
        pad2 = GamepadEx(gamepad2)

        waitForStart()

        while (!this.isStopRequested && this.isStarted) {
            robot.update()
            pad1.readButtons()

            robot.intake.position = gamepad1.right_trigger

            robot.drive.driveFieldCentric(
                gamepad1.left_stick_x,
                gamepad1.left_stick_y,
                gamepad1.right_stick_x
            )

            if (pad1.stateJustChanged(GamepadKeys.Button.X)) {
                robot.lift.limitsDisabled = robot.lift.limitsDisabled.not()
                gamepad1.rumble(1.0, 1.0, 20)
            }

            if (pad2.stateJustChanged(GamepadKeys.Button.X)) {
                robot.lift.limitsDisabled = robot.lift.limitsDisabled.not()
                gamepad2.rumble(1.0, 1.0, 20)
            }

            robot.lift.power = if (gamepad1.right_bumper) 1
            else if (gamepad1.left_bumper) -1
            else 0

            telemetry.update()
        }
    }

    private fun Boolean.flip() {

    }
}