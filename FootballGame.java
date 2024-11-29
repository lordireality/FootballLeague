
public class FootballGame {
	private static int gameNum = 1;
    private String awayTeam;
    private String homeTeam;
    private int awayScore;
    private int homeScore;
    private int temperature;

    public FootballGame(String awayTeam, String homeTeam, int awayScore, int homeScore, int temperature) {
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
        this.awayScore = awayScore;
        this.homeScore = homeScore;
        this.temperature = temperature;
    }
    public String getResults() {
        return "Игра #" + gameNum++ + "\n" +
                "Температура: " + temperature + "\n" +
                "Гостевая команда: " + awayTeam + ", " + awayScore + "\n" +
                "Домашняя команда: " + homeTeam + ", " + homeScore;
    }
}
