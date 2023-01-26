package com.example.constants;

import static com.example.constants.Constants.HORIZONTAL_LOW_CLAW_OFFSET;
import static com.example.constants.Constants.ROBOT_LENGTH;
import static com.example.constants.Constants.VERTICAL_LOW_CLAW_OFFSET;
import static com.example.constants.Constants.HORIZONTAL_HIGH_CLAW_OFFSET;
import static com.example.constants.Constants.VERTICAL_HIGH_CLAW_OFFSET;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class Pose {
    public double X;
    public double Y;
    public double X_NoOffset;
    public double Y_NoOffset;
    public double HEADING;
    public double START_TANGENT;
    public double END_TANGENT;
    public Vector2d vec;
    public Pose2d poseTan;
    public Pose2d poseHed;
    public Vector2d vecNoOffset;
    public Pose2d poseTanNoOffset;
    public Pose2d poseHedNoOffset;

    public Pose(double X, double Y, double HEADING_DEG, double START_TANGENT, double END_TANGENT, double VERTICAL_OFFSET, double HORIZONTAL_OFFSET) {
        this.X = X + sin(END_TANGENT) * HORIZONTAL_OFFSET + cos(END_TANGENT) * (VERTICAL_OFFSET - ROBOT_LENGTH / 2);
        this.Y = Y - cos(END_TANGENT) * HORIZONTAL_OFFSET + sin(END_TANGENT) * (VERTICAL_OFFSET - ROBOT_LENGTH / 2);
        this.X_NoOffset = X + cos(END_TANGENT) * (ROBOT_LENGTH / 2);
        this.Y_NoOffset = Y + sin(END_TANGENT) * (ROBOT_LENGTH / 2);
        this.HEADING = HEADING_DEG;
        this.START_TANGENT = START_TANGENT;
        this.END_TANGENT = END_TANGENT;
        vec = new Vector2d(this.X, this.Y);
        poseTan = new Pose2d(this.X, this.Y, END_TANGENT);
        poseHed = new Pose2d(this.X, this.Y, HEADING);
        vecNoOffset = new Vector2d(this.X_NoOffset, this.Y_NoOffset);
        poseTanNoOffset = new Pose2d(this.X_NoOffset, this.Y_NoOffset, END_TANGENT);
        poseHedNoOffset = new Pose2d(this.X_NoOffset, this.Y_NoOffset, HEADING);
    }

    public Pose(double X, double Y, double HEADING_DEG, double START_TANGENT, double END_TANGENT, double VERTICAL_OFFSET, double HORIZONTAL_OFFSET, OFFSET offset) {
        double verticalOffset = 0;
        switch (offset) {
            case ADD_ROBOT_LENGHT:
                verticalOffset = VERTICAL_OFFSET - ROBOT_LENGTH / 2;
                break;
            case NO_ADD:
                verticalOffset = VERTICAL_OFFSET;
                break;
        }
        this.X = X + sin(END_TANGENT) * HORIZONTAL_OFFSET + cos(END_TANGENT) * verticalOffset;
        this.Y = Y - cos(END_TANGENT) * HORIZONTAL_OFFSET + sin(END_TANGENT) * verticalOffset;
        this.X_NoOffset = X + cos(END_TANGENT) * (ROBOT_LENGTH / 2);
        this.Y_NoOffset = Y + sin(END_TANGENT) * (ROBOT_LENGTH / 2);
        this.HEADING = HEADING_DEG;
        this.START_TANGENT = START_TANGENT;
        this.END_TANGENT = END_TANGENT;
        vec = new Vector2d(this.X, this.Y);
        poseTan = new Pose2d(this.X, this.Y, END_TANGENT);
        poseHed = new Pose2d(this.X, this.Y, HEADING);
        vecNoOffset = new Vector2d(this.X_NoOffset, this.Y_NoOffset);
        poseTanNoOffset = new Pose2d(this.X_NoOffset, this.Y_NoOffset, END_TANGENT);
        poseHedNoOffset = new Pose2d(this.X_NoOffset, this.Y_NoOffset, HEADING);
    }

    public Pose(double X, double Y, double HEADING_DEG, double START_TANGENT, double END_TANGENT, CLAW_OFFSET CLAWOffset) {
        double horizontalOffset = 0;
        double verticalOffset = 0;
        switch (CLAWOffset) {
            case LOW_OFFSET:
                horizontalOffset = HORIZONTAL_LOW_CLAW_OFFSET;
                verticalOffset = VERTICAL_LOW_CLAW_OFFSET - ROBOT_LENGTH / 2;
                break;
            case HIGH_OFFSET:
                horizontalOffset = HORIZONTAL_HIGH_CLAW_OFFSET;
                verticalOffset = VERTICAL_HIGH_CLAW_OFFSET - ROBOT_LENGTH / 2;
                break;
            case NO_OFFSET:
                horizontalOffset = 0;
                verticalOffset = -ROBOT_LENGTH / 2;
        }

        this.X = X + sin(END_TANGENT) * horizontalOffset + cos(END_TANGENT) * verticalOffset;
        this.Y = Y - cos(END_TANGENT) * horizontalOffset + sin(END_TANGENT) * verticalOffset;
        this.HEADING = HEADING_DEG;
        this.START_TANGENT = START_TANGENT;
        this.END_TANGENT = END_TANGENT;
        vec = new Vector2d(this.X, this.Y);
        poseTan = new Pose2d(this.X, this.Y, END_TANGENT);
        poseHed = new Pose2d(this.X, this.Y, HEADING);
    }

    public enum OFFSET {
        ADD_ROBOT_LENGHT,
        NO_ADD
    }
    public enum CLAW_OFFSET {
        NO_OFFSET,
        LOW_OFFSET,
        HIGH_OFFSET
    }
}
