package com.sport.trade.wallet.contracts;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class balanceOfSPR {

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(310000L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(38000000000L); //see lates gas price on polygon explorer


    private long chainId = 137;


    ContractGasProvider contractGasProvider = new StaticGasProvider(GAS_PRICE,GAS_LIMIT);



    String CONTRACT_ADDRESS = "0xa9EC06A9F2C6BD127340c2908B6385B7E0081257";

    public static void main(String[] args) throws Exception {
        new balanceOfSPR();

    }


    private balanceOfSPR() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("https://polygon-mainnet.g.alchemy.com/v2/_1qhvimhzz1xX84JvLbxsw4-OUfV2um-")); // defaults to http://localhost:8545/

        TransactionManager transactionManager = new RawTransactionManager(
                web3j, getCredentialsFromPrivateKey(),
                chainId

        );

        SPR spr = loadContract(CONTRACT_ADDRESS, web3j, transactionManager);
        BigInteger balance= tokenomicsStart(spr);

        System.out.print("Balance is " + balance);

    }

    private BigInteger tokenomicsStart(SPR spr) throws Exception {
        return spr.balanceOf("0x535face7048eb6d8ca68c3d14f3d09205fc912ca").send();

    }


    private Credentials getCredentialsFromPrivateKey() {
        return Credentials.create("PVT KEY");
    }


    private SPR loadContract(String contractAddress, Web3j web3j, TransactionManager transactionManager) {
        return SPR.load(contractAddress, web3j, transactionManager, contractGasProvider);
    }



}
