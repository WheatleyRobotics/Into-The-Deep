package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    public Drivetrain(HardwareMap hardwareMap) {
        leftDrive = hardwareMap.get(DcMotor.class, "LeftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "RightDrive");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void drive(double speed, double turn) {
        double leftPower = speed + turn;
        double rightPower = speed - turn;

        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
    }
}