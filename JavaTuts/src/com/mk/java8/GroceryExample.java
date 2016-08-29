package com.mk.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GroceryExample {

	public static void main(String[] args) {
		
		
		List<Transaction> transList = Arrays.asList(new Transaction(1,TransactionType.GROCERY, 20),
				new Transaction(2,TransactionType.GROCERY, 90),
				new Transaction(3,TransactionType.FOOD, 20),
				new Transaction(4,TransactionType.GROCERY, 30),
				new Transaction(5,TransactionType.GROCERY, 40),
				new Transaction(6,TransactionType.GROCERY, 10));
		
		List<Transaction> groceryList = new ArrayList<>();
		for (Transaction transaction : transList) {
			if(transaction.getTransactionType().equals(TransactionType.GROCERY)){
				groceryList.add(transaction);
			}
		}
		
		for (int j = 0; j < groceryList.size(); j++) {
			System.out.print(groceryList.get(j).getTransactionId());
		}
		System.out.println("");
		Collections.sort(groceryList, new Comparator<Transaction>() {
			public int compare(Transaction t1, Transaction t2){
				return t2.getTransactionValue() > t1.getTransactionValue() ? 1 : -1;
			}
		});
		
		List<Long> transIds = new ArrayList<>();
		for (Transaction trans : groceryList) {
			transIds.add(trans.getTransactionId());
		}
	
		for (int i = 0; i < transIds.size(); i++) {
			System.out.print(transIds.get(i));
		}
		System.out.println("");
		List<Long> ids = transList.stream()
				.filter(x -> x.getTransactionType().equals(TransactionType.GROCERY))
				.sorted(Comparator.comparing(Transaction::getTransactionValue).reversed())
				.map(Transaction::getTransactionId)
				.collect(Collectors.toList());
		ids.forEach(System.out::print);
	}
}

class Transaction {
	
	private long transId;
	private TransactionType transactionType;
	private double value;
	
	Transaction(long id, TransactionType type, double value){
		this.transId = id;
		this.transactionType = type;
		this.value = value;
	}
	
	public long getTransactionId(){
		return transId;
	}
	
	public TransactionType getTransactionType()
	{
		return this.transactionType;
	}
	
	public double getTransactionValue()
	{
		return this.value;
	}
}

enum TransactionType{
	
	GROCERY,FOOD;
}
