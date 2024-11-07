package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.ArmTape;

@Autonomous(name = "WRAuto")
public class WRAuto extends LinearOpMode {
    private Drivetrain drivetrain;
    private Intake intake;
    private Arm arm;
    private ArmTape armTape;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize subsystems
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
        arm = new Arm(hardwareMap);
        armTape = new ArmTape(hardwareMap);


        // Wait for the game to start
        waitForStart();

        /*
        drivetrain.drive(0.1, 0);
        Thread.sleep(500);
        drivetrain.drive(0, 0);
         */
    }
}
