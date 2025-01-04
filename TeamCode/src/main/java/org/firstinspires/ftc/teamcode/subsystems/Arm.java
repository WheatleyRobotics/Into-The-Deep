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
    private static double Kp = 0.009; //0.01
    private static double Ki = 0;
    private static double Kd = 0;
    private double integralSum = 0;
    private double lastError = 0;

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
        targetPos = 2200;
    }

    public void chamber(){
        targetPos = 300;
    }

    public void dontSlam(){
        targetPos = 100;
    }

    public void normal(){
        targetPos = 80;
    }

    public void scoreChamber(){
        targetPos = 1400;
    }

    public void MoveUp(){
        ArmMotor.setPower(ArmSpeedUP);
    }

    public void MoveDown(){
        ArmMotor.setPower(ArmSpeedDown);
    }

    public void AutoMoveDown(){
        ArmMotor.setPower(-1);
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
}
