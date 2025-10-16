import java.util.Comparator;
import java.util.Scanner;

public class BinTreeTester {
    static Scanner sc = new Scanner(System.in);

    static class Data{
        public static final int NO = 1;
        public static final int NAME = 2;

        private Integer no;
        private String name;

        Integer keyCode(){
            return no;
        }

        public String toString(){
            return name;
        }

        void scanData(String guide, int sw){
            System.out.println(guide+"할 데이터를 입력하세요.");

            if ((sw&NO)==NO) {
                System.out.print("번호: ");
                no = sc.nextInt();
            }
            if ((sw&NAME)==NAME) {
                System.out.print("이름: ");
                name = sc.next();
            }
        }
    }

    enum Menu{
        ADD("삽입"),
        REMOVE("삭제"),
        SEARCH("검색"),
        PRINT("출력"),
        PRINTR("내림차순 출력"),
        MINKEY("가장 작은 키 값"),
        MINDATA("가장 작은 키를 갖는 데이터"),
        MAXKEY("가장 큰 키 값"),
        MAXDATA("가장 큰 키를 갖는 데이터"),
        TERMINATE("종료");
        
        private final String message;

        static Menu MenuAt(int idx){
            for (Menu m : Menu.values()) {
                if (m.ordinal() == idx) {
                    return m;
                }
            }
            return null;
        }

        Menu(String string){
            message = string;
        }

        String getMessage(){
            return message;
        }
    }

    static Menu SelectMenu(){
        int key;
        do{
            for (Menu m : Menu.values()) {
                System.out.printf("(%d) %s", m.ordinal(), m.getMessage());
            }
            System.out.print(" : ");
            key = sc.nextInt();
        } while(key<Menu.ADD.ordinal() || key>Menu.TERMINATE.ordinal());

        return Menu.MenuAt(key);
    }

    public static void main(String[] args) {
        Menu menu;
        Data data; //추가용 데이터 참조
        Data ptr; //검색용 데이터 참조
        Data temp = new Data(); //입력용 데이터

        class IntegerDecComparator implements Comparator<Integer>{
            public int compare(Integer n1, Integer n2){
                return (n1>n2)? -1:(n1<n2) ? 1:0;
            }
        }
        final IntegerDecComparator INT_DEC_COMP = new IntegerDecComparator();

        BinTree<Integer, Data> tree = new BinTree<Integer, Data>(INT_DEC_COMP);

        do{
            switch (menu = SelectMenu()) {
                case ADD:
                    data = new Data();
                    data.scanData("삽입", Data.NO | Data.NAME);
                    tree.add(data.keyCode(), data);
                    break;

                case REMOVE:
                    temp.scanData("삭제", Data.NO);
                    tree.remove(temp.keyCode());
                    break;

                case SEARCH:
                    temp.scanData("검색", Data.NO);
                    ptr = tree.search(temp.keyCode());
                    if (ptr != null) {
                        System.out.println("그 번호의 이름은 "+ptr+"입니다.");
                    } else {
                        System.out.println("해당 데이터가 없습니다.");
                    }
                    break;

                case PRINT:
                    tree.print();
                    break;

                case PRINTR:
                    tree.printR();
                    break;

                case MINKEY:
                    Integer key = tree.getMinKey();
                    if (key != null) {
                        System.out.println("가장 작은 키 값은 "+key+"입니다.");
                    } else {
                        System.out.println("데이터가 없습니다.");
                    }
                    break;

                case MINDATA:
                    ptr = tree.getDataWithMinKey();
                    if (ptr != null) {
                        System.out.println("가장 작은 키값을 갖는 데이터는 "+ptr+"입니다.");
                    } else {
                        System.out.println("데이터가 없습니다.");
                    }
                    break;

                case MAXKEY:
                    Integer key1 = tree.getMaxKey();
                    if (key1 != null) {
                        System.out.println("가장 큰 키 값은 "+key1+"입니다.");
                    } else {
                        System.out.println("데이터가 없습니다.");
                    }
                    break;

                case MAXDATA:
                    ptr = tree.getDataWithMaxKey();
                    if (ptr != null) {
                        System.out.println("가장 큰 키값을 갖는 데이터는 "+ptr+"입니다.");
                    } else {
                        System.out.println("데이터가 없습니다.");
                    }
                    break;
            }
        } while(menu != Menu.TERMINATE);
    }
}
