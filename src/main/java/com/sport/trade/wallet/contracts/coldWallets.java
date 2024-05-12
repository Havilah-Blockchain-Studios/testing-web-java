package com.sport.trade.wallet.contracts;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class coldWallets {


    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(310000L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(38000000000L); //see lates gas price on polygon explorer


    private long chainId = 137;


    ContractGasProvider contractGasProvider = new StaticGasProvider(GAS_PRICE,GAS_LIMIT);



//    String CONTRACT_ADDRESS = "0xa9EC06A9F2C6BD127340c2908B6385B7E0081257";
    String CONTRACT_ADDRESS ="0xfD14e1A3b8efE23B63Cd44a88505B34FB2402393";

    public static void main(String[] args) throws Exception {
        new coldWallets();

    }


    private coldWallets() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("https://polygon-mainnet.g.alchemy.com/v2/_1qhvimhzz1xX84JvLbxsw4-OUfV2um-")); // defaults to http://localhost:8545/

        TransactionManager transactionManager = new RawTransactionManager(
                web3j, getCredentialsFromPrivateKey(),
                chainId

        );

        SPR spr = loadContract(CONTRACT_ADDRESS, web3j, transactionManager);
        String seedWallet= seedWallet(spr);

        String founderWallet= founderWallet(spr);
        String angelWallet= angelWallet(spr);
        String treasury= treasuryWallet(spr);
        String airdropWallet= airdropWallet(spr);
        String liquidityWallet= liquidityWallet(spr);
        String marketingWallet= marketingWallet(spr);
        String operationsWallet= operationsWallet(spr);
        String dummyWallet= dummyWallet(spr);


        System.out.println("Seed Wallet ->  " + seedWallet);
        System.out.println("Founder Wallet ->  " + founderWallet);
        System.out.println("Angel Wallet ->  " + angelWallet);
        System.out.println("Treasury Wallet ->  " + treasury);
        System.out.println("Airdrop Wallet ->  " + airdropWallet);
        System.out.println("Liquidity Wallet ->  " + liquidityWallet);
        System.out.println("Marketing Wallet ->  " + marketingWallet);
        System.out.println("Operations Wallet ->  " + operationsWallet);
        System.out.println("Dummy Wallet ->  " + dummyWallet);


    }

    private String dummyWallet(SPR spr) throws Exception {
        return spr.dummyWallet().send();
    }

    private String marketingWallet(SPR spr) throws Exception {
        return spr.marketWallet().send();
    }

    private String operationsWallet(SPR spr) throws Exception {
        return spr.operationalWallet().send();
    }

    private String airdropWallet(SPR spr) throws Exception {
        return spr.airdropWallet().send();
    }

    private String liquidityWallet(SPR spr) throws Exception {
        return spr.liquidityWallet().send();
    }

    private String treasuryWallet(SPR spr) throws Exception {
        return spr.treasuryWallet().send();
    }

    private String angelWallet(SPR spr) throws Exception {
        return spr.angelWallet().send();
    }

    private String founderWallet(SPR spr) throws Exception {
        return spr.foundersWallet().send();
    }

    private String seedWallet(SPR spr) throws Exception {
        return spr.seedWallet().send();
    }



    private Credentials getCredentialsFromPrivateKey() {
        return Credentials.create("PVT KEY");
    }


    private SPR loadContract(String contractAddress, Web3j web3j, TransactionManager transactionManager) {
        return SPR.load(contractAddress, web3j, transactionManager, contractGasProvider);
    }



}


