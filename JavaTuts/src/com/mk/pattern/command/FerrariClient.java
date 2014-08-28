package com.mk.pattern.command;

public class FerrariClient {
	
	public static void main(String[] args) {
		final CarKey ferrariSwitchbladeKey = new CarKey();
		ferrariSwitchbladeKey.openHood();
		ferrariSwitchbladeKey.openGarage();
		ferrariSwitchbladeKey.openTrunk();
		ferrariSwitchbladeKey.openDoor();
		ferrariSwitchbladeKey.enableAlarm();
		System.out.println("-------------------------------");
		ferrariSwitchbladeKey.closeHood();
		ferrariSwitchbladeKey.closeGarage();
		ferrariSwitchbladeKey.closeTrunk();
		ferrariSwitchbladeKey.closeDoor();
		ferrariSwitchbladeKey.disableAlarm();
	}
}