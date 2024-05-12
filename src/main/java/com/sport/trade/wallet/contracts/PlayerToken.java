package com.sport.trade.wallet.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
public class PlayerToken extends Contract {
    public static final String BINARY = "60806040526008805460ff191690553480156200001b57600080fd5b506040516200156038038062001560833981810160405260808110156200004157600080fd5b81019080805160405193929190846401000000008211156200006257600080fd5b9083019060208201858111156200007857600080fd5b82516401000000008111828201881017156200009357600080fd5b82525081516020918201929091019080838360005b83811015620000c2578181015183820152602001620000a8565b50505050905090810190601f168015620000f05780820380516001836020036101000a031916815260200191505b50604052602001805160405193929190846401000000008211156200011457600080fd5b9083019060208201858111156200012a57600080fd5b82516401000000008111828201881017156200014557600080fd5b82525081516020918201929091019080838360005b83811015620001745781810151838201526020016200015a565b50505050905090810190601f168015620001a25780820380516001836020036101000a031916815260200191505b50604090815260208281015192909101518651929450925085918591620001cf916003918501906200044e565b508051620001e59060049060208401906200044e565b5050600680546001600160a01b038516610100026001600160a81b03199091168117909155600783905560088054610100600160a81b031916909117905550600a80546001600160a01b031916301790556009819055620002468162000251565b5050505050620004ea565b60006200025d62000291565b506008546200027b9061010090046001600160a01b031683620002ff565b506008805460ff19166001908117909155919050565b60065460009061010090046001600160a01b03163314620002f9576040805162461bcd60e51b815260206004820152601960248201527f43616e206f6e6c7920626520646f6e65206279204f776e657200000000000000604482015290519081900360640190fd5b50600190565b6001600160a01b0382166200035b576040805162461bcd60e51b815260206004820152601f60248201527f45524332303a206d696e7420746f20746865207a65726f206164647265737300604482015290519081900360640190fd5b6200036960008383620003d4565b60028054820190556001600160a01b038216600081815260208181526040808320805486019055805185815290517fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929181900390910190a3620003d060008383620003f6565b5050565b620003de620003fb565b620003f6838383620003f660201b620009401760201c565b505050565b6200040562000445565b15620004435760405162461bcd60e51b815260040180806020018281038252602b81526020018062001535602b913960400191505060405180910390fd5b565b60065460ff1690565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200049157805160ff1916838001178555620004c1565b82800160010185558215620004c1579182015b82811115620004c1578251825591602001919060010190620004a4565b50620004cf929150620004d3565b5090565b5b80821115620004cf5760008155600101620004d4565b61103b80620004fa6000396000f3fe608060405234801561001057600080fd5b50600436106101735760003560e01c806370a08231116100de578063a0712d6811610097578063c46760d311610071578063c46760d31461044e578063dd62ed3e14610472578063f9f7d9f7146104a0578063ffe374af146104c657610173565b8063a0712d68146103d9578063a457c2d7146103f6578063a9059cbb1461042257610173565b806370a082311461034357806384304b9d146103695780638ab66381146103955780638f32d59b1461039d5780639385f11f146103a557806395d89b41146103d157610173565b806323b872dd1161013057806323b872dd146102ab578063313ce567146102e157806339509351146102ff5780633eaaf86b1461032b57806351b42b00146103335780635c975abb1461033b57610173565b806306fdde0314610178578063095ea7b3146101f55780630f15f4c01461023557806315d0975c1461023f57806318160ddd1461026b5780631e965fa614610285575b600080fd5b6101806104ec565b6040805160208082528351818301528351919283929083019185019080838360005b838110156101ba5781810151838201526020016101a2565b50505050905090810190601f1680156101e75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6102216004803603604081101561020b57600080fd5b506001600160a01b038135169060200135610582565b604080519115158252519081900360200190f35b61023d6105a4565b005b6102216004803603604081101561025557600080fd5b506001600160a01b0381351690602001356105b7565b6102736105d5565b60408051918252519081900360200190f35b6102736004803603602081101561029b57600080fd5b50356001600160a01b03166105db565b610221600480360360608110156102c157600080fd5b506001600160a01b038135811691602081013590911690604001356105ed565b6102e961063b565b6040805160ff9092168252519081900360200190f35b6102216004803603604081101561031557600080fd5b506001600160a01b038135169060200135610640565b610273610663565b61023d610669565b61022161067a565b6102736004803603602081101561035957600080fd5b50356001600160a01b0316610683565b6102216004803603604081101561037f57600080fd5b506001600160a01b03813516906020013561069e565b610273610724565b61022161072a565b610221600480360360408110156103bb57600080fd5b506001600160a01b038135169060200135610797565b6101806107a3565b610221600480360360208110156103ef57600080fd5b5035610804565b6102216004803603604081101561040c57600080fd5b506001600160a01b038135169060200135610840565b6102216004803603604081101561043857600080fd5b506001600160a01b0381351690602001356108a7565b6104566108bf565b604080516001600160a01b039092168252519081900360200190f35b6102736004803603604081101561048857600080fd5b506001600160a01b03813581169160200135166108ce565b610273600480360360208110156104b657600080fd5b50356001600160a01b03166108f9565b610221600480360360208110156104dc57600080fd5b50356001600160a01b031661090b565b60038054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156105785780601f1061054d57610100808354040283529160200191610578565b820191906000526020600020905b81548152906001019060200180831161055b57829003601f168201915b5050505050905090565b60008061058d610945565b905061059a818585610949565b5060019392505050565b6105ac61072a565b506105b5610a35565b565b60006105c161072a565b506105cc8383610a49565b50600192915050565b60025490565b600b6020526000908152604090205481565b6000806105f8610945565b9050846001600160a01b031661060c610945565b6001600160a01b03161461062557610625858285610b5a565b610630858585610bd9565b506001949350505050565b600090565b60008061064b610945565b905061059a81858561065d85896108ce565b01610949565b60025481565b61067161072a565b506105b5610d34565b60065460ff1690565b6001600160a01b031660009081526020819052604090205490565b6008546000906001600160a01b038481166101009092041614610700576040805162461bcd60e51b81526020600482015260156024820152742ab9b2903a3930b739b332b910333ab731ba34b7b760591b604482015290519081900360640190fd5b33600090815260208190526040902054600d5561071d83836108a7565b5092915050565b600d5481565b60065460009061010090046001600160a01b03163314610791576040805162461bcd60e51b815260206004820152601960248201527f43616e206f6e6c7920626520646f6e65206279204f776e657200000000000000604482015290519081900360640190fd5b50600190565b600061071d83836108a7565b60048054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156105785780601f1061054d57610100808354040283529160200191610578565b600061080e61072a565b5060085461082a9061010090046001600160a01b031683610d4b565b506008805460ff19166001908117909155919050565b60008061084b610945565b9050600061085982866108ce565b90508381101561089a5760405162461bcd60e51b8152600401808060200182810382526025815260200180610fe16025913960400191505060405180910390fd5b6106308286868403610949565b6000806108b2610945565b905061059a818585610bd9565b600a546001600160a01b031681565b6001600160a01b03918216600090815260016020908152604080832093909416825291909152205490565b600c6020526000908152604090205481565b600061091561072a565b5050600680546001600160a01b03831661010002610100600160a81b03199091161790556001919050565b505050565b3390565b6001600160a01b03831661098e5760405162461bcd60e51b8152600401808060200182810382526024815260200180610fbd6024913960400191505060405180910390fd5b6001600160a01b0382166109d35760405162461bcd60e51b8152600401808060200182810382526022815260200180610f556022913960400191505060405180910390fd5b6001600160a01b03808416600081815260016020908152604080832094871680845294825291829020859055815185815291517f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259281900390910190a3505050565b610a3d610e1b565b6006805460ff19169055565b6001600160a01b038216610a8e5760405162461bcd60e51b8152600401808060200182810382526021815260200180610f776021913960400191505060405180910390fd5b610a9a82600083610e6b565b6001600160a01b03821660009081526020819052604090205481811015610af25760405162461bcd60e51b8152600401808060200182810382526022815260200180610f116022913960400191505060405180910390fd5b6001600160a01b0383166000818152602081815260408083208686039055600280548790039055805186815290519293927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929181900390910190a361094083600084610940565b6000610b6684846108ce565b90506000198114610bd35781811015610bc6576040805162461bcd60e51b815260206004820152601d60248201527f45524332303a20696e73756666696369656e7420616c6c6f77616e6365000000604482015290519081900360640190fd5b610bd38484848403610949565b50505050565b6001600160a01b038316610c1e5760405162461bcd60e51b8152600401808060200182810382526025815260200180610f986025913960400191505060405180910390fd5b6001600160a01b038216610c635760405162461bcd60e51b8152600401808060200182810382526023815260200180610eee6023913960400191505060405180910390fd5b610c6e838383610e6b565b6001600160a01b03831660009081526020819052604090205481811015610cc65760405162461bcd60e51b8152600401808060200182810382526022815260200180610f336022913960400191505060405180910390fd5b6001600160a01b038085166000818152602081815260408083208787039055938716808352918490208054870190558351868152935191937fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929081900390910190a3610bd3848484610940565b610d3c610e7e565b6006805460ff19166001179055565b6001600160a01b038216610da6576040805162461bcd60e51b815260206004820152601f60248201527f45524332303a206d696e7420746f20746865207a65726f206164647265737300604482015290519081900360640190fd5b610db260008383610e6b565b60028054820190556001600160a01b038216600081815260208181526040808320805486019055805185815290517fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929181900390910190a3610e1760008383610940565b5050565b610e2361067a565b6105b5576040805162461bcd60e51b815260206004820152601460248201527314185d5cd8589b194e881b9bdd081c185d5cd95960621b604482015290519081900360640190fd5b610e73610e7e565b610940838383610940565b610e8661067a565b156105b55760405162461bcd60e51b815260040180806020018281038252602b815260200180610ec3602b913960400191505060405180910390fdfe43616e6e6f742054726164652c205043206973206e6f742061637469766520617420746869732074696d6545524332303a207472616e7366657220746f20746865207a65726f206164647265737345524332303a206275726e20616d6f756e7420657863656564732062616c616e636545524332303a207472616e7366657220504320657863656564732062616c616e636545524332303a20617070726f766520746f20746865207a65726f206164647265737345524332303a206275726e2066726f6d20746865207a65726f206164647265737345524332303a207472616e736665722066726f6d20746865207a65726f206164647265737345524332303a20617070726f76652066726f6d20746865207a65726f206164647265737345524332303a2064656372656173656420616c6c6f77616e63652062656c6f77207a65726fa264697066735822122042e8bc12eb5192f616874770ace7824a6a63b140f2ca6c08d22f2835e648d66164736f6c6343000701003343616e6e6f742054726164652c205043206973206e6f742061637469766520617420746869732074696d65";

