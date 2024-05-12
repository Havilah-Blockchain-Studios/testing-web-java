package com.sport.trade.wallet.contracts;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class tokenomicsSPR {

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(480000L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(38000000000L); //see lates gas price on polygon explorer


    private long chainId = 137;


    ContractGasProvider contractGasProvider = new StaticGasProvider(GAS_PRICE,GAS_LIMIT);



    String CONTRACT_ADDRESS = "0xfD14e1A3b8efE23B63Cd44a88505B34FB2402393";

    public static void main(String[] args) throws Exception {
        new tokenomicsSPR();

    }


    private tokenomicsSPR() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("https://polygon-mainnet.g.alchemy.com/v2/_1qhvimhzz1xX84JvLbxsw4-OUfV2um-")); // defaults to http://localhost:8545/

        TransactionManager transactionManager = new RawTransactionManager(
                web3j, getCredentialsFromPrivateKey(),
                chainId

        );

        SPR spr = loadContract(CONTRACT_ADDRESS, web3j, transactionManager);
        String setAddressTxId= tokenomicsStart(spr);

        System.out.print("Transaction Hash is " + setAddressTxId);

    }

    private String tokenomicsStart(SPR spr) throws Exception {
        return spr.start().send().getTransactionHash();
    }


    private Credentials getCredentialsFromPrivateKey() {
        return Credentials.create("PVT KEY");
    }


    private SPR loadContract(String contractAddress, Web3j web3j, TransactionManager transactionManager) {
        return SPR.load(contractAddress, web3j, transactionManager, contractGasProvider);
    }



}



