package de.jotschi.jxattr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XAttrTest {

	File testFile = new File("testfile" + System.currentTimeMillis());

	@Before
	public void setup() throws IOException {
		testFile.createNewFile();
	}

	@After
	public void tearDown() throws IOException {
		testFile.delete();
	}

	@Test
	public void testGetXAttrAsString() throws IOException {
		String key = "testGetXAttrAsString";
		String originalValue = String.valueOf(System.currentTimeMillis());
		XAttr.setXAttr(testFile, key, originalValue);
		String readValue = XAttr.getXAttrAsString(testFile, key);
		assertEquals(originalValue, readValue);
	}

	@Test
	public void testGetXAttrAsByte() throws IOException {
		String key = "testGetXAttrAsByte";
		String originalValue = String.valueOf(System.currentTimeMillis());
		XAttr.setXAttr(testFile, key, originalValue);
		byte[] value = XAttr.getXAttrAsByte(testFile, key);
		System.out.println(value.toString());
	}

	@Test
	public void testSetxAttrString() throws IOException {
		String key = "testSetxAttrString";

		String value = String.valueOf(System.currentTimeMillis());
		assertTrue("The testvalue {" + value
				+ "} was not successfully set to the testfile {" + testFile
				+ "} with key {" + key + "}",
				XAttr.setXAttr(testFile, key, value));
	}

	@Test
	public void testSetXAttrByte() throws IOException {
		String value = String.valueOf(System.currentTimeMillis());
		byte[] byteValue = value.getBytes();
		assertTrue(XAttr.setXAttr(testFile, "testSetxAttrString", byteValue));
	}

	@Test
	public void testListXAttr() throws IOException {
		Vector<String> list = XAttr.listXAttr(testFile);
		for (String key : list) {
			System.out.println(key);
		}
	}

}