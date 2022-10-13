package ru.javawebinar.topjava.repository;
import ru.javawebinar.topjava.model.Meal;
import java.util.Collection;

public interface MealsRepository {
    void keep(Meal meal);
    Meal get(int id);
    Collection<Meal> getAllMeals();
    void delete(int id);
}
