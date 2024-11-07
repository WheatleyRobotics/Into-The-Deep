package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
    private DcMotor TopRightDrive;
    private DcMotor BottomRightDrive;
    private DcMotor TopLeftDrive;
    private DcMotor BottomLeftDrive;

    public Drivetrain(HardwareMap hardwareMap) {
        //TopRightDrive = hardwareMap.get(DcMotor.class, "RightFront");
        BottomRightDrive = hardwareMap.get(DcMotor.class, "RightBack");
        //TopLeftDrive = hardwareMap.get(DcMotor.class, "LeftFront");
        BottomLeftDrive = hardwareMap.get(DcMotor.class, "LeftBack");

        //TopRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        //BottomRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void drive(double Vertical, double Horizontal, double Pivot) {
        double ForwardBack = 0;
        double RightLeft = 0;
        double Rotation = 0;

        ForwardBack = Vertical;
        RightLeft = Horizontal;
        Rotation = Pivot;

        //TopRightDrive.setPower(-Rotation + (ForwardBack + RightLeft));
        BottomRightDrive.setPower(-Rotation + (ForwardBack - RightLeft));
        //TopLeftDrive.setPower(-Rotation + (-ForwardBack + RightLeft));
        BottomLeftDrive.setPower(-Rotation + (-ForwardBack - RightLeft));

        /*
        double rightPower = speed - turn;
        double leftPower = speed + turn;

        rightDrive.setPower(rightPower);
        leftDrive.setPower(leftPower);
         */
    }
}