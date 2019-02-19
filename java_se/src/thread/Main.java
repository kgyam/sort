package thread;

import javax.sound.midi.Soundbank;

public class Main {


    public Main(){}



    public class Apple{

        private int a=1;

        public void add(){
            int i=1;
            a=a+1;
            this.add2(i,a);

            System.out.println("add的i=  "+i);
            System.out.println("add的a=  "+a);
        }


        private void add2(int i,int a){
            i=i+1;
            a=a+1;
            System.out.println("add2的i=  "+i);
            System.out.println("add2的a=  "+a);
        }
    }

    public static void main(String[] args) {
        Main.Apple apple=new Main().new Apple();

        apple.add();
    }
}
