class lab1
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        String[] limbaje=new String[]{"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n=(int) (Math.random() * 1_000_000);
        n=n*3;

        String binar="10101";
        int numar=Integer.parseInt(binar,2);

        n+=numar;

        String hex="FF";
        int numar2=Integer.parseInt(hex,16);

    }
}