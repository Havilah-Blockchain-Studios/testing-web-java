package com.sport.trade.wallet.contracts;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;

public class playerTokenDeploy {

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(30000000L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(22_000_000_000L);

    // Sets the initial supply of JavaTokens in existence
    BigInteger initialSupply = BigInteger.valueOf(100000);
    BigInteger amount = BigInteger.valueOf(256);


    private long chainId = 137;


    ContractGasProvider contractGasProvider = new StaticGasProvider(GAS_PRICE,GAS_LIMIT);

    //    String CONTRACT_ADDRESS = "0xF14C77fE78530a55bb63763703B1f8621Ae9c4b7";
//    String CONTRACT_ADDRESS = "0x17A2B3D08312E1Ff66809b97eCcfe7f58Fb9DD68";
    public static void main(String[] args) throws Exception {
        new playerTokenDeploy();

    }


    public playerTokenDeploy() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("https://polygon-mainnet.g.alchemy.com/v2/_1qhvimhzz1xX84JvLbxsw4-OUfV2um-")); // defaults to http://localhost:8545/

        TransactionManager transactionManager = new RawTransactionManager(
                web3j, getCredentialsFromPrivateKey(),
                chainId

        );



        String deployedAddress= deployContract(web3j,transactionManager);
        System.out.println("deployed contract address "+deployedAddress);

//        SPR spr = loadContract(CONTRACT_ADDRESS, web3j, transactionManager);
//        String setAddressTxId= setAddress(spr);
//        String startokenomicsTxId=start(spr);
//        BigInteger balance=balanceOf(spr);
//        String txid = transferSPQ(spr);
//        String trea=treasury(spr);
//        System.out.print("Transaction = " + trea);
//        System.out.print("Transaction Hash =" + setAddressTxId);
//        System.out.print("Transaction = " + startokenomicsTxId);
        //To transact with a smart contract/To call a smart contract:*/
//        System.out.print("Balance is "+balance);

    }


    //get credentials from wallet
    private Credentials getCredentialsFromWallet() throws CipherException, IOException {
        return WalletUtils.loadCredentials("password", "/path/to/walletfile");
    }

    private Credentials getCredentialsFromPrivateKey() {
        return Credentials.create("PVT KEY");
    }

    private String deployContract(Web3j web3j, TransactionManager transactionManager) throws Exception {

        return PlayerToken.deploy(web3j, transactionManager,  contractGasProvider, "Virat Kohli", "PMC", "0x535FACe7048eB6d8ca68C3d14f3d09205FC912Ca", initialSupply)
                .send()
                .getContractAddress();
    }

    private SPR loadContract(String contractAddress,Web3j web3j, TransactionManager transactionManager) {
        return SPR.load(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    private String transferSPQ(SPR spr) throws Exception {
        return spr
                .transfer("0xcd10DF0BD6a60F90272919B89F62C769F7998334",amount)
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

        return spr.balanceOf("0xB87046F6c2Ba6794A2493e72cc9B5e181593cD12").send();

    }

    private String setAddress(SPR spr) throws Exception {
        String seedtxid= spr
                .setAddress("seed","0xB87046F6c2Ba6794A2493e72cc9B5e181593cD12")
                .send()
                .getTransactionHash();
        String angeltxid= spr
                .setAddress("angel","0x4A7032D92ddF37388aDc4f00b49c969AbA54F24a")
                .send()
                .getTransactionHash();
        String foundertxid= spr
                .setAddress("founders","0x9855DE9bF83B85Ffd0ab2787B034Ce767378d103")
                .send()
                .getTransactionHash();
        String airdroptxid= spr
                .setAddress("airdrop","0x7D425b5837C87041102aC33423E51c64c3BddE00")
                .send()
                .getTransactionHash();
        String markettxid= spr
                .setAddress("market","0xE93E83a43C1e7b7aCE9454Ae0d1cC38F324c702F")
                .send()
                .getTransactionHash();
        String liquiditytxid= spr
                .setAddress("liquidity","0x960B28D620AE3feDD1766c2D96A52455DE148354")
                .send()
                .getTransactionHash();
        String treasurytxid= spr
                .setAddress("treasury","0x6CfAF73467Bcb23Cf344b1B029B60e8D8174e6EE")
                .send()
                .getTransactionHash();
        String operationtxid= spr
                .setAddress("operations","0xf827EebfB13E097bD609473d67a3024A9aee1016")
                .send()
                .getTransactionHash();

        String txid=""+ seedtxid +" "+ angeltxid+ " "+ foundertxid+" "+airdroptxid+" "+markettxid+" "+liquiditytxid+" "+treasurytxid+" "+operationtxid;

        return txid;
    }
}




