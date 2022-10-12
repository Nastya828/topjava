package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet
public class AddMealServlet extends HttpServlet {
    private Map<Integer, Meal> meals;
    private AtomicInteger id;

    @Override
    public void init() throws ServletException {
        final Object meals = getServletContext().getAttribute("meals");
        if (meals != null) {
            this.meals = (ConcurrentHashMap<Integer, Meal>) meals;
        }
        assert meals != null;
        id = new AtomicInteger(((ConcurrentHashMap<?, ?>) meals).size());
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        final LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"));
        final String description = request.getParameter("description");
        final String calories = request.getParameter("calories");
        final int id = this.id.getAndIncrement();
        final Meal mealToAdd = new Meal(id, dateTime, description, Integer.parseInt(calories));
        meals.put(id, mealToAdd);
        response.sendRedirect(request.getContextPath() + "/");
    }
}
