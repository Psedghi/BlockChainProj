import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
Applies SHA-256 to a string and returns the result.
SHA-256 is a cryptographic hash function that takes a string of
arbitrary length and returns a string of 256 bits (32 bytes).
 */
public class StringUtil {


    public static String applySHA256(String input)  {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Applies sha256 to our input
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
            for(int i = 0; i < hash.length; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
