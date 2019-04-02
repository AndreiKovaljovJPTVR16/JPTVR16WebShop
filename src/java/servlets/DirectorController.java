package servlets;

import SecurityLogic.RoleLogic;
import SecurityLogic.RoleLogic.ROLE;
import entity.Product;
import entity.History;
import entity.Buyer;
import entity.Role;
import entity.User;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ProductFacade;
import session.HistoryFacade;
import session.BuyerFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;
import utils.Encription;


@WebServlet(name = "DirectorController", urlPatterns = {
    "/showChangeRole",
    "/changeRole",
   
    
    
})
public class DirectorController extends HttpServlet {
    @EJB private ProductFacade productFacade;
    @EJB private BuyerFacade buyerFacade;
    @EJB private HistoryFacade historyFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private UserFacade userFacade;
    @EJB private RoleFacade roleFacade;
    
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        RoleLogic rl = new RoleLogic();
        Encription encription = new Encription();
        Calendar c = new GregorianCalendar();
        String path = request.getServletPath();
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "Войдите!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        User regUser = (User) session.getAttribute("regUser");
        if(regUser == null){
            request.setAttribute("info", "Войдите!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        
        if(!rl.isRole(ROLE.DIRECTOR.toString(), regUser)){
            request.setAttribute("info", "Вы должны быть администратором!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        
        if(null != path) switch (path) {
            case "/showChangeRole":
                List<Role> listRoles = roleFacade.findAll();
                List<User> listUsers = userFacade.findAll();
                Role role;
                Map<User,Role> mapUsers = new HashMap<>();
                for(User user : listUsers){
                    role=rl.getRole(user);
                    mapUsers.put(user, role);
                }
                request.setAttribute("listRoles", listRoles);
                request.setAttribute("mapUsers", mapUsers);
                request.getRequestDispatcher("/page/admin/showChangeRole.jsp").forward(request, response);
                break;
            case "/changeRole":
                String roleId = request.getParameter("roleId");
                String userId = request.getParameter("userId");
                role = roleFacade.find(Long.parseLong(roleId));
                User user = userFacade.find(Long.parseLong(userId));
                if(!"admin".equals(user.getLogin())){
                    rl.setRole(role,user);
                }
                request.getRequestDispatcher("/showChangeRole").forward(request, response);
                break;
        
        }
        
        
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}