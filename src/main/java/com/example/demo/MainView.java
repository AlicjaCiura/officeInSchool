package com.example.demo;

import com.example.demo.service.TeacherService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
public class MainView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;
    @Autowired
    private TeacherService teacherService;

    public MainView() {
        addClassName("hello-world-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> Notification.show("Hello " + name.getValue()));

    }
}