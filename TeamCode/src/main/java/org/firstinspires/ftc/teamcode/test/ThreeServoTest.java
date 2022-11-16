package org.firstinspires.ftc.teamcode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "3 Servo Test")
public class ThreeServoTest extends LinearOpMode {
    Servo servo1;
    Servo servo2;
    Servo servo3;

    int activeServo = 1;

    double servoPosition1 = 0;
    double servoPosition2 = 0;
    double servoPosition3 = 0;

    double waitTime = 0;

    double addedPercentage = 0.02;

    int addMultiplier = 0;

    boolean holdingA = false;
    boolean holdingB = false;

    @Override
    public void runOpMode() throws InterruptedException {
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        while(opModeIsActive()) {
            servo1.setPosition(servoPosition1);
            servo2.setPosition(servoPosition2);
            servo3.setPosition(servoPosition3);

            switch (activeServo) {
                case 1:
                    servoPosition1 = (gamepad1.right_trigger - gamepad1.left_trigger) * 0.5 + 0.5;
                    break;
                case 2:
                    servoPosition2 = (gamepad1.right_trigger - gamepad1.left_trigger) * 0.5 + 0.5;
                    break;
                case 3:
                    servoPosition3 = (gamepad1.right_trigger - gamepad1.left_trigger) * 0.5 + 0.5;
                    break;
                default:
            }

            /*if (time > waitTime + 0.05) {
                waitTime = time;
                addMultiplier = 0;

                if (gamepad1.dpad_down)
                    addMultiplier = -1;
                if (gamepad1.dpad_up)
                    addMultiplier = 1;

                switch (activeServo) {
                    case 1:
                        if (servoPosition1 < 1 - addedPercentage)
                            servoPosition1 += addMultiplier * addedPercentage;
                        break;
                    case 2:
                        if (servoPosition2 < 1 - addedPercentage)
                            servoPosition2 += addMultiplier * addedPercentage;
                        break;
                    default:
                }
            }*/

            if (gamepad1.a) {
                if (!holdingA) {
                    holdingA = true;
                    if (activeServo < 3)
                        activeServo++;
                }
            } else
                holdingA = false;

            if (gamepad1.b) {
                if (!holdingB) {
                    holdingB = true;
                    if (activeServo > 1)
                        activeServo--;
                }
            } else
                holdingB = false;

            telemetry.addData("active servo", activeServo);
            telemetry.addData("servo position 1", servoPosition1);
            telemetry.addData("servo position 2", servoPosition2);
            telemetry.addData("servo position 3", servoPosition3);
            telemetry.update();
        }
    }
}
