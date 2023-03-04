package org.firstinspires.ftc.teamcode.newauto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.newauto.Cycle3Left;
import org.firstinspires.ftc.teamcode.newauto.Detect;

@Autonomous(name = "Left 3 Cones", preselectTeleOp = "New Drive")
public class LeftDetect extends Cycle3Left {
    @Override
    public void initCamera() {
        detect = new Detect(hardwareMap);
    }

    @Override
    public int getAutoCase() {
        return detect.getResult();
    }
}
