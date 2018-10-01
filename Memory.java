import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Memory{
  public static void main(String[] args){
    
    System.out.println(Arrays.toString(generateCards(5)));//1a  
    int[] b = {1,2,3,4,5,1,2,3,4,5};
    shuffleCards(b);//1b
    System.out.println(Arrays.toString(b));//print to verify whether array is shuffled   
    int[] a = {-1,4,5,1,2,4,-1,1,2,5};
    diplayCards(a, 0, 5);//1c
    isValidGuess(a, 0, -3);//1d
    checkAndUpdate(a, 2, 9);//1e
    System.out.println(Arrays.toString(a));//verify whether list is updated
    System.out.println(gameCompleted(a));
    playMemory();
  }
//1a
  // create a method with input of n represent number of pairs, and return an array size2*n
  public static int[] generateCards(int n){
    int [] cards = new int[n*2];   //returns an array of integers of size 2*n
    int i;
    int j=0;
    for(i=0; i< n*2; i++){//use for loop to generate pairs
      if(i<n) {
        cards[i]=i+1;
      }else{
        cards[i]=j+1; //start counting from index i
        j++;
      } 
    }
    return cards;
  }
  
  //1b
  //method for shuffling cards
  public static void shuffleCards(int[] a){
    Random random = new Random(123);//use seed for random object
    int count=0; 
    while(count< 100000){//swap i and j 100,000 times
      int i= random.nextInt(a.length);//i, j are two random in representing the index
      int j= random.nextInt(a.length);
      int temp=a[i];//swap the elements of array, use temp as 3rd variable
      a[i]=a[j];
      a[j]=temp;  
      count++;
    }
  }
  //1c
  //method x,y are the last two guess made by user
  //method goes through all the elements of the array
  public static void diplayCards(int[] a, int x, int y){
    //x, y are the last guess made by user, index of array
    for(int i = 0; i< a.length;i++){
      if(i==x){//when index i=x and i=y, display value at index i
        System.out.print(a[i]);
      }else if(i==y){
        System.out.print(a[i]);
      }else if(a[i]==-1){
      System.out.print("*");
      }else {
        System.out.print("-");
      }
    }System.out.println();//to change the line
  }
  
  //1d
  //a guess is considered to be valid if both positions represent a possible index of the array
  //if they do not refer to a card/element that has already been guessed
  public static boolean isValidGuess(int[] a, int x, int y){
    if ((x< 0)||(y< 0)||(x>= a.length)||(y>= a.length)){//a condition for not valid index of array
      System.out.println("One of your guesses is out of range. Your guesses should be numbers between 0 and " + (a.length-1) );
      return false;
    }else if(!(a[x] == -1)||(a[y] == -1)){//condition for already guessed   
      return true;
    }else{
      System.out.println("You have already correctly guessed one of these cards. You wasted a guess! :(");
      return false;
    }
  }
  //1e
  public static void checkAndUpdate(int[] a, int x, int y){
    if(a[x]!=a[y]){
      System.out.println("Sorry!:( Try again!");
    }else{
      System.out.println("Good job, you got it!");
      a[x]=-1;// update the array element to -1 to indicate the card is correclty guessed already
      a[y]=-1;
    }
  }
  //1f 
  //method to check if the game has end
  public static boolean gameCompleted(int[]a){
    int i;
    for(i=0; i< a.length; i++){//to go throught each element in array to see if all ==-1
      if(a[i]!=-1){//if some element is not -1, game is not ended yet
        return false;
      }
    } return true;
  }
  
  //1g
  public static void playMemory(){
//create a scanner
    Scanner playMemory = new Scanner(System.in);//1
    System.out.println("Welcome to Memory!\nPlease choose the size of your game.");
    int pairs = playMemory.nextInt();//number of pairs of cards  
    if (pairs<=0)
      throw new IllegalArgumentException("Sorry such a game cannot be built");
     
    //use an array with pairs player enter
    int[] memory= generateCards(pairs);//2
    shuffleCards(memory);//new array is generated after shuffling
  
    int count=0; //count how many times guesses
    while(! gameCompleted(memory)){
      
      System.out.println("Please enter your first guess: ");
      int guess1 = playMemory.nextInt();
      System.out.println("Please enter your second guess: ");
      int guess2 = playMemory.nextInt();
      
      if(isValidGuess(memory, guess1, guess2)){
        diplayCards(memory, guess1, guess2);
        checkAndUpdate(memory, guess1, guess2);
      }
     
      count++;
    }
    
    System.out.println("Congratulations! You finishd the game with only "+ count+ " tries");
    
    
    
  }
}















