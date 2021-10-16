package com.example.demo;

import com.example.demo.dto.PersonDto;
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
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
public class DialogNewPerson extends Dialog {

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");
    @Autowired
    private final PersonForm personForm;
    @Getter
    private PersonDto person;
    @Autowired
    TeacherService teacherService;

    public DialogNewPerson(PersonForm personForm) {
        this.personForm = personForm;

        add(createTitle());
        add(personForm);
        add(createButtonLayout());
        setWidth("50%");
        setHeight("50%");

        personForm.binder.bindInstanceFields(this);
        personForm.clearForm();

        cancel.addClickListener(e -> personForm.clearForm());
        save.addClickListener(event -> {
            teacherService.saveOrUpdate(personForm.binder.getBean());

            Notification notification = Notification.show("From submitted!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            this.close();
            
        });
        this.close();
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
