package com.company;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
// write your code here
        PeoplePublic ip = new PeoplePublic(); //создаем центральный процессор
        Dashboard db = new Dashboard(ip); //создаем приборную панель

        ip.changeData(10, 2000, 30);
        ip.changeData(20, 2500, 40);
        ip.changeData(60, 5000, 80);
    }
}
interface Notifier{
    public void addObserver(Observer obs);
    public void removeObserver(Observer obs);
    public void notifyObserver();
}

class PeoplePublic implements Notifier{
    private List observers; // список наблюдателей
    private int news; // скорость
    private int nv; // обороты двигателя
    private int Comedy; // давление масла

    public PeoplePublic(){
        observers = new ArrayList();
    }

    // добавить слушателя
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    // удалить слушателя
    public void removeObserver(Observer obs) {
        int i = observers.indexOf(obs);
        if (i >= 0){
            observers.remove(i);
        }
    }

    // уведомить слушателей
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++){
            Observer obs = (Observer)observers.get(i);
            obs.update(news, nv, Comedy);
        }
    }

    public void changeData(int news, int nv, int Comedy){ // метод для изменения характеристик при движении автомобиля
        this.Comedy = Comedy;
        this.nv = nv;
        this.news= news;
        notifyObserver(); // уведомляем слушателей об изменениях
    }

}

interface Observer{
    public void update(int news, int nv, int comedy);
}

class Dashboard implements Observer{
    private Notifier notifier;
    private int news; // скорость
    private int tv; // обороты двигателя
    private int comedy; // давление масла

    public Dashboard(Notifier notifier){
        this.notifier = notifier;
        notifier.addObserver(this); // регистрируем приборную панель в качестве наблюдателя
    }

    public void update(int news, int nv, int comedy) {
        this.news = news;
        this.tv = tv;
        this.comedy = comedy;
        show();
    }

    // отображение на приборной панели информации
    public void show(){
        System.out.println("news = " + news + ", nv = " + tv +
                ", comedy = " + comedy);
    }

}
