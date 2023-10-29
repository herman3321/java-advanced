package TvSystem;

import java.io.Serializable;

public class Subscription implements Serializable{

    private int installFee;
    private int nbTv;
    private Subscriber subscriber;
    private SubscriptionCycle cycle;
    private String date;

    private int totalFee;
    
    public Subscription(int installFee, Subscriber subscriber, SubscriptionCycle cycle, String date){
        this.nbTv = nbTv;
        this.subscriber = subscriber;
        this.cycle = cycle;
        this.date = date;
        
        this.installFee = nbTv*10;
    }

    public int getnbTv(){
        return nbTv;
    }

    public void setNbTv(int nbTv){
        this.nbTv = nbTv;
    }

    public Subscriber getSubscriber(){
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber){
        this.subscriber = subscriber;
    }

    public SubscriptionCycle getCycle(){
        return cycle;
    }

    public void setCycle(SubscriptionCycle cycle){
        this.cycle = cycle;
    }

    public String getDate(){
        return date;
    }

    public void set(String date){
        this.date = date;
    }

    public int getTotalFee(){
        totalFee = installFee + 5;
        return totalFee;
    }

    @Override
    public String toString(){
        return "Subscription{" +
                "installFee='" + installFee + '\'' +
                "nbTv='" + nbTv + '\'' +
                "subscriber='" + subscriber + '\'' +
                "Today='" + date + '\'' +
                "cycle='" + cycle + '\'' +
                '}';
    }

}
