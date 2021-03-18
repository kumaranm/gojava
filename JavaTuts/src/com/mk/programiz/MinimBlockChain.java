package com.mk.programiz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

class MinimBlock {

	private int index;
	private Date dateTime;
	private String data;
	private String previousHash;
	private String hash;
	private long nounce;
	private int difficulty;

	public MinimBlock(int index, Date dateTime, String data, int difficulty) {
		this.index = index;
		this.dateTime = dateTime;
		this.data = data;
		this.previousHash = "genesis";
		//proof of work or mining
		this.nounce = 0; //random
		this.hash = mineBlock(difficulty) ;//calculatehash();
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public String getHash() {
		return hash;
	}

	public void setPreviousHash(String prevHash) {
		previousHash = prevHash;
		mineBlock(difficulty);
//		hash = calculatehash();
	}

	public String calculatehash() {
		String block = index + String.valueOf(dateTime) + data + previousHash + nounce;
		return DigestUtils.sha256Hex(block);
	}

	public String mineBlock(int difficulty){
		String str = "";
		for (int j = 0; j < difficulty; j++) {
			str+= "0";
		}
			
		while(!this.calculatehash().substring(0, difficulty).equals(str)){
			nounce++;
			this.hash =  this.calculatehash();
		}
		System.out.println("Block Mined: " + this.hash);
		return this.hash;
	}
	
	public String toString() {
		return "[" + index + "|" + String.valueOf(dateTime) + "|" + data + "|" + previousHash + "|" + hash + "]";
	}
}

public class MinimBlockChain {

	private List<MinimBlock> chain = new ArrayList<>();
	private int difficulty;

	public MinimBlockChain(int difficulty) {
		this.difficulty = difficulty;
		chain.add(createGenesisBlock());
	}

	/*private int getDifficulty(){
		return difficulty;
	}*/
	
	private MinimBlock createGenesisBlock() {
		return new MinimBlock(0, new Date(), "Genesis", difficulty);
	}

	public MinimBlock getlatestBlock() {
		return chain.get(chain.size() - 1);
	}

	public void addBlock(MinimBlock block) {
		block.setPreviousHash(chain.get(chain.size() - 1).getHash());
		chain.add(block);
	}

	public void showChain() {
		for (MinimBlock minimBlock : chain) {
			System.out.println(minimBlock);
		}
	}

	public boolean isChainValid() {
		for (int i = 1; i < chain.size(); i++) {

			MinimBlock curr = chain.get(i);
			MinimBlock prev = chain.get(i - 1);

			if (!curr.getHash().equals(curr.calculatehash())) {
				// valid hash of current block
				return false;
			}
			if (!curr.getPreviousHash().equals(prev.getHash())) {
				// check prev hash
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

		int difficulty = 8;
		MinimBlockChain chain = new MinimBlockChain(difficulty);
		chain.addBlock(new MinimBlock(1, new Date(), "amount:4", difficulty));
		chain.addBlock(new MinimBlock(2, new Date(), "amount:100", difficulty));

		chain.showChain();
		System.out.println(chain.isChainValid());
	}

}
