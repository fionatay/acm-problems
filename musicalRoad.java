import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class musicalRoad {
	
	public static class Note {
		public double frequency;
		public int duration;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int targetMPH = Integer.parseInt(st.nextToken());
		int tempoBPM = Integer.parseInt(st.nextToken());
		
		List<Note> notes = new LinkedList<Note>();
		
		String line = br.readLine();
		while (!line.equals("")) {
			String token;
			st = new StringTokenizer(line);
			while (st.hasMoreElements()) {
				token = st.nextToken();
				StringReader sr = new StringReader(token);
				char letter = (char) sr.read();
				char c = (char) sr.read();
				int octave = 0;
				int duration;
				int steps = 0; // steps above A
				switch (letter) {
				case 'B': steps = -10; break;
				case 'C': steps = -9; break;
				case 'D': steps = -7; break;
				case 'E': steps = -5; break;
				case 'F': steps = -4; break;
				case 'G': steps = -2; break;
				}
				if (c == '#') {
					steps++;
					c = (char) sr.read();
				} else if (c == 'b') {
					steps--;
					c = (char) sr.read();
				}
				
				octave = Integer.parseInt(Character.toString(c));
				
				Note n = new Note();
				n.frequency = 440 * Math.pow(2, steps/12.0 + (octave - 4));
				n.duration = Integer.parseInt(Character.toString((char)sr.read()));
				System.out.println("Frequency: "+n.frequency+"  Duration: "+n.duration);
				notes.add(n);
			}
			line = br.readLine();
		}
	}
}
