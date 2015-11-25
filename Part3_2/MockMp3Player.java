package Part3_2;

import java.util.ArrayList;

public class MockMp3Player implements Mp3Player {
	ArrayList<String> playlist;
	int i = 1;
	boolean isPlaying = false;
	int progress = 0;
	
	public MockMp3Player(){
		
	}

	/** 
	* Begin playing the filename at the top of the
	* play list, or do nothing if playlist 
	* is empty. 
	*/
	@Override
	public void play(){
		if (playlist.isEmpty()){
			return;
		}
		isPlaying = true;
		progress++;
	}

	/** 
	* Pause playing. Play will resume at this spot. 
	*/
	@Override
	public void pause(){
		isPlaying = false;
	}

	/** 
	* Stop playing. The current song remains at the
	* top of the playlist, but rewinds to the 
	* beginning of the song.
	*/
	@Override
	public void stop(){
		isPlaying = false;
		progress = 0;
	}

	/** Returns the number of seconds into 
	* the current song.
	*/
	@Override
	public double currentPosition(){
		return progress;
	}


	/**
	* Returns the currently playing file name.
	*/
	@Override
	public String currentSong(){
		return playlist.get(i);
	}

	/** 
	* Advance to the next song in the playlist 
	* and begin playing it.
	*/
	@Override
	public void next(){
		i = i + 1;
		isPlaying = true;
		progress++;
	}

	/**
	* Go back to the previous song in the playlist
	* and begin playing it.
	*/
	@Override
	public void prev(){
		i = i - 1;
		isPlaying = true;
		progress++;
	}

	/** 
	* Returns true if a song is currently 
	* being played.
	*/
	@Override
	public boolean isPlaying(){
		return isPlaying;
	}

	/**
	* Load filenames into the playlist.
	*/
	@Override
	public void loadSongs(ArrayList names){
		playlist = new ArrayList<String>(names);
	}
}
