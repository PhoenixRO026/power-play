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
            robot.intake.position = 1.0
        }
    }
}