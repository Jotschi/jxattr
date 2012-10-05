package de.jotschi.jxattr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XAttrFileTest {

	public static final String FIRST_KEY = "xyz";
	File testFile = new File("testfile" + System.currentTimeMillis());

	@Before
	public void setup() throws IOException {
		testFile.createNewFile();
	}

	@Test
	public void test() throws IOException {

		XAttrFile file = new XAttrFile(testFile.getAbsolutePath());
		String originalValue = "Blub";
		file.setAttribute(FIRST_KEY, originalValue);

		// Read as String
		String stringValue = file.getAttributeAsString(FIRST_KEY);
		assertEquals(originalValue, stringValue);

		// Read as Byte
		byte[] byteValue = file.getAttribute(FIRST_KEY);
		assertEquals(originalValue, String.valueOf(stringValue));

		// Verify List
		boolean foundAttribute = false;
		Vector<String> list = file.getAttributeList();
		final String TARGET_KEY = "user." + FIRST_KEY;
		for (String key : list) {
			System.out.println("Found Attributes: " + key);
			if (TARGET_KEY.equalsIgnoreCase(key)) {
				foundAttribute = true;
			}
		}

		assertTrue(
				"The attribute was not found within the list of attributes.",
				foundAttribute);

	}

	@After
	public void tearDown() {
		testFile.delete();
	}
}
