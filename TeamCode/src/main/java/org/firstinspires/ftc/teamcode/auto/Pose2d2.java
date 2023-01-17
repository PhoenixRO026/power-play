package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class Pose2d2 {
    public double X;
    public double Y;
    public double Heading;

    public Pose2d2(double X, double Y, double Heading) {
        this.X = X;
        this.Y = Y;
        this.Heading = Heading;
    }

    public Pose2d pose2d() {
        return new Pose2d(X, Y, Heading);
    }
}
