package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.lang.Math;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class Drivetrain {
    private DcMotor TopRightDrive;
    private DcMotor BottomRightDrive;
    private DcMotor TopLeftDrive;
    private DcMotor BottomLeftDrive;
    //Gyro
    private IMU imu;
    private double TopRightDrivePower;
    private double BottomRightDrivePower;
    private double TopLeftDrivePower;
    private double BottomLeftDrivePower;

    boolean fast = false;

    public Drivetrain(HardwareMap hardwareMap) {
        TopRightDrive = hardwareMap.get(DcMotor.class, "RightFront");
        BottomRightDrive = hardwareMap.get(DcMotor.class, "RightBack");
        TopLeftDrive = hardwareMap.get(DcMotor.class, "LeftFront");
        BottomLeftDrive = hardwareMap.get(DcMotor.class, "LeftBack");


        TopRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BottomRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TopLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BottomLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        TopRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        BottomRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        TopLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        BottomLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        //Gyro
        imu = hardwareMap.get(IMU.class,"imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

        imu.initialize(parameters);
    }

    public void drive(double Vertical, double Horizontal, double Pivot) {
        double ForwardBack = 0;
        double RightLeft = 0;
        double Rotation = 0;

        ForwardBack = Vertical;
        RightLeft = Horizontal;
        Rotation = Pivot;

        //Gyro and Drive
        double Gyro = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double MoveRightLeft = RightLeft * Math.cos(-Gyro) - ForwardBack * Math.sin(-Gyro); // left stick x
        double MoveForwardBack = RightLeft * Math.sin(-Gyro) + ForwardBack * Math.cos(-Gyro); // left stick y

        //Slow
        //double Denominator = Math.max(Math.abs(MoveForwardBack) + Math.abs(MoveRightLeft) + Math.abs(Rotation), 5);

        double Denominator = Math.max(Math.abs(MoveForwardBack) + Math.abs(MoveRightLeft) + Math.abs(Rotation), 1);

        //Drive Code
        TopRightDrivePower = (((MoveForwardBack - MoveRightLeft) - Rotation) / Denominator);
        BottomRightDrivePower = (((MoveForwardBack + MoveRightLeft) - Rotation) / Denominator);
        TopLeftDrivePower = (((MoveForwardBack + MoveRightLeft) + Rotation) / Denominator);
        BottomLeftDrivePower = (((MoveForwardBack - MoveRightLeft) + Rotation) / Denominator);

        TopRightDrive.setPower(TopRightDrivePower);
        BottomRightDrive.setPower(BottomRightDrivePower);
        TopLeftDrive.setPower(TopLeftDrivePower);
        BottomLeftDrive.setPower(BottomLeftDrivePower);
    }

    public double rightFrontPower(){
        return TopRightDrivePower;
    }

    public double rightBackPower(){
        return BottomRightDrivePower;
    }

    public double leftFrontPower(){
        return TopLeftDrivePower;
    }

    public double leftBackPower(){
        return BottomLeftDrivePower;
    }

    //Gyro
    public YawPitchRollAngles GyroValues(){
        return imu.getRobotYawPitchRollAngles();
    }

    public void resetGyro(){
        imu.resetYaw();
    }
}