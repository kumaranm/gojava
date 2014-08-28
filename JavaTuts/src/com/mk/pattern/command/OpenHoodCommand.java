package com.mk.pattern.command;

import com.mk.pattern.command.model.Hood;

public class OpenHoodCommand implements Command {

	private Hood hood;

	public OpenHoodCommand(Hood hood) {
		this.hood = hood;
	}

	@Override
	public void execute() {
		hood.on();

	}
}
