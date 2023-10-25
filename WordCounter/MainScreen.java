package WordCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainScreen {
    public static void main(String[] args){
        JFrame jFrame = new JFrame("WORD COUNTER");
        jFrame.setLayout(null);
        jFrame.setBounds(100, 100, 500, 500);
        jFrame.setVisible(true);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(50, 50, 200, 200);
        
        JButton button = new JButton("Count");
        button.setBounds(180, 300, 100, 40);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                String text = textArea.getText();

                if(!text.equals("")){
                    String[] word = text.split("\\s");
                    JOptionPane.showMessageDialog(jFrame, "Total words are " +word.length);
                }
            }
        });
        jFrame.add(textArea);
        jFrame.add(button);
    }
}
