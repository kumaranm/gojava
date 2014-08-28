package com.mk.pattern.command;

import com.mk.pattern.command.model.Trunk;

public class CloseTrunkCommand implements Command {

	private Trunk trunk;

	public CloseTrunkCommand(Trunk trunk) {
		this.trunk = trunk;
	}

	@Override
	public void execute() {
		trunk.off();

	}
}
