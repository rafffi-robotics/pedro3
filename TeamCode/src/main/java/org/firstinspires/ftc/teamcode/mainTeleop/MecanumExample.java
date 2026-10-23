package org.firstinspires.ftc.teamcode.mainTeleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.test.InternalPid;


@TeleOp()
public class MecanumExample extends OpMode {

    MecanumDrive drive = new MecanumDrive();
    Intake intake = new Intake();
    public DcMotorEx flywheel;
    public int targetVel = 0;


    InternalPid internalPid = new InternalPid();
    double forward, strafe, rotate;


    @Override
    public void init() {

        drive.init(hardwareMap);
        intake.init(hardwareMap);
        flywheel = hardwareMap.get(DcMotorEx.class,"flywheel");
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients trialPIDF = new PIDFCoefficients(0.1, 0.2, 0.001, 12);

        //flywheel.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER, trialPIDF);
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        telemetry.addData("Target Velocity:", targetVel);
        telemetry.addData("Actual Velocity:", flywheel.getVelocity());
        forward = -gamepad1.left_stick_y;
        strafe = -gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
        drive.driveRobotRelative(forward, strafe, rotate);
        flywheel.setVelocity(targetVel);

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
            targetVel =targetVel - 10;
        }

    }

}


