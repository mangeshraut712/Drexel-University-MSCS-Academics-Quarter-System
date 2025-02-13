
public class SoundTesting {
	public static void main(String[] args) {
		Drum d = new Drum();
		Guitar g = new Guitar();
		Music m = new Piano();
		Music v = new Violin();

		MusicalInstruments.musicalInstruments(d);
		MusicalInstruments.musicalInstruments(g);
		MusicalInstruments.musicalInstruments(m);
		MusicalInstruments.musicalInstruments(v);

	}
}
