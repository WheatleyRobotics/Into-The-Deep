package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Camera;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

import java.util.List;
import java.util.Optional;

@TeleOp(name = "WRCode")
public class WROP extends LinearOpMode {

    private Drivetrain drivetrain;
    private Arm arm;
    private Intake intake;
    private Camera camera;

    @Override
    public void runOpMode() {
        // Initialize subsystems
        drivetrain = new Drivetrain(hardwareMap);
        arm = new Arm(hardwareMap);
        intake = new Intake(hardwareMap);
        camera = new Camera(hardwareMap, "Webcam 1");


        // Wait for the game to start
        waitForStart();

        // Main loop
        while (opModeIsActive()) {
            Optional<Pose3D> poseOptional = camera.getRobotPose();

            poseOptional.ifPresent(pose -> {
                // Use the pose information
                telemetry.addData("Robot Pose", pose.toString());
            });

            // Drivetrain control with deadzone
            double speed = -gamepad1.left_stick_y;
            double turn = -gamepad1.right_stick_x * 0.8;

            // Apply deadzone
            if (Math.abs(speed) < 0.05) speed = 0;
            if (Math.abs(turn) < 0.05) turn = 0;

            drivetrain.drive(speed, turn);

            // Arm control
            if (gamepad2.y) {
                arm.moveUp();
            } else if (gamepad2.a) {
                arm.moveDown();
            } else {
                arm.stop();
            }

            // Intake control
            if (gamepad2.right_bumper) {
                intake.openIntake();
            } else if (gamepad2.left_bumper) {
                intake.closeIntake();
            }

            // Arm servo control
            if (gamepad2.x) {
                intake.tiltArmUp();
                telemetry.addData("Limit Switch Status: ", "Not Pressed");
            } else if (gamepad2.b) {
                if(!intake.tiltArmDown()){
                    telemetry.addData("Limit Switch Status: ", "Pressed");
                }else{
                    telemetry.addData("Limit Switch Status: ", "Not Pressed");
                }
            }else{
                telemetry.addData("Limit Switch Status: ", "Not Pressed");
            }

            telemetry.update();
        }
        camera.stop();
    }
}