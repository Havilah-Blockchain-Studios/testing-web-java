// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.8.19;

//import openzepplin
import "./ERC20Upgradable.sol";

contract vest{
    function setDelegates(address, uint256, uint256) external returns(bool){}
    function lockToken(uint256, uint256, address) external returns (uint256){}
    function withdraw(uint256,address) external returns(bool){}
}
//begining of token contract
contract SPR is ERC20Upgradeable{
    address owner;
    mapping(bytes4 => bool) isRun;
    address public seedWallet;
    address public angelWallet;
    address public foundersWallet;
    address public airdropWallet;
    address public marketWallet;
    address public liquidityWallet;
    address public treasuryWallet;
    address public operationalWallet;
    address public dummyWallet;

    address public timeLock;

    uint256 _wei;
    uint256 vestId;
    uint256 public reserveMintingEndTime;
    vest _vest;



    function initialize(address _owner) public initializer {
        __ERC20_init("SportiqoNU10", "SPQNP");
        owner = _owner; // set initial value in initialiowner = msg.sender;
        reserveMintingEndTime = block.timestamp + (6 * (365 days));
    }
    function start() public returns(bool){
        isOwner();once();
        //ensure all the wallets has beens set
        require(seedWallet != address(0), "Seed wallet not set");
        require(angelWallet != address(0), "Angel wallet not set");
        require(foundersWallet != address(0), "Founders wallet not set");
        require(airdropWallet != address(0), "Airdrop wallet not set");
        require(marketWallet != address(0), "Market wallet not set");
        require(liquidityWallet != address(0), "Liquidity wallet not set");
        require(treasuryWallet != address(0), "Treasury wallet not set");
        require(operationalWallet != address(0), "Operational wallet not set");
        require(dummyWallet != address(0), "Dummy wallet not set");
        //mint 1B, the NPTR should have trannsfer ownership before this function call
        require(mint(1000000000)==true,"Minting is not done");
        //transfer 4% to seedWallet
        sendTo(owner,seedWallet, (4 *1000000000) / 100);
        // transfer 10% to angelWallet
        sendTo(owner,angelWallet, (10 *1000000000) / 100);
        //transfer 15 % to founderWallet
        sendTo(owner,foundersWallet, (15 * 1000000000) / 100);
        //transfer 2.997 % to airdropWallet
        sendTo(owner,airdropWallet, (2.997 * 1000000000) / 100);
        //transfer 6 % to marketWallet
        sendTo(owner,marketWallet, (6 * 1000000000) / 100);
        //transfer 12 % to liquidityWallet
        sendTo(owner,liquidityWallet, (12 *1000000000) / 100);
        //transfer 30 % to treasuryWallet
        sendTo(owner,treasuryWallet, (30 *1000000000) / 100);
        //transfer 20 % to operationalWallet
        sendTo(owner,operationalWallet, (20 *1000000000) / 100);
        //transfer 0.003% to dummy wallet
        sendTo(owner, dummyWallet, (0.003 * 1000000000)/100);

        return true;
    }
    //to start minting
    function mint(uint256 amount) public  returns (bool){
        //to mint, only done by owner
        isOwner();isEnable();
        _mint(owner, amount);
        return true;
    }
    //to send tokens to
    function sendTo(address from, address to, uint256 amount) public returns(bool){
        //to transfer minted tokens to
        isOwner();isEnable();
        require(balanceOf(from) >= amount || balanceOf(treasuryWallet) >= amount, "Insufficient amount");
        //remove tokens from smart contract wallet
        // if(balanceOf(from) >= amount){
        //     _balances[from] = _balances[from] - amount;
        // }else if(balanceOf(treasuryWallet) >= amount){
        //     _balances[treasuryWallet] = _balances[treasuryWallet] - amount;
        // }
        // //send tokens to wallet
        // _balances[to] = _balances[to] + amount;

        _transfer(from, to, amount);
        return true;
    }
    //to burn tokens only by  ADMIN
    function burn(uint256 amount) public  returns (bool){
        isOwner();isEnable();
        require(_balances[address(this)] >= amount, "Insufficient amount");
        _burn(address(this), amount);
        return true;
    }

    //to disable and enable token
    function enableToken(bool _value) public returns (bool){
        isOwner();
        _enable(_value);
    }
    //to transfer ownership
    function makeAdmin(address _address) public returns (bool){
        //transfer ownership status
        isOwner();
        owner = _address;
        return true;
    }

    //get available minted tokens
    function mintedTokens() public view returns (uint256 bal){
        //transfer ownership status
        bal = balanceOf(address(this));
        return bal;
    }
    //to change the tradding wallet or reward wallet
    function setAddress(string memory typex, address _address) public returns(bool){
        isOwner();
        if(strequ(typex,"seed")){
            seedWallet = _address;
        }else if(strequ(typex, "angel")){
            angelWallet = _address;
        }else if(strequ(typex, "founders")){
            foundersWallet = _address;
        }else if(strequ(typex, "airdrop")){
            airdropWallet = _address;
        }else if(strequ(typex, "market")){
            marketWallet = _address;
        }else if(strequ(typex, "liquidity")){
            liquidityWallet = _address;
        }else if(strequ(typex, "treasury")){
            treasuryWallet = _address;
        }else if(strequ(typex, "operations")){
            operationalWallet = _address;
        }else if(strequ(typex, "dummy")){
            dummyWallet = _address;
        }
        return true;
    }

    //auxillary functions
    function strequ(string memory a, string memory b) private pure returns (bool) {
        if(bytes(a).length != bytes(b).length) {
            return false;
        } else {
            return keccak256(abi.encodePacked(a)) == keccak256(abi.encodePacked(b));
        }
    }
    //check ownership
    function isOwner() public returns(bool){
        require(owner == msg.sender, "Can only be done by Owner");
        return true;
    }
    //this modifier allows this function to be done once
    function once() private returns (bool){
        require(isRun[msg.sig] == false, "This method has already being called");
        //set it to as being called
        isRun[msg.sig] = true;
        return true;
    }
}