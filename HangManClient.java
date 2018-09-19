import java.util.*;
import java.io.*;
public class HangManClient
{
public static void main (String [] args ) throws IOException
{
    Scanner reader= new Scanner (System.in );
    System.out.println("How long is the word?");
    int a = reader.nextInt ();
    char [] currentWord = new char [a];
    ArrayList <Character> alreadyGuessed = new ArrayList <Character> ();
   int response=0;
   char myLetter ='!';
   String word;
   int count =0;
   int numWrong =0;
   boolean done = false;
String order= "etaoinshrelcumwfgypbvkjxqz"; 
   File file1 = new File("langList.txt");
    Scanner fr= null;
     for (int w=0; w< currentWord.length; w++)
        currentWord [w]='0';
      ArrayList <String> availableWords = new ArrayList <String> ();
  try {
     fr = new Scanner (file1) ; 
    if (! file1.exists()) { // It's not there!
      throw new FileNotFoundException("Could not find file langList1");
    }
}
    finally {
     while ( fr.hasNext() )
    {
       word= fr.next ();
        if (word.length() == a)
            availableWords.add(word.toLowerCase());
    }
}
     while (done==false)
           {               
              availableWords = listAdjuster(currentWord, availableWords, alreadyGuessed); 
            for (int x=0; x< 26; x++)
              {
                  if (isThisLetterInThisArrayList(order.charAt(x),availableWords )&& !(alreadyGuessed.contains(order.charAt(x)) ))
                  {
                      myLetter = order.charAt(x);
                      break;
                    }
                }
           
                     if (availableWords.size()<3)
               { 
                   System.out.println ("Your word is, " + availableWords.get (0));
                   System.out.println ("You had this many wrong guesses: "+ numWrong);
                   done=true;
                   break;
                } 
                System.out.println("There are " + (availableWords.size() -1) + " possible words. Is there a letter: " + myLetter + "?");
                count =0;
             while (true)
             {
                    response = reader.nextInt ();
             if (response ==-1)   
               {
                  if (count ==0)
                    numWrong++;
                   break;                   
                }
                   currentWord [response] = myLetter; 
                   count++;
            } 
            alreadyGuessed.add(myLetter); 
            }
}
public static ArrayList <String> listAdjuster(char []currentWord, ArrayList <String> availableWords,ArrayList <Character> alreadyGuessed)
{    
    for (int x=availableWords.size()-1; x>0; x--)
    {
          for (int w=0; w< currentWord.length; w++)
            {
                       if ((currentWord [w] =='0' && alreadyGuessed.contains(availableWords.get(x-1).charAt(w)))  )
                          {
                             availableWords.remove(x-1);
                             break;
                            }
                            else if(currentWord [w] !='0' && currentWord [w] !=availableWords.get(x-1).charAt(w) )
                            {
                                availableWords.remove(x-1); 
                                break;
                            }
        }  
}
return availableWords;
}
public static boolean isThisLetterInThisArrayList (char letter,ArrayList <String> availableWords)
{
       for (int x=availableWords.size()-1; x>0; x--)
       {
           if (availableWords.get(x).indexOf (letter) >-1)
              return true;
        }
    return false;
}
}
