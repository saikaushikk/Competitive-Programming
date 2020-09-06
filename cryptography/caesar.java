import java.util.*;
import java.io.*;
class Main{
    public static String encrypt(String s,int offset)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)==' ')
            {
                sb.append(" ");
                continue;
            }
            int c = s.charAt(i) - 'a';
            c = (c+offset)%26;
            sb.append((char)(c+'a'));
        }
        return sb.toString();
    }
    public static String decrypt(String s,int offset)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)==' ')
            {
                sb.append(" ");
                continue;
            }
            int c = s.charAt(i) - 'a';
            c = (c-offset+26)%26;
            sb.append((char)(c+'a'));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string to be encoded:");
        String s = sc.nextLine();
        System.out.println("Enter the shift amount:");
        int n = sc.nextInt();
        String encrypt = encrypt(s, n);
        String decrypt = decrypt(encrypt, n);
        System.out.println("The encrypted string is:" + encrypt);
        System.out.println("The decrypted string is:" + decrypt);
        sc.close();
    }
}