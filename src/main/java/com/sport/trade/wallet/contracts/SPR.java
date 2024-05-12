package com.sport.trade.wallet.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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
public class SPR extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50612056806100206000396000f3fe608060405234801561001057600080fd5b50600436106101f05760003560e01c80638d75fe051161010f578063a98c56a5116100a2578063d085835a11610071578063d085835a146105d4578063d4698016146105dc578063dd62ed3e146105e4578063e4440a8614610612576101f0565b8063a98c56a51461054f578063ae6393291461056e578063be9a6555146105a4578063c4d66de8146105ac576101f0565b8063a0712d68116100de578063a0712d68146104d2578063a14779c9146104ef578063a457c2d7146104f7578063a9059cbb14610523576101f0565b80638d75fe05146104095780638f32d59b1461041157806395d89b41146104195780639b2ea4bd14610421576101f0565b806342966c68116101875780635b2dd462116101565780635b2dd462146103cb5780635bcd31a9146103d357806370a08231146103db5780637c2497ad14610401576101f0565b806342966c68146103785780634626402b14610395578063472905ca1461039d578063540c7e41146103c3576101f0565b80631bfaf155116101c35780631bfaf155146102f057806323b872dd146102f8578063313ce5671461032e578063395093511461034c576101f0565b806306fdde03146101f5578063095ea7b3146102725780630f4101e0146102b257806318160ddd146102d6575b600080fd5b6101fd61061a565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561023757818101518382015260200161021f565b50505050905090810190601f1680156102645780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b61029e6004803603604081101561028857600080fd5b506001600160a01b0381351690602001356106b0565b604080519115158252519081900360200190f35b6102ba6106d6565b604080516001600160a01b039092168252519081900360200190f35b6102de6106e5565b60408051918252519081900360200190f35b6102ba6106eb565b61029e6004803603606081101561030e57600080fd5b506001600160a01b038135811691602081013590911690604001356106fa565b610336610789565b6040805160ff9092168252519081900360200190f35b61029e6004803603604081101561036257600080fd5b506001600160a01b038135169060200135610792565b61029e6004803603602081101561038e57600080fd5b50356107e8565b6102ba610868565b61029e600480360360208110156103b357600080fd5b50356001600160a01b0316610877565b6102ba6108a7565b6102ba6108b6565b6102de6108c5565b6102de600480360360208110156103f157600080fd5b50356001600160a01b03166108cb565b6102ba6108e6565b6102de6108f5565b61029e610905565b6101fd61096d565b61029e6004803603604081101561043757600080fd5b81019060208101813564010000000081111561045257600080fd5b82018360208201111561046457600080fd5b8035906020019184600183028401116401000000008311171561048657600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550505090356001600160a01b031691506109ce9050565b61029e600480360360208110156104e857600080fd5b5035610c9c565b6102ba610cc5565b61029e6004803603604081101561050d57600080fd5b506001600160a01b038135169060200135610cd4565b61029e6004803603604081101561053957600080fd5b506001600160a01b038135169060200135610d44565b61029e6004803603602081101561056557600080fd5b50351515610d60565b61029e6004803603606081101561058457600080fd5b506001600160a01b03813581169160208101359091169060400135610d74565b61029e610e05565b6105d2600480360360208110156105c257600080fd5b50356001600160a01b03166112c3565b005b6102ba6113d6565b6102ba6113e5565b6102de600480360360408110156105fa57600080fd5b506001600160a01b03813581169160200135166113f4565b6102ba61141f565b60368054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156106a65780601f1061067b576101008083540402835291602001916106a6565b820191906000526020600020905b81548152906001019060200180831161068957829003601f168201915b5050505050905090565b60006106ba61142e565b6106cc6106c561148b565b848461148f565b5060015b92915050565b6067546001600160a01b031681565b60355490565b6069546001600160a01b031681565b600061070461142e565b61070f848484611583565b61077f8461071b61148b565b61077a85604051806060016040528060288152602001611f46602891396001600160a01b038a1660009081526034602052604081209061075961148b565b6001600160a01b0316815260208101919091526040016000205491906116e8565b61148f565b5060019392505050565b60385460ff1690565b600061079c61142e565b6106cc6107a761148b565b8461077a85603460006107b861148b565b6001600160a01b03908116825260208083019390935260409182016000908120918c16815292529020549061177f565b60006107f2610905565b506107fb61142e565b30600090815260336020526040902054821115610855576040805162461bcd60e51b8152602060048201526013602482015272125b9cdd59999a58da595b9d08185b5bdd5b9d606a1b604482015290519081900360640190fd5b61085f30836117e0565b5060015b919050565b606d546001600160a01b031681565b6000610881610905565b5050606580546001600160a01b0383166001600160a01b03199091161790556001919050565b606f546001600160a01b031681565b6068546001600160a01b031681565b60735481565b6001600160a01b031660009081526033602052604090205490565b606e546001600160a01b031681565b6000610900306108cb565b905090565b6065546000906001600160a01b03163314610967576040805162461bcd60e51b815260206004820152601960248201527f43616e206f6e6c7920626520646f6e65206279204f776e657200000000000000604482015290519081900360640190fd5b50600190565b60378054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156106a65780601f1061067b576101008083540402835291602001916106a6565b60006109d8610905565b506109ff83604051806040016040528060048152602001631cd9595960e21b8152506118e4565b15610a2457606780546001600160a01b0319166001600160a01b0384161790556106cc565b610a4b8360405180604001604052806005815260200164185b99d95b60da1b8152506118e4565b15610a7057606880546001600160a01b0319166001600160a01b0384161790556106cc565b610a9a8360405180604001604052806008815260200167666f756e6465727360c01b8152506118e4565b15610abf57606980546001600160a01b0319166001600160a01b0384161790556106cc565b610ae88360405180604001604052806007815260200166061697264726f760cc1b8152506118e4565b15610b0d57606a80546001600160a01b0319166001600160a01b0384161790556106cc565b610b3583604051806040016040528060068152602001651b585c9ad95d60d21b8152506118e4565b15610b5a57606b80546001600160a01b0319166001600160a01b0384161790556106cc565b610b8583604051806040016040528060098152602001686c697175696469747960b81b8152506118e4565b15610baa57606c80546001600160a01b0319166001600160a01b0384161790556106cc565b610bd48360405180604001604052806008815260200167747265617375727960c01b8152506118e4565b15610bf957606d80546001600160a01b0319166001600160a01b0384161790556106cc565b610c25836040518060400160405280600a8152602001696f7065726174696f6e7360b01b8152506118e4565b15610c4a57606e80546001600160a01b0319166001600160a01b0384161790556106cc565b610c71836040518060400160405280600581526020016464756d6d7960d81b8152506118e4565b156106cc57606f80546001600160a01b0384166001600160a01b031990911617905550600192915050565b6000610ca6610905565b50610caf61142e565b60655461085f906001600160a01b0316836119db565b606a546001600160a01b031681565b6000610cde61142e565b6106cc610ce961148b565b8461077a85604051806060016040528060258152602001611ffc6025913960346000610d1361148b565b6001600160a01b03908116825260208083019390935260409182016000908120918d168152925290205491906116e8565b6000610d4e61142e565b6106cc610d5961148b565b8484611583565b6000610d6a610905565b5061086382611ad5565b6000610d7e610905565b50610d8761142e565b81610d91856108cb565b101580610db35750606d548290610db0906001600160a01b03166108cb565b10155b610dfa576040805162461bcd60e51b8152602060048201526013602482015272125b9cdd59999a58da595b9d08185b5bdd5b9d606a1b604482015290519081900360640190fd5b61077f848484611583565b6000610e0f610905565b50610e18611aef565b506067546001600160a01b0316610e6c576040805162461bcd60e51b815260206004820152601360248201527214d95959081dd85b1b195d081b9bdd081cd95d606a1b604482015290519081900360640190fd5b6068546001600160a01b0316610ec0576040805162461bcd60e51b8152602060048201526014602482015273105b99d95b081dd85b1b195d081b9bdd081cd95d60621b604482015290519081900360640190fd5b6069546001600160a01b0316610f1d576040805162461bcd60e51b815260206004820152601760248201527f466f756e646572732077616c6c6574206e6f7420736574000000000000000000604482015290519081900360640190fd5b606a546001600160a01b0316610f73576040805162461bcd60e51b8152602060048201526016602482015275105a5c991c9bdc081dd85b1b195d081b9bdd081cd95d60521b604482015290519081900360640190fd5b606b546001600160a01b0316610fc8576040805162461bcd60e51b815260206004820152601560248201527413585c9ad95d081dd85b1b195d081b9bdd081cd95d605a1b604482015290519081900360640190fd5b606c546001600160a01b0316611025576040805162461bcd60e51b815260206004820152601860248201527f4c69717569646974792077616c6c6574206e6f74207365740000000000000000604482015290519081900360640190fd5b606d546001600160a01b0316611082576040805162461bcd60e51b815260206004820152601760248201527f54726561737572792077616c6c6574206e6f7420736574000000000000000000604482015290519081900360640190fd5b606e546001600160a01b03166110df576040805162461bcd60e51b815260206004820152601a60248201527f4f7065726174696f6e616c2077616c6c6574206e6f7420736574000000000000604482015290519081900360640190fd5b606f546001600160a01b0316611133576040805162461bcd60e51b8152602060048201526014602482015273111d5b5b5e481dd85b1b195d081b9bdd081cd95d60621b604482015290519081900360640190fd5b611140633b9aca00610c9c565b151560011461118c576040805162461bcd60e51b81526020600482015260136024820152724d696e74696e67206973206e6f7420646f6e6560681b604482015290519081900360640190fd5b6065546067546111ad916001600160a01b0390811691166302625a00610d74565b506065546068546111cf916001600160a01b0390811691166305f5e100610d74565b506065546069546111f1916001600160a01b0390811691166308f0d180610d74565b50606554606a54611213916001600160a01b0390811691166301c94e50610d74565b50606554606b54611235916001600160a01b0390811691166303938700610d74565b50606554606c54611257916001600160a01b0390811691166307270e00610d74565b50606554606d54611279916001600160a01b0390811691166311e1a300610d74565b50606554606e5461129b916001600160a01b039081169116630bebc200610d74565b50606554606f546112bb916001600160a01b039081169116617530610d74565b506001905090565b600054610100900460ff16806112dc57506112dc611b74565b806112ea575060005460ff16155b6113255760405162461bcd60e51b815260040180806020018281038252602e815260200180611f18602e913960400191505060405180910390fd5b600054610100900460ff16158015611350576000805460ff1961ff0019909116610100171660011790555b61139b6040518060400160405280600c81526020016b053706f727469716f4e5531360a41b8152506040518060400160405280600581526020016405350514e560dc1b815250611b7a565b606580546001600160a01b0319166001600160a01b03841617905542630b4735000160735580156113d2576000805461ff00191690555b5050565b6070546001600160a01b031681565b606c546001600160a01b031681565b6001600160a01b03918216600090815260346020908152604080832093909416825291909152205490565b606b546001600160a01b031681565b60385460ff610100909104161515600114611489576040805162461bcd60e51b8152602060048201526016602482015275151a1a5cc81d1bdad95b881a5cc8191a5cd8589b195960521b604482015290519081900360640190fd5b565b3390565b61149761142e565b6001600160a01b0383166114dc5760405162461bcd60e51b8152600401808060200182810382526024815260200180611fd86024913960400191505060405180910390fd5b6001600160a01b0382166115215760405162461bcd60e51b8152600401808060200182810382526022815260200180611ed06022913960400191505060405180910390fd5b6001600160a01b03808416600081815260346020908152604080832094871680845294825291829020859055815185815291517f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259281900390910190a3505050565b61158b61142e565b6001600160a01b0383166115d05760405162461bcd60e51b8152600401808060200182810382526025815260200180611fb36025913960400191505060405180910390fd5b6001600160a01b0382166116155760405162461bcd60e51b8152600401808060200182810382526023815260200180611e8b6023913960400191505060405180910390fd5b611620838383611c2b565b61165d81604051806060016040528060268152602001611ef2602691396001600160a01b03861660009081526033602052604090205491906116e8565b6001600160a01b03808516600090815260336020526040808220939093559084168152205461168c908261177f565b6001600160a01b0380841660008181526033602090815260409182902094909455805185815290519193928716927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef92918290030190a3505050565b600081848411156117775760405162461bcd60e51b81526004018080602001828103825283818151815260200191508051906020019080838360005b8381101561173c578181015183820152602001611724565b50505050905090810190601f1680156117695780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b505050900390565b6000828201838110156117d9576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b9392505050565b6117e861142e565b6001600160a01b03821661182d5760405162461bcd60e51b8152600401808060200182810382526021815260200180611f926021913960400191505060405180910390fd5b61183982600083611c2b565b61187681604051806060016040528060228152602001611eae602291396001600160a01b03851660009081526033602052604090205491906116e8565b6001600160a01b03831660009081526033602052604090205560355461189c9082611c30565b6035556040805182815290516000916001600160a01b038516917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9181900360200190a35050565b600081518351146118f7575060006106d0565b816040516020018082805190602001908083835b6020831061192a5780518252601f19909201916020918201910161190b565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405160208183030381529060405280519060200120836040516020018082805190602001908083835b602083106119985780518252601f199092019160209182019101611979565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051602081830303815290604052805190602001201490506106d0565b6119e361142e565b6001600160a01b038216611a3e576040805162461bcd60e51b815260206004820152601f60248201527f45524332303a206d696e7420746f20746865207a65726f206164647265737300604482015290519081900360640190fd5b611a4a60008383611c2b565b603554611a57908261177f565b6035556001600160a01b038216600090815260336020526040902054611a7d908261177f565b6001600160a01b03831660008181526033602090815260408083209490945583518581529351929391927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9281900390910190a35050565b603880549115156101000261ff0019909216919091179055565b600080356001600160e01b03191681526066602052604081205460ff1615611b485760405162461bcd60e51b8152600401808060200182810382526024815260200180611f6e6024913960400191505060405180910390fd5b50600080356001600160e01b03191681526066602052604090208054600160ff19909116811790915590565b303b1590565b600054610100900460ff1680611b935750611b93611b74565b80611ba1575060005460ff16155b611bdc5760405162461bcd60e51b815260040180806020018281038252602e815260200180611f18602e913960400191505060405180910390fd5b600054610100900460ff16158015611c07576000805460ff1961ff0019909116610100171660011790555b611c0f611c72565b611c198383611d14565b8015611c2b576000805461ff00191690555b505050565b60006117d983836040518060400160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f7700008152506116e8565b600054610100900460ff1680611c8b5750611c8b611b74565b80611c99575060005460ff16155b611cd45760405162461bcd60e51b815260040180806020018281038252602e815260200180611f18602e913960400191505060405180910390fd5b600054610100900460ff16158015611cff576000805460ff1961ff0019909116610100171660011790555b8015611d11576000805461ff00191690555b50565b600054610100900460ff1680611d2d5750611d2d611b74565b80611d3b575060005460ff16155b611d765760405162461bcd60e51b815260040180806020018281038252602e815260200180611f18602e913960400191505060405180910390fd5b600054610100900460ff16158015611da1576000805460ff1961ff0019909116610100171660011790555b8251611db4906036906020860190611df7565b508151611dc8906037906020850190611df7565b506038805461ff001960ff19909116601217166101001790558015611c2b576000805461ff0019169055505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10611e3857805160ff1916838001178555611e65565b82800160010185558215611e65579182015b82811115611e65578251825591602001919060010190611e4a565b50611e71929150611e75565b5090565b5b80821115611e715760008155600101611e7656fe45524332303a207472616e7366657220746f20746865207a65726f206164647265737345524332303a206275726e20616d6f756e7420657863656564732062616c616e636545524332303a20617070726f766520746f20746865207a65726f206164647265737345524332303a207472616e7366657220616d6f756e7420657863656564732062616c616e6365496e697469616c697a61626c653a20636f6e747261637420697320616c726561647920696e697469616c697a656445524332303a207472616e7366657220616d6f756e74206578636565647320616c6c6f77616e636554686973206d6574686f642068617320616c7265616479206265696e672063616c6c656445524332303a206275726e2066726f6d20746865207a65726f206164647265737345524332303a207472616e736665722066726f6d20746865207a65726f206164647265737345524332303a20617070726f76652066726f6d20746865207a65726f206164647265737345524332303a2064656372656173656420616c6c6f77616e63652062656c6f77207a65726fa2646970667358221220ac9e0f440750629c78643fc2e1e359b1c035a5e8131f9b6cfea352884becd6c064736f6c63430007010033";

    public static final String FUNC_AIRDROPWALLET = "airdropWallet";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_ANGELWALLET = "angelWallet";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_DUMMYWALLET = "dummyWallet";

    public static final String FUNC_ENABLETOKEN = "enableToken";

    public static final String FUNC_FOUNDERSWALLET = "foundersWallet";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_LIQUIDITYWALLET = "liquidityWallet";

    public static final String FUNC_MAKEADMIN = "makeAdmin";

    public static final String FUNC_MARKETWALLET = "marketWallet";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_MINTEDTOKENS = "mintedTokens";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OPERATIONALWALLET = "operationalWallet";

    public static final String FUNC_RESERVEMINTINGENDTIME = "reserveMintingEndTime";

    public static final String FUNC_SEEDWALLET = "seedWallet";

    public static final String FUNC_SENDTO = "sendTo";

    public static final String FUNC_SETADDRESS = "setAddress";

    public static final String FUNC_START = "start";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TIMELOCK = "timeLock";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TREASURYWALLET = "treasuryWallet";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected SPR(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SPR(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SPR(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SPR(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<String> airdropWallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_AIRDROPWALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> angelWallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ANGELWALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
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

    public RemoteFunctionCall<String> dummyWallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DUMMYWALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> enableToken(Boolean _value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ENABLETOKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> foundersWallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FOUNDERSWALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner)), 
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

    public RemoteFunctionCall<String> liquidityWallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LIQUIDITYWALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> makeAdmin(String _address) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MAKEADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> marketWallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MARKETWALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> mintedTokens() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MINTEDTOKENS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> operationalWallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OPERATIONALWALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> reserveMintingEndTime() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_RESERVEMINTINGENDTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> seedWallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SEEDWALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> sendTo(String from, String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SENDTO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAddress(String typex, String _address) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(typex), 
                new org.web3j.abi.datatypes.Address(160, _address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> start() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_START, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> timeLock() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TIMELOCK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String sender, String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> treasuryWallet() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TREASURYWALLET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static SPR load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SPR(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SPR load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SPR(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SPR load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SPR(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SPR load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SPR(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SPR> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SPR.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SPR> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SPR.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SPR> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SPR.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SPR> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SPR.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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
