package CursProgramare;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp(name = "drive dpad & bumpers")
public class DriveDPadAndBumpers extends LinearOpMode {
    DcMotor motorLF;
    DcMotor motorRF;
    DcMotor motorLB;
    DcMotor motorRB;

    @Override
    public void runOpMode() throws InterruptedException {
        motorLF = hardwareMap.get(DcMotor.class, "motorLF");
        motorRF = hardwareMap.get(DcMotor.class, "motorRF");
        motorLB = hardwareMap.get(DcMotor.class, "motorLB");
        motorRB = hardwareMap.get(DcMotor.class, "motorRB");

        motorRB.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive())  {
            if (gamepad1.dpad_up) {
                motorLB.setPower(1);
                motorLF.setPower(1);
                motorRB.setPower(1);
                motorRF.setPower(1);
            } else if (gamepad1.dpad_down) {
                motorLB.setPower(-1);
                motorLF.setPower(-1);
                motorRB.setPower(-1);
                motorRF.setPower(-1);
            } else if (gamepad1.dpad_right) {
                motorLB.setPower(-1);
                motorLF.setPower(1);
                motorRB.setPower(1);
                motorRF.setPower(-1);
            } else if (gamepad1.dpad_left) {
                motorLB.setPower(1);
                motorLF.setPower(-1);
                motorRB.setPower(-1);
                motorRF.setPower(1);
            } else if (gamepad1.left_bumper) {
                motorLB.setPower(-1);
                motorLF.setPower(-1);
                motorRB.setPower(1);
                motorRF.setPower(1);
            } else if (gamepad1.right_bumper) {
                motorLB.setPower(1);
                motorLF.setPower(1);
                motorRB.setPower(-1);
                motorRF.setPower(-1);
            } else {
                motorLB.setPower(0);
                motorLF.setPower(0);
                motorRB.setPower(0);
                motorRF.setPower(0);
            }
        }
    }
}
