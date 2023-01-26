package CursProgramare

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx

@TeleOp(name = "Kotlin Test")
class KotlinMotorTest: LinearOpMode() {
    override fun runOpMode() {
        val motor = hardwareMap.get(DcMotorEx::class.java, "motor")

        waitForStart()

        while (opModeIsActive())
            motor.power = (gamepad1.right_trigger - gamepad1.left_trigger).toDouble()
    }
}