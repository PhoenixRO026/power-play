package org.firstinspires.ftc.teamcode.util

import com.acmerobotics.roadrunner.DualNum
import com.acmerobotics.roadrunner.Time
import kotlin.math.sign
import kotlin.math.withSign


data class CustomMotorFeedfoward @JvmOverloads constructor(
        @JvmField
        val kS: Double,
        @JvmField
        val kV: Double,
        @JvmField
        val kA: Double,
        @JvmField
        val kSDecel: Double = kS,
        @JvmField
        val kVDecel: Double = kV,
        @JvmField
        val kADecel: Double = kA,
) {
    fun compute(vel: Double, accel: Double): Double {
        val decelerating = accel != 0.0 && sign(vel) != sign(accel)
        return if (decelerating)
            kSDecel.withSign(vel) + kVDecel * vel + kADecel * accel
        else
            kS.withSign(vel) + kV * vel + kA * accel
    }

    fun compute(vel: DualNum<Time>) = compute(vel[0], vel[1])
}