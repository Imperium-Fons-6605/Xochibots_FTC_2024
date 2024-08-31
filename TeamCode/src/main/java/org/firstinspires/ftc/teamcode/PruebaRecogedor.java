package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Recogedor", group = "Sensor")
public class PruebaRecogedor extends LinearOpMode {


    private DcMotorEx intake;
    private Servo output_izq;
    private Servo output_der;
    private DcMotorEx elevator;



    @Override
    public void runOpMode() throws InterruptedException {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        output_der = hardwareMap.get(Servo.class, "servoder");
        output_izq = hardwareMap.get(Servo.class, "servoizq");
        elevator = hardwareMap.get(DcMotorEx.class, "elevator");

        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);





        waitForStart();
        while(opModeIsActive()) {
            if(gamepad1.a){
                intake.setPower(1);
            } else if(gamepad1.b){
                intake.setPower(-1);
            }else {
                intake.setPower(0);
            }

            if(gamepad1.dpad_right){
                output_izq.setPosition(0);
                output_der.setPosition(0);
            } else if(gamepad1.dpad_up){
                output_izq.setPosition(90);
                output_der.setPosition(90);
            } else if(gamepad1.dpad_left){
                output_izq.setPosition(180);
                output_der.setPosition(180);
            }


                /*
                if(gamepad2.a){
                    elevator.setLevel(0);
                }
                if(gamepad2.b){
                    elevator.setLevel(1);
                }
                if(gamepad2.y){
                    elevator.setLevel(2);
                }



                elevator.update();
                sendTelemetry();

                */


            sendTelemetry();
            telemetry.update();

        }
    }

    public void sendTelemetry(){
        telemetry.addData("intake", intake.getVelocity());

    }
}
