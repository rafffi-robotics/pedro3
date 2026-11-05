package org.firstinspires.ftc.teamcode.subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.controller.PController;
import com.seattlesolvers.solverslib.controller.PDController;
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward;

@Configurable
public class Flywheel {
    private DcMotorEx motor;
    public static double  kD, kP;
    public static double targetRPM;

    PDController pdController = new PDController(kP, kD);

    public void init(HardwareMap hwmap) {
        motor = hwmap.get(DcMotorEx.class,"flywheelpollen");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }


    public void setTargetRPM(double target) {
        targetRPM = target;
    }

    public void update() {
        motor.setPower(pdController.calculate(motor.getVelocity(), targetRPM) );
    }
}


