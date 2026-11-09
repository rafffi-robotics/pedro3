package org.firstinspires.ftc.teamcode.mainTeleop;
import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.util.Timing;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.test.InternalPid;

import java.util.concurrent.TimeUnit;


@TeleOp()
public class MecanumExample extends OpMode {

    MecanumDrive drive = new MecanumDrive();
    Intake intake = new Intake();
    public DcMotorEx flywheel;
    public DcMotorEx flywheelNectar;
    public ServoEx pollenGate;
    public int targetVel = 0;
    private Timing.Timer gateTimer = new Timing.Timer(2000, TimeUnit.MILLISECONDS);


    InternalPid internalPid = new InternalPid();
    double forward, strafe, rotate;


    @Override
    public void init() {

        drive.init(hardwareMap);
        intake.init(hardwareMap);
        flywheel = hardwareMap.get(DcMotorEx.class,"flywheel");
      //  flywheelNectar = hardwareMap.get(DcMotorEx.class,"flywheelNectar");


        pollenGate = new ServoEx(hardwareMap, "pollenGate");
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //PIDFCoefficients trialPIDF = new PIDFCoefficients(0.1, 0.2, 0.001, 12);
       // PIDFCoefficients nectarPIDF = new PIDFCoefficients(.1, .2, .001, 12);

       // flywheel.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER, new PIDFCoefficients());
       // flywheelNectar.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER, nectarPIDF);
    }

    @Override
    public void start() {
//hi
    }

    @Override
    public void loop() {
        targetVel = Range.clip(targetVel, 0, 1800);
//        telemetry.addData("Target Velocity:", targetVel);
//        telemetry.addData("Actual Velocity:", flywheel.getVelocity());
        forward = -gamepad1.left_stick_y;
        strafe = -gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
        drive.driveRobotRelative(forward, strafe, rotate);
        flywheel.setVelocity(targetVel);
//        flywheelNectar.setVelocity(targetVel);
        if (gamepad1.right_trigger > 0.1) {
            intake.setPower(1);
        } else if (gamepad1.left_trigger > 0.1) {
            intake.setPower(-1);
        } else {
            intake.setPower(0);
        }
        if (gamepad1.dpadUpWasPressed()){
           targetVel = targetVel + 10;
        }
        if (gamepad1.dpadDownWasPressed()){
            targetVel = targetVel - 10;
        }
        if (gamepad1.aWasPressed()) {
            pollenGate.set(1.0);
            gateTimer.start();
        }
        if (gateTimer.done() && pollenGate.get() != 0) {
            pollenGate.set(.5);
        }
      telemetry.addData("actual velocity", targetVel);
        telemetry.addData("flywheel velo",flywheel.getVelocity());


    }

}


