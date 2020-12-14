package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi;

import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.annotations.EventHandler;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.enums.EventPriority;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Pair;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EventManager extends AbstractEventManager {

    private final List<Listener> listeners = new ArrayList<>();

    @Override
    public void registerEventListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void fireEvent(Event evt) {
        for (Pair<EventPriority, Pair<Listener, Method>> allMethod : getAllMethods(evt)) {
            try {
                allMethod.getValue().getValue().invoke(allMethod.getValue().getKey(), evt);
            } catch (Exception e) {
                System.out.println("An error occurred while firing an event: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private List<Pair<EventPriority, Pair<Listener, Method>>> getAllMethods(Event evt) {
        List<Pair<EventPriority, Pair<Listener, Method>>> map = new ArrayList<>();

        for (Listener listener : listeners) {
            for (Method method : listener.getClass().getMethods()) {
                if (method.getParameters().length == 1
                        && method.getParameters()[0].getType().isAssignableFrom(evt.getClass())) {
                    // if first parameter is equal to the event
                    EventHandler annotation = method.getAnnotation(EventHandler.class);
                    if (annotation == null) continue;

                    map.add(new Pair<>(annotation.priority(), new Pair<>(listener, method)));
                }
            }
        }

        map.sort(Comparator.comparing(e -> e.getKey().getPriority(), Comparator.reverseOrder()));
        map = map.stream().distinct().collect(Collectors.toList());

        return map;
    }
}
