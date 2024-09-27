package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;
import java.util.Optional;

public class Camera{
    private VisionPortal visionPortal;
    private AprilTagProcessor aprilTagProcessor;

    /**
     * Initializes the camera subsystem with the given HardwareMap and camera name.
     *
     * @param hardwareMap the hardware map from the OpMode
     * @param cameraName  the name of the camera as configured in the Robot Configuration
     */
    public Camera(HardwareMap hardwareMap, String cameraName) {
        // Create the AprilTag processor with default settings
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();

        // Get the camera from the hardware map
        WebcamName webcamName = hardwareMap.get(WebcamName.class, cameraName);

        // Create the VisionPortal with the camera and processor
        visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, aprilTagProcessor);
    }

    /**
     * Gets the robot's pose from the AprilTag detections.
     *
     * @return an Optional containing the robot's Pose3D if a detection is available, or an empty Optional
     */
    public Optional<Pose3D> getRobotPose() {
        List<AprilTagDetection> detections = aprilTagProcessor.getDetections();

        for (AprilTagDetection detection : detections) {
            if (detection.metadata != null && detection.robotPose != null) {
                return Optional.of(detection.robotPose);
            }
        }

        return Optional.empty();
    }

    /**
     * Stops the camera and releases resources.
     */
    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
            visionPortal = null;
        }
    }

    // Additional methods can be added here for more functionality
}