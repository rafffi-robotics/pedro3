package org.firstinspires.ftc.teamcode.earlyCode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Deprecated
public class FieldRelativeMethod {
    private DcMotor frontLeftMotor,frontRightMotor, backLeftMotor, backRightMotor;


    public void init(HardwareMap hwMap) {
        frontRightMotor = hwMap.get(DcMotor.class, "FR");
        backRightMotor = hwMap.get(DcMotor.class, "BR");
        frontLeftMotor = hwMap.get(DcMotor.class, "FL");
        backLeftMotor = hwMap.get(DcMotor.class, "BL");


        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void drive(double forward, double strafe, double rotate) {
        // if these next line were not here strafe and rotate would be reversed up
        strafe = -strafe;
        rotate = -rotate;

        double frontLeftPower = forward + strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backLeftPower = forward - strafe + rotate;
        double backRightPower = forward + strafe - rotate;
        double maxpower = 1.0;
        double maxspeed = 1.0;

        maxpower = Math.max(maxpower , Math.abs(frontLeftPower));
        maxpower = Math.max(maxpower , Math.abs(frontRightPower));
        maxpower = Math.max(maxpower , Math.abs(backLeftPower));
        maxpower = Math.max(maxpower , Math.abs(backRightPower));

        frontLeftMotor.setPower(maxspeed * (frontLeftPower/maxpower));
        frontRightMotor.setPower(maxspeed * (frontRightPower/maxpower));
        backLeftMotor.setPower(maxspeed * (backLeftPower/maxpower));
        backRightMotor.setPower(maxspeed * (backRightPower/maxpower));


    }

}
