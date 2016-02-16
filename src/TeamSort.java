import java.util.Comparator;


public class TeamSort implements Comparator<Team> {
    public int compare(Team t1, Team t2) { 
    	
    	int team1Point = t1.getPoints();//Initializing team1 points as an integer
    	int team2Point = t2.getPoints();//Initializing team2 points as an integer
    	
    	int team1GD = t1.getGoalDifference();//Initializing team1 goal difference as an integer
    	int team2GD = t2.getGoalDifference();//Initializing team2 goal difference as an integer
    	
    	String team1Name = t1.getTeamName(); //Initializing team1 name as an string
    	String team2Name = t2.getTeamName();//Initializing team2 points as an string
    	
    	
    	
    	if((team1Point == team2Point) && (team1GD == team2GD))//conditions for comparing teams points and goal difference to see if they are equal
    		return team2Name.compareTo(team1Name);//compare team names 
    	else if((team1Point == team2Point))//compare team points
    		return team1GD - team2GD;//
    	else
    		return team1Point - team2Point;
    		
    }
}