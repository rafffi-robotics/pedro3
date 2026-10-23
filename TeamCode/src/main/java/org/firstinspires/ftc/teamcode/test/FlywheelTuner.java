package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.Flywheel;

public class FlywheelTuner extends OpMode {
    private Flywheel flywheel = new Flywheel();
    @Override
    public void init() {
        flywheel.init(hardwareMap);
    }

    @Override
    public void loop() {
        flywheel.update();

    }
}
