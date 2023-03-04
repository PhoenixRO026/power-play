package org.firstinspires.ftc.teamcode.newauto.nocamera;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.newauto.Cycle3Right;

@Disabled
@Autonomous
public class RightController extends Cycle3Right {
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
