package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class AprilBoy extends OpMode {
    private IMU imu;

    private Limelight3A limelight;
    public boolean autoaim;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(8);
        imu = hardwareMap.get(IMU.class, "IMU");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.UP);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));
    }

        @Override
        public void start () {
            limelight.start();
        }

        @Override
        public void loop () {
//            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
//            limelight.updateRobotOrientation(orientation.getYaw());
            LLResult llResult =limelight.getLatestResult();
            if (llResult != null && llResult.isValid()){
                Pose3D botpose = llResult.getBotpose                                                            ();
                telemetry.addData("tx",llResult.getTx());
                telemetry.addData("ta",llResult.getTa());
                telemetry.addData("ty",llResult.getTy());

        }
    }
}

