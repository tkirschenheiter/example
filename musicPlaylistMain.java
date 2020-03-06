import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class musicPlaylistMain {

	public static void main(String[] args) throws Exception {
		
		//Change number of files depending on how many files you are reading in
		int numberOfFiles = 3;
		int index = 0;
		int rows = 50 * numberOfFiles;
		int cols= 4;
	
		//Delimiter to split up tsv file by tabs
		String delimiter = "\t";
		String[][] dataArray = new String [rows][cols];
		
		/*Array to hold files in so you can parse as many as you have in readData method
		- add file by dropping it into src folder and follow syntax below
		- be sure to change numberOfFiles variable above if you add more files
		- will only work with Spotify Viral 50 charts data converted to TSV files
		 */
		String [] myFiles  = new String[numberOfFiles];
		myFiles [0] = "src/week1data.tsv";
		myFiles [1] = "src/week2data.tsv";
		myFiles [2] = "src/week3data.tsv";
		
		//Creates an array list to be sorted 
		ArrayList<String> toSort = new ArrayList <>();
		
		//Method calls to read in data and sort it
		readData(dataArray, myFiles, index, delimiter, cols);
		sortTitles(dataArray, toSort, rows);
		
		//Adds sorted names to a Linked Hash Set to remove duplicates while keeping order
		LinkedHashSet<String> removeDuplicates = new LinkedHashSet<> (toSort);
		
		//Adds Hash Set back into an Array List with no duplicates
		ArrayList<String> sortedNoDuplicates = new ArrayList<> (removeDuplicates);
		
		//Creates new "playlist" to hold songs in
		Playlist playlist = new Playlist();
		
		//Method call to insert sorted list into playlist
		insertToPlaylist(sortedNoDuplicates, playlist);
		
		//Series of output to show program works as intended
		System.out.println("Playlist in ascending order: \n");
		playlist.displayList();
		System.out.println();
		System.out.println("Showing that program works as intended: \n");
		System.out.println(playlist.listenToSong());
		System.out.println(playlist.listenToSong());
		System.out.println(playlist.listenToSong());
		System.out.println(playlist.listenToSong());
		System.out.println(playlist.lastListened());
		System.out.println(playlist.listenToSong());
		System.out.println(playlist.lastListened());
		System.out.println(playlist.listenToSong());
		System.out.println(playlist.lastListened());	
	}
	
	//Method to read in data from a specified number of tsv files, adds them to a 2D array 
	public static void readData(String [][] dataArray, String [] myFiles , int index, String delimiter, int size2) throws Exception{
		for (int i = 0; i<myFiles.length; i++) {
		Scanner sc = new Scanner(new File(myFiles[i]));
			while (sc.hasNextLine()) {
				String [] temp = sc.nextLine().split(delimiter);
					for (int c = 0; c<size2; c++) {
						dataArray[index][c] = temp [c];
				    }
				index++;
			}
		sc.close();
		}	
	 }
	
	 //Method to sort titles by ascending order 
	 public static void sortTitles (String [][] dataArray, ArrayList<String> toSort, int rows) {
		 for (int i = 0; i < rows; i++) {
			 String nameSort = dataArray[i][1];
			 toSort.add(nameSort);
		 }
			 toSort.sort(String.CASE_INSENSITIVE_ORDER);		
	 }
	 
	 //Method to convert array list into queue
	 public static void insertToPlaylist (ArrayList<String> sortedNoDuplicates, Playlist playlist) {
		 for(String name:sortedNoDuplicates) {
	         playlist.insertSongLast(name);
	     }	
	 }
}
