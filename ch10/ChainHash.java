public class ChainHash<K,V> {
    class Node<K,V> {
        private K key; //키값
        private V data; //데이터
        private Node<K,V> next; //다음 노드에 대한 참조

        Node(K key, V data, Node<K,V> next){
            this.key = key;
            this.data = data;
            this.next = next;
        }

        K getKey(){
            return key;
        }

        V getValue(){
            return data;
        }

        public int hashCode(){
            return key.hashCode();
        }
    }

    private int size;
    private Node<K,V>[] table;

    public ChainHash(int capacity){
        try{
            table = new Node[capacity];
            this.size = capacity;
        } catch(OutOfMemoryError e){
            this.size = 0;
        }
    }

    public int hashValue(Object key){ //해시값을 구함
        return key.hashCode() % size;
    }

    public V search(K key){
        int hash = hashValue(key); //검색할 데이터의 해시값
        Node<K,V> p = table[hash]; //선택 노드

        while (p != null) {
            if (p.getKey().equals(key)) {
                return p.getValue(); //검색 성공
            }
            p = p.next; //다음 노드를 선택
        }

        return null;
    }

    public int add(K key, V data){ //키값이 key이고 데이터가 data인 요소를 추가
        int hash = hashValue(key); //추가할 데이터의 해시값
        Node<K,V> p = table[hash]; //선택 노드

        while (p != null) {
            if (p.getKey().equals(key)) { //이 키값은 이미 있음
                return 1; 
            }
            p = p.next; //다음 노드를 선택
        }

        Node<K,V> temp = new Node<K,V>(key, data, table[hash]);
        table[hash] = temp; //노드를 삽입
        return 0;
    }

    public int remove(K key){
        int hash = hashValue(key); //추가할 데이터의 해시값
        Node<K,V> p = table[hash]; //선택 노드
        Node<K,V> pp = null;

        while (p != null) {
            if (p.getKey().equals(key)) {
                if (pp==null) table[hash] = p.next;
                else pp.next = p.next;
                return 0;
            }
            pp = p;
            p = p.next; //다음 노드를 선택
        }

        return 1;
    }

    public void dump(){
        for (int i = 0; i < size; i++) {
            Node<K,V> p = table[i];
            System.out.printf("%02d", i);
            while (p != null) {
                System.out.printf("-> %s (%s)  ", p.getKey(), p.getValue());
                p = p.next;
            }
            System.out.println();
        }
    }
}
