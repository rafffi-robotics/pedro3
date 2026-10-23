package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class InternalPid {
    public DcMotorEx flywheel;
    public double targetRpm;

    public void init(HardwareMap hwMap){
        flywheel = hwMap.get(DcMotorEx.class,"flywheel");
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void setTargetRpm(){
        flywheel.setVelocity(3000);
    }
}



