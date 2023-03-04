package org.firstinspires.ftc.teamcode.newauto.nocamera;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.newauto.Cycle3Left;

@Disabled
@Autonomous
public class LeftController extends Cycle3Left {
    int caz = 13;

    @Override
    public void initCamera() {

    }

    @Override
    public int getAutoCase() {
        if (gamepad1.x)
            caz = 13;
        else if (gamepad1.a)
            caz = 14;
        else if (gamepad1.b)
            caz = 15;
        return caz;
    }
}
