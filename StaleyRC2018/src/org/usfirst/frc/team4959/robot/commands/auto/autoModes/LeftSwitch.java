package org.usfirst.frc.team4959.robot.commands.auto.autoModes;

import org.usfirst.frc.team4959.robot.commands.auto.autoCommands.Delay;
import org.usfirst.frc.team4959.robot.commands.auto.autoCommands.DriveTurn;
import org.usfirst.frc.team4959.robot.commands.auto.autoCommands.GyroTurning;
import org.usfirst.frc.team4959.robot.util.FieldDimensions;
import org.usfirst.frc.team4959.robot.util.PlateColorChecker;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Start on the left side of the field and aim to put a cube in the left side of
 * the switch. If the switch is not on our side, place the power cube in the
 * correct scale side.
 * 
 * DriveTurn(Inches, Power, Turn, Time) 
 * GyroTurning(Angle)
 */
public class LeftSwitch extends CommandGroup {

	public LeftSwitch() {

		// Start raising elevator

		// If left switch is ours
		if (PlateColorChecker.leftSwitchColor()) {
			addSequential(new DriveTurn(FieldDimensions.DS_TO_SWITCH, 0.85, 0, 5)); // Drive Forward to switch
			// Drop power cube into switch
			addSequential(new Delay(1.0));
			addSequential(new DriveTurn(-30, -0.75, 0, 2)); // Back off the switch
			// Turn to around the switch
		}
		// If right switch is ours
		else {
			addSequential(new DriveTurn(130, 0.99, 0, 2)); // Goes straight
			addSequential(new DriveTurn(50, 0.8, -0.75, 3)); // Moves forward while turning left
			addSequential(new DriveTurn(50, 0.8, 0.75, 3)); // Moves forward while turning right to straighten back out
			addSequential(new DriveTurn(30, 0.9, 0, 2)); // Move past the switch

			// If left scale is ours
			if (PlateColorChecker.leftScaleColor()) {
				addSequential(new DriveTurn(75, 1, 0, 4)); // Move all the way to the scale
				addSequential(new GyroTurning(90)); // Turn towards the scale
				// Place the power cube
				addSequential(new DriveTurn(-30, -0.5, 0, 2)); // Back off the scale }
			}
			// If right scale is ours
			else {
				addSequential(new DriveTurn(30, 0.9, 0, 4)); // Move to between the switch and scale
				addSequential(new DriveTurn(20, 0.8, 0.6, 5)); // Turn right
				addSequential(new DriveTurn(60, 1, 0, 5)); // Drive to the right side of the scale
				addSequential(new GyroTurning(-90)); // Turn towards the scale
				// Place the power cube
				addSequential(new DriveTurn(-30, -0.5, 0, 2)); // Back off the scale
			}
		}
	}
}
