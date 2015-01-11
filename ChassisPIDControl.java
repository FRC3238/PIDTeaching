package org.usfirst.frc.team3238.robot;

import edu.wpi.first.wpilibj.Timer;

public class ChassisPIDControl
{
    static double cummulativeError = 0;
    static double motorPower;
    static double time;
    static double oldTime = 0;
    static double timeDifference;

    static double getMotorValue(double error, double pConstant, double Iconstant)
    {
        time = Timer.getFPGATimestamp();
        if(oldTime == 0)
        {
            timeDifference = 0;
        } else
        {
            timeDifference = time - oldTime;
        }
        cummulativeError += error;
        if(error > 0)
        {
            motorPower = -(error * pConstant + cummulativeError * Iconstant
                    * timeDifference);
        }
        if(error < 0)
        {
            motorPower = 0;
        }
        oldTime = time;
        return motorPower;
    }

    static void reinit()
    {
        cummulativeError = 0;
        oldTime = 0;
    }
}
