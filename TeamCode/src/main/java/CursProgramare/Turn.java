package CursProgramare;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

@Autonomous(name = "turn")
public class Turn extends LinearOpMode {
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

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        motorRB.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        float first = AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle);

        motorLF.setPower(-0.2);
        motorRF.setPower(0.2);
        motorLB.setPower(-0.2);
        motorRB.setPower(0.2);

        sleep(200);

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        float last = AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle);
        while (last < first + 90) {
            sleep(50);
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            last = AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle);
        }

        setAllPowers(0, 0, 0, 0);
    }

    public void setAllPowers(double LF, double RF, double LB, double RB) {
        motorLF.setPower(LF);
        motorRF.setPower(RF);
        motorLB.setPower(LB);
        motorRB.setPower(RB);
    }
    /*public void setDirection(double dir) {
        motorLF.setPower();
        motorRF.setPower();
        motorLB.setPower();
        motorRB.setPower();
    }*/
}
