package com.voyager.utils;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户界面抗锯齿类 摘自： http://www.oschina.net/code/snippet_116768_7750
 *
 */
public class UIs {

	private static final String FALLBACK_FONT_FAMILY_NAME = Font.SANS_SERIF;
	private static final Map<String, String> FONT_FAMILY_NAMES = new HashMap<>();
	private static final String[] BEST_FONT_FAMILIES = { "微软雅黑", "arial",
			"sans-serif" };
	private static final int BEST_FONT_SIZE = 12; // 12px

	static {
		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] fontFamilyNames = env.getAvailableFontFamilyNames();
		for (String fontFamilyName : fontFamilyNames) {
			FONT_FAMILY_NAMES.put(fontFamilyName.toLowerCase(), fontFamilyName);
		}
		if (!FONT_FAMILY_NAMES.containsKey("serif")) {
			FONT_FAMILY_NAMES.put("serif", Font.SERIF);
		}
		if (!FONT_FAMILY_NAMES.containsKey("sans-serif")) {
			FONT_FAMILY_NAMES.put("sans-serif", Font.SANS_SERIF);
		}
	}

	public static void enableAntiAliasing() {
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");
	}

	public static String getLookAndFeel() {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					return info.getClassName();
				}
			}
		} catch (Exception ignore) {
		}
		return UIManager.getCrossPlatformLookAndFeelClassName();
	}

	public static String getFontFamily(String[] fontFamilies) {
		for (String fontFamily : fontFamilies) {
			fontFamily = fontFamily.toLowerCase();
			if (FONT_FAMILY_NAMES.containsKey(fontFamily)) {
				return FONT_FAMILY_NAMES.get(fontFamily);
			}
		}
		return FALLBACK_FONT_FAMILY_NAME;
	}

	public static String[] getBestFontFamilies() {
		return BEST_FONT_FAMILIES;
	}

	public static int getBestFontSize() {
		return BEST_FONT_SIZE;
	}

	/* ######################################## */

	public static void setUI() {
		enableAntiAliasing();
		// set LookAndFeel
		try {
			UIManager.setLookAndFeel(getLookAndFeel());
		} catch (Exception ignore) {
		}
		// set DefaultFont
		String bestFontFamily = getFontFamily(getBestFontFamilies());
		for (Map.Entry<Object, Object> entry : UIManager.getDefaults()
				.entrySet()) {
			if (entry.getValue() instanceof FontUIResource) {
				FontUIResource fontUIRes = (FontUIResource) entry.getValue();
				entry.setValue(new FontUIResource(
						bestFontFamily,
						fontUIRes.getStyle(),
						getBestFontSize() > fontUIRes.getSize() ? getBestFontSize()
								: fontUIRes.getSize()));
			}
		}
	}
}