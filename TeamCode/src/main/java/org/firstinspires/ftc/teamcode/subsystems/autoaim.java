package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
            if (tx != 0) {

            }
        }
    }
}