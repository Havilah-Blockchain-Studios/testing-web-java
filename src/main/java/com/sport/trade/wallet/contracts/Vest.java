package com.sport.trade.wallet.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.9.4.
 */
@SuppressWarnings("rawtypes")
public class Vest extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610134806100206000396000f3fe6080604052348015600f57600080fd5b5060043610603b5760003560e01c8062f714ce1460405780631b1b377614607d578063e8c2eded1460be575b600080fd5b606960048036036040811015605457600080fd5b50803590602001356001600160a01b031660ed565b604080519115158252519081900360200190f35b60ac60048036036060811015609157600080fd5b50803590602081013590604001356001600160a01b031660f5565b60408051918252519081900360200190f35b60696004803603606081101560d257600080fd5b506001600160a01b03813516906020810135906040013560f5565b600092915050565b6000939250505056fea26469706673582212200ac487e3b74be0d1720b47b9c6088c379864746b93b3ed8d44546f59a38d510764736f6c63430007010033";

    public static final String FUNC_LOCKTOKEN = "lockToken";

    public static final String FUNC_SETDELEGATES = "setDelegates";

    public static final String FUNC_WITHDRAW = "withdraw";

    @Deprecated
    protected Vest(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Vest(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Vest(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Vest(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> lockToken(BigInteger param0, BigInteger param1, String param2) {
        final Function function = new Function(
                FUNC_LOCKTOKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1), 
                new org.web3j.abi.datatypes.Address(160, param2)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setDelegates(String param0, BigInteger param1, BigInteger param2) {
        final Function function = new Function(
                FUNC_SETDELEGATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1), 
                new org.web3j.abi.datatypes.generated.Uint256(param2)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(BigInteger param0, String param1) {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0), 
                new org.web3j.abi.datatypes.Address(160, param1)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Vest load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Vest(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Vest load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Vest(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Vest load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Vest(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Vest load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Vest(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Vest> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Vest.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Vest> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Vest.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Vest> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Vest.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Vest> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Vest.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
