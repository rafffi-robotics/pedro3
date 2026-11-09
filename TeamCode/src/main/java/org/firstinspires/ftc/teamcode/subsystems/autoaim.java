package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class autoaim extends OpMode {

    private final MecanumDrive drive = new MecanumDrive();
    private final AprilBoy april = new AprilBoy();
    double kP = .0002;
    double error = 0;
    double lastError = 0;
    double goalx = 0;
    double angletolerance = 0.3;
    double kD = .0001;
    double curlTime = 0;
    double lastTime = 0;
    double forward, strafe, rotate;

    double[] stepSizes = {1.0, 0.1, 0.01, 0.001, 0.0001};
    int stepIndex =1;

    @Override
    public void init() {
        april.init();
        drive.init(hardwareMap);
        telemetry.addLine("initialized");
    }

    @Override
    public void start() {
        curlTime = getRuntime();
    }

    @Override
    public void loop() {
//---------drive inputs-------
        forward = -gamepad1.left_stick_y;
        strafe = -gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
        april.update();
        double tx = april.getTx();
        if (gamepad1.left_trigger > 0.3) {
            if (april.isValid()) {
                error =  goalx -tx;
                if (Math.abs(error) < angletolerance) {
                    rotate = 0;
                } else {
                    double pTerm = error * kP;

                    curlTime =getRuntime();


                    double dT = curlTime - lastTime;
                    double dTerm = ((error- lastError) /dT * kD);

                    rotate = Range.clip(pTerm +dTerm, -.4, .4);
                    lastError = error;
                    lastTime = curlTime;
                }
            } else {
                lastTime =getRuntime();
                lastError = 0;
            }
        } else {
            lastError =0;
            lastTime =getRuntime();
        }
    //drive
        drive.driveRobotRelative(forward, strafe, rotate );



// 'B' button cycles through the different step sizes for tuning.
            if (gamepad1.bWasPressed()) {
                stepIndex = (stepIndex + 1) % stepSizes.length; // Modulo wraps
            }

// D-pad left/right adjusts the P gain.
            if (gamepad1.dpadLeftWasPressed()) {
                kP -= stepSizes[stepIndex];
            }
            if (gamepad1.dpadRightWasPressed()) {
                kP += stepSizes[stepIndex];
            }

// D-pad up/down adjusts the D gain.
            if (gamepad1.dpadUpWasPressed()) {
                kD += stepSizes[stepIndex];
            }
            if (gamepad1.dpadDownWasPressed()) {
                kD -= stepSizes[stepIndex];
            }

        // ---------------- TELEMETRY ----------------
        if (april.isValid()) {
            if (gamepad1.left_trigger > 0.3) {
                telemetry.addLine("AUTO ALIGN");
            }


            telemetry.addData("Error", error);
        } else {
            telemetry.addLine("MANUAL Rotate Mode");
        }

        telemetry.addLine("------------------------------");
        telemetry.addData("Tuning P", "P: %.4f (D-Pad L/R)", kP);
        telemetry.addData("Tuning D", "D: %.4f (D-Pad U/D)", kD);
        telemetry.addData("Step Size", "%.4f (B Button)", stepSizes[stepIndex]);
        }
    }