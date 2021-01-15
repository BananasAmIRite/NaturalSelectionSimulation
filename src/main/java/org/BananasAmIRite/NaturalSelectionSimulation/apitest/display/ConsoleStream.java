package org.BananasAmIRite.NaturalSelectionSimulation.apitest.display;

import javax.swing.*;
import java.io.OutputStream;
import java.util.ArrayList;

public class ConsoleStream extends OutputStream {

    private final ArrayList<Byte> buffer = new ArrayList<>();
    private final JTextArea textArea;

    public ConsoleStream() {
        textArea = new JTextArea();

        textArea.setEnabled(false);
    }

    @Override
    public void write(int b) {
        buffer.add((byte) b);
    }

    @Override
    public void flush() {
        byte[] br = new byte[buffer.size()];

        for (int i = 0; i < buffer.size(); i++) {
            br[i] = buffer.get(i);
        }

        getTextArea().append(new String(br));

        buffer.clear();
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
