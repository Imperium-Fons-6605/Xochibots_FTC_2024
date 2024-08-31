package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.Elevator;

@TeleOp(name = "SubsystemTest", group = "Sensor")
public class SubsystemsTest extends LinearOpMode {

    Servo outtakeLeader;
    Servo outtakeFollower;
    Servo outtakeDoor;
    Servo airplaneLatch;

    Elevator elevator;
    @Override
    public void runOpMode() throws InterruptedException {
        outtakeLeader = hardwareMap.get(Servo.class, "servoizq");
        outtakeFollower = hardwareMap.get(Servo.class, "servoder");
        airplaneLatch = hardwareMap.get(Servo.class, "airplane");
        outtakeDoor = hardwareMap.get(Servo.class, "door");

        outtakeFollower.setDirection(Servo.Direction.REVERSE);

        elevator = new Elevator(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.a){
                elevator.setLevel(0);
            } else if (gamepad1.b){
                elevator.setLevel(1);
            } else if (gamepad1.y){
                elevator.setLevel(2);
            }

            if(gamepad1.dpad_right) {
                outtakeFollower.setPosition(0);
                outtakeLeader.setPosition(0);

            } else if (gamepad1.dpad_down){
                outtakeFollower.setPosition(0.3);
                outtakeLeader.setPosition(0.3);
            }


            if(gamepad1.left_trigger > 0.5){
                outtakeDoor.setPosition(0.1);
                airplaneLatch.setPosition(0.1);
            } else if (gamepad1.left_trigger <= 0.5){
                outtakeDoor.setPosition(0.3);
                airplaneLatch.setPosition(0.3);
            }

            elevator.update();

        }

    }

    public void sendTelemetry(){
        telemetry.addData("elevator", elevator.getPosition());


    }
}
