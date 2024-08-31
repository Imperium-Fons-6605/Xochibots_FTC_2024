package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    DcMotor motor;

    public Intake(HardwareMap hardwareMap){
        motor =  hardwareMap.get(DcMotor.class, "Intake");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void run(){
        motor.setPower(-1);
    }
    public void stop(){
        motor.setPower(0);
    }

    public void reverse(){
        motor.setPower(1);
    }


}
