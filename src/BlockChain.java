import java.util.ArrayList;
import com.google.gson.GsonBuilder;
public class BlockChain {

    //want to start storing our blocks in an arraylist
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    //want to set a constant value for the difficulty of mining
    //can set something like 4 so it's not too hard to mine
    public static int difficulty = 0;

    public static void main(String[] args){
        //add blocks to the blockchain ArrayList
        blockchain.add(new Block("First block", "0"));
        System.out.println("Trying to mine first block");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Second block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine second block");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Third block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Trying to mine the third block");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is valid: " + isChainValid());


        //print out the blockchain
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }

    /*
     //After creating something that is closer to what a blockchain looks like we need to check the integrity of the chain
    //We can do this by checking the hash of the current block and comparing it to the hash of the previous block
    //If the hashes don't match, then we know that the blockchain has been tampered with
    One thing that we have to update is if the block has been mined than the chain has been tampered with.
     */
    public static boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        //loop through the blockchain to check hashes, kinda like authentication factoring
        //if there are any changes to the hashes, then it must return false!

        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        for(int i = 1; i < blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare the registered hash and calculated hash
            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current hashes are not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Previous hashes are not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)){
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

}
