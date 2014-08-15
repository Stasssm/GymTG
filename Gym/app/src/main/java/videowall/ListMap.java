package videowall;

import java.util.HashMap;
import java.util.Map;

public class ListMap {
	private Map<String, String> playlist;

	public ListMap() {
		if (playlist == null) {
			playlist = new HashMap<String, String>();
			playlist.put("biceps", "PLXhLffqasu00A1C80YcLfDnrlWDLl0vaY");
			playlist.put("abs", "PLXhLffqasu03FD7yh4k0bDH9KyOSEor1w");
			playlist.put("shoulders", "PLXhLffqasu00GmGvDK7nBYXrxes28wsj-");
			playlist.put("forearms", "PLXhLffqasu03ieu3XqsLQ0QfZXH65XYd6");
			playlist.put("chest", "PLXhLffqasu00-PKaP3IgH0RcMnrduOPYH");
			playlist.put("quads", "PLXhLffqasu03E65XMHTC5ZbLiwi5RJQRJ");
			playlist.put("neck", "PLXhLffqasu00pnoMmsNoSc4fmh9zKPsWS");
			playlist.put("traps", "PLXhLffqasu02acuiZsBGjoanFjTaOG80Z");
			playlist.put("triceps", "PLXhLffqasu03X6i_aPDNWRMnwc25dqEi9");
			playlist.put("lats", "PLXhLffqasu008XBkdJhU1MIrFhoR7ZUBi");
			playlist.put("middleback", "PLXhLffqasu008XBkdJhU1MIrFhoR7ZUBi");
			playlist.put("lowerback", "PLXhLffqasu008XBkdJhU1MIrFhoR7ZUBi");
			playlist.put("glutes", "PLXhLffqasu032f3EuTkSSDYCF2m_tmVIi");
			playlist.put("hamstrings", "PLXhLffqasu03E65XMHTC5ZbLiwi5RJQRJ");
			playlist.put("calves", "PLXhLffqasu03bdNoeaSRN_SqnWmW2Uemf");
		}
	}
	
	public String get(String key){
		return playlist.get(key);
	}
}
