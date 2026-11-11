package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "Test: Motor Direction", group = "Test")
public class MotorDirectionTest extends OpMode {
    ElapsedTime timer = new ElapsedTime();
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    // CRServo intake;
    DcMotorEx leftLift;
    DcMotorEx rightLift;

    @Override
    public void init() {
        frontLeftDrive = hardwareMap.get(DcMotor.class, "FL");
        frontRightDrive = hardwareMap.get(DcMotor.class, "FR");
        backLeftDrive = hardwareMap.get(DcMotor.class, "BL");
        backRightDrive = hardwareMap.get(DcMotor.class, "BR");
        //intake = hardwareMap.get(CRServo.class, "intake");
//        leftLift = hardwareMap.get(DcMotorEx.class, "");
//        rightLift = hardwareMap.get(DcMotorEx.class, "");

        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
//        leftLift.setDirection(DcMotorSimple.Direction.FORWARD);
//        leftLift.setDirection(DcMotorSimple.Direction.FORWARD);

        //RESET ENCODER VALUES
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//        leftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        telemetry.addData("Status", "Initialized");
        telemetry.addData(">", "Press Play to start test.");
        telemetry.addData(">", "Each motor will spin FORWARD for 2 seconds.");
        telemetry.update();

    }

    @Override
    public void start() {
        timer.reset();

    }

    @Override
    public void loop() {
        // Test Front Left
        if (timer.seconds() < 2) {
            telemetry.clearAll();
            telemetry.addData("Testing", "Front Left Motor");
            telemetry.addData("Encoder FL", frontLeftDrive.getCurrentPosition());
            telemetry.addData("Encoder FR", frontRightDrive.getCurrentPosition());
            telemetry.addData("Encoder BL", backLeftDrive.getCurrentPosition());
            telemetry.addData("Encoder BR", backRightDrive.getCurrentPosition());
            //  telemetry.addData("Fly1", flywheel1.getCurrentPosition());
            //telemetry.addData("Fly2", flywheel2.getCurrentPosition());
            telemetry.update();
            frontLeftDrive.setPower(0.1);
        }
        // Test Front Right
        if (timer.seconds() > 4 && timer.seconds() < 6) {
            frontLeftDrive.setPower(0);
            telemetry.clearAll();
            telemetry.addData("Testing", "Front Right Motor");
            telemetry.addData("Encoder FL", frontLeftDrive.getCurrentPosition());
            telemetry.addData("Encoder FR", frontRightDrive.getCurrentPosition());
            telemetry.addData("Encoder BL", backLeftDrive.getCurrentPosition());
            telemetry.addData("Encoder BR", backRightDrive.getCurrentPosition());
//            telemetry.addData("Fly1", flywheel1.getCurrentPosition());
//            telemetry.addData("Fly2", flywheel2.getCurrentPosition());
            telemetry.update();
            frontRightDrive.setPower(0.1);
        }
        // Test Back Left
        if (timer.seconds() > 8 && timer.seconds() < 10) {
            frontRightDrive.setPower(0);
            telemetry.clearAll();
            telemetry.addData("Testing", "Back Left Motor");
            telemetry.addData("Encoder FL", frontLeftDrive.getCurrentPosition());
            telemetry.addData("Encoder FR", frontRightDrive.getCurrentPosition());
            telemetry.addData("Encoder BL", backLeftDrive.getCurrentPosition());
            telemetry.addData("Encoder BR", backRightDrive.getCurrentPosition());
//            telemetry.addData("Fly1", flywheel1.getCurrentPosition());
//            telemetry.addData("Fly2", flywheel2.getCurrentPosition());
            telemetry.update();
            backLeftDrive.setPower(0.1);
        }
        if (timer.seconds() > 12 && timer.seconds() < 14) {
            backLeftDrive.setPower(0);
            telemetry.clearAll();
            telemetry.addData("Testing", "Back Right Motor");
            telemetry.addData("Encoder FL", frontLeftDrive.getCurrentPosition());
            telemetry.addData("Encoder FR", frontRightDrive.getCurrentPosition());
            telemetry.addData("Encoder BL", backLeftDrive.getCurrentPosition());
            telemetry.addData("Encoder BR", backRightDrive.getCurrentPosition());
//            telemetry.addData("Fly1", flywheel1.getCurrentPosition());
//            telemetry.addData("Fly2", flywheel2.getCurrentPosition());
            telemetry.update();
            backRightDrive.setPower(0.1);
        }
        else if (timer.seconds() > 16) {
            frontLeftDrive.setPower(0);
            frontRightDrive.setPower(0);
            backLeftDrive.setPower(0);
            backRightDrive.setPower(0);
            telemetry.update();
        }
    }
}