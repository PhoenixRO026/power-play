package org.firstinspires.ftc.teamcode.newteleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name = "Slow Speed Drive")
public class SlowSpeedDrive extends JavaDrive{
    @Override
    public boolean getSniperMode(float trigger) {
        return true;
    }

    @Override
    public double getSniperSpeed() {
        return 0.2;
    }
}
