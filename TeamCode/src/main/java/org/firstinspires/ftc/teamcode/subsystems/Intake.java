package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class Intake {
    private Servo armServo;
    private Servo intakeServo;
    private double armServoPosition = 1; // Start at middle position
    private double intakeServoPosition = 0;

    private DigitalChannel armLimitSwitch;

    public Intake(HardwareMap hardwareMap) {
        armServo = hardwareMap.get(Servo.class, "ArmServo");
        intakeServo = hardwareMap.get(Servo.class, "IntakeServo");

        // Initialize the limit switch
        armLimitSwitch = hardwareMap.get(DigitalChannel.class, "ArmLimitSwitch");
        armLimitSwitch.setMode(DigitalChannel.Mode.INPUT);

        armServo.setPosition(armServoPosition);
        intakeServo.setPosition(intakeServoPosition);
    }

    public void tiltArmUp() {
        armServoPosition = Math.min(armServoPosition + 0.001, 1);
        armServo.setPosition(armServoPosition);
    }

    public void tiltArmDown() {
        // Check if the limit switch is pressed
        if (!armLimitSwitch.getState()) {
            return;
        }
        armServoPosition = Math.max(armServoPosition - 0.001, 0);
        armServo.setPosition(armServoPosition);
    }

    public void openIntake() {
        intakeServoPosition = Math.min(intakeServoPosition + 0.0001, 1);
        intakeServo.setPosition(intakeServoPosition);
    }

    public void closeIntake() {
        intakeServoPosition = Math.max(intakeServoPosition - 0.0001, 0);
        intakeServo.setPosition(intakeServoPosition);
    }
}