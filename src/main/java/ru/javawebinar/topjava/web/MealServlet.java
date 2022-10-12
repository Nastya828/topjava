package ru.javawebinar.topjava.web;

import com.sun.source.tree.Tree;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static ru.javawebinar.topjava.util.MealsUtil.*;

@WebServlet
public class MealServlet extends HttpServlet {
    private Map<Integer, Meal> meals;
    @Override
    public void init() throws ServletException {
        final Object meals = getServletContext().getAttribute("meals");
        if (meals != null) {
            this.meals = (ConcurrentHashMap<Integer, Meal>) meals;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mealsForTables",
                filteredByStreams(meals.values(), LocalTime.of(0, 0),
                        LocalTime.of(23, 59), CALORIES_PER_DAY));

        getServletContext().getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
