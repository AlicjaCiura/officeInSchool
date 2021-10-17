package com.example.demo;

import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@SpringComponent
public class DialogNewPerson extends Dialog {

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private final PersonForm personForm;

    public DialogNewPerson(@Autowired TeacherService teacherService) {
        this.personForm = new PersonForm();

        add(createTitle());
        add(this.personForm);
        add(createButtonLayout());
        setWidth("50%");
        setHeight("50%");

        personForm.binder.bindInstanceFields(this);
        personForm.clearForm();

        cancel.addClickListener(e -> personForm.clearForm());
        save.addClickListener(event -> {
            Teacher t = teacherService.saveOrUpdate(personForm.binder.getBean());
            Optional.ofNullable(t).map(
                    i -> success())
                    .orElse(failed())
                    .open();
            this.removeAll();
            this.close();
        });
        this.close();
    }

    private Notification failed() {
        Notification notification = new Notification("From not submitted!", 500, Notification.Position.BOTTOM_CENTER);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        return notification;
    }

    private Notification success() {
        Notification notification = new Notification("From submitted!", 500, Notification.Position.BOTTOM_CENTER);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        return  notification  ;
    }

    private Component createTitle() {
        return new H3("Add new teacher");
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }
}
