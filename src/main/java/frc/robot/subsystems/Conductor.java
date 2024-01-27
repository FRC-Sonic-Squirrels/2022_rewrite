package frc.robot.subsystems;

import com.ctre.phoenix6.controls.MusicTone;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Conductor extends SubsystemBase{
    Sequencer sequencer;
    Player player;

    public static final String logPrefix = "[conductor] ";

    public Conductor() {
        this.player = new Player();

        try {
            this.sequencer = MidiSystem.getSequencer(false);
            this.sequencer.open();

            this.sequencer.getTransmitter()
                .setReceiver(this.player);

        } catch (MidiUnavailableException e) {
            DriverStation.reportError(logPrefix + "Sequencer init issue", false);
        }

    }

    public void loadMusic() {
        File file = new File(Filesystem.getDeployDirectory(), "out.midi");
        FileInputStream fstream = null;

        try {
            fstream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            DriverStation.reportError(logPrefix + "MIDI file not found", false);
        }

        try {
            this.sequencer.setSequence(fstream);
        } catch (IOException | InvalidMidiDataException e) {
            DriverStation.reportError(logPrefix + "Couldnt read file", false);
        }
    }

    public void play() {
        System.out.println("Playing MIDI");
        if (this.sequencer.getSequence() == null) {
            DriverStation.reportError(logPrefix + "No MIDI Sequence loaded", false);
            return;
        }

        this.sequencer.start();
    }

    public void pause() {
        this.sequencer.stop();

        this.player.off();
    }

    public void stop() {
        System.out.println(logPrefix + "Stopped MIDI");
        this.sequencer.stop();
        this.sequencer.setMicrosecondPosition(0);
        
        this.player.off();
    }
}

class Player implements Receiver {
    private static final int NOTE_ON = 0x90;
    private static final int NOTE_OFF = 0x80;

    ArrayList<Instrument> instruments;

    public Player() {
        ArrayList<Instrument> instruments = new ArrayList<>();

        for (int id : Constants.CONDUCTOR_CONSTANTS.MOTOR_IDS) {
            TalonFX motor = new TalonFX(id, Constants.CANBUS_STR);
            Instrument instrument = new Instrument(motor);

            instruments.add(instrument);
        }

        this.instruments = instruments;
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if (message instanceof ShortMessage) {
            ShortMessage sMessage = (ShortMessage) message;

            int cmd = sMessage.getCommand();
            int channel = sMessage.getChannel();
            int key = sMessage.getData1();

            if (channel >= instruments.size()) {
                DriverStation.reportWarning(Conductor.logPrefix + "MIDI channel out of bounds", false);

                return;
            }

            Instrument instrument = instruments.get(channel);

            switch (cmd) {
                case NOTE_ON:
                    instrument.noteOn(key, 0);
                    break;
                case NOTE_OFF:
                    instrument.noteOff(key);
                    break;
            }
        } else {
            // ignore
        }
    }

    public void off() {
        for (Instrument inst : this.instruments) {
            inst.allNotesOff();
        }
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'close'");
    }    
}

class Instrument implements MidiChannel {
    TalonFX motor;

    public Instrument(TalonFX motor) {
        this.motor = motor;
    }

    @Override
    public void noteOn(int noteNumber, int velocity) {
        double frequency = Constants.CONDUCTOR_CONSTANTS.MIDI[noteNumber];
        MusicTone tone = new MusicTone(frequency);
        
        motor.setControl(tone);
    }
    @Override
    public void noteOff(int noteNumber, int velocity) {
        MusicTone tone = new MusicTone(0.0);
        
        motor.setControl(tone);
    }
    @Override
    public void noteOff(int noteNumber) {
        MusicTone tone = new MusicTone(0.0);
        
        motor.setControl(tone);
    }

    // bruh
    @Override public void setPolyPressure(int noteNumber, int pressure) {}
    @Override public int getPolyPressure(int noteNumber) { return 0; }
    @Override public void setChannelPressure(int pressure) {}
    @Override public int getChannelPressure() { return 0; }
    @Override public void controlChange(int controller, int value) {}
    @Override public int getController(int controller) { return 0; }
    @Override public void programChange(int program) {}
    @Override public void programChange(int bank, int program) {}
    @Override public int getProgram() { return 0; }
    @Override public void setPitchBend(int bend) {}
    @Override public int getPitchBend() { return 8192; }
    @Override public void resetAllControllers() {}
    @Override public void allNotesOff() {
        MusicTone tone = new MusicTone(0.0);
        
        motor.setControl(tone);
    }
    @Override public void allSoundOff() {
        MusicTone tone = new MusicTone(0.0);
        
        motor.setControl(tone);
    }
    @Override public boolean localControl(boolean on) { return false; }
    @Override public void setMono(boolean on) {}
    @Override public boolean getMono() { return true; }
    @Override public void setOmni(boolean on) {}
    @Override public boolean getOmni() { return false; }
    @Override public void setMute(boolean mute) {}
    @Override public boolean getMute() { return false; }
    @Override public void setSolo(boolean soloState) {}
    @Override public boolean getSolo() { return false; }

}