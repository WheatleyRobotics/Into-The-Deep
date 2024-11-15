package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private final CRServo IntakeServo;
    private static final double IntakeSpeed = 1;

    public Intake(HardwareMap hardwareMap){
        IntakeServo = hardwareMap.get(CRServo.class, "IntakeServo");
    }

    public void IntakeIn() {
        IntakeServo.setPower(IntakeSpeed);
    }

    public void IntakeOut(){
        IntakeServo.setPower(-IntakeSpeed);
    }

    public void StopIntake(){
        IntakeServo.setPower(0);
    }
}
