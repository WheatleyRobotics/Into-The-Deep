package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private Servo armServo;
    private Servo intakeServo;
    private double armServoPosition = 1; // Start at middle position
    private double intakeServoPosition = 0;

    private RevTouchSensor armLimitSwitch;

    public Intake(HardwareMap hardwareMap) {
        armServo = hardwareMap.get(Servo.class, "ArmServo");
        intakeServo = hardwareMap.get(Servo.class, "IntakeServo");

        // Initialize the limit switch
        armLimitSwitch = hardwareMap.get(RevTouchSensor.class, "ArmLimitSwitch");

        armServo.setPosition(armServoPosition);
        intakeServo.setPosition(intakeServoPosition);
    }

    public void tiltArmUp() {
        armServoPosition = Math.min(armServoPosition + 0.001, 1.1);
        armServo.setPosition(armServoPosition);
    }

    public boolean tiltArmDown() {
        // Prevent moving down if the limit switch is pressed
        if (armLimitSwitch.isPressed()) {
            return false;
        }
        armServoPosition = Math.max(armServoPosition - 0.001, 0);
        armServo.setPosition(armServoPosition);
        return true;
    }

    public void openIntake() {
        intakeServoPosition = Math.min(intakeServoPosition, 1);
        intakeServo.setPosition(intakeServoPosition);
    }

    public void closeIntake() {
        intakeServoPosition = Math.max(intakeServoPosition - 0.0001, 0);
        intakeServo.setPosition(intakeServoPosition);
    }
}