package org.firstinspires.ftc.teamcode.newteleop.systems

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.robotcore.external.Telemetry

class Intake(
    hardwareMap: HardwareMap,
    private val telemetry: Telemetry? = null
) {
    private val servo: Servo = hardwareMap.get(Servo::class.java, "intake")
    private val servoStart = 0.29
    private val servoEnd = 0.04
    private var servoMod = servoEnd - servoStart
    var position : Number = 0.0
        set(value) {
            val clampedValue = value.toDouble().coerceIn(0.0, 1.0)
            if (clampedValue == field.toDouble())
                return
            val newPos = servoStart + servoMod * clampedValue
            servo.position = newPos
            field = newPos
            telemetry?.addData("Intake position", newPos)
            telemetry?.addData("Intake true position", servo.position)
        }

    init {
        position = servoStart
    }
}
