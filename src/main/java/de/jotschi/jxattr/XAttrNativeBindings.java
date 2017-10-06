package de.jotschi.jxattr;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;

@Properties({ @Platform(cinclude = "sys/xattr.h") })
public class XAttrNativeBindings {

	public static final int XATTR_CREATE = 0x1;
	public static final int XATTR_REPLACE = 0x2;

	static {
		Loader.load();
	}

	protected static native int listxattr(String filePath,
			@Cast("char*") byte[] list, int listSize);

	protected static native int getxattr(String filePath, String key,
			@Cast("void*") byte[] value, int size);

	protected static native int setxattr(String filePath, String key,
			String value, int len, int flags);

	protected static native int setxattr(String filePath, String key,
			byte[] value, int len, int flags);
}
