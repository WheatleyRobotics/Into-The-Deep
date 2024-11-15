package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    private DcMotor ArmMotor;
    private static final double ArmSpeedUP = 1;
    private static final double ArmSpeedDown = -0.2;

    public Arm(HardwareMap hardwareMap){
        ArmMotor = hardwareMap.get(DcMotor.class, "ArmMotor");
        
        //ArmMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void MoveUp(){
        ArmMotor.setPower(ArmSpeedUP);
    }

    public void MoveDown(){
        ArmMotor.setPower(ArmSpeedDown);
    }

    public void ArmStop(){
        ArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ArmMotor.setPower(0);
        //ArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
}
