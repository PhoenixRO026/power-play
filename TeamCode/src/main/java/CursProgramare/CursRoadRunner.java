package CursProgramare;

import static org.firstinspires.ftc.teamcode.drive.DriveConstants.MAX_ACCEL;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.MAX_ANG_VEL;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.MAX_VEL;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.TRACK_WIDTH;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.auto.Lift;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.teleop.SimpleDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Disabled
@Autonomous(name = "Curs RoadRunner")
public class CursRoadRunner extends LinearOpMode {
    SampleMecanumDrive drive;
    TrajectorySequence trajectorySequence;
    Pose2d start = new Pose2d(-36, -63.15, Math.toRadians(90));
    Lift lift;
    Servo intake;
    double intakePos = SimpleDrive.intakeStart;
    double liftPos = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(start);
        lift = new Lift(hardwareMap);
        intake = hardwareMap.get(Servo.class, "intake");
        intake.setPosition(intakePos);

        trajectorySequence = drive.trajectorySequenceBuilder(start)
                .setTangent(Math.toRadians(10))
                .addTemporalMarker(() -> liftPos = 820)
                .splineToConstantHeading(new Vector2d(-10.2, -46.56), Math.toRadians(90))
                .splineTo(new Vector2d(-10, -20), Math.toRadians(90))
                .splineTo(
                        new Vector2d(-24 + 5.985351, 0 - 5.985351),
                        Math.toRadians(135),
                        SampleMecanumDrive.getVelocityConstraint(MAX_VEL / 2, MAX_ANG_VEL, TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(MAX_ACCEL)
                )
                .addTemporalMarker(() -> intakePos = SimpleDrive.intakeEnd)
                .waitSeconds(1)
                .back(10)
                .build();

        waitForStart();

        drive.followTrajectorySequenceAsync(trajectorySequence);

        while (opModeIsActive()) {
            drive.update();
            intake.setPosition(intakePos);
            lift.setHeightMM(liftPos);
        }
    }
}
