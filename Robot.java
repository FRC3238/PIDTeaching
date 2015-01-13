package org.usfirst.frc.team3238.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class Robot extends IterativeRobot
{
    AnalogInput ultrasonicSensor;
    Jaguar jagLeft;
    Jaguar jagRight;
    RobotDrive driveControl;
    Joystick joystick;

    double m_setpoint;
    double m_pConstant;
    double m_iConstant;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        ultrasonicSensor = new AnalogInput(0);
        jagLeft = new Jaguar(1);
        jagRight = new Jaguar(0);
        driveControl = new RobotDrive(jagLeft, jagRight);
        joystick = new Joystick(0);
    }

    public void autonomousInit()
    {
        ArrayList<String> fileContents = FileReader.readFile("settings.txt");
        m_setpoint = Double.parseDouble(fileContents.get(0));
        m_pConstant = Double.parseDouble(fileContents.get(1));
        m_iConstant = Double.parseDouble(fileContents.get(2));
        System.out.println(m_setpoint);
        System.out.println(m_pConstant);
        System.out.println(m_iConstant);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {
        double distance = 2.678677012 * ultrasonicSensor.getVoltage() 
            + 0.0204464172;
        double error = distance - m_setpoint;
        driveControl.arcadeDrive(-(ChassisPIDControl.getMotorValue(error,
                m_pConstant, m_iConstant)), 0);
        // driveControl.arcadeDrive(0, 0);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        driveControl.arcadeDrive(-joystick.getY(), -joystick.getTwist(), true);
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic()
    {

    }

    public void disabledPeriodic()
    {
        System.out.println(2.678677012 * ultrasonicSensor.getVoltage() 
            + 0.0204464172);
    }
}
