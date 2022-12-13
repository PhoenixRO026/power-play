package CursProgramare;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.opencv.core.Mat;

@Autonomous(name = "Curs RoadRunner")
public class CursRoadRunner extends LinearOpMode {
    SampleMecanumDrive drive;
    TrajectorySequence trajectorySequence;
    Pose2d start = new Pose2d(0, 0, Math.toRadians(90));

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(start);
        trajectorySequence = drive.trajectorySequenceBuilder(start)
                .setTangent(Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-45, 45, Math.toRadians(0)), Math.toRadians(180))
                .build();

        waitForStart();

        drive.followTrajectorySequenceAsync(trajectorySequence);

        while (opModeIsActive()) {
            drive.update();
        }
    }
}
