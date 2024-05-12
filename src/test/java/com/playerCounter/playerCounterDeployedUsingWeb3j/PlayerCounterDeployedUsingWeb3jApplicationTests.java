package com.playerCounter.playerCounterDeployedUsingWeb3j;
import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.NoOpProcessor;
import com.sport.trade.wallet.contracts.*;

public class PlayerCounterDeployedUsingWeb3jApplicationTests {

	//variable declaration
	static Web3jService service;
	static Web3j web3j;
	static String PlayerCounterAddr;
	static String privatekey = "<Owner Private Key>";
	static Credentials credentials;
	static String houseAddr = "<House Address>";
	static BigInteger totalSupply = new BigInteger("1000000000000000000000");
	static PlayerToken playcounter;
	static String rpc_url = "https://rpc-mumbai.maticvigil.com";
	static String _name = "Player COunter";
	static String _symbol = "PC";
	static String[] users = new String[2];
	static String[] usersKeys = new String[2];
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//place the users address and private keys here
		users[0] = "<Wallet Address>";
		usersKeys[0] = "<Wallet Private Key>";
		//users 2
		users[1] = "<Wallet Address>";
		usersKeys[1] = "<Wallet Private Key>";
		System.out.println("Starting Tests");
		if(start()) {
			//move to the next test, deployment
			if(deploy()) {
				//move to the next test, minting
				if(mint(100)) {
					//move to the next tests, sending PCs to five users
					for(int i=0;i<users.length;i++) {
						transferToUser(users[i], "50");
						balance(users[i]);
					}
					//disabling trade
					if(pause()) {
						//enabling trade
						if(resume()) {
							//return PCs back to houseAddr
							for(int i=0;i<users.length;i++) {
								transferToHouse(users[i], usersKeys[i], "50");
							}
							System.out.println("Testing Complete");
						}
					}
					
				}
			}
		}
	}
	
	static boolean start() {
		//to ready the web3 connection to mumbai
		try {
			service = new HttpService(rpc_url);
			web3j = Web3j.build(service);
			//create the credentials for signing purposes
			BigInteger privkey = new BigInteger(privatekey, 16);
	        ECKeyPair ecKeyPair = ECKeyPair.create(privkey);
	        credentials = Credentials.create(ecKeyPair);
	        System.out.println("Connected to node successfully");
	        return true;
		}
		catch(Exception e) {e.printStackTrace();return false;}
	}
	static boolean deploy() {
		System.out.println("Deploying Player Counter");
        try {
        	TransactionManager txManager = new RawTransactionManager(web3j, credentials, 80001);
        	//arguements for deployment
        	//String _name, String _symbol, String _houseAddr, BigInteger _totalSupply
        	playcounter = PlayerToken.deploy(web3j, txManager, new DefaultGasProvider(), _name, _symbol, houseAddr, totalSupply).send();
			System.out.println("Deployed to " + playcounter.getContractAddress());
			System.out.println("Current balance of house address " + String.valueOf(playcounter.balanceOf(houseAddr).send()));
			PlayerCounterAddr = playcounter.getContractAddress();
			return true;
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
         
	}
	static boolean mint(int amount) {
		//mint a certain amount to house address
		try {
			System.out.println("Trying to mint " + String.valueOf(amount) + " " + _symbol + "s");
	        playcounter.mint(BigInteger.valueOf((long) (amount * Math.pow(10, 18)))).send();
	        System.out.println("Minted successfully");
	        System.out.println("Current balance of house address " + String.valueOf(playcounter.balanceOf(houseAddr).send()));
	        return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		}
	static boolean transferToUser(String _address, String amount) {
		//send PCs to user
		try {
			System.out.println("Transferring " + String.valueOf(amount) + " to user " + _address);
	        playcounter.sendPcToUser(_address, new BigInteger(amount + "000000000000000000")).send();
	        System.out.println("Transferred successfully");
	        return true;
		}
		catch(Exception e) {return false;}
	}
	static boolean transferToHouse(String _address, String _privateKey, String amount) {
		//return PC back to house
		try {
			System.out.println("Returning " + String.valueOf(amount) + "PCs to house, from " + _address);
			//create the credentials for signing purposes
			BigInteger privkey = new BigInteger(_privateKey, 16);
	        ECKeyPair ecKeyPair = ECKeyPair.create(privkey);
	        Credentials _credentials = Credentials.create(ecKeyPair);
	        TransactionManager txManager = new RawTransactionManager(web3j, credentials, 80001);
        	@SuppressWarnings("deprecation")
			PlayerToken _playcounter = PlayerToken.load(PlayerCounterAddr, web3j, txManager, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
	        _playcounter.transferPc(houseAddr, new BigInteger(amount + "000000000000000000")).send();
	        System.out.println("Returned successfully");
	        return true;
		}
		catch(Exception e) {return false;}
	}
	static boolean pause() {
		//disable PC
		try {
			System.out.println("Disabling trade");
	        playcounter.deactivate().send();
	        System.out.println("Disabled");
	        return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
			}
	}
	static boolean resume() {
		//enable token
		try {
			System.out.println("Enabling trade");
	        playcounter.activate().send();
	        System.out.println("Enabled");
	        return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	static boolean balance(String address) {
		//mint a certain amount to house address
		try {
			System.out.println("Current balance of address " + String.valueOf(playcounter.balanceOf(address).send()));
	        return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		}
	
}

