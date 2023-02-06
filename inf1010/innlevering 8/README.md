This delivery has been corrected, and the grade is: 6/6 (passed)
Veldig pent. Ryddig og oversiktlig, for det meste, selv uten kommentarer.

Du kan gjerne bruke litt flere vanlige arrays framfor ArrayLists her, men for øvrig har jeg få innvendinger.

Du burde ikke fylle Rute.possible med verdier i konstruktøren, det kan gå riktig gæærnt om metoden finnAlleMuligeTall() kalles flere ganger. En alternativ måte å implementere finnAlleMuligeTall() på:

// Returns an array containing all candidate values for insertion. The array may contain trailing zeroes.

public int[] finnAlleMuligeTall() {

    int[] candidates = new int[Brett.size]; // consider making this a field variable for long-term memory efficiency

    int counter = 0;    // likewise

    // Assume Row/Box/Column knows or can deduce whether or not a value is already taken.
    for (int j = 1; j <= Brett.size; j ++) {

        if (!(myRow.isValueTaken(j) && myColumn.isValueTaken(j) && myBox.isValueTaken(j))
            candidates[counter++] = j;
    }

    // candidatesLength = counter;  // crucial if candidates is a field variable
}
isValueTaken(int j) kan implementeres på flere måter, f.eks. ved at Structure går igjennom rutene sine, eller har et boolean-array.