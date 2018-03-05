// 정수 변수
package step02;

public class Exam04_1 {
    public static void main(String[] args){
        byte b;
        // 정수 리터럴은 기본이 4바이트 메모리를 차지한다.
        // 그런데 byte 메모리에 저장할 수 있는 값이라면 허락한다.
        b = -128;
        b = 127;
        // 다음과 같이 4바이트 리터럴 값을 메모리에 넣을 수 없다면,
        // 컴파일 오류가 발생한다!
        b = -129; // 컴파일 오류!
        b = 128; // 컴파일 오류!

        short s;
        // 4바이트 정수 리터럴을 short 메모리(2byte)에 저장할 수 있다면,
        // 허락한다.
        s = -32768;
        s = 32767;
        // 만약 2바이트 범위를 벗어난다면 컴파일 오류!
        s = -32769; // 컴파일 오류!
        s = 32768; // 컴파일 오류!

        int i;
        i = -2147483648;
        i = 2147483647;

        //정수 값을 표현할때 4바이트 크기를 넘어가는 수를 표현했기 때문에
        // 오류 발생
        // ---> 반드시 숫자 뒤에 L, l을 붙여야 한다.
        //i = -2147483649; 오류
        //i = 2147483648;

        // 다음은 l,L을 붙임으로서 정수 리터럴을 잘 표현했다.
        // 그러나 i 메모리가 4바이트를 갖기 때문에
        // 오른쪽의 값을 모두 저장할 수 없다. 그래서 오류 발생!
       // i = -2147483649L; 오류
        //i = 2147483648L;

        long l;
        l = -9223372036854775808L;
        l = 9223372036854775807L;

        byte b2;
        b2 = 100;
        b2 = 100L;

    }
}
