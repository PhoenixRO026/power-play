package CursProgramare;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@Autonomous(name = "amogus")
public class amogus extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motorLF = hardwareMap.get(DcMotor.class, "motorLF");
        DcMotor motorRF = hardwareMap.get(DcMotor.class, "motorRF");
        DcMotor motorLB = hardwareMap.get(DcMotor.class, "motorLB");
        DcMotor motorRB = hardwareMap.get(DcMotor.class, "motorRB");

        motorRB.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        motorLF.setPower(0.2);
        motorRF.setPower(0.2);
        motorLB.setPower(0.2);
        motorRB.setPower(0.2);

        sleep(1500);

        motorLF.setPower(0);
        motorRF.setPower(0);
        motorLB.setPower(0);
        motorRB.setPower(0);

        sleep(500);

        motorLF.setPower(-0.2);
        motorRF.setPower(0.2);
        motorLB.setPower(0.2);
        motorRB.setPower(-0.2);

        sleep(1500);

        motorLF.setPower(0);
        motorRF.setPower(0);
        motorLB.setPower(0);
        motorRB.setPower(0);

        sleep(500);

        motorLF.setPower(-0.2);
        motorRF.setPower(-0.2);
        motorLB.setPower(-0.2);
        motorRB.setPower(-0.2);

        sleep(1500);

        motorLF.setPower(0);
        motorRF.setPower(0);
        motorLB.setPower(0);
        motorRB.setPower(0);

        sleep(500);

        motorLF.setPower(0.2);
        motorRF.setPower(-0.2);
        motorLB.setPower(-0.2);
        motorRB.setPower(0.2);

        sleep(1500);

        motorLF.setPower(0);
        motorRF.setPower(0);
        motorLB.setPower(0);
        motorRB.setPower(0);
    }
}
