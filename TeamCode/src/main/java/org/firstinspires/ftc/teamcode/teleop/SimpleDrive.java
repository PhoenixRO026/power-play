package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "Simple Drive")
public class SimpleDrive extends LinearOpMode {
    SampleMecanumDrive drive;
    Servo intake;
    //DcMotorServo lift;
    DcMotorEx lift;

    boolean sniperMode = false;
    boolean ultraSniperMode = false;
    double driveSpeed = 1;

    /*double timeStep = 0.1;
    float liftAngle = 0;
    double liftLastTime = 0;
    float liftStep = 100;*/
    int liftPos = 0;
    int liftStopPos = 0;
    boolean liftRunning = false;

    public static double intakeStart = 0.29;
    public static double intakeEnd = 0.04;
    double intakeMod = intakeEnd - intakeStart;
    double intakePos = 0;
    boolean limitsDisabled;



    @Override
    public void runOpMode() throws InterruptedException {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //lift = new DcMotorServo(hardwareMap,"lift",19.203208f,28);
        lift = hardwareMap.get(DcMotorEx.class, "lift" );
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        intake = hardwareMap.get(Servo.class, "intake");

        waitForStart();

        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive()) {
            movement();
            intakePos = intakeStart + gamepad2.right_trigger * intakeMod;
            intake.setPosition(intakePos);

            liftPos = lift.getCurrentPosition();
            limitsDisabled = gamepad2.left_trigger >= 0.2;
            if (gamepad2.y && (liftPos <= 4028 || limitsDisabled)) {
                lift.setPower(1);
                lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                liftRunning = true;
            } else if (gamepad2.a && (liftPos >= 0 || limitsDisabled)) {
                lift.setPower(-1);
                lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                liftRunning = true;
            } else if (gamepad2.x) {
                lift.setPower(1);
                lift.setTargetPosition(0);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftRunning = true;
            } else if (!lift.isBusy() || lift.getMode() == DcMotor.RunMode.RUN_USING_ENCODER) {
                if (liftRunning) {
                    liftStopPos = liftPos;
                    liftRunning = false;
                }
                lift.setPower(0.01);
                lift.setTargetPosition(liftStopPos);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }


            /*lift.setAngle(liftAngle, 1);
            if (time > liftLastTime + timeStep) {
                if (gamepad1.right_bumper) {
                    liftLastTime = time;
                    liftAngle += liftStep;
                } else if (gamepad1.left_bumper) {
                    liftLastTime = time;
                    if (liftAngle > 0)
                        liftAngle -= liftStep;
                }
            }*/

            telemetry.addData("Mode", lift.getMode());
            telemetry.addData("Lift Power", lift.getPower());
            telemetry.addData("Is busy", lift.isBusy());
            telemetry.addData("Target pos", lift.getTargetPosition());
            telemetry.addData("Intake pos", intakePos);
            telemetry.addData("Lift pos", liftPos);
            telemetry.update();
        }
    }

    void movement() {
        sniperMode = gamepad1.right_trigger >= 0.2;

        ultraSniperMode = gamepad1.left_trigger >= 0.2;

        if(sniperMode){
            driveSpeed = 0.5;
        }else driveSpeed = 1;

        if(ultraSniperMode)
            driveSpeed = 0.25;

        double leftX, leftY, rightX;

        if(gamepad1.dpad_left || gamepad1.dpad_right)
            leftX = -btoi(gamepad1.dpad_left) + btoi(gamepad1.dpad_right);
        else leftX = gamepad1.left_stick_x;

        if(gamepad1.dpad_down || gamepad1.dpad_up)
            leftY = -btoi(gamepad1.dpad_down) + btoi(gamepad1.dpad_up);
        else leftY = -gamepad1.left_stick_y;

        if(gamepad1.x || gamepad1.b)
            rightX = -btoi(gamepad1.x) + btoi(gamepad1.b);
        else rightX = gamepad1.right_stick_x;

        drive.update();

        //Pose2d poseEstimate = drive.getPoseEstimate();

        Vector2d input = new Vector2d(
                leftY,
                -leftX
        );

        drive.setWeightedDrivePower(
                new Pose2d(
                        input.getX() * driveSpeed,  //left_stick_y
                        input.getY() * driveSpeed,  //left_stick_x
                        -rightX * driveSpeed  //right_stick_x
                )
        );
    }

    int btoi(boolean bool){
        return bool ? 1 : 0;
    }
}
