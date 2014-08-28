package com.mk.pattern.command;

import com.mk.pattern.command.model.Trunk;

public class OpenTrunkCommand implements Command {

	private Trunk trunk;

	public OpenTrunkCommand(Trunk trunk) {
		this.trunk = trunk;
	}

	@Override
	public void execute() {
		trunk.on();

	}
}
