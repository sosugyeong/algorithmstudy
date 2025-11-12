import java.util.Arrays;
import java.util.Comparator;

public class PhyscExamSort {

    static class PhyscData{
        String name;
        int height;
        double vision;

        PhyscData(String name, int height, double vision){
            this.name = name;
            this.height = height;
            this.vision = vision;
        }

        public String toString(){
            return name+" "+height+" "+vision;
        }

        static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();

        private static class HeightOrderComparator implements Comparator<PhyscData> {
            public int compare(PhyscData d1, PhyscData d2){
                return (d1.height > d2.height) ? 1 : (d1.height < d2.height) ? -1 : 0;
            }
            
        }
    }

    public static void main(String[] args) {
        PhyscData[] x = {
            new PhyscData("선아현", 182, 0.9),
            new PhyscData("박문대", 178, 0.8),
            new PhyscData("차유진", 183, 1.3),
            new PhyscData("류청우", 187, 1.5),
            new PhyscData("이세진", 185, 1.2),
            new PhyscData("배세진", 177, 1.0),
            new PhyscData("김래빈", 181, 0.7),
        };

        Arrays.sort(x, PhyscData.HEIGHT_ORDER);

        System.out.println("# 신체검사 리스트 #");
        System.out.println("이름       키   시력");
        System.out.println("--------------------");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
        }
    }
}
