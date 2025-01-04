package org.firstinspires.ftc.teamcode.Autos;

import android.content.res.Resources;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.ArmNormal;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.ArmTape;

@Autonomous(name = "Liam Blackmail Auto")
public class WRAuto extends LinearOpMode {
    private Drivetrain drivetrain;
    private Intake intake;
    private ArmNormal arm;
    private ArmTape armTape;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize subsystems
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
        arm = new ArmNormal(hardwareMap);
        armTape = new ArmTape(hardwareMap);


        // Wait for the game to start
        waitForStart();

//        drivetrain.drive(1,0,0);
//        Thread.sleep(360);
//        drivetrain.drive(0,0,0);
//        Thread.sleep(10);
        arm.MoveUp();
        Thread.sleep(700);
        arm.ArmStop();
        Thread.sleep(10);
        armTape.ArmMoveUp();
        Thread.sleep(500);
        armTape.AutoMoveUp();
        Thread.sleep(370);
        armTape.AutoArmTapeStop();
        intake.IntakeOut();
        Thread.sleep(2000);
        intake.StopIntake();
        Thread.sleep(50);
        //Auto go back to normal
        armTape.ArmMoveDown();
        Thread.sleep(300);
        armTape.AutoMoveDown();
        Thread.sleep(360);
        armTape.ArmTapeStop();
        Thread.sleep(10);
        arm.AutoMoveDown();
        Thread.sleep(500);
        arm.AutoArmStop();
        Thread.sleep(2000);


        //intake.StopIntake();

        /*
        drivetrain.drive(0.1, 0);
        Thread.sleep(500);
        drivetrain.drive(0, 0);
         */
    }
}
