package me.freebuild.superspytx.secretword.utility;

public class StringsUtility {
	
	public static String getSpacedChars(String[] chars) {
		String buildup = "";
		for(int i = 0; i<chars.length; i++) {
			buildup += " " + chars[i];
		}
		
		return buildup;
	}

}
