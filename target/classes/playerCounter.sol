// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.8.19;

import "./ERC20.sol";
import "./Pausable.sol";


contract playerToken is ERC20, Pausable{
    address owner;
    uint256 numberOfToken;
    bool flag=false;
    address houseAddr;
    uint256 amount;
    address public pcSMCA;

    mapping(address => uint256) public _PCBuying;
    mapping(address => uint256) public _PCSelling;


    uint256 public userTotalPC;



    constructor(string memory _name, string memory _symbol, address _houseAddr, uint256 _totalSupply ) ERC20(_name,_symbol) {


        owner = _houseAddr;
        numberOfToken=_totalSupply;
        houseAddr=_houseAddr;
        pcSMCA=address(this);
        amount=_totalSupply;
        mint(numberOfToken);

    }






    function mint(uint256 _amount) public  returns (bool){


        isOwner();


        _mint(houseAddr, _amount);

        flag=true;



        return true;
    }


    function sendPcToUser(address to, uint256 _amount) public returns (bool){

        transfer(to, _amount);

        // _PCBuying[to]+=_amount;

        return true;
    }

    function transferPc(address to, uint256 _amount) public returns (bool){

        require(to==houseAddr,"Use transfer function");

        userTotalPC=_balances[msg.sender];

        transfer(to, _amount);

        // _PCSelling[msg.sender]+=_amount;

        return true;
    }

    // function userPC(address _user) public returns (bool) {
    //     userTotalPC=_PCBuying[_user] - _PCSelling[_user];
    // }





    // function sendToHouse() public returns(bool){

    //     isOwner();
    //     require(flag,"You have to mint token first");
    //     require(_balances[address(this)] >= _amount, "Insufficient amount");

    //     _balances[address(this)] = _balances[address(this)] - _amount;

    //     _balances[houseAddr] =_balances[houseAddr]+_amount;
    //     return true;
    // }


    function makeOwner(address _address) public returns (bool){

        isOwner();
        owner = _address;
        return true;
    }



    function isOwner() public returns(bool){
        require(owner == msg.sender, "Can only be done by Owner");
        return true;
    }

    function decreaseSupply(address account1, uint256 _amount) public returns (bool){

        isOwner();

        _burn(account1, _amount);

        return true;
    }


    function deactivate() public {
        isOwner();
        _pause();
    }

    function activate() public  {
        isOwner();
        _unpause();
    }

    function _beforeTokenTransfer(address from, address to, uint256 _amount)
    internal
    whenNotPaused
    override
    {
        super._beforeTokenTransfer(from, to, _amount);
    }
}
