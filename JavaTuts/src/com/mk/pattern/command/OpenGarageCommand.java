package com.mk.pattern.command;

import com.mk.pattern.command.model.Garage;

public class OpenGarageCommand implements Command {

	private Garage garage;

	public OpenGarageCommand(Garage garage) {
		this.garage = garage;
	}

	@Override
	public void execute() {
		garage.on();

	}
}
