import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JOptionPane;



public class League 
{
	private static String currentTopTeam = "";
	private static ArrayList<Team> teams = new ArrayList<Team>();//Array list constructor importing the names into and array.
	static String name = "";// Initializing name as a string.

	public static void main(String[] args)
	{	
		for (int i = 0; i < 6; i++)// For loop taking 4 team names.
		{
			name = JOptionPane.showInputDialog(null, "Team Name/Person Name:");//Dialog box for team input.

			for (int j = 0; j <name.length(); j++)//For Loop to go through the length of a team name
			{
				if (((int) name.charAt(j) >= 48 && (int) name.charAt(j) <= 57))//IF statement condition to check valid or invalid characters using ASCII table values. 
				{
					JOptionPane.showMessageDialog(null, "Numeric Values are not accepted.", "Invalid Input", 0);//Dialog box printing and error message based on the IF statement conditions.

					i--;
					{
						break;// Break if the program get the correct information from the error condition. to continue to the rest of the code.
					}
				}
				else// Else statement to do what is below should it meet the conditions above
				{
					teams.add(new Team(name));// Add team name to the array
					name = "";// Initializing the name as a string.
				}
			}
		}
		printLeagueTable();//calling the printleagueTable Method
		playLeague();//calling the playLague Method
		
		//System.out.println("Champions: " + teams.get(teams.size()-1).getTeamName());// prints the current league champion
	}

	private static void printLeagueTable()
	{
		Collections.sort(teams, new TeamSort());//sorting for the team array.
		System.out.print("\nPos\t" + "Team\t" +"   " + "Points\t" + "Played\t" + "Win\t" + "Lose\t" + "Draw\t" +"GF\t" + "GA\t" + "GD\n");//print statement for the table headers

		int counter = 1;// counter initialization.
		for(int i=teams.size() -1 ; i >=0 ; i--) // for loop to go through the teams in the array.
		{
			System.out.println(counter + "\t" + teams.get(i).getTeamName()+ "\t" + teams.get(i).getPoints() + "\t"+ teams.get(i).getPlayed()+ "\t" + teams.get(i).getWin() + "\t" + teams.get(i).getLose() + "\t" + teams.get(i).getDraw() + "\t" + teams.get(i).getGoalsFor() + "\t" + teams.get(i).getGoalsAgainst() + "\t" + teams.get(i).getGoalDifference());//Printing the results from the array into a tablesd format.
			counter ++; // Counter increments through the table for the positions
		}
		currentTopTeam = teams.get(teams.size() -1 ).getTeamName();//tells who is currently at the top of the league table.
		//System.out.println("");
		//System.out.println("Current top team: " + currentTopTeam);// print statement to say who is at the top of the table.
		
		JOptionPane.showConfirmDialog(null,"Click OK to continue", null,JOptionPane.OK_CANCEL_OPTION);

	}

	private static void playLeague()
	{
		int totalMatches = 0;// initializing tatalMatches integer
		String score = "";// Initializing the score as string 
		while(totalMatches != teams.size()*2)// While loop for getting the amount matches when 2 teams are always playing.
		{
			Random random = new Random();// randomly play schedule the teams to play in 
			int teamNo = random.nextInt(teams.size() -1); // checks whether a team has played if it has then do remove it therefore it wont play again.

			Team currentTeam = teams.get(teamNo);// Pulling the teams names from the array to play
			if(currentTeam.getPlayedTeams().size() < teams.size() -1) //checking whether a team has played...if they have then remove them from the list
			{
				for(int i=0 ;i < teams.size(); i++)//for loop to check throughout the array of teams.
				{
					if((currentTeam.getTeamName().compareTo(teams.get(i).getTeamName()) != 0) && currentTeam.checkTeamPLayed(teams.get(i)) == false)//IF condition to compare if two teams have played each other..if so then dont play again
					{
						//System.out.println("");// print line for space.
						//System.out.println("Round Robin Matches: ");//print line showing the words in speech marks.
						//System.out.print(currentTeam.getTeamName() + " VS " + teams.get(i).getTeamName());// print line to do Team A VS Team B.

						score = JOptionPane.showInputDialog(null, "Match Result: " + currentTeam.getTeamName() + " VS " + teams.get(i).getTeamName(),null);//dialog box to take the scores.
							//check score input
						
							computeGoalScored(score, currentTeam, teams.get(i));//calling computeGaolScored method
							printLeagueTable();//calling PrintLeagueTable method
						}
					}
					totalMatches += 2;// 
				}
				continue;// continue statement to go to the rest of code
			}
		}
	
