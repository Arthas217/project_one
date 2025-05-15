package org.com.zlk.chxg.od;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 小明来到某学校当老师，需要将学生按考试总分或单科分数进行排名，帮帮他
 *
 * 输入描述：
 *   第1行输入两个整数，学生人数n和科目 数量m   0<n<100, 0<m<10
 *   第2行输入m个科目名称，彼此之间用空格隔开。 科目名称只由英文字母构成，，单个长度不超过10个字符，科目的出现顺序和后续输入的学生成绩一一对应。不会出现重复的科目名称。
 *   第3行开始的n行，每行包含一个学生的姓名和该生m个科目的成绩（空格隔开），学生不会重名。学生姓名只由英文字母构成，长度不超过10个字符，成绩是0~100的整数，依次对应第2行中输入的科目。
 *  第n+2行，输入用作排名的科目名称。若科目不存在，则按总分进行排序。
 *
 * 输出描述：
 *   输入一行，按成绩排序后的学生名字，空格隔开。 成绩相同的按照学生姓名字典顺序排序。
 *
 *
 *
 * 示例1
 *
 * 输入
 * 3 2
 * yuwen shuxue
 * fangfang  95 90
 * xiaohua 88 95
 * minmin 100 82
 * shuxue
 *
 * 输出
 * xiaohua fangfang minmin
 *
 * 说明
 * 按shuxue成绩排名，依次是xiaohua、fangfang、minmin
 *
 *
 * 示例2
 *
 * 输入
 * 3 2
 * yuwen shuxue
 * fangfang  95 90
 * xiaohua 88 95
 * minmin 100 82
 * zongfen
 *
 * 输出
 * fangfang minmin xiaohua
 *
 * 说明
 * 排序科目不存在，按总分排序fangfang和minmin总分相同，按姓名的字典顺序，fangfang排在前面
 *
 * java编程要求： 时间限制1秒， 空间限制262MB，
 *
 *
 * 请帮我完成上面题目中的描述逻辑ACM模式单行多行输入输出，使用java代码实现以上题目的要求
 *
 * @author 会游泳的蚂蚁
 * @date 2025/5/15 15:44
 */
public class OD202505 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取学生人数n和科目数量m
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        // 读取科目名称
        String[] subjects = scanner.nextLine().split(" ");

        // 读取学生信息
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().split(" ");
            String name = parts[0];
            int[] scores = new int[m];
            for (int j = 0; j < m; j++) {
                scores[j] = Integer.parseInt(parts[j + 1]);
            }
            students.add(new Student(name, scores));
        }

        // 读取排序科目
        String sortSubject = scanner.nextLine();

        // 判断科目是否存在
        int subjectIndex = -1;
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equals(sortSubject)) {
                subjectIndex = i;
                break;
            }
        }

        // 排序
        if (subjectIndex != -1) {
            // 按指定科目排序
            int finalSubjectIndex = subjectIndex;
            students.sort((s1, s2) -> {
                if (s1.scores[finalSubjectIndex] != s2.scores[finalSubjectIndex]) {
                    return s2.scores[finalSubjectIndex] - s1.scores[finalSubjectIndex]; // 降序
                } else {
                    return s1.name.compareTo(s2.name); // 字典序
                }
            });
        } else {
            // 按总分排序
            students.sort((s1, s2) -> {
                int sum1 = Arrays.stream(s1.scores).sum();
                int sum2 = Arrays.stream(s2.scores).sum();
                if (sum1 != sum2) {
                    return sum2 - sum1; // 降序
                } else {
                    return s1.name.compareTo(s2.name); // 字典序
                }
            });
        }

        // 输出结果
        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append(student.name).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    static class Student {
        String name;
        int[] scores;

        public Student(String name, int[] scores) {
            this.name = name;
            this.scores = scores;
        }
    }
}
