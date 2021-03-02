package com.mk.programiz;

enum CpuType {
	AMD, INTEL;
}

enum Manufacturer {
	FOXCONN, SAMSUNG
}

interface CPU {

	public void process();
}

interface MFactory {
	public CPU produceCPU();
}


class AMDCPU implements CPU {

	@Override
	public void process() {
		System.out.println("Amd is processing");
	}
}
class IntelCPU implements CPU {

	@Override
	public void process() {
		System.out.println("Intel is processing");
	}
}

class SamsungFactory implements MFactory {

		@Override
	public CPU produceCPU() {
		return new AMDCPU(); 
	}
}

class FoxConFactory implements MFactory {

	@Override
	public CPU produceCPU() {
		return new IntelCPU(); 
	}
}

class Computer {
	
	CPU cpu;
	public Computer(MFactory factory) {
		cpu = factory.produceCPU();
		cpu.process();
	}
}

class CreateUserAbsFactory {

	public static void main(String[] args) {
		int sys = 1;
		Computer comp; 
		if(sys == 0){
			comp = new Computer(new SamsungFactory());
		}
		if(sys == 1){
			comp = new Computer(new FoxConFactory());
		}
	}

}
