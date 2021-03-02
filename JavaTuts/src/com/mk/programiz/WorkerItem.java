package com.mk.programiz;

public class WorkerItem extends WorkItem  {

	WorkerItem(int id) {
		super(id);
	}

	void unitOfWork() {
		System.out.println("Executing WorkItem " + id);
	}
}
