package com.mk.programiz;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Futures {

	public static void main(String[] args) throws Exception {

		CompletableFuture<String> cf = new CompletableFuture<>();

		cf.runAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			System.out.println("Im running in a seperate thread");
//			cf.complete("Done");
		});
		
		System.out.println("Main thread 1");
		System.out.println("Main thread");
		System.out.println(cf.get());
	}

}
