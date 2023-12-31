import java.util.Date;
/*
This class is a simple block that will be used to create a blockchain.

For context: a blockchain is pretty similar to a linkedList, but instead of
    having a pointer to the previous block, we have a hash of the previous block.
    This is what makes the blockchain immutable. If you change a block, you change
    the hash of the block, which changes the hash of the next block, and so on.
    This is why it's so hard to tamper with a blockchain. The reference direction of the block is backwards
    (where linkedList points to the next node)

    The blockchain is similar to a linkedList in that it is a series of blocks. The elements of a blockchain
    node include the hash of the current node, hash of the previous node, and the data and timestamp of
    when the block was created.

     Another thing to note: the data in a blockchain is immutable. If you want to change the data, you
        have to change the hash of the block, which changes the hash of the next block, and so on.
 */
public class Block {
    // the string hash will be used to hold our digital signature
    public String hash;
    //the previousHash will point to the current hash
    public String previousHash;
    //the data will be a simple message
    private String data;
    //the timeStamp will be used to track when the block was created (in milliseconds)
    private long timeStamp;
    //this will be used for the mining process
    private int count;

    //Creating the block constructor
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    /*
    now we want to apply the SHA256 algorithm to the block to calculate the hash
    since we want the block to include things that aren't tampered with, we want to include
        previousHash, data, and timeStamp.
     */
    public String calculateHash() {
        String calculatedHash = StringUtil.applySHA256(
                previousHash +
                        Long.toString(timeStamp) +
                        data
        );
        return calculatedHash; //make sure this is set AFTER all other values are set!
    }

    /*
    now we want to create a method called mineBlock that will be used to mine the block
    we want to make sure that the hash of the block starts with a certain number of 0's
    this is called proof of work. The more 0's, the harder it is to mine the block
     */

    public void mineBlock(int difficulty){
        //create a string with difficulty * "0"
        String target = new String(new char[difficulty]).replace('\0', '0');
        //while the hash of the block doesn't start with the target, keep incrementing the count
        while(!hash.substring(0, difficulty).equals(target)){
            count++;
            hash = calculateHash();
        }
        System.out.println("Block mined! : " + hash);
    }

}
