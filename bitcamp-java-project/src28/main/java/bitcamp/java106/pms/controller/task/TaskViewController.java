// Controller 규칙에 따라 메서드 작성
package bitcamp.java106.pms.controller.task;

import java.io.PrintWriter;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.TaskDao;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.domain.Task;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

@Component("/task/view")
public class TaskViewController implements Controller {
    TeamDao teamDao;
    TaskDao taskDao;
    
    public TaskViewController(
            TeamDao teamDao, 
            TaskDao taskDao) {
        this.teamDao = teamDao;
        this.taskDao = taskDao;
    }

    @Override
    public void service(ServerRequest request, ServerResponse response) {
        PrintWriter out = response.getWriter();
        String teamName = request.getParameter("teamName");
        if (teamName == null) {
            out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = teamDao.get(teamName);
        if (team == null) {
            out.printf("'%s' 팀은 존재하지 않습니다.\n", teamName);
            return;
        }
        int taskNo = Integer.parseInt(request.getParameter("no"));
        
        Task task = taskDao.get(taskNo);
        if (task == null) {
            System.out.printf("'%s'팀의 %d번 작업을 찾을 수 없습니다.\n",
                    teamName, taskNo);
            return;
        }
        
        out.printf("작업명: %s\n", task.getTitle());
        out.printf("시작일: %s\n", task.getStartDate());
        out.printf("종료일: %s\n", task.getEndDate());
        out.printf("작업자: %s\n", 
                (task.getWorker() == null) ? "-" : task.getWorker().getId());
        out.printf("작업상태: %s\n", getStateLabel(task.getState()));
    }

    public static String getStateLabel(int state) {
        switch (state) {
        case Task.READY: return "작업대기";
        case Task.WORKING: return "작업중";
        case Task.COMPLETE: return "작업완료";
        default:
            return null;
        }
    }
}

//ver 26 - TaskController에서 view() 메서드를 추출하여 클래스로 정의.
//ver 23 - @Component 애노테이션을 붙인다.
//ver 22 - TaskDao 변경 사항에 맞춰 이 클래스를 변경한다.
//ver 18 - ArrayList가 적용된 TaskDao를 사용한다.
//ver 17 - 클래스 생성