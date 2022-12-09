package CursProgramare;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name = "test mecanica")
public class TestMecanica extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "motor");
        Servo servo = hardwareMap.get(Servo.class, "servo");

        waitForStart();

        while (opModeIsActive()) {
            motor.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
            servo.setPosition(gamepad1.left_stick_y);
        }
    }
}
