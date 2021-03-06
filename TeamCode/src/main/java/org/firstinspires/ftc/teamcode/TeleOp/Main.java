package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Robot.MainRobot;

@TeleOp(name = "Main", group = "")
public class Main extends LinearOpMode {
    private MainRobot robot;

    boolean BtnPresedDuckArm;
    boolean ArmState = false;
    boolean ArmState1 = false;


    @Override
    public void runOpMode () throws InterruptedException{
        String[] enabledComponents = {"duckArm", "arm"};
        robot = new MainRobot(hardwareMap, telemetry, enabledComponents, this);

        robot.logging.setLog("state", "Initializing");
        robot.startThreads();
        robot.initDrive(DcMotor.RunMode.RUN_USING_ENCODER, DcMotor.ZeroPowerBehavior.FLOAT, new Pose2d(0, 0, 0));
        robot.logging.setLog("state", "Initialized, waiting for start");

        waitForStart();

        robot.logging.setLog("state", "Running");
        controlLoop();

        robot.stopRobot();
        robot.logging.setLog("state", "Stopped");
    }

    private void controlLoop() {
        while (opModeIsActive()){
            Drive();
            arm();
            DuckArm();


        }
    }

    public void DuckArm(){
        if(gamepad1.a){
            robot.duckArm.moveArmForward();
        } else if (gamepad1.b) {
            robot.duckArm.moveArmBackward();
        } else {
            robot.duckArm.stopArm();
        }
    }

    public void arm(){
         if (gamepad1.left_trigger >= 0.5){
             robot.arm.armDown();
         } else if (gamepad1.left_bumper){
             robot.arm.armUp();
         } else{
             robot.arm.stopArm();
         }
         if (gamepad1.right_trigger >= 0.5){
             robot.arm.gripperClose();
         }
        if (gamepad1.right_bumper){
             robot.arm.gripperOpen();
        }
    }
    public void Drive(){
        //joystick
        double LY = -1 * gamepad1.left_stick_y;
        double LX = gamepad1.left_stick_x;
        double RX = gamepad1.right_stick_x;

        //algemene formule
        double LAchter = LY - LX + RX;
        double RAchter = LY + LX - RX;
        double LVoor = LY + LX + RX;
        double RVoor = LY - LX - RX;
        //let op volgorede testen
        robot.drive.setMotorPowers(LVoor, LAchter, RAchter, RVoor);
    }
}
