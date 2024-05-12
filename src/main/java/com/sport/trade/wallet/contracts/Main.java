package com.sport.trade.wallet.contracts;






import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ChainId;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;


public class Main {
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    // Sets the initial supply of JavaTokens in existence
    BigInteger initialSupply = BigInteger.valueOf(100000);
    BigInteger amount = BigInteger.valueOf(256);


    private long chainId = 80001;


    ContractGasProvider contractGasProvider = new StaticGasProvider(GAS_PRICE, GAS_LIMIT);

//    String CONTRACT_ADDRESS = "0xF14C77fE78530a55bb63763703B1f8621Ae9c4b7";
        String CONTRACT_ADDRESS = "0xD3aC92D0c67985Ff32DfFC93c2d7930b47A592af";
    public static void main(String[] args) throws Exception {
        new Main();

    }


    public Main() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("https://polygon-mumbai.g.alchemy.com/v2/UdTYnfdB7YqzLECUjJHo6AdV8xohYTZ1")); // defaults to http://localhost:8545/

        TransactionManager transactionManager = new RawTransactionManager(
                web3j, getCredentialsFromPrivateKey(),
                chainId

        );



//        String deployedAddress= deployContract(web3j,transactionManager);
//        System.out.println("deployed contract address "+deployedAddress);

        SPR spr = loadContract(CONTRACT_ADDRESS, web3j, transactionManager);
//        String setAddressTxId= setAddress(spr);
        String startokenomicsTxId=tokenomicsStart(spr);

//        BigInteger balance=balanceOf(spr);
//        String txid = mintSPR(spr);
//        String trea=treasury(spr);
//        System.out.print("Transaction = " + txid);
//          System.out.print("Transaction Hash =" + setAddressTxId);
        System.out.print("Transaction = " + startokenomicsTxId);
        //To transact with a smart contract/To call a smart contract:*/
//        System.out.print("Balance is "+balance);

//        String seedWallet= seedWallet(spr);
//
//        String founderWallet= founderWallet(spr);
//        String angelWallet= angelWallet(spr);
//        String treasury= treasuryWallet(spr);
//        String airdropWallet= airdropWallet(spr);
//        String liquidityWallet= liquidityWallet(spr);
//        String marketingWallet= marketingWallet(spr);
//        String operationsWallet= operationsWallet(spr);
//        String dummyWallet= dummyWallet(spr);
//
//
//        System.out.println("Seed Wallet ->  " + seedWallet);
//        System.out.println("Founder Wallet ->  " + founderWallet);
//        System.out.println("Angel Wallet ->  " + angelWallet);
//        System.out.println("Treasury Wallet ->  " + treasury);
//        System.out.println("Airdrop Wallet ->  " + airdropWallet);
//        System.out.println("Liquidity Wallet ->  " + liquidityWallet);
//        System.out.println("Marketing Wallet ->  " + marketingWallet);
//        System.out.println("Operations Wallet ->  " + operationsWallet);
//        System.out.println("Dummy Wallet ->  " + dummyWallet);
    }

    private String sendTo(SPR spr) throws Exception {
        return spr.sendTo("0x7a99cafcc9d161e168a32f3240f4e6f70259f7c1","0x3805fa8d9a0a651ec5121f130840b25089c59d95", BigInteger.valueOf(256)).send().getTransactionHash();
    }

    private String mintSPR(SPR spr) throws Exception {
        return spr.mint(BigInteger.valueOf(1000)).send().getTransactionHash();
    }
    private String tokenomicsStart(SPR spr) throws Exception {
        return spr.start().send().getTransactionHash();
    }



    //get credentials from wallet
    private Credentials getCredentialsFromWallet() throws CipherException, IOException {
        return WalletUtils.loadCredentials("password", "/path/to/walletfile");
    }

    private Credentials getCredentialsFromPrivateKey() {
        return Credentials.create("PVT KEY");
    }

    private String deployContract(Web3j web3j, TransactionManager transactionManager) throws Exception {

        return PlayerToken.deploy(web3j, transactionManager, contractGasProvider, "Virat Kohli", "VKC", "0x7a99CafCc9d161E168a32F3240f4e6F70259f7C1", initialSupply)
                .send()
                .getContractAddress();
    }

    private SPR loadContract(String contractAddress,Web3j web3j, TransactionManager transactionManager) {
        return SPR.load(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    private String transferSPQ(SPR spr) throws Exception {
        return spr
                .transfer("0x3805fa8d9a0a651ec5121f130840b25089c59d95",amount)
                .send()
                .getTransactionHash();

    }


    private String start(SPR spr) throws Exception{

        return spr.start().send().getTransactionHash();

    }
    private String treasury(SPR spr) throws Exception{

        return spr.operationalWallet().send();

    }
    private BigInteger balanceOf(SPR spr) throws Exception{

        return spr.balanceOf("0x3805fa8d9a0a651ec5121f130840b25089c59d95").send();

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






}



