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
                System.out.println("결과 : 0");
                return;
            }
            // 커스텀구분자 이후의 문자열
            String newStr = "";

            // 구분자(delimeter) 리스트 선언 및 기본 구분자 추가
            List<String> deli = new ArrayList<>();
            deli.add(",");
            deli.add(":");

            // 첫글자로 case 구분(숫자인지 아닌지)
            if (Character.isDigit(str.charAt(0))) {
                // 구분자 없는 경우
                newStr = str;
            } else if (str.contains("//") && str.contains("\\n")) {
                // 구분자 있는 경우
                // "//"와 "\n" 사이가 오직 한글자이면서 숫자가 아닐 때만 구분자 추가함
                int a = str.indexOf("//");
                int b = str.indexOf("\\n");
                // "//"와 "\n" 사이에 한 글자가 있다면 "//"와 "\n" 인덱스 차이는 3
                if (b - a == 3 && !Character.isDigit(str.charAt(2))) {
                    // 만약 가운데가 숫자가 아니라면 delimeter 리스트에 추가
                    deli.add(str.substring(2, 3));
                    // 커스텀 구분자 이후의 문자열을 따로 저장
                    newStr = str.substring(5);
                    // "//"와 "\n" 사이에 아무글자가 없다면 "//"와 "\n" 인덱스 차이는 2
                } else if (b - a == 2) {
                    // 커스텀 구분자 이후의 문자열을 따로 저장
                    newStr = str.substring(4);
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IllegalArgumentException();
            }

            // 구분자를 모아서 StringTokenizer로 전달하기 위해 join
            String joinDeli = String.join("", deli);
            try {
                // 기본구분자 외에 추가구분자가 존재하지 않는 경우
                if (deli.size() < 3) {
                    StringTokenizer st = new StringTokenizer(newStr, joinDeli);
                    // 토큰을 순회하며 합계 계산, 숫자가 아니거나 음수이면 오류 생성
                    while (st.hasMoreTokens()) {
                        int num = Integer.parseInt(st.nextToken());
                        if (num < 0) {
                            throw new IllegalArgumentException();
                        }
                        sum += num;
                    }
                // 기본구분자 외에 추가 구분자가 있는 경우
                } else if (deli.size() == 3) {
                    StringTokenizer st = new StringTokenizer(newStr, joinDeli);
                    while (st.hasMoreTokens()) {
                        int num = Integer.parseInt(st.nextToken());
                        if (num < 0) {
                            throw new IllegalArgumentException();
                        }
                        sum += num;
                    }
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
            System.out.println("결과 : " + sum);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
