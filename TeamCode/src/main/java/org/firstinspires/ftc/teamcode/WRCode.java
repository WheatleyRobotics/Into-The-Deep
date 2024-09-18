package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@TeleOp(name = "WRCode2")
public class WRCode extends LinearOpMode {

    private Drivetrain drivetrain;
    private Arm arm;
    private Intake intake;

    @Override
    public void runOpMode() {
        // Initialize subsystems
        drivetrain = new Drivetrain(hardwareMap);
        arm = new Arm(hardwareMap);
        intake = new Intake(hardwareMap);

        // Wait for the game to start
        waitForStart();

        // Main loop
        while (opModeIsActive()) {
            // Drivetrain control
            double speed = -gamepad1.left_stick_y;
            double turn = -gamepad1.right_stick_x * 0.8;
            drivetrain.drive(speed, turn);

            // Arm control
            if (gamepad1.y) {
                arm.moveUp();
            } else if (gamepad1.a) {
                arm.moveDown();
            } else {
                arm.stop();
            }

            // Intake control
            if (gamepad1.right_bumper) {
                intake.openIntake();
            } else if (gamepad1.left_bumper) {
                intake.closeIntake();
            }

            // Arm servo control
            if (gamepad1.x) {
                intake.tiltArmUp();
            } else if (gamepad1.b) {
                intake.tiltArmDown();
            }

            // Update telemetry if needed
            telemetry.update();
        }
    }
}