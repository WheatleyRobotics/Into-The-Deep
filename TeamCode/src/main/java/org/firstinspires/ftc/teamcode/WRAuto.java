package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Autonomous(name = "WRAuto")
public class WRAuto extends LinearOpMode {
    private Drivetrain drivetrain;
    private Arm arm;
    private Intake intake;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize subsystems
        drivetrain = new Drivetrain(hardwareMap);
        arm = new Arm(hardwareMap);
        intake = new Intake(hardwareMap);

        // Wait for the game to start
        waitForStart();

        drivetrain.drive(0.1, 0);
        Thread.sleep(500);
        drivetrain.drive(0, 0);
    }
}
