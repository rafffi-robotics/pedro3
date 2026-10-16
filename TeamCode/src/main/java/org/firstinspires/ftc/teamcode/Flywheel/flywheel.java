package org.firstinspires.ftc.teamcode.Flywheel;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class flywheel {
    private DcMotorEx m1;

    private double cpm=28;

    private double gearRatio= 1;

    private double kv, ks,kp;

    private void init(HardwareMap hwmap) {
    m1 =hwmap.get(DcMotorEx.class,"flywheel pollen");
    m1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

public void setMotorPower(double power) {
        m1.setPower(power);
}
public double getTickspersec(){
        return m1.getVelocity();
}

    public double getRPM() {
        return ((getTickspersec()/cpm)*60) / gearRatio;
    }
}


