package trivia.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    private ArrayList players = new ArrayList();
    private int[] places = new int[6];
    private int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {
        getPlayers().add(playerName);
        getPlaces()[howManyPlayers()] = 0;
        getPurses()[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + getPlayers().size());
        return true;
    }

    public int howManyPlayers() {
        return getPlayers().size();
    }

    public void roll(int roll) {
        System.out.println(getPlayers().get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(getPlayers().get(currentPlayer) + " is getting out of the penalty box");
                getPlaces()[currentPlayer] = getPlaces()[currentPlayer] + roll;
                if (getPlaces()[currentPlayer] > 11) getPlaces()[currentPlayer] = getPlaces()[currentPlayer] - 12;

                System.out.println(getPlayers().get(currentPlayer)
                        + "'s new location is "
                        + getPlaces()[currentPlayer]);
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(getPlayers().get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            getPlaces()[currentPlayer] = getPlaces()[currentPlayer] + roll;
            if (getPlaces()[currentPlayer] > 11) getPlaces()[currentPlayer] = getPlaces()[currentPlayer] - 12;

            System.out.println(getPlayers().get(currentPlayer)
                    + "'s new location is "
                    + getPlaces()[currentPlayer]);
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }
    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (getPlaces()[currentPlayer] == 0) return "Pop";
        if (getPlaces()[currentPlayer] == 4) return "Pop";
        if (getPlaces()[currentPlayer] == 8) return "Pop";
        if (getPlaces()[currentPlayer] == 1) return "Science";
        if (getPlaces()[currentPlayer] == 5) return "Science";
        if (getPlaces()[currentPlayer] == 9) return "Science";
        if (getPlaces()[currentPlayer] == 2) return "Sports";
        if (getPlaces()[currentPlayer] == 6) return "Sports";
        if (getPlaces()[currentPlayer] == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                getPurses()[currentPlayer]++;
                System.out.println(getPlayers().get(currentPlayer)
                        + " now has "
                        + getPurses()[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == getPlayers().size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == getPlayers().size()) currentPlayer = 0;
                return true;
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            getPurses()[currentPlayer]++;
            System.out.println(getPlayers().get(currentPlayer)
                    + " now has "
                    + getPurses()[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == getPlayers().size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(getPlayers().get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == getPlayers().size()) currentPlayer = 0;
        return true;
    }

    private boolean didPlayerWin() {
        return !(getPurses()[currentPlayer] == 6);
    }

    public ArrayList getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList players) {
        this.players = players;
    }

    public int[] getPlaces() {
        return places;
    }

    public void setPlaces(int[] places) {
        this.places = places;
    }

    public int[] getPurses() {
        return purses;
    }

    public void setPurses(int[] purses) {
        this.purses = purses;
    }
}
