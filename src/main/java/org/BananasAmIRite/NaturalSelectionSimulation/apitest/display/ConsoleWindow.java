package org.BananasAmIRite.NaturalSelectionSimulation.apitest.display;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.PrintStream;

public class ConsoleWindow extends JFrame {

    private final ConsoleStream consoleStream;

    public ConsoleWindow() {
        setTitle("Console");
        consoleStream = new ConsoleStream();

        DefaultCaret caret = (DefaultCaret)consoleStream.getTextArea().getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        add(new JScrollPane(consoleStream.getTextArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        PrintStream ps = new PrintStream(consoleStream, true);

        System.setOut(ps);
        System.setErr(ps);

        setSize(500, 400);
        setVisible(false);
        setLayout(new GridLayout(1, 1));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

}
