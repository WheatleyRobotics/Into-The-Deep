package org.firstinspires.ftc.teamcode.subsystems;


import android.sax.StartElementListener;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Arm {
    private DcMotor ArmMotor;
    private static final double ArmSpeedUP = 1;
    private static final double ArmSpeedDown = -0.2;

    //pid
    public int targetPos = 0;
    private static double Kp = 0.0009; //0.0009
    private static double Ki = 0;
    private static double Kd = 0;
    private double integralSum = 0;
    private double lastError = 0;

    //Presets
    private final int high = 2150;
    private final int chamber = 350;
    private final int dontSlam = 700;
    private final int normal = 80;
    private final int scoreChamber = 1400;

    //Manuel
    int change = 5;
    boolean overidebol = false;

    public Arm(HardwareMap hardwareMap){
        ArmMotor = hardwareMap.get(DcMotor.class, "ArmMotor");

        ArmMotor.setDirection(DcMotor.Direction.REVERSE);
    }
    // 2500 - 20 = 2480
    public void PID(int tp){
        ElapsedTime timer = new ElapsedTime();
        if(ArmMotor.getCurrentPosition() <= tp - 20 || ArmMotor.getCurrentPosition() >= tp + 20){
            int encoderPosition = ArmValue();
            int error = tp - encoderPosition;
            double derivative = (error - lastError) / timer.seconds();
            integralSum = integralSum + (error * timer.seconds());
            double pout = (Kp * error) + (Ki * integralSum) + (Kd * derivative);
            ArmMotor.setPower(pout);

            lastError = error;
            // reset the timer for next time
            timer.reset();
        }
    }

    public void high(){
        if (overidebol == true) {
            change *= 5;
        }
        targetPos = high+change;
    }

    public void chamber(){
        if (overidebol == true) {
            change *= 5;
        }
        targetPos = chamber+change;
    }

    //public void dontSlam(){
    //    targetPos = dontSlam;
    //}

    public void normal(){
        if (overidebol == true) {
            change *= 5;
        }
        targetPos = normal+change;
    }

    public void scoreChamber(){
        if (overidebol == true) {
            change *= 5;
        }
        targetPos = scoreChamber+change;
    }

    public void MoveUp(){
        ArmMotor.setPower(ArmSpeedUP);
    }

    public void MoveDown(){
        ArmMotor.setPower(ArmSpeedDown);
    }

    public void resetchange(){
        change = 5;
    }

    public void AutoMoveDown(){
        ArmMotor.setPower(-1);
    }

    public void overide(){
        overidebol = true;
    }

    public void overidefalse(){
        overidebol = false;
    }

    public boolean tel(){
        return overidebol;
    }

    public void AutoArmStop(){
        ArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ArmMotor.setPower(0);
    }

   public void ArmStop(){
        ArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ArmMotor.setPower(0);
        ArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public int ArmValue(){
        return ArmMotor.getCurrentPosition();
    }

    public void ResetArm(){
        ArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
