package org.firstinspires.ftc.teamcode.newauto;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class ConfigPose {
    public double x;
    public double y;
    public double xOffset = 0;
    public double yOffset = 0;
    public double heading;

    public ConfigPose(Pose2d pose) {
        this.x = pose.getX();
        this.y = pose.getY();
        this.heading = pose.getHeading();
    }

    public ConfigPose(double x, double y, double heading) {
        this.x = x;
        this.y = y;
        this.heading = heading;
    }

    public ConfigPose offset(double x, double y) {
        this.xOffset = Math.sin(heading) * x + Math.cos(heading) * y;
        this.yOffset = -Math.cos(heading) * x + Math.sin(heading) * y;
        return this;
    }

    private ConfigPose setOffset(double xOffset, double yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        return this;
    }

    public Pose2d pose2d() {
        return new Pose2d(x + xOffset, y + yOffset, heading);
    }

    public ConfigPose reversed() {
        return new ConfigPose(-x, y, flipHeadingRad(heading)).setOffset(-xOffset, yOffset);
    }

    public static double flipHeadingRad(double heading) {
        double headingDeg = Math.toDegrees(heading) % 360;
        return Math.toRadians((540 - headingDeg) % 360);
    }
}
