package CursProgramare;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "tank drive")
public class SimpleTankDrive extends LinearOpMode {

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

        while (opModeIsActive()) {
            //left wheels
            motorLB.setPower(-gamepad1.left_stick_y);
            motorLF.setPower(-gamepad1.left_stick_y);
            //right wheels
            motorRB.setPower(-gamepad1.right_stick_y);
            motorRF.setPower(-gamepad1.right_stick_y);
        }
    }
}
