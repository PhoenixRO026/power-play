package org.firstinspires.ftc.teamcode.newteleop

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.arcrobotics.ftclib.gamepad.ButtonReader
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlin.math.max

@TeleOp
class KotlinDrive : LinearOpMode() {
    private lateinit var robot: Robot
    private lateinit var liftLimitButton: ButtonReader
    private lateinit var resetHeadingButton: ButtonReader
    private lateinit var dpadUsed: ButtonReader

    override fun runOpMode() {
        telemetry = MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().telemetry)
        robot = Robot(hardwareMap, telemetry)
        liftLimitButton = ButtonReader { gamepad1.x || gamepad2.x }
        resetHeadingButton = ButtonReader { gamepad1.b }
        dpadUsed = ButtonReader { gamepad1.dpad_up || gamepad1.dpad_down || gamepad1.dpad_left || gamepad1.dpad_right }

        waitForStart()

        while (!this.isStopRequested && this.isStarted) {
            robot.update()
            liftLimitButton.readValue()
            resetHeadingButton.readValue()

            robot.intake.position = max(gamepad1.right_trigger, gamepad2.right_trigger)

            robot.drive.sniperMode = gamepad1.left_trigger >= 0.2

            if (resetHeadingButton.wasJustPressed()) {
                robot.drive.resetFieldCentric()
                gamepad1.rumble(1.0, 1.0, 300)
            }

            if (dpadUsed.isDown)
                robot.drive.driveRobotCentric(
                    gamepad1.dpad_right - gamepad1.dpad_left,
                    gamepad1.dpad_up - gamepad1.dpad_down,
                    gamepad1.right_stick_x
                )
            else
                robot.drive.driveFieldCentric(
                    gamepad1.left_stick_x,
                    -gamepad1.left_stick_y,
                    gamepad1.right_stick_x
                )

            if (liftLimitButton.wasJustPressed()) {
                robot.lift.limitsDisabled = robot.lift.limitsDisabled.not()
                gamepad1.rumble(1.0, 1.0, 300)
                gamepad2.rumble(1.0, 1.0, 300)
            }

            robot.lift.power = if (gamepad1.right_bumper || gamepad2.y) 1
            else if (gamepad1.left_bumper || gamepad2.a) -1
            else 0

            telemetry.update()
        }
    }

    private operator fun Boolean.minus(other: Boolean) = if (this) 1.0 else 0.0 + if (other) -1.0 else 0.0
}