import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // створення масиву об'єктів класу
        Institution [] institutions = new Institution[5];
        institutions[0] = new Institution("Harvard University", 1636, 20000, "University", 3.9);
        institutions[1] = new Institution("Stanford University", 1885, 17000, "University", 3.8);
        institutions[2] = new Institution("MIT", 1861, 11000, "University", 3.9);
        institutions[3] = new Institution("California State University", 1857, 50000, "University", 3.0);
        institutions[4] = new Institution("Yale University", 1701, 14000, "University", 3.9);

        sort(institutions);
    }
    public static void sort(Institution[] institutions){

        //first sort
        Institution[] resArr = new Institution[institutions.length];
        double [] avgScores = new double[institutions.length];
        for(int i=0; i<institutions.length; i++){
            avgScores[i] = institutions[i].getAverageScore(); // збирання середніх оцінок усіх інститутів для сортування
        }
        Arrays.sort(avgScores); // сортування середніх оцінок
        for (int i=0; i<institutions.length; i++){
            for(Institution el : institutions){
                if(el.getAverageScore()==avgScores[i] && !Arrays.asList(resArr).contains(el)){
                    resArr[i] = el; // привласнення відсортованих інститутів
                }
            }
        }


        // second sort
        for(int i=0; i<institutions.length; i++){
            int [] studentCounts = new int[institutions.length];
            if(i<institutions.length-1){
                if(resArr[i].getAverageScore()==resArr[i+1].getAverageScore()){
                    int dublicatesCount = 0; //кількість дублікатів підряд
                    for(int j=0; j<institutions.length-i; j++){
                        if(resArr[i].getAverageScore()==resArr[i+j].getAverageScore()){
                            studentCounts[j] = resArr[i+j].getStudentCount(); // збирання кількості студентів для сортування
                            dublicatesCount++;
                    }}
                    //сортування масиву кількості студентів
                    Arrays.sort(studentCounts);
                    reverse(studentCounts);
                    for(int j=0; j<studentCounts.length; j++){
                        if(studentCounts[j]==0) break;
                        // привласнення інститутів у правильному порядку в результатний масив
                        resArr[i+j] = getInstituteByStudentCount(institutions, studentCounts[j]);
                    }
                    i += dublicatesCount;
                }

            }

        }
        for(Institution el:resArr){
            System.out.println("Name: " + el.getName() + ". Average Score: " + el.getAverageScore() + ". Student Count: " + el.getStudentCount());
        }

    }
//отримати інститут за кількістю студентів
    public static Institution getInstituteByStudentCount(Institution[] institutions, int count){
        for(Institution el : institutions){
            if(el.getStudentCount()==count) return el;
        }
        return null;
    }
    //обертання відсортованого масиву
    public static void reverse(int[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
}

class Institution{
    // 5 полів класу
    private final String name;
    private final int establishedYear;
    private final int studentCount;
    private final String type;
    private final double averageScore;

    // конструктор
    public Institution(String name, int establishedYear, int studentCount, String type, double averageScore) {
        this.name = name;
        this.establishedYear = establishedYear;
        this.studentCount = studentCount;
        this.type = type;
        this.averageScore = averageScore;
    }
// геттери
    public String getName() {
        return name;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public double getAverageScore() {
        return averageScore;
    }
}