    public static final String FUNC__PCBUYING = "_PCBuying";

    public static final String FUNC__PCSELLING = "_PCSelling";

    public static final String FUNC__TOTALSUPPLY = "_totalSupply";

    public static final String FUNC_ACTIVATE = "activate";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_DEACTIVATE = "deactivate";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_DECREASESUPPLY = "decreaseSupply";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_MAKEOWNER = "makeOwner";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_PAUSED = "paused";

    public static final String FUNC_PCSMCA = "pcSMCA";

    public static final String FUNC_SENDPCTOUSER = "sendPcToUser";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFERPC = "transferPc";

    public static final String FUNC_USERTOTALPC = "userTotalPC";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected PlayerToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PlayerToken(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PlayerToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PlayerToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

//    public static List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
//        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
//        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
//        for (Contract.EventValuesWithLog eventValues : valueList) {
//            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
//            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
//            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
//            responses.add(typedResponse);
//        }
//        return responses;
//    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

//    public static List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
//        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
//        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
//        for (Contract.EventValuesWithLog eventValues : valueList) {
//            TransferEventResponse typedResponse = new TransferEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
//            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
//            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
//            responses.add(typedResponse);
//        }
//        return responses;
//    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> _PCBuying(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__PCBUYING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> _PCSelling(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__PCSELLING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> _totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> activate() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ACTIVATE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> deactivate() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DEACTIVATE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger subtractedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(subtractedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseSupply(String account1, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASESUPPLY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account1), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> isOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ISOWNER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> makeOwner(String _address) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MAKEOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> paused() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PAUSED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> pcSMCA() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PCSMCA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> sendPcToUser(String to, BigInteger _amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SENDPCTOUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferPc(String to, BigInteger _amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERPC, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> userTotalPC() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_USERTOTALPC, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static PlayerToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PlayerToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PlayerToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PlayerToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PlayerToken load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PlayerToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PlayerToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PlayerToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PlayerToken> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _name, String _symbol, String _houseAddr, BigInteger _totalSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_symbol), 
                new org.web3j.abi.datatypes.Address(160, _houseAddr), 
                new org.web3j.abi.datatypes.generated.Uint256(_totalSupply)));
        return deployRemoteCall(PlayerToken.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<PlayerToken> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _name, String _symbol, String _houseAddr, BigInteger _totalSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_symbol), 
                new org.web3j.abi.datatypes.Address(160, _houseAddr), 
                new org.web3j.abi.datatypes.generated.Uint256(_totalSupply)));
        return deployRemoteCall(PlayerToken.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PlayerToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _name, String _symbol, String _houseAddr, BigInteger _totalSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_symbol), 
                new org.web3j.abi.datatypes.Address(160, _houseAddr), 
                new org.web3j.abi.datatypes.generated.Uint256(_totalSupply)));
        return deployRemoteCall(PlayerToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PlayerToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _name, String _symbol, String _houseAddr, BigInteger _totalSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_symbol), 
                new org.web3j.abi.datatypes.Address(160, _houseAddr), 
                new org.web3j.abi.datatypes.generated.Uint256(_totalSupply)));
        return deployRemoteCall(PlayerToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
