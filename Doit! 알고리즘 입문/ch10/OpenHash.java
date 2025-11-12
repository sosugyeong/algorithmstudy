import org.w3c.dom.Node;

public class OpenHash<K,V> {
    //버킷의 상태 (데이터 저장, 비어있음, 삭제 마침)
    enum Status {OCCUPIED, EMPTY, DELETED};

    static class Bucket<K,V> {
        private K key;
        private V data;
        private Status stat;

        Bucket(){
            stat = Status.EMPTY;
        }

        void set(K key, V data, Status stat){
            this.key = key;
            this.data = data;
            this.stat = stat;
        }

        void setStat(Status stat){ //상태를 설정
            this.stat = stat;
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

    private int size; //해시테이블 크기
    private Bucket<K,V>[] table;

    public OpenHash(int size){
        try {
            table = new Bucket[size];
            for (int i = 0; i < size; i++) {
                table[i] = new Bucket<K,V>();
            }
            this.size = size;
        } catch (OutOfMemoryError e) { //테이블을 생성할 수 없음
            this.size = 0;
        }
    }

    public int hashValue(Object key){
        return key.hashCode()%size;
    }

    public int rehashValue(int hash){ //재해시값 구하기
        return (hash+1)%size;
    }

    public Bucket<K,V> searchNode(K key){
        int hash = hashValue(key); //검색할 데이터의 해시값
        Bucket<K,V> p = table[hash]; //선택 노드

        for (int i = 0; p.stat!=Status.EMPTY && i<size; i++) {
            if (p.stat==Status.OCCUPIED && p.getKey().equals(key)) {
                return p;
            }
            hash = rehashValue(hash);
            p = table[hash]; //다음 노드를 선택
        }

        return null;
    }

    public V search(K key){
        Bucket<K,V> p = searchNode(key); //선택 노드

        if (p != null) {
                return p.getValue(); //검색 성공
        } else {
            return null;
        }
    }

    public int add(K key, V data){ //키값이 key이고 데이터가 data인 요소를 추가

        if (search(key)!=null) {//키값이 이미 등록됨
            return 1;
        }

        int hash = hashValue(key); //추가할 데이터의 해시값
        Bucket<K,V> p = table[hash]; //선택 노드

        for (int i = 0; i<size; i++) {
            if (p.stat==Status.EMPTY || p.getKey().equals(key)) {
                p.set(key, data, Status.OCCUPIED);
                return 0;
            }
            hash = rehashValue(hash); //재해시
            p = table[hash];
        }

        return 2; //해시 테이블이 가득 참
    }

    public int remove(K key){
        Bucket<K,V> p = searchNode(key);
        if (p == null) {
                return 1;
        }
        
        p.setStat(Status.DELETED);
        return 0;
    }

    public void dump(){
        for (int i = 0; i < size; i++) {
            System.out.printf("%02d", i);
            switch (table[i].stat) {
                case OCCUPIED:
                    System.out.printf("%s (%s)\n", table[i].getKey(), table[i].getValue());
                    break;

                case EMPTY:
                    System.out.println("--- 비어있음 ---");
                    break;

                case DELETED:
                    System.out.println("--- 삭제 마침 ---");
                    break;
            

            }
        }
    }
}
