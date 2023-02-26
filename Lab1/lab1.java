public class lab1
{   /* Explicatii 
    public static void main(String []args)
    {
        //static -> tine de clasa in sine nu de obiect
        for(int i=0;i<args.length;i++)
            System.out.println(args[i]);
        
        lab1 app= new lab1(); //facem un obiect de tip lab1
        app.test(); //sau test() daca metoda test de mai jos e facuta static
        
    }
    private void test() //daca pun static -> metoda tine de clasa nu de obiect
    //pot face test() in main
    {
        System.out.println("Hello world!" + Math.random());
    }
    */

    public static void main(String []args)
    {
        System.out.println("Hello World!");

        String languages[]={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random()*1_000_000);

        n=n*3;
        
        n=n+0b10101;

        n=n+0xFF;

        n=n*6;

        int s;
        while(n>9)
        {
            s=0;
            while(n!=0)
            {
                s=s+n%10;
                n=n/10;
            }
            n=s;
        }
        System.out.println(n);
        int result=n;
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}
