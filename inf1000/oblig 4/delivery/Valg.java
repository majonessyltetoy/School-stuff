import java.util.*;
import java.io.*;


class Valg {
    public static void main(String[] args) throws Exception
    {
        // Variables
        File fil = new File("stemmer.txt");
        int[] votes = new int[4];
        Scanner in = new Scanner(fil);
        String party = new String("");
        
        // Traverse the file
        while (in.hasNextLine())
        {
            party = in.nextLine();
            
            if (party.equals("Ap")) {
                votes[0]++;
            }
            
            else if (party.equals("KrF")) {
                votes[1]++;
            }
            
            else if (party.equals("Sp")) {
                votes[2]++;
            }
            
            else
            {
                votes[3]++;
            }
        }
        // We have no further use for the file
        in.close();
        
        // We initiate who has the most votes with the first party
        int mostVotes = 0;
        for (int i=0; i < votes.length; i++)
        {
            if (votes[mostVotes] < votes[i])
                mostVotes = i;
        }
        
        // Count how many voted
        double voteCount = votes[0] + votes[1] + votes[2] + votes[3];
        
        
        // Estimate percentage
        // Note: the total percentage will be sligthly off because we round to
        // the nearest integer
        System.out.println("Ap: " +
        Math.round((votes[0] / voteCount) * 100)  + "%");
        
        System.out.println("KrF: " + 
        Math.round((votes[1] / voteCount) * 100) + "%");
        
        System.out.println("Sp: " + 
        Math.round((votes[2] / voteCount) * 100) + "%");
        
        System.out.println("H: " + 
        Math.round((votes[3] / voteCount) * 100) + "%");
        
        
        // Print who won the election
        if (mostVotes == 0) {
            System.out.println("The winner is Ap");
        }
        else if (mostVotes == 1) {
            System.out.println("The winner is KrF");
        }
        else if (mostVotes == 2) {
            System.out.println("The winner is Sp");
        }
        else {
            System.out.println("The winner is H");
        }
    }
}
