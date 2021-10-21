package com.example.demo.view;

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
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

        personForm.clearForm();

        cancel.addClickListener(e -> {
            personForm.clearForm();
            this.removeAll();
            this.close();
        });
        save.addClickListener(event -> {
            try {
                Optional<Teacher> t = Optional.ofNullable(teacherService.saveOrUpdate(personForm.binder.getBean()));
                t.map(i -> success()).ifPresent(Notification::open);
                this.removeAll();
                this.close();
            }                                               
            catch (TransactionSystemException e) {
                failed(createMessage(e)).open();
            }
        });

    }

    private String createMessage(TransactionSystemException e) {
        Set<ConstraintViolation<?>> error = ((ConstraintViolationException) e.getCause().getCause()).getConstraintViolations();
        return error.stream()
                .map(err -> String.format("%s %s", err.getPropertyPath(), err.getMessage()))
                .collect(Collectors.joining("\n"));
    }

    private Notification failed(String message) {
        Notification notification = new Notification("From not submitted! \n" + message, 10000,
                                                     Notification.Position.MIDDLE);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        return notification;
    }

    private Notification success() {
        Notification notification = new Notification("From submitted!", 500, Notification.Position.BOTTOM_CENTER);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        return notification;
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
