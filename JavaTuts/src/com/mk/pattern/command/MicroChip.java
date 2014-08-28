package com.mk.pattern.command;

public class MicroChip {

	protected Command[] onCommands;
	protected Command[] offCommands;

	public MicroChip(int cmdQty) {
		onCommands = new Command[cmdQty];
		offCommands = new Command[cmdQty];
		NullObjectCommand nullObjCmd = new NullObjectCommand();
		for (int i = 0; i < cmdQty; i++) {
			onCommands[i] = nullObjCmd;
			offCommands[i] = nullObjCmd;
		}
	}

	public void configureCommand(int position, Command on, Command off) {
		onCommands[position] = on;
		offCommands[position] = off;
	}

	public void executeOnCommand(int position) {
		onCommands[position].execute();
	}

	public void executeOffCommand(int position) {
		offCommands[position].execute();
	}

}
