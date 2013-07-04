package newgame;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;

public class sound implements Runnable
{

	private static InputStream file;

	public void play()
	{

		try 
		{
			file = sound.class.getClassLoader().getResourceAsStream("src/Resources/1-welcome.wav"); // File oeffnen

			//MP3 anpassungen 

			AudioInputStream in= AudioSystem.getAudioInputStream(file);
			AudioInputStream din = null;
			AudioFormat baseFormat = in.getFormat();
			AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,baseFormat.getSampleRate(),16,baseFormat.getChannels(),baseFormat.getChannels() * 2,baseFormat.getSampleRate(),false);
			din = AudioSystem.getAudioInputStream(decodedFormat, in);
			// Play now.

			rawplay(decodedFormat, din);
			in.close();

		} catch (Exception e){} 
	} 

	// Player

	private void rawplay(AudioFormat targetFormat, AudioInputStream din) throws IOException, LineUnavailableException
	{
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat); 
		if (line != null)
		{

			// Start
			line.start();
			int nBytesRead = 0;
			while (nBytesRead != -1)
			{
				nBytesRead = din.read(data, 0, data.length);
				if (nBytesRead != -1)line.write(data, 0, nBytesRead);
			}
			// Stop
			line.drain();
			line.stop();
			line.close();
			din.close();
		} 
	}

	private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException
	{
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}

	@Override
	public void run() {
		while(true){
		play();
		}
	} 
}