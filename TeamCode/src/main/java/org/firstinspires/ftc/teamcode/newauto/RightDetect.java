package org.firstinspires.ftc.teamcode.newauto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.newauto.Cycle3Right;
import org.firstinspires.ftc.teamcode.newauto.Detect;

@Autonomous(name = "Right 3 Cones", preselectTeleOp = "New Drive")
public class RightDetect extends Cycle3Right {
    @Override
    public void initCamera() {
        detect = new Detect(hardwareMap);
    }

    @Override
    public int getAutoCase() {
        return detect.getResult();
    }
}
