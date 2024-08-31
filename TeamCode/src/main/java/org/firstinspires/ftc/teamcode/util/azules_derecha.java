package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import java.util.Timer;


@Autonomous (name = "azul derecha")
public class azules_derecha extends LinearOpMode {

    private SampleMecanumDrive chassis;

    Pose2d poseEstimate;
    @Override
    public void runOpMode() throws InterruptedException {

        chassis = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        while(opModeIsActive()){

            poseEstimate = chassis.getPoseEstimate();

            Vector2d vector1 = new Vector2d(
                    1,
                    0
            ).rotated(-poseEstimate.getHeading());
            Vector2d vector2 = new Vector2d(
                    0,
                    1
            ).rotated(-poseEstimate.getHeading());


            chassis.trajectoryBuilder(new Pose2d())



        }

    }
}
