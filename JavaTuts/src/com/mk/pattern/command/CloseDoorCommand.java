package com.mk.pattern.command;

import com.mk.pattern.command.model.Door;

public class CloseDoorCommand implements Command {

	private Door door;

	public CloseDoorCommand(Door door) {
		this.door = door;
	}

	@Override
	public void execute() {
		door.off();

	}
}
