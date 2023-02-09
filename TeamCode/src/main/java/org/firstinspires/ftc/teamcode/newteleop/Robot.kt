package org.firstinspires.ftc.teamcode.newteleop

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.newteleop.systems.Drive
import org.firstinspires.ftc.teamcode.newteleop.systems.Intake
import org.firstinspires.ftc.teamcode.newteleop.systems.Lift

class Robot @JvmOverloads constructor(
    hardwareMap: HardwareMap,
    private val telemetry: Telemetry? = null
) {
    @JvmField val intake = Intake(hardwareMap, telemetry)
    @JvmField val drive = Drive(hardwareMap)
    @JvmField val lift = Lift(hardwareMap, telemetry)

    private var oldTime = System.currentTimeMillis()
    private var newTime = System.currentTimeMillis()

    fun update() {
        newTime = System.currentTimeMillis()
        telemetry?.addData("FPS", 1.0 / (newTime - oldTime) * 1000)
        oldTime = newTime
        drive.update()
        lift.update()
    }
}