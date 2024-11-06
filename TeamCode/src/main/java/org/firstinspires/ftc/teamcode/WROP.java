package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@TeleOp(name = "WRCode")
public class WROP extends LinearOpMode {

    private Drivetrain drivetrain;
    //private Arm arm;
     //private Intake intake;

    @Override
    public void runOpMode() {
        // Initialize subsystems
        drivetrain = new Drivetrain(hardwareMap);
        //arm = new Arm(hardwareMap);
        //intake = new Intake(hardwareMap);

        // Wait for the game to start
        waitForStart();

        // Main loop
        while (opModeIsActive()) {
            // Drivetrain control
            double Vertical = gamepad1.left_stick_y;
            double Horizontal = gamepad1.left_stick_x;
            double Pivot = gamepad1.right_stick_x;
            drivetrain.drive(Vertical, Horizontal, Pivot);

            /*
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
                telemetry.addData("Limit Switch Status: ", "Not Pressed");
            } else if (gamepad1.b) {
                if(!intake.tiltArmDown()){
                    telemetry.addData("Limit Switch Status: ", "Pressed");
                }else{
                    telemetry.addData("Limit Switch Status: ", "Not Pressed");
                }
            }
             */
            telemetry.update();
        }
    }
}