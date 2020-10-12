import java.util.*;

class Main
{   
    public static void encryptDecrypt(String plaintext, String keyword)
    {
        //Converting plaintext to char array
        char msg[] = plaintext.toCharArray();
        int msgLen = msg.length;
        int i,j;
        
        // Creating new char arrays
        char key[] = new char[msgLen];
        char encryptedMsg[] = new char[msgLen];
        char decryptedMsg[] = new char[msgLen];
        
        /* generate key, using keyword in cyclic 
        manner equal to the length of 
        original message i.e plaintext */
        for(i = 0, j = 0; i < msgLen; ++i, ++j)
        {
            if(j == keyword.length())
            {
                j = 0;
            }
            key[i] = keyword.charAt(j);
        }
        
        //encryption code 
        for(i = 0; i < msgLen; ++i)
            encryptedMsg[i] = (char) (((msg[i] + key[i]) % 26) + 'A');
        
        //decryption code
        for(i = 0; i < msgLen; ++i)
            decryptedMsg[i] = (char)((((encryptedMsg[i] - key[i]) + 26) % 26) + 'A');
        
        System.out.println("Original Message: " + plaintext);  
        System.out.println("Keyword: " + keyword);
        /* String.valueOf() method converts
            char[] to String */
        System.out.println();
        System.out.println("Encrypted Message: " + String.valueOf(encryptedMsg));
        System.out.println();        
        System.out.println("Decrypted Message: " + String.valueOf(decryptedMsg));
   }
   public static void main(String arg[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plaintext to encrypt:");
        String plaintext = sc.next();
        plaintext = plaintext.toUpperCase();
        System.out.println("Enter key:");
        String keyword = sc.next();   
        keyword = keyword.toUpperCase();
        encryptDecrypt(plaintext,keyword);
        sc.close();
    }
}