package CursProgramare;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@Autonomous(name = "simple foward")
public class SimpleForward extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motorLF = hardwareMap.get(DcMotor.class, "motorLF");
        DcMotor motorRF = hardwareMap.get(DcMotor.class, "motorRF");
        DcMotor motorLB = hardwareMap.get(DcMotor.class, "motorLB");
        DcMotor motorRB = hardwareMap.get(DcMotor.class, "motorRB");

        motorRB.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        motorLF.setPower(1);
        motorRF.setPower(1);
        motorLB.setPower(1);
        motorRB.setPower(1);

        sleep(3000);

        motorLF.setPower(0);
        motorRF.setPower(0);
        motorLB.setPower(0);
        motorRB.setPower(0);
    }
}
