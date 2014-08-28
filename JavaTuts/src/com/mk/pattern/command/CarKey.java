package com.mk.pattern.command;

import com.mk.pattern.command.model.Alarm;
import com.mk.pattern.command.model.Door;
import com.mk.pattern.command.model.Garage;
import com.mk.pattern.command.model.Hood;
import com.mk.pattern.command.model.Trunk;

public class CarKey {
	private MicroChip microChip;

	public CarKey() {
		final int commandQuantity = 5;
		microChip = new MicroChip(commandQuantity);

		final Hood hood = new Hood();
		final OpenHoodCommand openHoodCmd = new OpenHoodCommand(hood);
		final CloseHoodCommand closeHoodCmd = new CloseHoodCommand(hood);
		microChip.configureCommand(0, openHoodCmd, closeHoodCmd);

		final Door door = new Door();
		final OpenDoorCommand openDoorCmd = new OpenDoorCommand(door);
		final CloseDoorCommand closeDoorCmd = new CloseDoorCommand(door);
		microChip.configureCommand(1, openDoorCmd, closeDoorCmd);

		final Garage garage = new Garage();
		final OpenGarageCommand openGarageCmd = new OpenGarageCommand(garage);
		final CloseGarageCommand closeGarageCmd = new CloseGarageCommand(garage);
		microChip.configureCommand(2, openGarageCmd, closeGarageCmd);

		final Trunk trunk = new Trunk();
		final OpenTrunkCommand openTrunkCmd = new OpenTrunkCommand(trunk);
		final CloseTrunkCommand closeTrunkCmd = new CloseTrunkCommand(trunk);
		microChip.configureCommand(3, openTrunkCmd, closeTrunkCmd);

		final Alarm alarm = new Alarm();
		final EnableAlarmCommand enableAlarmCmd = new EnableAlarmCommand(alarm);
		final DisableAlarmCommand disableAlarmCmd = new DisableAlarmCommand(alarm);
		microChip.configureCommand(4, enableAlarmCmd, disableAlarmCmd);
	}

	public void openHood() {
		microChip.executeOnCommand(0);
	}

	public void closeHood() {
		microChip.executeOffCommand(0);
	}

	public void openDoor() {
		microChip.executeOnCommand(1);
	}

	public void closeDoor() {
		microChip.executeOffCommand(1);
	}

	public void openGarage() {
		microChip.executeOnCommand(2);
	}

	public void closeGarage() {
		microChip.executeOffCommand(2);
	}

	public void openTrunk() {
		microChip.executeOnCommand(3);
	}

	public void closeTrunk() {
		microChip.executeOffCommand(3);
	}

	public void enableAlarm() {
		microChip.executeOnCommand(4);
	}

	public void disableAlarm() {
		microChip.executeOffCommand(4);
	}

}