package com.mk.pattern.command;

import com.mk.pattern.command.model.Door;

public class OpenDoorCommand implements Command {

	private Door door;

	public OpenDoorCommand(Door door) {
		this.door = door;
	}

	@Override
	public void execute() {
		door.on();

	}
}
