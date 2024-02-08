/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) 
    {
       if(size == maxSize){
            return false;
       }
       else{
            tracks[size] = track;
            this.size++;
            return true;
       }
        
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        String line = "";
        for (int i = 0; i < tracks.length; i++){
            if (tracks[i] != null){
                line = track[i].toString();
            }
        }
        return "";
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
    public void removeLast() {
        if (tracks[0] != null) {
            for (int i = 0; i < tracks.length; i++) {
                if (tracks[i + 1] == null) { // Check if the next place in array is empty
                    tracks[i] = null; // Replace current place with null
                    size = size - 1; //list size - 1
                    break; // After finding the empty place in array and removing the previous, break the loop
                }
            }
        }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int total = 0;
        for (int i = 0; i < tracks.length; i++) {
        
            total = total + tracks[i].getDuration();
        }
        return total;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        for (int i = 0; i < tracks.length; i++) {
            if (title.equals(tracks[i].getTitle())) {
                return i;
            }
        }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        if (i < 0 || i >= tracks.length || this.getsize() == this.getMaxSize()){
            return false;
        }
        else {
            for (int j = tracks.length - 1; j > i; j--){
                tracks[j] = tracks[j-1];
            }
            tracks[i] = track; 
            this.size++; //list size + 1 
            return true;
        }     
    }
        
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public int remove(int i) {
        if (i < 0 || i >= tracks.length || this.getSize() == 0){
            return -1;
        }
    
        for (int j = i; j < tracks.length - 1; j++){
            tracks[j] = tracks[j + 1];
        }
        this.size++; //list size - 1
    
        return 0;
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        if (this.getSize() == 0){
            return;
        }
        int removedIndex = -1;
        for (int i = 0; i < tracks.length; i++){
            if (title.equals(tracks[i].getTitle())){
                tracks[i] = null;
                removedIndex = i;
                break;
            }
        }
        if (removedIndex != -1) {
            for (int j = removedIndex; j < tracks.length - 1; j++){
                tracks[j] = tracks[j + 1];
            }
            tracks[tracks.length - 1] = null; // Set the last element to null
            size = size - 1; //list size - 1
        }
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        if(size == 0){
            return;
        }
        tracks[0] = null;
        for(int j = 0; j < (size - 1); j++)
        {
            tracks[j]= tracks[j+1];
        }
        tracks[size - 1] = null;
        this.size--;
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
    public void add(PlayList other) {
        int total = size + other.getSize();
        if (total <= maxSize) {
            for (int i = size, j = 0; i < total; i++, j++) { //2 for loops in one
                tracks[i] = other.getTrack(j);
            }
            size = total;
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        int min = 0;
        int minIndex = 0;

        if (start < 0 || start > size -1){
            return -1;
        }
        min = tracks[start].getDuration();
        for (int i = start + 1; i < size - 1; i++){

            if (tracks[i].getDuration < min){
                min = tracks[i].getDuration();
                minIndex = i;
            }
        }
        return minIndex();
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() 
    {
        if(size == 0) return null;
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        temp = 0;
        minIndex = 0;
        for (int i = 0; i < size; i++)
        {
            minIndex = minIndex(i); //starting point is place 0, minimum of all tracks
            temp = tracks[i]; //temporarily savin the i track in temp
            tracks[i] = tracks[minIndex];
            tracks[minIndex] = temp;
            temp = null;
        }
    }
}



