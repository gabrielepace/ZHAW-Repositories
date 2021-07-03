
public class SongStub implements Song {
	private String title;
	private boolean playing;

	public SongStub(String title) {
		this.title = title;
		this.playing = false;
	}
	
	@Override
	public float getPlayTime() {
		return 10.0f;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public boolean isPlaying() {
		return this.playing;
	}

	@Override
	public void start() throws JukeBoxException {
		if (this.playing) {
			throw new JukeBoxException("song is already playing");
		} else {
			this.playing = true;
		}
	}

}
