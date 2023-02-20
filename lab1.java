class lab1
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        String[] limbaje=new String[]{"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n=(int) (Math.random() * 1000000);
        n=n*3;

        String binar="10101";
        int numar=Integer.parseInt(binar,2);

        n=n+numar;

        String hex="FF";
        int numar2=Integer.parseInt(hex,16);

        n=n+numar2;

        n=n*6;
        
        int sumacifre=n;
        while(sumacifre>9)
        {
            sumacifre=sumacifre+n%10;
            n=n/10;
        }

        System.out.println("Willy-nilly, this semester I will learn ");
        System.out.println(limbaje[sumacifre]);


    }
}