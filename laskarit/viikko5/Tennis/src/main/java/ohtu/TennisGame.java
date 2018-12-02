package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1Score++;
        else
            player2Score++;
    }

    public String getScore() {

        if (player1Score == player2Score) {
            return equalScores();
        }

        if (player1Score >=4 || player2Score >=4) {
            return gameEndScore();
        }

        return scoreToString(player1Score) + "-" + scoreToString(player2Score);
    }

    private String equalScores() {
        if (player1Score == 4) {
            return "Deuce";
        }
        return scoreToString(player1Score) + "-All";
    }
    private String scoreToString(int score) {
        switch (score)
        {
            case 0:
                return  "Love";
            case 1:
                return  "Fifteen";
            case 2:
                return  "Thirty";
            case 3:
                return  "Forty";
            default:
                return "Deuce";
        }
    }

    private String gameEndScore() {

        if (Math.abs(player1Score - player2Score) > 1) {
            return winScore();
        }

        return advantageScore();
    }
    private String advantageScore() {
		String leader = leadingPlayer();
        if (player1Score > player2Score) {
            return "Advantage " + leader;
        }
        return "Advantage " + leader;
    }
    private String winScore() {
		String leader = leadingPlayer();
        if (player1Score > player2Score) {
            return "Win for " + leader;
        }
        return "Win for " + leader;
    }
	private String leadingPlayer() {
		if (player1Score > player2Score) {
            return "player1";
        }
		return "player2";
	}
}