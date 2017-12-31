package com.serg.engine.pool;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.serg.engine.sprite.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool<T extends Sprite> {

    protected final List<T> activeObjects = new ArrayList<T>();
    protected final List<T> freeObjects = new ArrayList<T>();

    protected abstract T newObject();

    public T obtain() {
        T object;
        if (freeObjects.isEmpty()) {
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        activeObjects.add(object);
        debugLog();
        return object;
    }

    /**
     * Обновление всех активных объектоа
     * @param delta
     */
    public void updateActiveSprites(float delta) {
        for (int i = 0; i < activeObjects.size(); i++) {
            Sprite sprite = activeObjects.get(i);
            if (sprite.isDestroyed()) {
                throw new RuntimeException("Попытка обновления объекта, помеченного на удаление");
            }
            sprite.update(delta);
        }
    }

    /**
     * Освобождение объектов, помеченных на удаление
     */
    public void freeAllDestroyedActiveObjects() {
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if (sprite.isDestroyed()) {
                free(sprite);
                i--;
                sprite.flushDestroy();
            }
        }
    }

    /**
     * Отрисовка активных объектов
     * @param batch
     */
    public void drawActiveObjects(SpriteBatch batch) {
        for (int i = 0; i < activeObjects.size(); i++) {
            Sprite sprite = activeObjects.get(i);
            if (sprite.isDestroyed()) {
                throw new RuntimeException("Попытка отрисовки объекта, помеченного на удаление");
            }
            sprite.draw(batch);
        }
    }

    /**
     * Освобождаем все активные объекты
     */
    public void freeAllActiveObjects() {
        freeObjects.addAll(activeObjects);
        activeObjects.clear();
    }

    /**
     * Освобождение объекта
     * @param object
     */
    private void free(T object) {
        if (!activeObjects.remove(object)) {
            throw new RuntimeException("Попытка удаления несуществующего объекта");
        }
        freeObjects.add(object);
        debugLog();
    }

    protected void debugLog() {

    }

    public List<T> getActiveObjects() {
        return activeObjects;
    }

    public void dispose() {
        activeObjects.clear();
        freeObjects.clear();
    }
}
