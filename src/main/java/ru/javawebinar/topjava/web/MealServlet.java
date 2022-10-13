package ru.javawebinar.topjava.web;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryMealsRepository;
import ru.javawebinar.topjava.repository.MealsRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
    private MealsRepository mealsRepository;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mealsRepository = new InMemoryMealsRepository();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("mealsForTables",
                    filteredByStreams(mealsRepository.getAllMeals(), LocalTime.MIN,
                            LocalTime.MAX, CALORIES_PER_DAY));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            mealsRepository.delete(Integer.parseInt(request.getParameter("mealId")));
            response.sendRedirect("meals");
        } else if (action.equals("create")) {
            final Meal meal = new Meal(0, LocalDateTime.now(), "", 500);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
        } else if (action.equals("update")) {
            request.setAttribute("meal", mealsRepository.get(Integer.parseInt(request.getParameter("mealId"))));
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        mealsRepository.keep(new Meal(id.isEmpty() ? null : Integer.parseInt(id),
                LocalDateTime.parse(request.getParameter("date")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories"))));
        response.sendRedirect("meals");
    }
}
