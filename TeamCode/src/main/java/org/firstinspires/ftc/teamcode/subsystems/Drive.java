package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.ftc.SparkFunOTOSCorrected;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dev.frozenmilk.dairy.core.FeatureRegistrar;
import dev.frozenmilk.dairy.core.dependency.Dependency;
import dev.frozenmilk.dairy.core.dependency.annotation.SingleAnnotation;
import dev.frozenmilk.dairy.core.wrapper.Wrapper;
import dev.frozenmilk.mercurial.Mercurial;
import dev.frozenmilk.mercurial.commands.Lambda;
import dev.frozenmilk.mercurial.subsystems.Subsystem;
import dev.frozenmilk.mercurial.subsystems.SubsystemObjectCell;
import kotlin.annotation.MustBeDocumented;

public class Drive implements Subsystem {
    public static final Drive INSTANCE = new Drive();
    private static DcMotorEx leftFront, leftBack, rightFront, rightBack;
    private static SparkFunOTOSCorrected otos;
    private static SparkFunOTOS.Pose2D pose;

    private Drive() { }

    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.TYPE) @MustBeDocumented @Inherited
    public @interface Attach { }

    private Dependency<?> dependency = Subsystem.DEFAULT_DEPENDENCY.and(new SingleAnnotation<>(Attach.class));

    @NonNull @Override
    public Dependency<?> getDependency() { return dependency; }

    @Override
    public void setDependency(@NonNull Dependency<?> dependency) { this.dependency = dependency; }

    @Override
    public void preUserInitHook(@NonNull Wrapper opMode) { }

    @Override
    public void postUserInitHook(@NonNull Wrapper opMode) {
        HardwareMap hwmap = opMode.getOpMode().hardwareMap;
        try {
            leftBack = hwmap.get(DcMotorEx.class, "LeftBack");
            leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception e) {
            System.err.println("LeftBack motor not connected: " + e.getMessage());
        }
        try {
            leftFront = hwmap.get(DcMotorEx.class, "LeftFront");
            leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception e) {
            System.err.println("LeftFront motor not connected: " + e.getMessage());
        }
        try {
            rightBack = hwmap.get(DcMotorEx.class, "RightBack");
        } catch (Exception e) {
            System.err.println("RightBack motor not connected: " + e.getMessage());
        }
        try {
            rightFront = hwmap.get(DcMotorEx.class, "RightFront");
        } catch (Exception e) {
            System.err.println("RightFront motor not connected: " + e.getMessage());
        }
        try {
            otos = hwmap.get(SparkFunOTOSCorrected.class, "otos");
            otos.setAngularUnit(AngleUnit.RADIANS);
            System.out.println(otos.calibrateImu(255, false));
        } catch (Exception e) {
            System.err.println("OTOS sensor not connected: " + e.getMessage());
        }
        setDefaultCommand(driveCommand());
    }

    @Override
    public void postUserInitLoopHook(@NonNull Wrapper opMode){ //if (otos.getImuCalibrationProgress() == 0){ setDefaultCommand(driveCommand()); }
    }
    @Override
    public void postUserLoopHook(@NonNull Wrapper opMode){ //if (otos.getImuCalibrationProgress() == 0){ setDefaultCommand(driveCommand()); }
    }

    public static void driveUpdate() {
        // read the gamepads
        double rightX = Mercurial.gamepad1().leftStickX().state();
        double rightY = Mercurial.gamepad1().leftStickY().state();
        double turn = Mercurial.gamepad1().rightStickX().state();

        double heading = 0;
        if (otos != null) {
            heading = otos.getPosition().h; // Use field-centric heading if OTOS exists
        }

        // Determine the control logic based on the presence of otos
        double rotX = rightX * Math.cos(-heading) - rightY * Math.sin(-heading);
        double rotY = rightX * Math.sin(-heading) + rightY * Math.cos(-heading);

        if (otos == null) {
            // Robot-centric drive: no transformation needed
            rotX = rightX;
            rotY = rightY;
        }

        // Do the kinematics math
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(turn), 1);
        double lfPower = (rotY + rotX + turn) / denominator;
        double lbPower = (rotY - rotX + turn) / denominator;
        double rfPower = (rotY - rotX - turn) / denominator;
        double rbPower = (rotY + rotX - turn) / denominator;

        // Safely set the Motor Power
        safeSetPower(leftBack, lbPower);
        safeSetPower(leftFront, lfPower);
        safeSetPower(rightBack, rbPower);
        safeSetPower(rightFront, rfPower);
    }

    private static void resetHeading(){
        if (otos != null) {
            otos.setPosition(new SparkFunOTOS.Pose2D(0, 0, 0));
        } else {
            System.err.println("OTOS sensor is not connected. Cannot reset heading.");
        }
    }

    private static void safeSetPower(DcMotorEx motor, double power) {
        if (motor != null) {
            motor.setPower(power);
        } else {
            System.err.println("Motor is not connected. Skipping power set.");
        }
    }

    @NonNull
    public static Lambda driveCommand() {
        return new Lambda("driveCommand")
                .addRequirements(INSTANCE)
                .setExecute(Drive::driveUpdate);
    }

    @NonNull
    public static Lambda zeroHeading() {
        return new Lambda("zeroHeading")
                .addRequirements(INSTANCE)
                .setInit(Drive::resetHeading);
    }
}