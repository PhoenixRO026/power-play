package org.firstinspires.ftc.teamcode.newteleop.systems

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive

class Drive(hardwareMap: HardwareMap) {
    private val drive = SampleMecanumDrive(hardwareMap)
    private val heading
        get() = drive.poseEstimate.heading
    private var speed = 1.0
    var sniperSpeed = 0.35
    var sniperMode = false
        set(value) {
            if (value == field)
                return
            speed = if (value) sniperSpeed else 1.0
            field = value
        }

    init {
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER)
        drive.poseEstimate
    }

    fun update() = drive.update()

    fun resetFieldCentric() {
        drive.poseEstimate = Pose2d(drive.poseEstimate.vec(), 0.0)
    }

    private fun driveRobotCentric(drivePower: Pose2d) = drive.setWeightedDrivePower(Pose2d(drivePower.vec().rotated(Math.toRadians(-90.0)), -drivePower.heading).times(speed))
    fun driveRobotCentric(x: Number, y: Number, rotation: Number) = driveRobotCentric(Pose2d(x.toDouble(), y.toDouble(), rotation.toDouble()))
    private fun driveFieldCentric(drivePower: Pose2d) = driveRobotCentric(Pose2d(drivePower.vec().rotated(-heading), drivePower.heading))
    private fun driveFieldCentric(x: Double, y: Double, rotation: Double) = driveFieldCentric(Pose2d(x, y, rotation))
    fun driveFieldCentric(x: Number, y: Number, rotation: Number) = driveFieldCentric(x.toDouble(), y.toDouble(), rotation.toDouble())
}