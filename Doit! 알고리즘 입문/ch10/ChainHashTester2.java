import java.util.Scanner;

public class ChainHashTester2 {
    static Scanner sc = new Scanner(System.in);

    static class Data {
        static final int NO   = 1;
        static final int NAME = 2;

        private Integer no; // 회원번호(키값)
        private String  name; // 이름

        String keyCode() { //키값
            return name;
        }

        public String toString() {
            return Integer.toString(no);
        }

        void scanData(String guide, int sw) { //데이터를 읽어 들임
            System.out.println(guide + "할 데이터를 입력하세요.");

            if ((sw & NO) == NO) {
                System.out.print("번호: ");
                no = sc.nextInt();
            }
            if ((sw & NAME) == NAME) {
                System.out.print("이름: ");
                name = sc.next();
            }
        }
    }

    enum Menu {
        ADD(      "추가"),
        REMOVE(   "삭제"),
        SEARCH(   "검색"),
        DUMP(     "표시"),
        TERMINATE("종료");

        private final String message;

        static Menu MenuAt(int idx) {// 순서가 idx번째인 열거를 반환
            for (Menu m : Menu.values())
                if (m.ordinal() == idx)
                    return m;
            return null;
        }

        Menu(String string) { // 생성자(constructor)
            message = string;
        }

        String getMessage() { // 표시할 문자열을 반환
            return message;
        }
    }

    static Menu SelectMenu() {
        int key;
        do {
            for (Menu m : Menu.values())
                System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
            System.out.print(" : ");
            key = sc.nextInt();
        } while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());

        return Menu.MenuAt(key);
    }

    public static void main(String[] args) {
        Menu menu; // 메뉴 
        Data data; // 추가용 데이터 참조
        Data temp = new Data(); // 읽어 들일 데이터

        ChainHash<String, Data> hash = new ChainHash<String, Data>(13);

        do {
            switch (menu = SelectMenu()) {
            case ADD :
                    data = new Data();
                    data.scanData("추가", Data.NO | Data.NAME);
                    hash.add(data.keyCode(), data);
                    break;

            case REMOVE :
                    temp.scanData("삭제", Data.NAME);
                    hash.remove(temp.keyCode());
                    break;

            case SEARCH :
                    temp.scanData("검색", Data.NAME);
                    Data t = hash.search(temp.keyCode());
                    if (t != null)
                        System.out.println("그 키를 갖는 데이터는 " + t + "입니다.");
                    else
                        System.out.println("해당 데이터가 없습니다.");
                    break;

            case DUMP :
                    hash.dump();
                    break;
            }
        } while (menu != Menu.TERMINATE);
    }
}
