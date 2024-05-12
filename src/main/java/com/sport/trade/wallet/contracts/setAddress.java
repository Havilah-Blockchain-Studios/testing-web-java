package com.sport.trade.wallet.contracts;






import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

//loading SPQ smart contract from polygon mainnet

public class setAddress {
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(210000L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(38000000000L); //see lates gas price on polygon explorer


    private long chainId = 137;


    ContractGasProvider contractGasProvider = new StaticGasProvider(GAS_PRICE,GAS_LIMIT);


    //    String CONTRACT_ADDRESS = "0xF14C77fE78530a55bb63763703B1f8621Ae9c4b7";
    String CONTRACT_ADDRESS = "0xfD14e1A3b8efE23B63Cd44a88505B34FB2402393";

    public static void main(String[] args) throws Exception {
        new setAddress();

    }


    private setAddress() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("https://polygon-mainnet.g.alchemy.com/v2/_1qhvimhzz1xX84JvLbxsw4-OUfV2um-")); // defaults to http://localhost:8545/

        TransactionManager transactionManager = new RawTransactionManager(
                web3j, getCredentialsFromPrivateKey(),
                chainId

        );

        SPR spr = loadContract(CONTRACT_ADDRESS, web3j, transactionManager);
        String setAddressTxId= setAddress(spr);

        System.out.print("Transaction Hash is " + setAddressTxId);

    }

    private String setAddress(SPR spr) throws Exception {
        String seedtxid= spr
                .setAddress("seed","0x9945917a264FfA8537FFD1eE96ca640830426077")
                .send()
                .getTransactionHash();
        String angeltxid= spr
                .setAddress("angel","0x59f0741623B6c07a6Ca687a02C4b4563aAfd7742")
                .send()
                .getTransactionHash();
        String foundertxid= spr
                .setAddress("founders","0x00741f154afe95EB5888630f8671DBA61C1EBEc0")
                .send()
                .getTransactionHash();
        String airdroptxid= spr
                .setAddress("airdrop","0x91C40747441bD1F41C876CE80Fc45B5566225b32")
                .send()
                .getTransactionHash();
        String markettxid= spr
                .setAddress("market","0x7D0485f530312b4501de28a0688723f4D97622a7")
                .send()
                .getTransactionHash();
        String liquiditytxid= spr
                .setAddress("liquidity","0xeaD0a88E00258631eEaa17DfcfB12E71d8b95465")
                .send()
                .getTransactionHash();
        String treasurytxid= spr
                .setAddress("treasury","0xA7C28736dA12dc25131562948117498Edec6b265")
                .send()
                .getTransactionHash();
        String operationtxid= spr
                .setAddress("operations","0x661e1F2B276027b896847740741Ef983Bf886Ef9")
                .send()
                .getTransactionHash();

        String dummytxid= spr
                .setAddress("dummy","0x791366fEB384207BbdE93d71320a6DA806D07B3D")
                .send()
                .getTransactionHash();

        String txid=""+ seedtxid +" "+angeltxid+" "+foundertxid+" " + airdroptxid+" "+ markettxid+" "+liquiditytxid+" "+treasurytxid+" "+ operationtxid+" "+dummytxid;
//        String txid= spr.mint(BigInteger.valueOf(1000)).send().getTransactionHash();
        return txid;
    }


    private Credentials getCredentialsFromPrivateKey() {
        return Credentials.create("PVT KEY");
    }


    private SPR loadContract(String contractAddress, Web3j web3j, TransactionManager transactionManager) {
        return SPR.load(contractAddress, web3j, transactionManager, contractGasProvider);
    }




}


