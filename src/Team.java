import java.util.ArrayList;

public class Team {
	private String teamName;//name declaration
	private int goalsFor;//goals for declaration
	private int goalsAgainst;//goals against declaration
	private int played;//played declaration
	private int win;//win declaration
	private int lose;//lose declaration
	private int draw;//draw declaration
	private int points;//points declaration
	private int goalDifference;//goal difference declaration
	
	private ArrayList<Team> playedAgainst;//Array declaration


	public Team(String name){
		this.teamName = name;//initializing variable "name"
		this.goalsFor = 0;//initializing variable "goals for"
		this.goalsAgainst = 0;//initializing variable "goals against"
		this.played = 0;//initializing variable "played"
		this.win = 0;//initializing variable "win"
		this.lose = 0;//initializing variable "lose"
		this.draw = 0;//initializing variable "draw"
		this.points = 0;//initializing variable "points"
		this.goalDifference = 0;//initializing variable "goal difference"
		this.playedAgainst = new ArrayList<Team>();//initializing variable " played teams in an array"
	}
		public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public void setGoalsFor(int goalsFor) {
		this.goalsFor += goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst += goalsAgainst;
	}

	public int getPlayed() {
		return played;
	}

	public void setPlayed(int played) {
		this.played += played;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win += win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose += lose;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw += draw;
	}

	public int getPoints() {
		return points;
	}
	
	public ArrayList<Team> getPlayedTeams(){
		return this.playedAgainst;
	}

	public void setPoints(int points) {
		this.points += points;
	}

	public int getGoalDifference() {
		return goalDifference;
	}

	public void setGoalDifference(int goalDifference) {
		this.goalDifference += goalDifference;
	}
	
	public boolean checkTeamPLayed(Team team){
		for(int i =0; i < playedAgainst.size(); i++){
			if(team.getTeamName().compareTo(playedAgainst.get(i).getTeamName()) == 0){
				return true;
			}
		}
		return false;
	}
	
	public void addTeamPlayed(Team team){
		this.playedAgainst.add(team);
	}
}

