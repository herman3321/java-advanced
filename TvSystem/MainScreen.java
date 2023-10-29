package TvSystem;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MainScreen extends JFrame{
    //Panel 1

    JPanel subscriberPanel;
    JTextField subName;
    JTextField subLastName;
    JTextField subMobile;
    JTextField subCity;

    JLabel nameLBL;
    JLabel lastLBL;
    JLabel mobileLBL;
    JLabel cityLBL;

    //Panel 2

    JTextField startCycleFLD;
    JTextField endCycleFLD;
    JTextField numberTvFLD;
    JLabel todayLBL;
    JLabel startCycleLBL;
    JLabel endCycleLBL;
    JLabel numberTvLBL;
    SimpleDateFormat df;
    Date currentDate;
    JPanel cyclePanel;

    //Panel 3

    JCheckBox sportCHKB;
    JCheckBox moviesCHKB;
    JCheckBox docCHKB;
    JPanel packagePanel;

    //Panel 4

    JTextArea channelsAreaS;
    JTextArea channelsAreaM;
    JTextArea channelsAreaD;
    JPanel detailsPanel;

    //Panel 5

    JLabel installFeeLBL;
    JLabel packageFeeLBL;
    JLabel totalFeeLBL;
    JPanel feePanel;

    //Panel 6

    JTable table;
    DefaultTableModel tableModel;
    JPanel tablePanel;

    //Panel 7

    JButton saveBTN;
    JButton loadBTN;
    JButton newBTN;
    JPanel buttonPanel;

    //classes, object and global variables

    Subscriber subscriber;
    Subscription subscription;
    int packageSelectedPrice;
    int totalPrice;

    //saving

    ArrayList<Subscription> listToSave = new ArrayList<>();
    File file;



    public MainScreen(){

        subscriberPanel = new JPanel();
        Border panel1Title = BorderFactory.createTitledBorder("Subscriber Details");
        subscriberPanel.setBorder(panel1Title);
        subscriberPanel.setBounds(15,15,300,200);
        subscriberPanel.setLayout(new GridLayout(5,2));
        
        nameLBL = new JLabel("First Name: ");
        lastLBL = new JLabel("Last Name: ");
        mobileLBL = new JLabel("Mobile: ");
        cityLBL = new JLabel("City: ");

        subName = new JTextField();
        subLastName = new JTextField();
        subMobile = new JTextField();
        subCity = new JTextField();

        //adding components to panel1
        subscriberPanel.add(nameLBL);
        subscriberPanel.add(subName);
        subscriberPanel.add(lastLBL);
        subscriberPanel.add(subLastName);
        subscriberPanel.add(mobileLBL);
        subscriberPanel.add(subMobile);
        subscriberPanel.add(cityLBL);
        subscriberPanel.add(subCity);

        //make opacity for fields
        subName.setOpaque(false);
        subLastName.setOpaque(false);
        subMobile.setOpaque(false);
        subCity.setOpaque(false);

        /***************** Panel2 ****************/

        cyclePanel = new JPanel();
        cyclePanel.setBounds(15,230,300,300);
        cyclePanel.setLayout(new GridLayout(7,1));
        Border cycleBorder = BorderFactory.createTitledBorder("Subscriber Details");
        cyclePanel.setBorder(cycleBorder);

        //components of cycle panel
        todayLBL= new JLabel();
        df = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = new Date();
        todayLBL.setText("Today "+df.format(currentDate));

        //start cycle date
        startCycleLBL = new JLabel("Start Cycle Date (DD/MM/YYYY)");
        startCycleFLD = new JTextField();

        //end cycle date
        endCycleLBL = new JLabel("End Cycle Date (DD/MM/YYYY)");
        endCycleFLD = new JTextField();

         //number of Tvs
        numberTvLBL = new JLabel("Number of Tv: ");
        numberTvFLD = new JTextField();

        //adding components to panel2
        cyclePanel.add(todayLBL);
        cyclePanel.add(startCycleLBL);
        cyclePanel.add(startCycleFLD);
        cyclePanel.add(endCycleLBL);
        cyclePanel.add(endCycleFLD);
        cyclePanel.add(numberTvLBL);
        cyclePanel.add(numberTvFLD);

        //make opacity for fields
        startCycleFLD.setOpaque(false);
        endCycleFLD.setOpaque(false);
        numberTvFLD.setOpaque(false);

        /***************** Panel3 ****************/

        packagePanel = new JPanel();
        packagePanel.setBounds(330,15,300,200);
        packagePanel.setLayout(new GridLayout(5,1));
        Border packBorder = BorderFactory.createTitledBorder("Available Packages");
        packagePanel.setBorder(packBorder);

        JLabel packagesLBL = new JLabel("Select your Package");

        sportCHKB = new JCheckBox("Sports Package");
        moviesCHKB = new JCheckBox("Movies Package");
        docCHKB = new JCheckBox("Documentary Package");

        JButton subscribeBTN = new JButton("Subscribe");

        packagePanel.add(packagesLBL);
        packagePanel.add(sportCHKB);
        packagePanel.add(moviesCHKB);
        packagePanel.add(docCHKB);
        packagePanel.add(subscribeBTN);

        sportCHKB.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e){
                if (sportCHKB.isSelected()){
                    DisplaySportsChannel();
                }else{
                    channelsAreaS.setText("");
                }
            }
        });

        moviesCHKB.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e){
                if (sportCHKB.isSelected()){
                    DisplayMoviesChannel();
                }else{
                    channelsAreaM.setText("");
                }
            }
        });

        docCHKB.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e){
                if (sportCHKB.isSelected()){
                    DisplayDocumentaryChannel();
                }else{
                    channelsAreaD.setText("");
                }
            }
        });

        subscribeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    GetSubscriber();
                }catch(ParseException err){
                    err.printStackTrace();
                }
            }
        });

        /***************** Panel4 ****************/

        detailsPanel = new JPanel();
        detailsPanel.setBounds(330,230,300,500);
        detailsPanel.setLayout(new GridLayout(3,1));
        Border detailBorder = BorderFactory.createTitledBorder("Available Channels");
        detailsPanel.setBorder(detailBorder);

        channelsAreaS = new JTextArea(5,1);
        channelsAreaS.setEditable(false);
        channelsAreaS.setOpaque(false);
        channelsAreaS.setLineWrap(true);

        channelsAreaM = new JTextArea(5,1);
        channelsAreaM.setEditable(false);
        channelsAreaM.setOpaque(false);
        channelsAreaM.setLineWrap(true);
        
        channelsAreaD = new JTextArea(5,1);
        channelsAreaD.setEditable(false);
        channelsAreaD.setOpaque(false);
        channelsAreaD.setLineWrap(true);

        detailsPanel.add(channelsAreaS);
        detailsPanel.add(channelsAreaM);
        detailsPanel.add(channelsAreaD);

        /***************** Panel5 ****************/

        feePanel = new JPanel();
        feePanel.setBounds(645,15,200,200);
        feePanel.setLayout(new GridLayout(3,1));
        Border feeBorder = BorderFactory.createTitledBorder("Fees & Check");
        feePanel.setBorder(feeBorder);

        installFeeLBL = new JLabel("Installation Fee: ");
        packageFeeLBL = new JLabel("Package Fee: ");
        totalFeeLBL = new JLabel("Total Amount to Pay: ");

        feePanel.add(installFeeLBL);
        feePanel.add(packageFeeLBL);
        feePanel.add(totalFeeLBL);

        /***************** Panel6 ****************/

        tablePanel = new JPanel();
        tablePanel.setBounds(645,230,515,500);
        tablePanel.setLayout(new GridLayout(3,1));
        Border tableBorder = BorderFactory.createTitledBorder("Our customers");
        tablePanel.setBorder(tableBorder);

        tableModel = new DefaultTableModel();

        table = new JTable(tableModel);
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Phone Name");
        tableModel.addColumn("Start Cycle");
        tableModel.addColumn("End Cycle");

        JScrollPane scrollPanel = new JScrollPane(table);
        tablePanel.add(scrollPanel);

        /***************** Panel7 ****************/

        buttonPanel = new JPanel();
        buttonPanel.setBounds(860,15,300,200);
        buttonPanel.setLayout(new GridLayout(4,1));
        Border buttonBorder = BorderFactory.createTitledBorder("Action Tab");
        buttonPanel.setBorder(buttonBorder);

        saveBTN = new JButton("Save Subscription");
        loadBTN = new JButton("Load Subscription");
        newBTN = new JButton("New Subscription");

        buttonPanel.add(newBTN);
        buttonPanel.add(saveBTN);
        buttonPanel.add(loadBTN);

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                SaveSubscriptionToDisk();
            }
        });

        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                NewSubscription();
            }
        });

        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ArrayList<Subscription> k = LoadDataFromDisk();
            }
        });

        //adding panels to JFRAME
        setLayout(null);
        add(subscriberPanel);
        add(cyclePanel);
        add(packagePanel);
        add(detailsPanel);
        add(feePanel);
        add(tablePanel);
        add(buttonPanel);
    }
    /************************ Methods *************************/

    private int DisplaySportsChannel(){
        SportChannel s1 = new SportChannel("AFN Sports", "EN", "SPRT", 5);
        SportChannel s2 = new SportChannel("beIN Sport",  "FR", "SPRT", 5);
        SportChannel s3 = new SportChannel("Eleven Sports", "EN", "SPRT", 6);
        SportChannel s4 = new SportChannel("NBA TV", "EN", "SPRT", 2);
        SportChannel s5 = new SportChannel("NFL Network", "AR", "SPRT", 4);
        SportChannel s6 = new SportChannel("The Ski Channel", "USA", "SPRT", 2);

        ArrayList<SportChannel> sportChannel = new ArrayList<>();
        sportChannel.add(s1);
        sportChannel.add(s2);
        sportChannel.add(s3);
        sportChannel.add(s4);
        sportChannel.add(s5);
        sportChannel.add(s6);

        String spoChannelString = "";
        int packagePrice = 0;

        for(int i=0; i< sportChannel.size(); i++){
            spoChannelString +=
                "    "+ sportChannel.get(i).getChannelName()
                +"     "+ sportChannel.get(i).getLanguage()
                +"     "+ sportChannel.get(i).getPrice()
                +"\n";
            
            packagePrice =+ sportChannel.get(i).getPrice();
        }

        channelsAreaS.setText(spoChannelString);

        return packagePrice;
    }

    private int DisplayMoviesChannel(){
        MovieChannel m1 = new MovieChannel("MBC", "EN", "MOV", 4);
        MovieChannel m2 = new MovieChannel("Cinema One",  "EN", "MOV", 5);
        MovieChannel m3 = new MovieChannel("Cinema Pro", "RU", "MOV", 6);
        MovieChannel m4 = new MovieChannel("Cinema 1", "AR", "MOV", 2);
        MovieChannel m5 = new MovieChannel("Movie Home", "GR", "MOV", 4);
        MovieChannel m6 = new MovieChannel("Film4", "FR", "MOV", 2);

        ArrayList<MovieChannel> movieChannel = new ArrayList<>();
        movieChannel.add(m1);
        movieChannel.add(m2);
        movieChannel.add(m3);
        movieChannel.add(m4);
        movieChannel.add(m5);
        movieChannel.add(m6);

        String movChannelString = "";
        int packagePrice = 0;

        for(int i=0; i< movieChannel.size(); i++){
            movChannelString +=
                "    "+ movieChannel.get(i).getChannelName()
                +"     "+ movieChannel.get(i).getLanguage()
                +"     "+ movieChannel.get(i).getPrice()
                +"\n";

            packagePrice =+ movieChannel.get(i).getPrice();
        }
        channelsAreaM.setText(movChannelString);
        
        return packagePrice;
    }

    private int DisplayDocumentaryChannel(){
        DocumentaryChannel d1 = new DocumentaryChannel("NAT GEO", "SP", "DOC", 3);
        DocumentaryChannel d2 = new DocumentaryChannel("PBS", "EN", "DOC", 2);
        DocumentaryChannel d3 = new DocumentaryChannel("Al Jazeera Documentary", "IN", "DOC", 3);
        DocumentaryChannel d4 = new DocumentaryChannel("Canal D", "USA", "DOC", 4);
        DocumentaryChannel d5 = new DocumentaryChannel("Discovery Historia", "AR", "DOC", 5);
        DocumentaryChannel d6 = new DocumentaryChannel("World Documentary", "GR", "DOC", 1);

        ArrayList<DocumentaryChannel> documentaryChannel = new ArrayList<>();
        documentaryChannel.add(d1);
        documentaryChannel.add(d2);
        documentaryChannel.add(d3);
        documentaryChannel.add(d4);
        documentaryChannel.add(d5);
        documentaryChannel.add(d6);

        String docChannelString = "";
        int packagePrice = 0;

        for(int i=0; i< documentaryChannel.size(); i++){
            docChannelString +=
                "    "+ documentaryChannel.get(i).getChannelName()
                +"     "+ documentaryChannel.get(i).getLanguage()
                +"     "+ documentaryChannel.get(i).getPrice()
                +"\n";

            packagePrice =+ documentaryChannel.get(i).getPrice();
        }
        channelsAreaD.setText(docChannelString);

        return packagePrice;
    }

    private void GetSubscriber() throws ParseException{
        Date currentDate = new Date();
        subscriber = new Subscriber(
            subName.getText(),
            subLastName.getText(),
            subCity.getText(),
            Integer.parseInt(subMobile.getText()));

            Date startCycle = df.parse(startCycleFLD.getText());
            Date endCycle = df.parse(endCycleFLD.getText());

            SubscriptionCycle cycle = new SubscriptionCycle(
                df.format(startCycle),
                df.format(endCycle)
        );

        subscription = new Subscription(
                Integer.parseInt(numberTvFLD.getText()),
                subscriber,
                cycle,
                df.format(currentDate)
        );

        installFeeLBL.setText("Installation Fee: "+
            subscription.getTotalFee()+"rwf");

        ShowPrice();

    }

    private void ShowPrice(){

        if (docCHKB.isSelected()){
            packageSelectedPrice += DisplayDocumentaryChannel();
        }else if(moviesCHKB.isSelected()){
            packageSelectedPrice += DisplayMoviesChannel();
        }else {
            packageSelectedPrice += DisplaySportsChannel();
        }

        packageFeeLBL.setText("Packages Fee: "+packageSelectedPrice+"rwf");
        totalPrice = subscription.getTotalFee() + packageSelectedPrice;

        totalFeeLBL.setText("Total Amount to Pay: "+ totalPrice+ "rwf");

    }

    private void SaveSubscriptionToDisk(){
        listToSave.add(subscription);

        file= new File("c:\\myFile.dat");

        try{
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            
            oos.writeObject(listToSave);
            oos.flush();
            oos.close();
        }catch(FileNotFoundException e){
                e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void NewSubscription(){
        subName.setText("");
        subLastName.setText("");
        subCity.setText("");
        subMobile.setText("");

        startCycleFLD.setText("");
        endCycleFLD.setText("");
        numberTvFLD.setText("");

        installFeeLBL.setText("Installation Fee: ");
        packageFeeLBL.setText("Packages Fee: ");
        totalFeeLBL.setText("Total amount to pay: ");

        moviesCHKB.setSelected(false);
        sportCHKB.setSelected(false);
        docCHKB.setSelected(false);
    }

    private ArrayList<Subscription> LoadDataFromDisk(){
        ArrayList<Subscription> s = new ArrayList<>();
        file= new File("c:\\myFile.dat");

        try{
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);

            s = (ArrayList) ois.readObject();
            ois.close();
            is.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        for(Subscription sub: s){
            DisplaySubscriptionInTable(sub);
        }

        return s;

    }

    public void DisplaySubscriptionInTable(Subscription sub){
        tableModel.addRow(new Object[]{
            sub.getSubscriber().getfName(),
            sub.getSubscriber().getlName(),
            sub.getSubscriber().getCity(),
            sub.getSubscriber().getPhone(),
            sub.getCycle().getStartDate(),
            sub.getCycle().getEndDate(),
            sub.getTotalFee()
        });
    }

    public static void main(String[] args){
        
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
        mainScreen.setBounds(100,100,1000,800);
    }
    
}
