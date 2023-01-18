package com.example.constants;

public class Pose {
    public double X;
    public double Y;
    public double HEADING;

    public Pose(double X, double Y, double HEADING_DEG) {
        this.X = X;
        this.Y = Y;
        this.HEADING = Math.toRadians(HEADING_DEG);
    }
}
