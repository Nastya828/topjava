package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryMealsRepository;
import ru.javawebinar.topjava.repository.MealsRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

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
                    filteredByStreams(mealsRepository.getAll(), LocalTime.MIN,
                            LocalTime.MAX, CALORIES_PER_DAY));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
        switch (Objects.requireNonNull(action)) {
            case ("delete"):
                mealsRepository.delete(Integer.parseInt(request.getParameter("mealId")));
                response.sendRedirect("meals");
                break;
            case ("create"):
                final Meal meal = new Meal(LocalDateTime.now(), "", 500);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
            case ("update"):
                int id = Integer.parseInt(request.getParameter("mealId"));
                if (mealsRepository.get(id) != null) {
                    request.setAttribute("meal", mealsRepository.get(id));
                }
                request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
                break;
//            default:
//                request.setAttribute("mealsForTables",
//                        filteredByStreams(mealsRepository.getAll(), LocalTime.MIN,
//                                LocalTime.MAX, CALORIES_PER_DAY));
//                request.getRequestDispatcher("/mealList.jsp").forward(request, response);
//                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        mealsRepository.save(new Meal(id.isEmpty() ? null : Integer.parseInt(id),
                LocalDateTime.parse(request.getParameter("date")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories"))));
        response.sendRedirect("meals");
    }
}
