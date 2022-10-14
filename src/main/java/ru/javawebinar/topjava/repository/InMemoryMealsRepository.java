package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealsRepository implements MealsRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger idCounter = new AtomicInteger(0);

//    {
//        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
//        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
//        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
//        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
//        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
//        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
//        save(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
//
//    }

    @Override
    public Meal save(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(idCounter.getAndIncrement());
        }
        return repository.put(meal.getId(), meal);
    }

    @Override
    public Meal get(int id) {
        return repository.getOrDefault(id, null);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }
}
