package bitcamp.java106.pms.servlet.teammember;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import bitcamp.java106.pms.dao.TeamMemberDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.support.WebApplicationContextUtils;

@SuppressWarnings("serial")
@WebServlet("/team/member/list")
public class TeamMemberListServlet extends HttpServlet {

    TeamMemberDao teamMemberDao;
    
    @Override
    public void init() throws ServletException {
        ApplicationContext iocContainer = 
                WebApplicationContextUtils.getWebApplicationContext(
                        this.getServletContext()); 
        teamMemberDao = iocContainer.getBean(TeamMemberDao.class);
    }
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
            List<Member> members = teamMemberDao.selectListWithEmail(name);
            request.setAttribute("members", members);
//            response.setContentType("text/html;charset=UTF-8"); => list.jsp 에서 했기 때문에 안써도됨
            request.getRequestDispatcher("/teammember/list.jsp").include(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error", e);
            request.setAttribute("title", "팀 멤버 조회 실패!");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}

//ver 39 - forward 적용
