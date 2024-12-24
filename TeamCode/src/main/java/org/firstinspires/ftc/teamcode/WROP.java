package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.ArmTape;

@TeleOp(name = "WRCode")
public class WROP extends LinearOpMode {

    private Drivetrain drivetrain;
    private Intake intake;
    private Arm arm;
    private ArmTape armTape;

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
            // Drivetrain control
            double Vertical = -gamepad1.left_stick_y;
            double Horizontal = -gamepad1.left_stick_x;
            double Pivot = gamepad1.right_stick_x;
            drivetrain.drive(Vertical, Horizontal, Pivot);

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

            if(gamepad2.x){
                arm.MoveUp();
                sleep(700);
                arm.ArmStop();
                sleep(10);
                armTape.MoveUp();
                sleep(1250);
                armTape.AutoMoveUp();
                sleep(370);
                armTape.AutoArmTapeStop();
                intake.IntakeOut();
                sleep(2000);
                intake.StopIntake();
                sleep(50);
                //Arm go back to normal
                armTape.MoveDown();
                sleep(1250);
                armTape.AutoMoveDown();
                sleep(360);
                armTape.ArmTapeStop();
                sleep(10);
                arm.AutoMoveDown();
                sleep(500);
                arm.AutoArmStop();
                sleep(2000);
            }

//            /*
//            // Arm control
//            if (gamepad1.y) {
//                arm.moveUp();
//            } else if (gamepad1.a) {
//                arm.moveDown();
//            } else {
//                arm.stop();
//            }
//
//            // Intake control
//            if (gamepad1.right_bumper) {
//                intake.openIntake();
//            } else if (gamepad1.left_bumper) {
//                intake.closeIntake();
//            }
//
//            // Arm servo control
//            if (gamepad1.x) {
//                intake.tiltArmUp();
//                telemetry.addData("Limit Switch Status: ", "Not Pressed");
//            } else if (gamepad1.b) {
//                if(!intake.tiltArmDown()){
//                    telemetry.addData("Limit Switch Status: ", "Pressed");
//                }else{
//                    telemetry.addData("Limit Switch Status: ", "Not Pressed");
//                }
//            }
//             */

            telemetry.update();
        }
    }
}