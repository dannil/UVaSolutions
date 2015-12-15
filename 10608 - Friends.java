import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	public class Person {

		private boolean isInGroup;
		private List<Person> friends;

		public Person() {
			this.friends = new ArrayList<Person>();
		}

		public List<Person> getFriends() {
			return this.friends;
		}

		public boolean isInGroup() {
			return this.isInGroup;
		}

		public void setInGroup(boolean isInGroup) {
			this.isInGroup = isInGroup;
		}

	}

	public int groupOfFriends(Person p) {
		Queue<Person> persons = new LinkedList<Person>();
		persons.add(p);

		int numberOfPersonsInGroup = 0;

		// Figure our the largest group
		while (!persons.isEmpty()) {
			Person friendOf = persons.poll();

			// If our current friend of p (pFriend) isn't in a group, add
			// him/her to p's friend group
			if (!friendOf.isInGroup()) {
				numberOfPersonsInGroup++;
				// pFriend is now in a friend group
				friendOf.setInGroup(true);

				// If pFriend is now in p's friend group, it means that all
				// of pFriend's friend is also in this group
				for (Person newFriend : friendOf.getFriends()) {
					persons.add(newFriend);
				}
			}
		}

		// Return the resulting number of persons
		return numberOfPersonsInGroup;
	}

	public void run() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCases = Integer.parseInt(br.readLine());

		for (int i = 0; i < testCases; i++) {
			String[] citizensAndPairs = br.readLine().split(" ");
			int citizensInTown = Integer.parseInt(citizensAndPairs[0]);
			int pairsOfFriends = Integer.parseInt(citizensAndPairs[1]);

			Person[] personGraph = new Person[citizensInTown + 1];

			// The persons are indexed 1 .. n, so we 1-index the array instead
			// of usually used 0-index
			for (int j = 1; j < citizensInTown + 1; j++) {
				personGraph[j] = new Person();
			}

			for (int k = 0; k < pairsOfFriends; k++) {
				String[] friends = br.readLine().split(" ");

				int person1 = Integer.parseInt(friends[0]);
				int person2 = Integer.parseInt(friends[1]);

				// Create the person graph by adding friend relationships
				personGraph[person1].getFriends().add(personGraph[person2]);
				personGraph[person2].getFriends().add(personGraph[person1]);
			}

			int maxGroup = -1;

			// Figure out the largest friend group by looping through all of the
			// persons (1 .. n)
			for (int l = 1; l < citizensInTown + 1; l++) {
				if (!personGraph[l].isInGroup()) {
					maxGroup = Math.max(maxGroup, groupOfFriends(personGraph[l]));
				}
			}

			System.out.println(maxGroup);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Main main = new Main();
		main.run();
	}

}
