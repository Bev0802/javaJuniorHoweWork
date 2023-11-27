package org.example.HW_1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart <T extends Food>{

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs(){
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> {
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                    index.getAndIncrement(), food.getName(),
                    food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет",
                    food.getCarbohydrates() ? "Да" : "Нет");
        });
    }
    // region Дополнительная задaча: Переработать метод балансировки корзины товаров cardBalancing() с использованием Stream API
    /**
     * Балансировка корзины
     */
    public void cardBalancing() {
        final boolean[] proteins = {foodstuffs.stream().anyMatch(Food::getProteins)};
        final boolean[] fats = {foodstuffs.stream().anyMatch(Food::getFats)};
        final boolean[] carbohydrates = {foodstuffs.stream().anyMatch(Food::getCarbohydrates)};

        if (proteins[0] && fats[0] && carbohydrates[0]) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        market.getThings(Food.class).stream()
                .filter(thing -> !proteins[0] && thing.getProteins()
                        || !fats[0] && thing.getFats()
                        || !carbohydrates[0] && thing.getCarbohydrates())
                .findFirst()
                .ifPresent(thing -> {
                    if (!proteins[0] && thing.getProteins()) {
                        proteins[0] = true;
                        foodstuffs.add((T) thing);
                    } else if (!fats[0] && thing.getFats()) {
                        fats[0] = true;
                        foodstuffs.add((T) thing);
                    } else if (!carbohydrates[0] && thing.getCarbohydrates()) {
                        carbohydrates[0] = true;
                        foodstuffs.add((T) thing);
                    }
                });

        if (proteins[0] && fats[0] && carbohydrates[0]) {
            System.out.println("Корзина сбалансирована по БЖУ.");
        } else {
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        }
    }
    // endregion

}
