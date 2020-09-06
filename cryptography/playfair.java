import java.util.*;
import java.io.*;
class Main{
    static Map<Character,int[]> map;
    public static char[][] buildMatrix(String key)
    {
        char[][] matrix = new char[5][5];
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        for(char c:key.toCharArray())
            set.add(c);
        int i=0,j=0;
        for(char c:set)
        {
            matrix[i][j] = c;
            map.put(c,new int[]{i,j});
            j++;
            if(j==5)
            {
                i++;
                j=0;
            }
            if(i==5)
                break;
        }
        for(char c='a';c<='z';c++)
        {
            if(c=='j' || set.contains(c))
                continue;
            matrix[i][j] = c;
            map.put(c,new int[]{i,j});
            j++;
            if(j==5)
            {
                i++;
                j=0;
            }
            if(i==5)
                break;
        }
        return matrix;
    }
    public static String encrypt(String text,char[][] matrix)
    {
        StringBuilder sb = new StringBuilder();
        int n = text.length();
        for(int i=0;i<n;i+=2)
        {
            char x = text.charAt(i);
            char y = text.charAt(i+1);
            int[] xPos = map.get(x);
            int[] yPos = map.get(y);
            //same row
            if(xPos[0]==yPos[0])
            {
                char a = matrix[xPos[0]][(xPos[1]+1)%5];
                char b = matrix[yPos[0]][(yPos[1]+1)%5];
                sb.append(a);
                sb.append(b);
            }
            //same column
            else if(xPos[1]==yPos[1])
            {
                char a = matrix[(xPos[0]+1)%5][xPos[1]];
                char b = matrix[(yPos[0]+1)%5][yPos[1]];
                sb.append(a);
                sb.append(b);
            }
            //form rectangle
            else
            {
                char a = matrix[yPos[0]][xPos[1]];
                char b = matrix[xPos[0]][yPos[1]];
                sb.append(b);
                sb.append(a);
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        map = new HashMap<>();
        System.out.println("Enter the key:");
        String key = sc.nextLine();
        char[][] matrix = buildMatrix(key);
        System.out.println("The matrix is:");
        for(int i=0;i<5;i++)
        {
            System.out.println(matrix[i]);
        }
        System.out.println("Enter the text:");
        String text = sc.nextLine();
        if(text.length()%2==1)
        {
            text = text + 'z';
        }
        text = text.replaceAll("j", "i");
        System.out.println("The encrypted text is:" + encrypt(text, matrix));
        sc.close();
    }
}