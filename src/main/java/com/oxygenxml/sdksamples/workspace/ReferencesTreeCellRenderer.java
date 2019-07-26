package com.oxygenxml.sdksamples.workspace;

import java.awt.Color;
import java.awt.Component;
import java.net.URL;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.oxygenxml.sdksamples.translator.Tags;
import com.oxygenxml.sdksamples.translator.Translator;

import ro.sync.exml.workspace.api.standalone.StandalonePluginWorkspace;

public class ReferencesTreeCellRenderer extends DefaultTreeCellRenderer {

	private static final Logger LOGGER = Logger.getLogger(ReferencesTreeCellRenderer.class);
	/**
	 * The translator of the DITA reference categories.
	 */
	private Translator translator;
	/**
	 * The Image Icons for the reference categories.
	 */
	private ImageIcon imageIcon = null;
	private ImageIcon linkIcon = null;
	private ImageIcon contentIcon = null;
	private ImageIcon crossIcon = null;

	/**
	 * The constructor including the image icon for the categories.
	 * 
	 * @param pluginWorkspaceAccess
	 */
	public ReferencesTreeCellRenderer(StandalonePluginWorkspace pluginWorkspaceAccess, Translator translator) {
		super();

		this.translator = translator;
		URL imageUrl = StandalonePluginWorkspace.class.getResource("/images/node-customizer/ElementImage16.png");
		if (imageUrl != null) {
			this.imageIcon = (ImageIcon) pluginWorkspaceAccess.getImageUtilities().loadIcon(imageUrl);
		}

		URL crossUrl = StandalonePluginWorkspace.class.getResource("/images/node-customizer/ElementXref16.png");
		if (crossUrl != null) {
			this.crossIcon = (ImageIcon) pluginWorkspaceAccess.getImageUtilities().loadIcon(crossUrl);
		}

		URL contentUrl = StandalonePluginWorkspace.class.getResource("/images/Conref16.png");
		if (contentUrl != null) {
			this.contentIcon = (ImageIcon) pluginWorkspaceAccess.getImageUtilities().loadIcon(contentUrl);
		}

		URL linkUrl = StandalonePluginWorkspace.class.getResource("/images/node-customizer/ElementLink16.png");
		if (linkUrl != null) {
			this.linkIcon = (ImageIcon) pluginWorkspaceAccess.getImageUtilities().loadIcon(linkUrl);
		}

	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {

		JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		label.setIcon(null);

		if (value instanceof DefaultMutableTreeNode) {
			// Attribute nodes
			if (((DefaultMutableTreeNode) value).getUserObject() instanceof NodeRange) {
				NodeRange nodeRange = (NodeRange) ((DefaultMutableTreeNode) value).getUserObject();

				String hrefAttr = nodeRange.getAttributeValue("href");
				if (hrefAttr != null) {
					setDisplayNodeValue(hrefAttr);
				} else {
					String keyrefAttr = nodeRange.getAttributeValue("keyref");
					if (keyrefAttr != null) {
						setDisplayNodeValue(keyrefAttr);
					} else {
						String conrefAttr = nodeRange.getAttributeValue("conref");
						if (conrefAttr != null) {
							setDisplayNodeValue(conrefAttr);
						} else {
							String conkeyrefAttr = nodeRange.getAttributeValue("conkeyref");
							if (conkeyrefAttr != null) {
								setDisplayNodeValue(conkeyrefAttr);
							}
						}
					}
				}

			} else {// Reference categories nodes
				String toDisplayString = null;
				if (((DefaultMutableTreeNode) value).getUserObject() instanceof String) {
					if (((DefaultMutableTreeNode) value).getUserObject().equals(Tags.IMAGE_REFERENCES)) {
						toDisplayString = translator.getTranslation(Tags.IMAGE_REFERENCES);
						label.setIcon(imageIcon);
					} else if (((DefaultMutableTreeNode) value).getUserObject().equals(Tags.CROSS_REFERENCES)) {
						toDisplayString = translator.getTranslation(Tags.CROSS_REFERENCES);
						label.setIcon(crossIcon);
					} else if (((DefaultMutableTreeNode) value).getUserObject().equals(Tags.CONTENT_REFERENCES)) {
						toDisplayString = translator.getTranslation(Tags.CONTENT_REFERENCES);
						label.setIcon(contentIcon);
					} else if (((DefaultMutableTreeNode) value).getUserObject().equals(Tags.RELATED_LINKS)) {
						toDisplayString = translator.getTranslation(Tags.RELATED_LINKS);
						label.setIcon(linkIcon);
					} else if (((DefaultMutableTreeNode) value).getUserObject()
							.equals(Tags.OUTGOING_REFERENCES_NOT_AVAILABLE)) {
						toDisplayString = translator.getTranslation(Tags.OUTGOING_REFERENCES_NOT_AVAILABLE);
					} else if (((DefaultMutableTreeNode) value).getUserObject()
							.equals(Tags.NO_OUTGOING_REFERENCES_FOUND)) {
						toDisplayString = translator.getTranslation(Tags.NO_OUTGOING_REFERENCES_FOUND);
					}
				}
				label.setText(toDisplayString);
			}
		}
		return label;
	}

	/**
	 * Display maximum 20 characters for each node value and set the toolTip with
	 * the full node value.
	 * 
	 * @param node                    The DOM node
	 * @param referenceAttributeValue The attribute value
	 * @return the displayed node text
	 */
	private void setDisplayNodeValue(String referenceAttributeValue) {
		String toDisplayString = null;

		// TODO show file name.
//		StringTokenizer st = new StringTokenizer(referenceAttributeValue, "/");
//		String filename = null;
//		while (st.hasMoreTokens()) {
//			filename = st.nextToken();
//		}
//
//		int filenameLength = filename.length();
//		int refLength = referenceAttributeValue.length();
//
//		if (filenameLength == refLength) {
//			toDisplayString = referenceAttributeValue;
//		} else {
//			int diff = refLength - filenameLength;
//			if ((refLength - diff) >= 5) {
//				toDisplayString = "... " + referenceAttributeValue.substring(diff - 5);
//			} else {
//				toDisplayString = "... " + referenceAttributeValue.substring(diff);
//			}
//		}

		if (referenceAttributeValue.length() > 20) {
			toDisplayString = "... " + referenceAttributeValue.substring(referenceAttributeValue.length() - 20);
		} else {
			toDisplayString = referenceAttributeValue;
		}

		this.setText(toDisplayString);
		this.setToolTipText(referenceAttributeValue);
	}

}