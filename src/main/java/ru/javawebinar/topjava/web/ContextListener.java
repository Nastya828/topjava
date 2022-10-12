package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.javawebinar.topjava.util.MealsUtil.mealsList;


@WebListener
public class ContextListener implements ServletContextListener {
    private Map<Integer, Meal> meals;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final ServletContext servletContext =
                sce.getServletContext();
        meals = new ConcurrentHashMap<>();
        servletContext.setAttribute("meals", meals);
        for (int i = 0; i < mealsList.size(); i++) {
            this.meals.put(i, mealsList.get(i));
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        meals = null;
    }
}
