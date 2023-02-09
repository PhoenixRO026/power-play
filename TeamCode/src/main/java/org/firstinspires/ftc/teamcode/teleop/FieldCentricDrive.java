package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "Field Centric Drive")
public class FieldCentricDrive extends LinearOpMode {
    SampleMecanumDrive drive;
    Servo intake;
    DcMotorEx lift;

    GamepadEx pad1;
    GamepadEx pad2;

    boolean sniperMode = false;
    double driveSpeed = 1;

    public static double intakeStart = 0.29;
    public static double intakeEnd = 0.04;
    double intakePos = intakeStart;

    int liftPos = 0;
    int liftTarget = 0;
    boolean liftLimitsDisabled = false;

    public LiftStates liftState = LiftStates.HOLDING;

    public static int topLift = 4080;
    public static int minBreakingVel = 150;

    enum LiftStates {
        ADVANCING,
        BRAKING,
        HOLDING
    }


    @Override
    public void runOpMode() throws InterruptedException {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intake = hardwareMap.get(Servo.class, "intake");

        lift = hardwareMap.get(DcMotorEx.class, "lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);

        pad1 = new GamepadEx(gamepad1);
        pad2 = new GamepadEx(gamepad2);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        liftPos = lift.getCurrentPosition();
        liftTarget = liftPos;
        lift.setTargetPosition(liftTarget);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (!this.isStopRequested() && this.isStarted()) {
            movement();
            moveIntake();
            moveLift();

            telemetry.addData("Lift position", liftPos);
            telemetry.addData("Lift target position", liftTarget);
            telemetry.addData("Lift velocity", lift.getVelocity());
            telemetry.addData("Lift limits disabled", liftLimitsDisabled);
            telemetry.addData("Lift state", liftState);
            telemetry.addData("Lift mode", lift.getMode());
            telemetry.addData("Lift power", lift.getPower());
            telemetry.addData("Intake position", intakePos);
            telemetry.addData("Sniper mode", sniperMode);
            telemetry.update();
        }
    }

    public void moveLift() {
        liftPos = lift.getCurrentPosition();
        lift.setTargetPosition(liftTarget);

        boolean moving = gamepad1.left_bumper || gamepad1.right_bumper || gamepad2.a || gamepad2.y;

        if (moving)
            liftState = LiftStates.ADVANCING;

        switch (liftState) {
            case ADVANCING:
                if (!moving) {
                    liftState = LiftStates.BRAKING;
                } else {
                    lift.setPower(1);
                    if (gamepad1.right_bumper || gamepad2.y) {
                        liftTarget = topLift;
                    } else if (gamepad1.left_bumper || gamepad2.a) {
                        liftTarget = 0;
                    }
                }
                break;
            case BRAKING:
                if (Math.abs(lift.getVelocity()) <= minBreakingVel) {
                    liftState = LiftStates.HOLDING;
                    liftTarget = liftPos;
                } else
                    lift.setPower(0);
                break;
            case HOLDING:
                lift.setPower(0.5);
        }
    }

    public void moveIntake() {
        double intakeMod = intakeEnd - intakeStart;
        intakePos = intakeStart + Math.max(gamepad1.right_trigger, gamepad2.right_trigger) * intakeMod;
        intake.setPosition(intakePos);
    }

    public void movement() {
        sniperMode = gamepad1.left_trigger >= 0.2;

        if (sniperMode)
            driveSpeed = 0.35;
        else
            driveSpeed = 1;

        boolean fieldCentric = !(gamepad1.dpad_left || gamepad1.dpad_right || gamepad1.dpad_up || gamepad1.dpad_down);

        double leftX, leftY, rightX;

        if (pad1.gamepad.dpad_left || pad1.gamepad.dpad_right)
            leftX = btoi(gamepad1.dpad_right) - btoi(gamepad1.dpad_left);
        else
            leftX = gamepad1.left_stick_x;

        if (pad1.gamepad.dpad_down || pad1.gamepad.dpad_up)
            leftY = btoi(gamepad1.dpad_up) - btoi(gamepad1.dpad_down);
        else
            leftY = -gamepad1.left_stick_y;

        rightX = gamepad1.right_stick_x;

        drive.update();

        Vector2d input = new Vector2d(leftY, -leftX);

        if (fieldCentric)
            input = input.rotated(-drive.getPoseEstimate().getHeading());

        drive.setWeightedDrivePower(new Pose2d(
                input.getX() * driveSpeed,
                input.getY() * driveSpeed,
                -rightX * driveSpeed
        ));
    }

    int btoi(boolean bool){
        return bool ? 1 : 0;
    }
}
