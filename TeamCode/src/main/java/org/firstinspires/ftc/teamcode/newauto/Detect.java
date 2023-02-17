package org.firstinspires.ftc.teamcode.newauto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.test.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;

public class Detect {
    private OpenCvWebcam webcam;

    private AprilTagDetectionPipeline aprilTagDetectionPipeline;

    private static final double FEET_PER_METER = 3.28084;

    private double fx = 578.272;
    private double fy = 578.272;
    private double cx = 402.145;
    private double cy = 221.506;

    // UNITS ARE METERS
    private double tagSize = 0.166;

    private int numFramesWithoutDetection = 0;

    private final float DECIMATION_HIGH = 3;
    private final float DECIMATION_LOW = 2;
    private final float THRESHOLD_HIGH_DECIMATION_RANGE_METERS = 1.0f;
    private final int THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION = 4;

    private int lastRestult = 13;

    public Detect(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        FtcDashboard.getInstance().startCameraStream(webcam, 0);

        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagSize, fx, fy, cx, cy);
        webcam.setPipeline(aprilTagDetectionPipeline);

        webcam.setMillisecondsPermissionTimeout(2500); // Timeout for obtaining permission is configurable. Set before opening.
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {}
        });
    }

    public int getResult() {
        ArrayList<AprilTagDetection> detections = aprilTagDetectionPipeline.getDetectionsUpdate();
        int result = -1;
        if(detections != null)
        {
            // If we don't see any tags
            if(detections.size() == 0)
            {
                numFramesWithoutDetection++;

                // If we haven't seen a tag for a few frames, lower the decimation
                // so we can hopefully pick one up if we're e.g. far back
                if(numFramesWithoutDetection >= THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION)
                {
                    aprilTagDetectionPipeline.setDecimation(DECIMATION_LOW);
                }
            }
            // We do see tags!
            else
            {
                numFramesWithoutDetection = 0;

                // If the target is within 1 meter, turn on high decimation to
                // increase the frame rate
                if(detections.get(0).pose.z < THRESHOLD_HIGH_DECIMATION_RANGE_METERS)
                {
                    aprilTagDetectionPipeline.setDecimation(DECIMATION_HIGH);
                }

                for (AprilTagDetection detection : detections) {
                    if (detection.id >= 13 && detection.id <= 15) {
                        result = detection.id;
                    }
                }
            }
        }
        if (result != -1) {
            lastRestult = result;
            return  result;
        }
        return lastRestult;
    }
}
