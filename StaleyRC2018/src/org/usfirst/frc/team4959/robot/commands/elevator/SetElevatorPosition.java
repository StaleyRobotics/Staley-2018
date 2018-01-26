package org.usfirst.frc.team4959.robot.commands.elevator;

import org.usfirst.frc.team4959.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevatorPosition extends Command {
	
	private double pos;

    public SetElevatorPosition(double pos) {
    	this.pos = pos;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.startPID(pos);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevator.pidOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stopPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
