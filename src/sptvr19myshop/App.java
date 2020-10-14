package sptvr19myshop;

/**
 *
 * @author agulj
 */
import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import java.util.Scanner;
import java.util.Random;

class App {
    Thing things[] = new Thing[10];
    
    int max_count = 20;
    int max_price = 70;
    
    Random rand = new Random();
    Scanner scan = new Scanner(System.in);
    
    String names[] = {"Яблоко","Банан","Апельсин","Помело","Дыня","Киви"};    
    public void run() {

        boolean work = true;
        reset();
        
        int money = 150;        
        Person pers = new Person(money);
        
        while (work) {
            System.out.println("\n1. Купить фрукт");
            System.out.println("2. Проверить фрукт на складе");
            System.out.println("3. Проверить деньги");
            System.out.println("4. Выход");
            
            System.out.print("\n> Выберите функцию: ");
            int function = scan.nextInt();
                         
            switch(function) {
                case 1:
                    System.out.println("\n------------- ");
                    System.out.print("Скажите фрукт ");
                    int choosed_item = scan.nextInt();                  
                    if (pers.getMoney() >= things[choosed_item-1].getPrice()) {
                        System.out.println("Поздравляю с покупкой!");
                        if (things[choosed_item-1].getCount() > 1) {
                            things[choosed_item-1].setCount(things[choosed_item-1].getCount()-1);
                            pers.setMoney(pers.getMoney()- things[choosed_item-1].getPrice());
                            Thing this_thing = new Thing(things[choosed_item-1].getName(),1,things[choosed_item-1].getPrice());
                            for (int i = 0; i < things.length; i++) {
                                if (pers.getSpecialThings(i) != null && this_thing != null) { 
                                            Thing same = pers.getSpecialThings(i);
                                            same.addCount();
                                            this_thing = null;
                                    }
                                }
                            }
                        Thing this_thing = null;
                            pers.setThings(this_thing);
                        } else {                        
                            pers.setMoney(pers.getMoney() - things[choosed_item-1].getPrice());
                            Thing this_thing = new Thing(things[choosed_item-1].getName(),1,things[choosed_item-1].getPrice());
                            things[choosed_item-1] = null;                            
                            for (int i = 0; i < things.length; i++) {
                                if (pers.getSpecialThings(i) != null && this_thing != null) { 
{
                                        Thing same = pers.getSpecialThings(i);
                                        same.addCount();
                                        this_thing = null;
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("------------- ");
                    break;
                case 2:
                    System.out.println("\n------------- ");
                    System.out.println("Storage: ");
                    for (int i = 0; i < things.length; i++) {
                        if (things[i] != null) {
                            System.out.println(things[i].toString());
                        }
                    }

                    break;
                case 3:
                    System.out.println("\n------------------------- ");
                    System.out.println("Your money: " + pers.getMoney() + "$");
                    System.out.println("\nYour inventory: ");
                    for (int i = 0; i < pers.getThings().length; i++) {
                        System.out.print(pers.toString(i));
                    }
                    System.out.println("------------------------- ");
                    break;
                case 4:
                    work = false;
                    break;
                default: 
                    System.out.println("\n---------- ");
                    System.out.println("Нет такого действия! ");
                    System.out.println("------------ ");
                    continue;
            }   

        }
    }

    class Thing {
    private String name;
    private int count = 0;
    private int price;

    Thing(String name, int count, int price, int ID) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    Thing(String name, int i, int price) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String getName() {
        return name;
    }
    public int getCount() {
        return count;
    }
    public int getPrice() {
        return price;
    }    
    public void setName(String name) {
        this.name = name;
    }
    public void setCount(int count) {
        this.count = count;
    } 
    public void addCount() {
        this.count++;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    
    public String toString() {
        return name + " x " + count + " (" + price + "$" + ')';
    }
}

class Person {
    private int money;
    private boolean breakthis;
    private Thing[] selfthings = new Thing[10];
    Person(int money) {
        this.money = money;
    }
    public int getMoney() {
        return money;
    }
    public Thing[] getThings() {
        return selfthings;
    }
    public Thing getSpecialThings(int i) {
        return selfthings[i];
    }
    public void setMoney(int money) {
        this.money = money;
    }

    public void setThings(Thing thing) {
        for (int i = 0; i < this.selfthings.length; i++) {
            if (this.selfthings[i] == null) {
                this.selfthings[i] = thing;                    
                breakthis = true;
            }
            if (breakthis == true) {
                breakthis = false;
                break;
            }
        }             
    }
    
    public String toString(int i) {
       if (this.selfthings[i] != null) {
            return "- " + this.selfthings[i].getName() + " (" + this.selfthings[i].getPrice() + "$) x " + this.selfthings[i].getCount() + "\n";  
       }
       return "";
    }
}