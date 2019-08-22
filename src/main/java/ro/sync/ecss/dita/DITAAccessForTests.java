package ro.sync.ecss.dita;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.oxygenxml.ditareferences.workspace.rellinks.RelLink;
import com.oxygenxml.ditareferences.workspace.rellinks.RelLinkImpl;

public class DITAAccessForTests{
	 /**
	   * Get the list of related links from all the relationship tables defined in the DITA Maps.
	   * @param originatorURL The topic for which we are searching for outgoing links.
	   * 
	   * @return The list of related links from all the relationship tables defined in the DITA Maps.
	   * 
	   * @since 22
	   */
	public static List<RelLink> getRelatedLinksFromReltable(URL originatorURL){
		List<RelLink> links = new ArrayList<RelLink>();
		try {
			links.add(new RelLinkImpl(
					new File("test/source1.dita").toURI().toURL(), 
					new File("test/target1.dita").toURI().toURL(), null, null, new File("test/defLocation1.dita").toURI().toURL()));
			
			links.add(new RelLinkImpl(
					new File("test/source2.dita").toURI().toURL(), 
					new File("test/target2.dita").toURI().toURL(), null, null, new File("test/defLocation2.dita").toURI().toURL()));
			
			links.add(new RelLinkImpl(
					new File("test/source1.dita").toURI().toURL(), 
					new URL("http://www.google.com"), "html", "external", new File("test/defLocation3.dita").toURI().toURL()));
		} catch (MalformedURLException e) {
			//e.printStackTrace();
		}
		return links;
	}
}
