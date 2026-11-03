package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class LeaveUniversal extends LinearOpMode {

    ElapsedTime timer = new ElapsedTime();
    private DcMotorEx frontLeftDrive;
    private DcMotorEx backLeftDrive;
    private DcMotorEx frontRightDrive;
    private DcMotorEx backRightDrive;
    @Override
    public void runOpMode()  {
        frontRightDrive = hardwareMap.get(DcMotorEx.class, "FR");
        backLeftDrive = hardwareMap.get(DcMotorEx.class, "BL");
        backRightDrive = hardwareMap.get(DcMotorEx.class, "BR");
        frontLeftDrive = hardwareMap.get(DcMotorEx.class, "FL");


        frontRightDrive.setPower(1);
        backRightDrive.setPower(1);
        backLeftDrive.setPower(1);
        frontLeftDrive.setPower(1);

        timer.reset();

        while (opModeIsActive() && timer.seconds() < 2.0) {
            idle();
        }

        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        frontLeftDrive.setPower(0);
    }


}
