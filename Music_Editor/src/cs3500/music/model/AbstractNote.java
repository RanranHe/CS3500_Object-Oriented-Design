package cs3500.music.model;

import cs3500.model.ANote;

public class AbstractNote {
	private ANote note;
	public AbstractNote(ANote note) {
		// TODO Auto-generated constructor stub
		this.note=note;
	}
	
	public int getStart() {
		// TODO Auto-generated method stub
		return note.getStart();
	}

	public int getPitch() {
		// TODO Auto-generated method stub
		return note.getOldPitch();
		//return note.getOctave();
	}

	public int getDuration() {
		// TODO Auto-generated method stub
		return note.getEnd()-note.getStart();
	}

	public int getVolume() {
		// TODO Auto-generated method stub
		return note.getVolume();
	}

	public int getInstrument() {
		// TODO Auto-generated method stub
		return note.getInstrument();
	}

}
