package org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.annotations;

import org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.enums.EventPriority;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
    EventPriority priority() default EventPriority.MEDIUM;
}