	private static void computeGoalScored(String score, Team currentTeamA, Team currentTeamB) 
	{// Conditions for when TEAM A wins again TEAM B 
		int currentTeamAScore = Integer.parseInt(score.substring(0, 1));// reading TeamA score as integer
		int currentTeamBScore = Integer.parseInt(score.substring(2, 3));// reading TeamB score as integer

		if(currentTeamAScore > currentTeamBScore)// comparison for TeamA and TeamB score if A is greater than B
		{
			for(Team a: teams)// For when team A wins
			{
				if(a.getTeamName().compareTo(currentTeamA.getTeamName()) == 0)//get team name for A
				{
					a.setPoints(+3);// add 3 points when A wins
					a.setGoalDifference(+ (currentTeamAScore - currentTeamBScore));//minus TeamA score from Team B score for team A goal Difference
					a.setPlayed(+1);//add one every time a team plays 
					a.setWin(+1); //add one ever time a team wins
					a.setGoalsFor(+ currentTeamAScore);// add teamA's current score plus for goals for
					a.setGoalsAgainst(currentTeamBScore);// add teamB's current score plus for goals against

					for(Team b: teams)// for when team B Lose
					{
						if(b.getTeamName().compareTo(currentTeamB.getTeamName())==0)//get team name for B
						{
							b.setGoalDifference(+ (currentTeamBScore - currentTeamAScore));//minus Team score from Team B score for team A goal Difference
							b.setPlayed(+1);//add one every time a team plays 
							b.setLose(+1);//add one ever time a team wins
							b.setGoalsFor(+ currentTeamAScore);// add teamA's current score plus for goals for
							b.setGoalsAgainst( + currentTeamBScore);// add teamB's current score plus for goals against
							b.addTeamPlayed(currentTeamA);// add teamA to played.
						}
					}
					a.addTeamPlayed(currentTeamB);//Add team B that played
				}
			}
		}
		else if(currentTeamAScore < currentTeamBScore)//Else if to check when B wins and A loses
		{
			for(Team a: teams)// Team A Loses
			{
				if(a.getTeamName().compareTo(currentTeamA.getTeamName()) == 0)//team 
				{	
					a.setGoalDifference(+ (currentTeamAScore - currentTeamBScore));
					a.setPlayed(+1);//
					a.setLose(+1);//

					for(Team b: teams)//
					{
						if(b.getTeamName().compareTo(currentTeamB.getTeamName())==0)//
						{
							b.setGoalDifference(+ (currentTeamBScore - currentTeamAScore));//
							b.setPlayed(+1);//
							b.setWin(+1);//
							b.setPoints(+3);//
							b.setGoalsFor(+ currentTeamBScore);//
							b.setGoalsAgainst(+ currentTeamAScore);//
							b.addTeamPlayed(currentTeamA);//
						}
					}
					a.addTeamPlayed(currentTeamB);//
				}
			}
		}
		else if(currentTeamAScore == currentTeamBScore)//
		{//Team A and Team B draw
			for(Team a: teams)//
			{
				if(a.getTeamName().compareTo(currentTeamA.getTeamName()) == 0)//
				{
					a.setPoints(+1);//
					a.setGoalDifference(+ (currentTeamAScore - currentTeamBScore));//
					a.setPlayed(+1);//
					a.setDraw(+1);//
					a.setGoalsFor(+ currentTeamAScore);//
					a.setGoalsAgainst(+ currentTeamBScore);//

					for(Team b: teams)//
					{
						if(b.getTeamName().compareTo(currentTeamB.getTeamName())==0)//
						{
							b.setGoalDifference(+ (currentTeamBScore - currentTeamAScore));//
							b.setPlayed(+1);//
							b.setPoints(+1);//
							b.setDraw(+1);//
							b.setGoalsFor(+ currentTeamBScore);//
							b.setGoalsAgainst(+ currentTeamAScore);//
							b.addTeamPlayed(currentTeamA);//
						}
					}
					a.addTeamPlayed(currentTeamB);//
				}
			}
		}
	}
}
