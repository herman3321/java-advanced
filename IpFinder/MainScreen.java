package IpFinder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class MainScreen {

    public static void main(String[] args){
        JFrame jFrame = new JFrame("IP FINDER");
        jFrame.setBounds(100,100,1000,100);
        jFrame.setVisible(true);

        JLabel label = new JLabel("Enter your URL");
        label.setBounds(50,50,150,20);

        JTextField textField = new JTextField();
        textField.setBounds(50,100,100,100);

        JButton button = new JButton("Find IP");
        button.setBounds(50,50,80,30);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String url = textField.getText();
                try{
                    InetAddress ina = InetAddress.getByName(url);
                    String ip = ina.getHostAddress();
                    JOptionPane.showMessageDialog(jFrame, ip);

                }
                catch(UnknownHostException UnknownHostException){
                    UnknownHostException.printStackTrace();
                    String err = "Unavailable IP Address Please correct your URL!!!";
                    JOptionPane.showMessageDialog(jFrame, err);
                }
            }
        });
        jFrame.add(label);
        jFrame.add(textField);
        jFrame.add(button);
        jFrame.setLayout(new GridLayout());
    }
}