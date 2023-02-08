package org.firstinspires.ftc.teamcode.newteleop

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.newteleop.systems.Drive
import org.firstinspires.ftc.teamcode.newteleop.systems.Intake

class Robot(
    hardwareMap: HardwareMap,
    telemetry: Telemetry? = null
) {
    val intake = Intake(hardwareMap, telemetry)
    val drive = Drive(hardwareMap)

    fun update() {
        drive.update()
    }
}