package de.jotschi.jxattr;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class XAttrRunner {
	public static final String FIRST_KEY = "xyz";

	static {
		System.loadLibrary("jniXAttrNativeBindings");
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			throw new RuntimeException("Missing filename argument");
		}
		File testFile = new File(args[0]);
		XAttrFile file = new XAttrFile(testFile.getAbsolutePath());
		String originalValue = "Blub";
		file.setAttribute(FIRST_KEY, originalValue);

		// Read as String
		String stringValue = file.getAttributeAsString(FIRST_KEY);
		System.out.println("Found  Value:" + stringValue);

		// Read as Byte
		byte[] byteValue = file.getAttribute(FIRST_KEY);
		System.out.println("Found Value from bytes: " + new String(byteValue));

		// Verify List
		Vector<String> list = file.getAttributeList();
		for (String key : list) {
			System.out.println("Found Attributes: " + key);
		}

	}

}
