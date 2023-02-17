package org.firstinspires.ftc.teamcode.newteleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Intake2Test extends LinearOpMode {
    Servo intake2;
    double intakeDown = 0.08;
    double intakeUp = 0.3;
    double intakeDif = intakeUp - intakeDown;
    double intake2pos = intakeUp;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        intake2 = hardwareMap.get(Servo.class, "intake2");
        intake2.setPosition(intake2pos);

        waitForStart();

        while (opModeIsActive()) {
            /*intake2pos = intakeDown + intakeDif * gamepad1.left_trigger;
            intake2.setPosition(intake2pos);*/

            if (gamepad1.y)
                intake2pos += 0.001;
            else if (gamepad1.a)
                intake2pos -= 0.0001;

            intake2.setPosition(intake2pos);

            telemetry.addData("Intake 2 pos", intake2pos);
            telemetry.update();
        }
    }
}
