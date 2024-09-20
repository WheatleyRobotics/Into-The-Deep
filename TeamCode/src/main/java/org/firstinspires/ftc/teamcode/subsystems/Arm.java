package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    private DcMotor armMotorRight;
    private DcMotor armMotorLeft;
    private static final double ARM_SPEED = 1;

    public Arm(HardwareMap hardwareMap) {
        armMotorRight = hardwareMap.get(DcMotor.class, "ArmMotorRight");
        armMotorLeft = hardwareMap.get(DcMotor.class, "ArmMotorLeft");

        armMotorLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    public void moveUp() {
        armMotorRight.setPower(ARM_SPEED);
        armMotorLeft.setPower(ARM_SPEED);
    }

    public void moveDown() {
        armMotorRight.setPower(-ARM_SPEED);
        armMotorLeft.setPower(-ARM_SPEED);
    }

    public void stop() {
        armMotorRight.setPower(0);
        armMotorLeft.setPower(0);
    }
}