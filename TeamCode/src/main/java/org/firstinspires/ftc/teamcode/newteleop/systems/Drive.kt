package org.firstinspires.ftc.teamcode.newteleop.systems

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive

class Drive(hardwareMap: HardwareMap) {
    private val drive = SampleMecanumDrive(hardwareMap)
    val heading
        get() = drive.poseEstimate.heading

    init {
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER)
    }

    fun update() = drive.update()
}