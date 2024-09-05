package inh;

class gp{
    public void print(){
        System.out.println("gp");
    }
}

class p extends gp{
    public void print(){
        System.out.println("p");
    }
}

class c extends p{

    public void print(){
        super.print();
        System.out.println("c");
    }
}
public class dd{
    public static void main(String[] args) {
        c c = new c();
        c.print();
    }
}
