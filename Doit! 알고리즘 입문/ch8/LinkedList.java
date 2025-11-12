import java.util.Comparator;

public class LinkedList<E> {
    class Node<E> {
        private E data; //데이터
        private Node<E> next; //뒤쪽 포인터

        Node(E data, Node<E> next){
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> head; //머리 포인터
    private Node<E> crnt; //선택 포인터 (현재 선택한 노드, 검색, 선택, 삭제 용도로 사용)

    public LinkedList(){
        head = crnt = null;
    }

    //주어진 조건을 만족하는 노드를 검색
    public E search(E obj, Comparator<? super E> c){
        Node<E> ptr = head; //현재 스캔중인 노드

        while (ptr != null) {
            if (c.compare(obj, ptr.data)==0) { //검색 성공
                crnt = ptr;
                return ptr.data;
            }
            ptr = ptr.next; //다음 노드를 선택
        }
        return null; //검색 실패
    }

    //리스트의 머리에 노드를 삽입
    public void addFirst(E obj){
        Node<E> ptr = head;
        head = crnt = new Node<E>(obj, ptr);
    }

    //리스트의 꼬리에 노드를 삽입
    public void addLast(E obj){
        if (head == null) {
            addFirst(obj);
        } else {
            Node<E> ptr = head;
            while (ptr.next != null) {
                ptr = ptr.next;
            }
            ptr.next = crnt = new Node<E>(obj, null);
        }
    }

    //머리 노드를 삭제
    public void removeFirst(){
        if (head != null) {
            head = crnt = head.next;
        }
    }
    //꼬리 노드를 삭제
    public void removeLast(){
        if (head != null) {
            if (head.next == null) {
                removeFirst();
            } else {
                Node<E> ptr = head; //스캔중인 노드
                Node<E> pre = head; //스캔중인 노드이 앞쪽 노드

                while (ptr.next != null) {
                    pre = ptr;
                    ptr = ptr.next;
                }
                pre.next = null;
                crnt = pre;
            }
        }
    }

    //노드 p를 삭제
    public void remove(Node p){
        if (head != null) {
            if (p == head) {
                removeFirst();
            } else {
                Node<E> ptr = head;

                while (ptr.next != p) {
                    ptr = ptr.next;
                    if (ptr == null) return; //p가 리스트에 없음
                }
                ptr.next = p.next;
                crnt = ptr;
            }
        }
    }

    //선택 노드를 삭제
    public void removeCurrentNode(){
        remove(crnt);
    }

    //모든 노드를 삭제
    public void clear(){
        while (head != null) {//노드에 아무것도 없을때까지 머리노드를 삭제
            removeFirst();
        }
        crnt = null;
    }

    //선택 노드를 하나 뒤쪽으로 진행
    public boolean next(){
        if (crnt==null || crnt.next==null) {
            return false;
        }
        crnt = crnt.next;
        return true;
    }

    //선택 노드를 출력
    public void printCurrentNode(){
        if (crnt == null) {
            System.out.println("선택한 노드가 없습니다.");
        } else {
            System.out.println(crnt.data);
        }
    }

    //모든 노드를 출력
    public void dump(){
        Node<E> ptr = head;

        while (ptr != null) {
            System.out.println(ptr.data);
            ptr = ptr.next;
        }
    }

    public void purge(Comparator<? super E> c){//이름이 같은 노드를 하나만 남기고 삭제
        Node<E> ptr = head;

        while (ptr != null) {
            int count = 0;
            Node<E> ptr2 = ptr;
            Node<E> pre = ptr;

            while (pre.next != null) {
                ptr2 = pre.next;
                if (c.compare(ptr.data, ptr2.data) == 0) {
                    pre.next = ptr2.next;
                    count++;
                } else {pre = ptr2;}
            }
            if (count == 0) {
                ptr = ptr.next;
            } else {
                Node<E> temp = ptr;
                remove(ptr);
                ptr = temp.next;
            }
        }
        crnt = head;
    }

    public E retrieve(int n){//머리부터 n개 뒤의 노드 데이터에 대한 참조 반환 
        Node<E> ptr = head;

        while (n>=0 && ptr != null) {
            if (n-- == 0) {
                crnt = ptr;
                return ptr.data;
            }
            ptr=ptr.next;
        }
        return null;
    }
}
