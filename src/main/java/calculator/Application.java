package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String str = Console.readLine();
        int sum = 0;
        try {
            // 공백입력시
            if (str.length() <= 0) {
                System.out.println("case 1");
                System.out.println("결과 : 0");
                return;
            }
            // 커스텀구분자 이후의 문자열
            String newStr = "";

            List<String> deli = new ArrayList<>();
            deli.add(",");
            deli.add(":");

            // 첫글자로 case 구분
            if (Character.isDigit(str.charAt(0))) {
                // 구분자 없는 경우
                newStr = str;
            } else if (str.contains("//") && str.contains("\\n")) {
                // 구분자 있는 경우
                // "//"와 "\n" 사이가 오직 한글자이면서 숫자가 아닐 때만 구분자 추가함
                int a = str.indexOf("//");
                int b = str.indexOf("\\n");
                System.out.println("a: " + a);
                System.out.println("b: " + b);
                if (b - a == 3 && !Character.isDigit(str.charAt(2))) {
                    // 만약 가운데가 숫자가 아니라면 delimeter 리스트에 추가
                    deli.add(str.substring(2, 3));
                    // 커스텀 구분자 이후의 문자열을 따로 저장
                    newStr = str.substring(5);
                } else if (b - a == 2) {
                    newStr = str.substring(4);
                } else {
                    System.out.println("case 2");
                    throw new IllegalArgumentException();
                }
            } else {
                System.out.println("case 3");
                throw new IllegalArgumentException();
            }
            System.out.println("newstr: " + newStr);
            String joinDeli = String.join("", deli);
            try {
                if (deli.size() < 3) {
                    StringTokenizer st = new StringTokenizer(newStr, joinDeli);
                    while (st.hasMoreTokens()) {
                        int num = Integer.parseInt(st.nextToken());
                        if (num < 0) {
                            System.out.println("case 4");
                            throw new IllegalArgumentException();
                        }
                        sum += num;
                    }
                } else if (deli.size() == 3) {
                    StringTokenizer st = new StringTokenizer(newStr, joinDeli);
                    while (st.hasMoreTokens()) {
                        int num = Integer.parseInt(st.nextToken());
                        if (num < 0) {
                            System.out.println("case 5");
                            throw new IllegalArgumentException();
                        }
                        sum += num;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("case 6");
                throw new IllegalArgumentException();
            }
            System.out.println("결과 : " + sum);
        } catch (Exception e) {
            System.out.println("case 7");
            throw new IllegalArgumentException();
        }
    }
}
