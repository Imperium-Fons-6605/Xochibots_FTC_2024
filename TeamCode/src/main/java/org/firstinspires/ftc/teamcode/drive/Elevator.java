package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Elevator {
    DcMotor follower;
    DcMotor leader;

    double servoValue;
    PIDFController pid;
    PIDCoefficients pidConstants = new PIDCoefficients(1,0,0);

    public Elevator(HardwareMap hardwareMap){
        follower =  hardwareMap.get(DcMotor.class, "follower");
        leader = hardwareMap.get(DcMotor.class, "leader");

        pid = new PIDFController(pidConstants);

        follower.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leader.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        follower.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leader.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        follower.setTargetPosition(0);
        leader.setTargetPosition(0);

        follower.setPower(1.0);
        leader.setPower(1.0);

        follower.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leader.setMode(DcMotor.RunMode.RUN_TO_POSITION);



    }

    public void setLevel(int level){
        if(level == 0){

            follower.setTargetPosition(0);
            leader.setTargetPosition(0);

            follower.setPower(1.0);
            leader.setPower(1.0);

            follower.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leader.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } else if (level == 1){

            follower.setTargetPosition(200);
            leader.setTargetPosition(200);

            follower.setPower(1.0);
            leader.setPower(1.0);

            follower.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leader.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } else if (level == 2){

            follower.setTargetPosition(500);
            leader.setTargetPosition(500);

            follower.setPower(1.0);
            leader.setPower(1.0);

            follower.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leader.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    public void update(){
        //follower.setPower(pid.update(getPosition()));
        //leader.setPower(pid.update(getPosition()));

    }

    public double getPosition(){
        return  leader.getCurrentPosition()  * DriveConstants.DRUM_RADIUS * 2 * Math.PI/ DriveConstants.TICKS_PER_REV;

    }



    public void setRawMotor(double potencia){
        follower.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leader.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        follower.setPower(potencia);
        leader.setPower(potencia);
    }

}
