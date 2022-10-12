package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet
public class UpdateMealServlet extends HttpServlet {
    private Map<Integer, Meal> meals;
    private int id;
    public void init() throws ServletException {
        final Object meals = getServletContext().getAttribute("meals");
        if (meals != null) {
            this.meals = (ConcurrentHashMap<Integer, Meal>) meals;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqAction = request.getParameter("action");
        if (reqAction.equals("update")) {
            id = Integer.parseInt(request.getParameter("mealId"));
            final Meal mealBefore = meals.get(id);
            request.setAttribute("mealBefore", mealBefore);
        }
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        final LocalDateTime dateTime;
        final String description;
        final String calories;
        final int id = this.id;
        if (!request.getParameter("date").equals("")) {
            dateTime = LocalDateTime.parse(request.getParameter("date"));
        } else {
            dateTime = meals.get(id).getDateTime();
        }
        if (!request.getParameter("description").equals("")) {
            description = (request.getParameter("description"));
        } else {
            description = meals.get(id).getDescription();
        }
        if (!request.getParameter("calories").equals("")) {
            calories = (request.getParameter("calories"));
        } else {
            calories = String.valueOf(meals.get(id).getCalories());
        }
        Meal newMeal = new Meal(id, dateTime, description, Integer.parseInt(calories));
        meals.replace(id, newMeal);
        response.sendRedirect(request.getContextPath() + "/");
    }
}
