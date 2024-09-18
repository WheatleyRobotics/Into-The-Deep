package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private Servo armServo;
    private Servo intakeServo;
    private double armServoPosition = 0.5; // Start at middle position
    private double intakeServoPosition = 0.5;

    public Intake(HardwareMap hardwareMap) {
        armServo = hardwareMap.get(Servo.class, "ArmServo");
        intakeServo = hardwareMap.get(Servo.class, "IntakeServo");

        armServo.setPosition(armServoPosition);
        intakeServo.setPosition(intakeServoPosition);
    }

    public void tiltArmUp() {
        armServoPosition = Math.min(armServoPosition + 0.01, 1);
        armServo.setPosition(armServoPosition);
    }

    public void tiltArmDown() {
        armServoPosition = Math.max(armServoPosition - 0.01, 0);
        armServo.setPosition(armServoPosition);
    }

    public void openIntake() {
        intakeServoPosition = Math.min(intakeServoPosition + 0.01, 1);
        intakeServo.setPosition(intakeServoPosition);
    }

    public void closeIntake() {
        intakeServoPosition = Math.max(intakeServoPosition - 0.01, 0);
        intakeServo.setPosition(intakeServoPosition);
    }
}