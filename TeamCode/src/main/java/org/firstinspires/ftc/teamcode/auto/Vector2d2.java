package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Vector2d;

public class Vector2d2 {
    public double X;
    public double Y;

    public Vector2d2(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    public Vector2d vector2d() {
        return new Vector2d(X, Y);
    }
}
