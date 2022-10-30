package clasesBasicas;

public class Resultado {

	private Torneo tour;
	private int year;
	private String winner;
	private String runnerUp;
	private int rankWinner;
	private int rankRunnerUp;
	private String finalScore;
	
	public Resultado(Torneo tour, int year, String winner, String runnerUp, int rankWinner, int rankRunnerUp,
			String finalScore) {
		this.tour = tour;
		this.year = year;
		this.winner = winner;
		this.runnerUp = runnerUp;
		this.rankWinner = rankWinner;
		this.rankRunnerUp = rankRunnerUp;
		this.finalScore = finalScore;
	}

	public Torneo getTour() {
		return tour;
	}

	public void setTour(Torneo tour) {
		this.tour = tour;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getRunnerUp() {
		return runnerUp;
	}

	public void setRunnerUp(String runnerUp) {
		this.runnerUp = runnerUp;
	}

	public int getRankWinner() {
		return rankWinner;
	}

	public void setRankWinner(int rankWinner) {
		this.rankWinner = rankWinner;
	}

	public int getRankRunnerUp() {
		return rankRunnerUp;
	}

	public void setRankRunnerUp(int rankRunnerUp) {
		this.rankRunnerUp = rankRunnerUp;
	}

	public String getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}

	@Override
	public String toString() {
		return year + "," + tour.getCode() + "," + winner + "," + rankWinner + "," + runnerUp
				+ ", rankWinner=" + "," + rankRunnerUp + ", finalScore=" + finalScore + "]";
	}
	
}
