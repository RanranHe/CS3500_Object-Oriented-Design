package cs3500.music.test;

import java.util.List;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Patch;
import javax.sound.midi.Receiver;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;
import javax.sound.midi.VoiceStatus;

public class MockMidiSynth implements Synthesizer {

	@Override
	public Info getDeviceInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void open() throws MidiUnavailableException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getMicrosecondPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxReceivers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxTransmitters() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Receiver getReceiver() throws MidiUnavailableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receiver> getReceivers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transmitter getTransmitter() throws MidiUnavailableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transmitter> getTransmitters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxPolyphony() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLatency() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MidiChannel[] getChannels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoiceStatus[] getVoiceStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSoundbankSupported(Soundbank soundbank) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadInstrument(Instrument instrument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unloadInstrument(Instrument instrument) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean remapInstrument(Instrument from, Instrument to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Soundbank getDefaultSoundbank() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instrument[] getAvailableInstruments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instrument[] getLoadedInstruments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean loadAllInstruments(Soundbank soundbank) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unloadAllInstruments(Soundbank soundbank) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean loadInstruments(Soundbank soundbank, Patch[] patchList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unloadInstruments(Soundbank soundbank, Patch[] patchList) {
		// TODO Auto-generated method stub
		
	}

}
