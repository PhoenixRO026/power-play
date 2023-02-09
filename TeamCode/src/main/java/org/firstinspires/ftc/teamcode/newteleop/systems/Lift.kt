package org.firstinspires.ftc.teamcode.newteleop.systems

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import kotlin.math.abs

class Lift(
    hardwareMap: HardwareMap,
    private val telemetry: Telemetry? = null
) {
    private val motor = hardwareMap.get(DcMotorEx::class.java, "lift")
    private val liftBottom = 50
    private val liftTop = 4080
    private val holdPower = 0.5
    private enum class LiftState {
        ADVANCING,
        BRAKING,
        HOLDING
    }
    private var state = LiftState.HOLDING
    private val minBrakeVelocity = 150
    var power: Number = 0.0
        set(value) {
            val clampedVal = value.toDouble().coerceIn(-1.0, 1.0)
            if (clampedVal != field) {
                if (field == 0.0) {
                    state = LiftState.BRAKING
                    motor.power = 0.0
                } else {
                    state = LiftState.ADVANCING
                    if (clampedVal > 0.0)
                        motor.targetPosition = liftTop
                    else
                        motor.targetPosition = liftBottom
                    motor.power = abs(clampedVal)
                }
                telemetry?.addData("Lift power", clampedVal)
                field = clampedVal
            }
        }

    init {
        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        motor.direction = DcMotorSimple.Direction.REVERSE
        motor.targetPosition = 0
        motor.mode = DcMotor.RunMode.RUN_TO_POSITION
        motor.power = holdPower
    }

    fun update() {
        if (state == LiftState.BRAKING && motor.velocity <= minBrakeVelocity) {
            state = LiftState.HOLDING
            motor.targetPosition = motor.currentPosition
            motor.power = holdPower
        }
        telemetry?.addData("Lift position", motor.currentPosition)
        telemetry?.addData("Lift target position", motor.targetPosition)
        telemetry?.addData("Lift velocity", motor.velocity)
        telemetry?.addData("Lift state", state)
        telemetry?.addData("Lift mode", motor.mode)
        telemetry?.addData("Lift motor power", motor.power)
    }
}