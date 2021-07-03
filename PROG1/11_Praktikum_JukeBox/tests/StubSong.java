
public class StubSong implements Song {
	
	private boolean isPlaying = false;
	
	public StubSong(String songTitle) {}

	@Override
	public String getTitle() {
		return "Coldplay - Talk";
	}

	@Override
	public double getPlayTime() {
		return 3.50;
	}

	@Override
	public boolean isPlaying() {
		return isPlaying;
	}

	@Override
	public void start() throws JukeBoxException {
		if(isPlaying) {
			throw new JukeBoxException(getTitle());
		}else {
			isPlaying = true;
		}
	}

}
