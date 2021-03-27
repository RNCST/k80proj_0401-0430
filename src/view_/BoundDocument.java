package view_;

import javax.swing.*;
import javax.swing.text.*;


/**
 * @author OSH
 *	JTextField.setDocument(new BoundDocument(Font.length, jtf_xx);
 */
public class BoundDocument extends PlainDocument {
	protected int charLimit;
	protected JTextComponent textComp;

	public BoundDocument(int charLimit) {
		this.charLimit = charLimit;
	}

	public BoundDocument(int charLimit, JTextComponent textComp) {
		this.charLimit = charLimit;
		this.textComp = textComp;
	}

	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if (textComp.getText().getBytes().length + str.getBytes().length <= charLimit) {
			super.insertString(offs, str, a);
		}
	}
}