package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.HardwareConfigIds;

public class DuckArm extends RobotComponent{

    private DcMotor DuckArm;

    public boolean ArmState = false;

    public DuckArm(HardwareMap hardwareMap, MainRobot inputRobot) {
        super(inputRobot);

        //initializing method
        DuckArm = hardwareMap.get(DcMotor.class, HardwareConfigIds.duckArm);
    }

    @Override
    public void startThreads(){
        //start threads here if necessary
    }

    public void moveArm (){
        DuckArm.setPower(1);
        ArmState = true;

    }

    public void stopArm (){
        DuckArm.setPower(0);
        ArmState = false;
    }
}