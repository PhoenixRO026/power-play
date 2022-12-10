package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {

    double ticksPerRotation = (((1+(46.0/17))) * (1+(46.0/11))) * 28;
    int mmPerRotation = 112;
    DcMotorEx motor;
    public Lift(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "lift");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setHeightMM(double heightMM) {
        int targetPosition = (int) (heightMM * ticksPerRotation / mmPerRotation);
        motor.setTargetPosition(targetPosition);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (motor.isBusy()) {
            motor.setPower(1);
        } else {
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public double getHeightMM() {
        return motor.getCurrentPosition() / ticksPerRotation * mmPerRotation;
    }
}
