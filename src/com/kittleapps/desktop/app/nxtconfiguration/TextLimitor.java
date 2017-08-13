package com.kittleapps.desktop.app.nxtconfiguration;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
public class TextLimitor extends PlainDocument {
	private static final long serialVersionUID = 2252612165640276904L;
	private final int limit;
	TextLimitor(final int limit) {
		super();
		this.limit = limit;
	}
	TextLimitor(final int limit, final boolean upper) {
		super();
		this.limit = limit;
	}
	@Override
	public void insertString(final int offset, final String str, final AttributeSet attr) throws BadLocationException {
		if (str == null) {
			return;
		}
		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}
}