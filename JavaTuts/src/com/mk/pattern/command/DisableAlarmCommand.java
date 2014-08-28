package com.mk.pattern.command;

import com.mk.pattern.command.model.Alarm;

public class DisableAlarmCommand implements Command {

	private Alarm alarm;

	public DisableAlarmCommand(Alarm alarm) {
		this.alarm = alarm;
	}

	@Override
	public void execute() {
		alarm.off();

	}
}
