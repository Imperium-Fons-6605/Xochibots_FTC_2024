package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.Elevator;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
@TeleOp(name = "Mecanum", group = "Sensor")
public class SampleMecanum extends LinearOpMode {

    private SampleMecanumDrive chassis;

    Servo outtake_der;
    Servo outtake_izq;
    Servo door;
    Servo airplane;
    Elevator elevator;

    DcMotor intake;

    private Pose2d poseEstimate = new Pose2d();




    @Override
    public void runOpMode() throws InterruptedException {
        chassis = new SampleMecanumDrive(hardwareMap);
        elevator = new Elevator(hardwareMap);
        outtake_der = hardwareMap.get(Servo.class, "servoder");
        outtake_izq = hardwareMap.get(Servo.class, "servoizq");
        door = hardwareMap.get(Servo.class, "door");
        intake = hardwareMap.get(DcMotor.class, "intake");
        airplane = hardwareMap.get(Servo.class, "airplane");

        //airplane.setDirection(Servo.Direction.REVERSE);

        chassis.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        while(opModeIsActive()) {

            poseEstimate = chassis.getPoseEstimate();

            Vector2d input = new Vector2d(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x
            ).rotated(-poseEstimate.getHeading());

            chassis.setWeightedDrivePower(
                    new Pose2d(
                            input.getX(),
                            input.getY(),
                            -gamepad1.right_stick_x
                    )
            );


            if(gamepad1.a){
                elevator.setRawMotor(1);
            } else if (gamepad1.y){
                elevator.setRawMotor(-1);
            } else{
                elevator.setRawMotor(0);
            }

            if(opModeIsActive()){
                door.setPosition(0.5);
            } else if (gamepad1.dpad_down){
                door
                        .setPosition(0);
            }

            if(gamepad1.left_trigger > 0.5){
                intake.setPower(1);
            } else if (gamepad1.right_trigger > 0.5){
                intake.setPower(-1);
            } else{
                intake.setPower(0);
            }

            if(gamepad1.left_bumper){
                airplane.setPosition(1);
            } else if(!gamepad1.left_bumper){
                airplane.setPosition(0.8);
            }

            elevator.update();
            chassis.update();
            sendTelemetry();

        }
    }

    public void sendTelemetry(){
        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());


        telemetry.addData("controlX", gamepad1.left_stick_x);
        telemetry.addData("controly", gamepad1.left_stick_y);
        telemetry.addData("controlrot", gamepad1.right_stick_x);
        telemetry.update();


    }
}
