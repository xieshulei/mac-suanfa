package ink.shulei.hannuota;

public class Hannuota {
    private static int i = 0;
    public static void main(String[] args) {

        int c = doc(64,'A','B','C');
        System.out.println(c);

    }

    public static int doc(int num,char a,char b,char c){
        if (num == 1){
            i++;
//            System.out.println(i++);
//            System.out.println("把第1个盘从"+a+"移动到"+c);
        }else {
            doc(num - 1,a,c,b);
            i++;
//            System.out.println(i++);
//            System.out.println("把第"  + num + "个盘从"+a+"移动到" + c);
            doc(num - 1,b,a,c);
        }
        return i;
    }
}
