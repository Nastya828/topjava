package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static ru.javawebinar.topjava.util.MealsUtil.*;

@WebServlet(name = "MealServlet", value = "/MealServlet")
public class MealServlet extends HttpServlet {
    private List<Meal> mealsForServlet;

    @Override
    public void init() throws ServletException {
        mealsForServlet = new CopyOnWriteArrayList<>();
        mealsForServlet.addAll(meals);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mealsForTables",
                filteredByStreams(mealsForServlet, LocalTime.of(0, 0),
                        LocalTime.of(23, 59), CALORIES_PER_DAY));
        getServletContext().getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        final LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"), formatter);
        final String description = request.getParameter("description");
        final String calories = request.getParameter("calories");

        final Meal mealToAdd = new Meal(dateTime, description, Integer.parseInt(calories));
        mealsForServlet.add(mealToAdd);
        doGet(request, response);
    }
}
