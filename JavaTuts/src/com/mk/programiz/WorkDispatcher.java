package com.mk.programiz;

import java.util.PriorityQueue;

abstract class WorkItem implements Comparable<WorkerItem> {

	int id;

	WorkItem(int id) {
		this.id = id;
	}

	abstract void unitOfWork();

	@Override
	public int compareTo(WorkerItem o) {
		if (this.id == o.id) {
			return 0;
		} else {
			return (this.id > o.id) ? -1 : 1;
		}
	}
}

public class WorkDispatcher implements Runnable {

	private PriorityQueue<WorkItem> queue = new PriorityQueue<>();

	void addElement(WorkItem wi) {
		queue.offer(wi);
	}

	WorkItem getElement() {
		return queue.poll();
	}

	int getQueueSize() {
		return queue.size();
	}

	public static void main(String[] args) throws Exception {

		WorkDispatcher wd = new WorkDispatcher();

		WorkItem wi1 = new WorkerItem(4);
		WorkItem wi2 = new WorkerItem(3);
		wd.addElement(wi1);
		wd.addElement(wi2);

		Thread t = new Thread(wd);
		t.start();

		for (int i = 1; i < 5; i += 3) {
			WorkItem wi3 = new WorkerItem(i);
			wd.addElement(wi3);
			WorkItem wi4 = new WorkerItem(i * 4);
			wd.addElement(wi4);
			WorkItem wi5 = new WorkerItem(i * 6);
			wd.addElement(wi5);
			System.out.println("Adding WorkItems ");
			Thread.sleep(10000);
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (getQueueSize() != 0) {
					WorkItem temp = getElement();
					temp.unitOfWork();
				} else {
					System.out.println("Waiting ");
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}