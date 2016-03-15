package org.usfirst.frc.team3238.robot;

import edu.wpi.first.wpilibj.Timer;

public class ChassisPIDControl
{
    static double cumulativeError = 0,
                  motorPower;
                  time;
                  oldTime = 0;
                  timeDifference;

    static double getMotorValue(double error, double pConstant, 
        double iConstant)
    {
        time = Timer.getFPGATimestamp();
        timeDifference = 0;
        motorPower = 0;
        if(oldTime != 0)
        {
            timeDifference = time - oldTime;
        }
        cumulativeError += error;
        if(error > 0)
        {
            motorPower = -(error * pConstant) + (cumulativeError * iConstant
                    * timeDifference);
        }
        oldTime = time;
        return motorPower;
    }

    static void reinit()
    {
        cumulativeError = 0;
        oldTime = 0;
    }
}
