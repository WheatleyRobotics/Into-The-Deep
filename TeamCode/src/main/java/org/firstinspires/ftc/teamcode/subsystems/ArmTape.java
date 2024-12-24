package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmTape {
    private DcMotor ArmTapeMotor;
    private static final double ArmTapeMotorSpeed = 0.5;
    private static final double ArmTapeAutoSpeed = 0.3;

    public ArmTape(HardwareMap hardwareMap){
        ArmTapeMotor = hardwareMap.get(DcMotor.class, "ArmTapeMotor");

        ArmTapeMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void MoveUp(){
        ArmTapeMotor.setPower(ArmTapeMotorSpeed);
    }

    public void MoveDown(){
        ArmTapeMotor.setPower(-ArmTapeMotorSpeed);
    }

    public void AutoMoveUp(){
        ArmTapeMotor.setPower(ArmTapeAutoSpeed);
    }

    public void AutoMoveDown(){
        ArmTapeMotor.setPower(-ArmTapeAutoSpeed);
    }

    public void AutoArmTapeStop(){
        ArmTapeMotor.setPower(0.3);
    }

    public void ArmTapeStop(){
        ArmTapeMotor.setPower(0);
    }
}
