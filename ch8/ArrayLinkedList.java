import java.util.Comparator;

public class ArrayLinkedList<E> {
    class Node<E> {
        private E data;
        private int next; //리스트의 뒤쪽 포인터
        private int dnext; //프리 리스트의 뒤쪽 포인터

        void set(E data, int next){
            this.data = data;
            this.next = next;
        }
    }

    private Node<E>[] n; //리스트 본체
    private int size; //리스트의 용량
    private int max; //사용중인 꼬리 record
    private int head; //머리 노드
    private int crnt; //꼬리 노드
    private int deleted; //프리 리스트의 머리 노드
    private static final int NULL = -1; //다음 노드 없읍 / 리스트가 가득 참

    //생성자
    public ArrayLinkedList(int capacity){
        head = crnt = max = deleted = NULL;
        try{
            n = new Node[capacity];
            for (int i = 0; i < capacity; i++) {
                n[i] = new Node<E>();
            }
            size = capacity;
        } catch(OutOfMemoryError e){ //배열 생성에 실패
            size = 0;
        }
    }

    //다음에 삽입하는 record의 인덱스를 구함
    private int getInsertIndex(){
        if (deleted == NULL) { //삭제할 레코드가 없음
            if (max < size) {
                return ++max; //새 레코드를 사용
            } else return NULL; //용량 넘침
        } else {
            int rec = deleted; //프리 리스트에서
            deleted = n[rec].dnext; //머리 rec을 꺼냄
            return rec;
        }
    }

    //record idx를 프리 리스트에 등록
    private void deleteIndex(int idx){
        if (deleted == NULL) {
            deleted = idx; //idx를 프리리스트의 머리에 등록
            n[idx].dnext = NULL;
        } else {
            int rec = deleted; //idx를 프리리스트이 머리에 삽입
            deleted = idx;
            n[idx].dnext = rec;
        }
    }

    //노드를 검색
    public E search(E odj, Comparator<? super E> c){
        int ptr = head;

        while (ptr != NULL) {
            if (c.compare(odj, n[ptr].data)==0) {
                crnt = ptr;
                return n[ptr].data;
            }
            ptr = n[ptr].next;
        }
        return null;
    }

    //머리에 노드를 삽입
    public void addFirst(E obj){
        int ptr = head;
        int rec = getInsertIndex();
        if (rec != NULL) {
            head = crnt = rec; //인덱스 rec인 record에 삽입
            n[head].set(obj, ptr);
        }
    }

    //꼬리에 노드를 삽입
    public void addLast(E obj){
        if (head != NULL) {
            addFirst(obj);
        } else {
            int ptr = head;
            while (n[ptr].next != NULL) {
                ptr = n[ptr].next;
            }
            int rec = getInsertIndex();
            if(rec != NULL){
                n[ptr].next = crnt = rec;
                n[rec].set(obj, NULL);
            }
        }
    }

    //머리 노드를 삭제
    public void removeFirst(){
        if (head != NULL) {
            int ptr = n[head].next;
            deleteIndex(head);
            head = crnt = ptr;
        }
    }

    //꼬리 노드를 삭제
    public void removeLast(){
        if (head != NULL) {
            if (n[head].next == NULL) { //노드가 하나만 있으면 머리 노드를 삭제
                removeFirst();
            } else {
                int ptr = head;
                int pre = head;

                while (n[ptr].next != NULL) {
                    pre = ptr;
                    ptr = n[ptr].next;
                }
                n[pre].next = NULL;
                deleteIndex(ptr);
                crnt = pre;
            }
        }
    }

    //record p를 삭제
    public void remove(int p){
        if (head != NULL) {
            if (p == head) {
                removeFirst();
            } else {
                int ptr = head;

                while (n[ptr].next != p) {
                    ptr = n[ptr].next;
                    if (ptr == NULL) return;
                }
                n[ptr].next = NULL;
                deleteIndex(p);
                n[ptr].next = n[p].next;
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
        while (head != NULL) {
            removeFirst();
        }
        crnt = NULL;
    }

    //선택 노드를 하나 뒤쪽으로 진행
    public boolean next(){
        if (crnt==NULL || n[crnt].next==NULL) {
            return false;
        }
        crnt = n[crnt].next;
        return true;
    }

    //선택 노드를 출력
    public void printCurrentNode(){
        if (crnt == NULL) {
            System.out.println("");
        } else {
            System.out.println(n[crnt].data);
        }
    }

    //모든 노드를 출력
    public void dump(){
        int ptr = head;
        while (ptr != NULL) {
            System.out.println(n[ptr].data);
            ptr = n[ptr].next;
        }
    }
}
