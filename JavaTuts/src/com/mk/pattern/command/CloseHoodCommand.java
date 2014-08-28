package com.mk.pattern.command;

import com.mk.pattern.command.model.Hood;

public class CloseHoodCommand implements Command {

	private Hood hood;

	public CloseHoodCommand(Hood hood) {
		this.hood = hood;
	}

	@Override
	public void execute() {
		hood.off();

	}
}
