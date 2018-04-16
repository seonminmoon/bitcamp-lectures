package bitcamp.java106.pms.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Board;

@Component
public class BoardDao extends AbstractDao<Board> {
    
    public BoardDao() throws Exception {
        load();
    }
    
    // <load(), save() 변경>
    // 1) Stream 적용
    // 2) 전체적으로 try ~ catch ~ 적용
    // 3) out.writeObject(boards.next());
    //    this.insert((Board) in.readObject()); 추가하고 나머지 삭제
    
    public void load() throws Exception {
        try (
        ObjectInputStream in = new ObjectInputStream(
                               new BufferedInputStream(
                               new FileInputStream("data/board.data")));
        ){
            while (true) {
                try {
                    this.insert((Board) in.readObject());
                } catch (Exception e) { // 데이터를 모두 읽었거나 파일 형식에 문제가 있다면,
                    //e.printStackTrace();
                    break; // 반복문을 나간다.
                }
            }
        }
    }
    
    
    public void save() throws Exception {
        try ( // catch 는 작성하지 않는다. 발생한 오류는 호출자에게 보내버림
              // 왜냐면 무슨 오류인지 어떻게 해결해야되는지 알아야되기 때문임
              // try 를 빼면 close 가 안되니깐 try는 남겨놓는다.
              // try 빼고 close 만 하면 오류 발생 시 close 까지 가지 않고 오류 넘겨주고 메서드 끝나서 안됨
        ObjectOutputStream out = new ObjectOutputStream(
                                 new BufferedOutputStream(
                                 new FileOutputStream("data/board.data")));
        ) {
            Iterator<Board> boards = this.list();
            while (boards.hasNext()) {
                out.writeObject(boards.next());
            }
        }
    }
    
    public int indexOf(Object key) {
        int no = (Integer) key; // Integer ==> int : auto-unboxing
        for (int i = 0; i < collection.size(); i++) {
            Board originBoard = collection.get(i);
            if (originBoard.getNo() == no) {
                return i;
            }
        }
        return -1;
    }
}

//ver 24 - File I/O 적용
//ver 23 - @Component 애노테이션을 붙인다.
//ver 22 - 추상 클래스 AbstractDao를 상속 받는다.
//ver 19 - 우리 만든 ArrayList 대신 java.util.LinkedList를 사용하여 목록을 다룬다. 
//ver 18 - ArrayList를 이용하여 인스턴스(의 주소) 목록을 다룬다. 
//ver 16 - 인스턴스 변수를 직접 사용하는 대신 겟터, 셋터 사용.
//ver 14 - BoardController로부터 데이터 관리 기능을 분리하여 BoardDao 생성.





