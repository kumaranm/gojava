package com.mk.pattern.command;

import com.mk.pattern.command.model.Alarm;

public class EnableAlarmCommand implements Command {

	private Alarm alarm;

	public EnableAlarmCommand(Alarm alarm) {
		this.alarm = alarm;
	}

	@Override
	public void execute() {
		alarm.on();

	}
}
