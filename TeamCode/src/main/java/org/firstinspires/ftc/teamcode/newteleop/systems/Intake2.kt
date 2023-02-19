package org.firstinspires.ftc.teamcode.newteleop.systems

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.newauto.Consts

class Intake2(
    hardwareMap: HardwareMap,
    private val telemetry: Telemetry? = null
) {
    private val servo: Servo = hardwareMap.get(Servo::class.java, "intake2")
    private val servoStart = Consts.intake2Down
    private val servoEnd = Consts.intake2Up
    private var servoMod = servoEnd - servoStart
    private var offset = 0.0
    var position : Number = 0.0
        set(value) {
            val clampedValue = value.toDouble().coerceIn(0.0, 1.0)
            if (clampedValue == field.toDouble())
                return
            val newPos = servoStart + servoMod * clampedValue + offset
            servo.position = newPos
            field = clampedValue
        }

    init {
        position = 0
    }

    fun update() {
        telemetry?.addData("Intake 2 position", position)
        telemetry?.addData("Intake 2 true position", servo.position)
        telemetry?.addData("Intake 2 offset", offset)
    }

    fun increaseOffset() {
        offset += 0.001
        servo.position = servoStart + servoMod * position.toDouble() + offset
    }

    fun decreaseOffset() {
        offset -= 0.001
        servo.position = servoStart + servoMod * position.toDouble() + offset
    }
}
