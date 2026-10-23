package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Intake {
    private DcMotor motor;

    public void init(HardwareMap hwmap) {
        motor = hwmap.get(DcMotor.class, "intake");
    }

    public void setPower(double power) {
        motor.setPower(power);
    }
}
