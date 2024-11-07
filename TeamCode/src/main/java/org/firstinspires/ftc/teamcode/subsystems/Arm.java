package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    private DcMotor ArmMotor;
    private static final double ArmSpeed = 0.5;

    public Arm(HardwareMap hardwareMap){
        ArmMotor = hardwareMap.get(DcMotor.class, "ArmMotor");

        ArmMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void MoveUp(){
        ArmMotor.setPower(ArmSpeed);
    }

    public void MoveDown(){
        ArmMotor.setPower(ArmSpeed);
    }

    public void Stop(){
        ArmMotor.setPower(0);
    }
}
