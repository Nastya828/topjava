package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet
public class DeleteMealServlet extends HttpServlet {
    private Map<Integer, Meal> meals;

    public void init() throws ServletException {
        final Object meals = getServletContext().getAttribute("meals");
        if (meals != null) {
            this.meals = (ConcurrentHashMap<Integer, Meal>) meals;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqAction = request.getParameter("action");
        if (reqAction.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("mealId"));
            meals.remove(id);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}
