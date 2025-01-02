package org.firstinspires.ftc.teamcode.Tele;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.ArmTape;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@TeleOp(name = "WRCode")
public class CompWROP extends LinearOpMode {
    private Drivetrain drivetrain;
    private Intake intake;
    private Arm arm;
    private ArmTape armTape;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        // Initialize subsystems
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
        arm = new Arm(hardwareMap);
        armTape = new ArmTape(hardwareMap);

        // Wait for the game to start
        waitForStart();

        // Main loop
        while (opModeIsActive()) {
            // Driver controls
            // Drivetrain control
            double Vertical = -gamepad1.left_stick_y;
            double Horizontal = gamepad1.left_stick_x;
            double Pivot = gamepad1.right_stick_x;
            drivetrain.drive(Vertical, Horizontal, Pivot);

            if(gamepad1.a){
                drivetrain.resetGyro();
            }

            //Operator Controls
            //Intake control
            if(gamepad2.right_bumper){
                intake.IntakeIn();
            }
            else if(gamepad2.left_bumper){
                intake.IntakeOut();
            }
            else{
                intake.StopIntake();
            }

            //Arm Control
            if(gamepad2.dpad_up){
                arm.MoveUp();
            }
            else if(gamepad2.dpad_down){
                arm.MoveDown();
            }
            else{
                arm.ArmStop();
            }

            //Arm Tape Control
            if(gamepad2.y){
                armTape.MoveUp();
            }
            else if(gamepad2.a){
                armTape.MoveDown();
            }
            else{
                armTape.ArmTapeStop();
            }

            //Arm preset
            if(gamepad2.x){
                if(arm.ArmValue() < 2000){
                    arm.MoveUp();
                }
            }

            //Telemetry Data
            telemetry.addData("Status", "Enabled");
            telemetry.addData("Arm Motor Pos:", arm.ArmValue());
            telemetry.addData("Run Time:", runtime);
            telemetry.addData("Gyro:", drivetrain.GyroValues());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", drivetrain.leftFrontPower(), drivetrain.rightFrontPower());
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", drivetrain.leftBackPower(), drivetrain.rightBackPower());
            telemetry.update();
        }
    }
}