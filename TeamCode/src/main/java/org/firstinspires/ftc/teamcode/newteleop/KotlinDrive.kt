package org.firstinspires.ftc.teamcode.newteleop

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@Disabled
@TeleOp
class KotlinDrive : LinearOpMode() {
    lateinit var robot: Robot

    override fun runOpMode() {
        telemetry = MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().telemetry)
        robot = Robot(hardwareMap, telemetry)

        waitForStart()

        while (!this.isStopRequested && this.isStarted) {
            robot.update()
            robot.intake.position = gamepad1.right_trigger
            robot.drive.driveFieldCentric(
                gamepad1.left_stick_x,
                gamepad1.left_stick_y,
                gamepad1.right_stick_x
            )
            robot.lift.power = if (gamepad1.right_bumper)
                1
            else if (gamepad1.left_bumper)
                -1
            else
                0
            telemetry.update()
        }
    }
}