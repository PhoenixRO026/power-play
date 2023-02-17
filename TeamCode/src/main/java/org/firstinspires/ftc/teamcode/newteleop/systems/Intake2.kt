package org.firstinspires.ftc.teamcode.newteleop.systems

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.robotcore.external.Telemetry

class Intake2(
    hardwareMap: HardwareMap,
    private val telemetry: Telemetry? = null
) {
    private val servo: Servo = hardwareMap.get(Servo::class.java, "intake2")
    private val servoStart = 0.018
    private val servoEnd = 0.216
    private var servoMod = servoEnd - servoStart
    var position : Number = 0.0
        set(value) {
            val clampedValue = value.toDouble().coerceIn(0.0, 1.0)
            if (clampedValue == field.toDouble())
                return
            val newPos = servoStart + servoMod * clampedValue
            servo.position = newPos
            field = clampedValue
        }

    init {
        position = 0
    }

    fun update() {
        telemetry?.addData("Intake 2 position", position)
        telemetry?.addData("Intake 2 true position", servo.position)
    }
}
