class MinOppgave2 {
    public static void main(String[] args) {
    
        // This is a sing-along so feel free to join in
    
        // Bottles to start with
        int bottles = 99;
    
        // Looping and subtracting one each time
        while (bottles != 0) {
            printLyrics(bottles);
            bottles--;
        }
    }
    
    static void printLyrics(int bottles) {
        
        // Sing the lyrics out loud!!
        System.out.print(bottles + " bottles of beer on the wall, " + bottles + " bottles of beer.\nTake one down and pass it around, " + (bottles - 1) + " bottles of beer on the wall.\n\n");
    }
}
