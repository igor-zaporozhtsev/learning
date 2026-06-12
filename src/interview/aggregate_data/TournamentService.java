package interview.aggregate_data;

import Java8.streamAPI.groupingBy.Worker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TournamentService {
    /*
       Create tournament brackets based on player rankings.

       Return Map where:
       Key is Round Number (Integer): 1, 2, 3, etc.
       Value is List of Match descriptions (String): "PlayerA vs PlayerB"

       Rules:
       1. Players are seeded by their ranking (lower number = better)
       2. First round: highest seed plays lowest seed
          - #1 vs #8, #2 vs #7, #3 vs #6, #4 vs #5 (for 8 players)
       3. Match format: "{betterSeed} vs {worseSeed}"
       4. Winners advance to next round (determined by MatchResult)
       5. If player is disqualified (Player.isDisqualified), they auto-lose
       6. Continue until one winner remains

       Edge cases:
       - If both players disqualified in a match → skip match, no one advances
       - If match result references non-existent players → skip that result
       - Only include rounds that have matches (don't add empty rounds)
    */

	public static class Player {
		String name;
		int seed;           // 1 = best, higher = worse
		boolean disqualified;

		public Player(
			String name,
			int seed,
			boolean disqualified
		) {
			this.name = name;
			this.seed = seed;
			this.disqualified = disqualified;
		}

		public String getName() { return name; }
		public int getSeed() { return seed; }
		public boolean isDisqualified() { return disqualified; }
	}

	public static class Tournament{
		private final List<Player> players;
		private List<MatchResult> results;

		public Tournament(
			final List<Player> players
		) {
			this.players = players;
			this.results = List.of();
		}

		public void addRoundResults(
			List<MatchResult> results
		){
			this.results = results;
		}

		public Map<Integer, List<String>> generateBracket(){
			Map<Integer, List<String>> bracket = new HashMap<>();

			if (!results.isEmpty()) {
				//take results and seed players according to level and wins in 1 round of competition
				return Map.of();
			} else {



				List<Player> sorted = players.stream()
					.sorted(Comparator.comparingInt(Player::getSeed))
					.toList();

				List<String> opponents = new ArrayList<>();

				for (int f = 0; f < sorted.size() / 2; f++) {
					Player first = sorted.get(f);
					Player last = sorted.get(sorted.size() - 1 - f);
					opponents.add(first.name + " vs " + last.name);
				}
				bracket.put(1, opponents);

				//List<String> opponents = IntStream.range(0, players.size() / 2)
				//.mapToObj(f -> players.get(f).name + " vs " + players.get(players.size() - 1 - f).name)
				//.collect(Collectors.toList());
			}
			return bracket;
		}
	}

	public static class MatchResult {
		int roundNumber;
		String player1Name;
		String player2Name;
		String winnerName;     // who won this match

		public MatchResult(
			int roundNumber,
			String player1Name,
			String player2Name,
			String winnerName
		) {
			this.roundNumber = roundNumber;
			this.player1Name = player1Name;
			this.player2Name = player2Name;
			this.winnerName = winnerName;
		}

		public int getRoundNumber() { return roundNumber; }
		public String getPlayer1Name() { return player1Name; }
		public String getPlayer2Name() { return player2Name; }
		public String getWinnerName() { return winnerName; }
	}

	public static void main(String[] args) {

		List<Player> players = List.of(
			new Player("Alice", 1, false),
			new Player("Bob", 2, false),
			new Player("Charlie", 3, false),
			new Player("Diana", 4, false),
			new Player("Eve", 5, false),
			new Player("Frank", 6, false),
			new Player("Grace", 7, false),
			new Player("Henry", 8, true)     // disqualified!
		);

		Tournament tournament = new Tournament(players);
		Map<Integer, List<String>> bracket = tournament.generateBracket();


		// Round 1 results
		List<MatchResult> results = List.of(
			new MatchResult(1, "Alice", "Henry", "Alice"),    // Henry DQ'd
			new MatchResult(1, "Bob", "Grace", "Bob"),
			new MatchResult(1, "Charlie", "Frank", "Charlie"),
			new MatchResult(1, "Diana", "Eve", "Diana"),
			// Round 2 results
			new MatchResult(2, "Alice", "Bob", "Alice"),
			new MatchResult(2, "Charlie", "Diana", "Charlie"),
			// Round 3 (finals)
			new MatchResult(3, "Alice", "Charlie", "Alice")
		);

		System.out.println(bracket);

        /* Expected output:
        {
          1: ["Alice vs Henry", "Bob vs Grace", "Charlie vs Frank", "Diana vs Eve"],
          2: ["Alice vs Bob", "Charlie vs Diana"],
          3: ["Alice vs Charlie"]
        }
        */
	}
}
