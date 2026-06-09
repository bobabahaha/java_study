import java.util.Scanner;

public class a{
    public static void main(String[] args){


    Scanner sc = new Scanner(System.in);
    System.out.println("请输入用户年龄：");
    int a = sc.nextInt();
    if(a>=70)
    {
        System.out.println("老人");
    } else if (a<70 && a >30){

        System.out.println("中年");
        }else if(a >=18 && a<=30){
            System.out.println("青年");
        }
        else System.out.println("小孩");
    }
}
