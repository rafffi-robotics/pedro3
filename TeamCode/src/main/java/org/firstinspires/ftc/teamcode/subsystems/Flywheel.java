package org.firstinspires.ftc.teamcode.subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.controller.PController;
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward;

@Configurable
public class Flywheel {
    private DcMotorEx motor;
    public static double kV, kS, kP;
    public static double targetRPM;

    PController p = new PController(kP);
    SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(kS, kV);


    public void init(HardwareMap hwmap) {
        motor = hwmap.get(DcMotorEx.class,"flywheelpollen");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


    public void setTargetRPM(double target) {
        targetRPM = target;
    }

    public void update() {
        motor.setPower(p.calculate(motor.getVelocity(), targetRPM) + feedforward.calculate(targetRPM));
    }
}


