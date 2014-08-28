package com.mk.pattern.command;

import com.mk.pattern.command.model.Garage;

public class CloseGarageCommand implements Command {

	private Garage garage;

	public CloseGarageCommand(Garage garage) {
		this.garage = garage;
	}

	@Override
	public void execute() {
		garage.off();

	}
}
