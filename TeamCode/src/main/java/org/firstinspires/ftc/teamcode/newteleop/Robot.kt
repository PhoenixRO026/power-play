package org.firstinspires.ftc.teamcode.newteleop

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.newteleop.systems.Drive
import org.firstinspires.ftc.teamcode.newteleop.systems.Intake
import org.firstinspires.ftc.teamcode.newteleop.systems.Lift

class Robot @JvmOverloads constructor(
    hardwareMap: HardwareMap,
    telemetry: Telemetry? = null
) {
    @JvmField val intake = Intake(hardwareMap, telemetry)
    @JvmField val drive = Drive(hardwareMap)
    @JvmField val lift = Lift(hardwareMap, telemetry)

    fun update() {
        drive.update()
        lift.update()
    }
}