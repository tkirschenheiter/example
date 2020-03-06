//Song class
class Song {
	private String track;
	private Song next;
	private Song last;
	
	//Song constructor
	public Song (String track) {
		this.track = track;
	}
	
	public String toString() {
		return track;
	}
	
	public void setNext(Song song) {
		this.next = song;
	}
	
	public Song getNext() {
		return next;
	}
	
	public Song getLast() {
        return last;
    }

    public void setLast(Song song) {
        this.last = song;
    }
	
}

//Playlist class
public class Playlist {
	private Song first;
	private Song last;
	private SongHistoryList history = new SongHistoryList();
	
	//Playlist constructor
	public Playlist() {
	    
	}
	
	public boolean isEmpty() {
		return (first == null);
	}
	 
	 public Song getFirst() {
		 return first;
	 }
	 
	 public void displayList() {
		 //start from the first element 
	     Song current = first;
	     while(current != null){
	     // display the node contents
	         System.out.println(current);
	         current = current.getNext();
	     }
	 }
	 
	 public void addSong(Song song) {
		 if(isEmpty()) {
			 last = song;
	         first = song;
	     }
	     else{
	         song.setNext(last);
	         last.setLast(song);
	         last = song;
	     }
	 }
	
	 public void insertSongFirst (String name) {
		 if (isEmpty()) {
			 first = new Song (name);
	     }
	     else {
	    	 Song newNode = new Song(name);
	    	 newNode.setNext(first);
	    	 first = newNode;
	     }
	    	
	 }
	 
	 public void insertSongLast(String name) {
		 Song newNode = new Song(name); 
		 	if(isEmpty() )                
		 		first = newNode;             
		    else
		      last.setNext(newNode);        
		      last = newNode;                
     }
	 
	 public Song listenToSong() {
		 Song song;
	     	if(isEmpty()){
	            return null;
	        }
	     	else{
	            song = first;
	            delete();
	        }
	     history.addSong(song);
	     return song;
     }
	 
	 public void delete() {
	        if(!(isEmpty())) {
	            if (first.getNext() != null)
	                first = first.getNext();
	            else {
	                first = null;
	                last = null;
	            }
	        }
	 }
	 
	 public Song lastListened(){
	        // retrieves the next song to listen to
	        return history.lastListened();
	 }
}
