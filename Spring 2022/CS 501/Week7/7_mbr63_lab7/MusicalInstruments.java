class MusicalInstruments {
	public static void musicalInstruments(Music play) {
		play.sound();
	}
}

abstract class Music {
	public abstract void sound();
}

class Drum extends Music {
	@Override
	public void sound() {
		System.out.println("Boom!");
	}
}

class Guitar extends Music {
	@Override
	public void sound() {
		System.out.println("Strum!");
	}
}

class Piano extends Music {
	@Override
	public void sound() {
		System.out.println("Strings!");
	}
}

class Violin extends Music {
	@Override
	public void sound() {
		System.out.println("Tudum!");
	}
}
