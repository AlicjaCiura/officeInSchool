package com.example.demo.view;

import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

@PageTitle("Teachers")
@Route(value = "teachers", layout = MainLayout.class)
public class TeacherView extends HorizontalLayout {

    public static final String FILTER = "Filter";
    private Grid<Teacher> grid;
    private ListDataProvider<Teacher> dataProvider;
    private Button addNew;
    @Autowired
    private final TeacherService teacherService;

    private Grid.Column<Teacher> idColumn;
    private Grid.Column<Teacher> firstNameColumn;
    private Grid.Column<Teacher> lastNameColumn;
    private Grid.Column<Teacher> phoneNumberColumn;
    private Grid.Column<Teacher> mailColumn;

    public TeacherView(@Autowired TeacherService teacherService) {
        this.teacherService = teacherService;
        addClassName("hello-world-view");
        setSizeFull();
        createGrid();
        createButton();
        add(grid, addNew);
    }

    private void createButton() {
        addNew = new Button("New teacher");
        addNew.addClickListener(e -> {
                                    DialogNewPerson d = new DialogNewPerson(teacherService);
                                    d.open();
                                }
                               );
    }

    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
        addFiltersToGrid();
    }

    private void createGridComponent() {
        grid = new Grid<>();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setMaxHeight("80%");
        dataProvider = new ListDataProvider<>(teacherService.findAll());
        grid.setDataProvider(dataProvider);
    }

    private void addColumnsToGrid() {
        createIdColumn();
        createFirstNameColumn();
        createLastNameColumn();
        createPhoneNumberColumn();
        createMailColumn();
    }

    private void createIdColumn() {
        idColumn = grid.addColumn(Teacher::getId, "id").setHeader("ID").setWidth("120px").setFlexGrow(0);
    }

    private void createFirstNameColumn() {
        firstNameColumn =
                grid.addColumn(Teacher::getFirstName)
                        .setComparator(Teacher::getFirstName).setHeader("First name");
    }

    private void createLastNameColumn() {
        lastNameColumn = grid.addColumn(Teacher::getLastName)
                .setComparator(Teacher::getLastName).setHeader("Last name");
    }

    private void createPhoneNumberColumn() {
        phoneNumberColumn =
                grid.addColumn(Teacher::getPhone)
                        .setComparator(Teacher::getPhone).setHeader("Phone number");
    }

    private void createMailColumn() {
        mailColumn =
                grid.addColumn(Teacher::getEmail)
                        .setComparator(Teacher::getEmail).setHeader("Email");
    }

    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();

        TextField idFilter = createFilter(t -> t.getId().toString());
        filterRow.getCell(idColumn).setComponent(idFilter);

        TextField firstNameFilter = createFilter(Teacher::getFirstName);
        filterRow.getCell(firstNameColumn).setComponent(firstNameFilter);

        TextField lastNameFilter = createFilter(Teacher::getLastName);
        filterRow.getCell(lastNameColumn).setComponent(lastNameFilter);

        TextField phoneFilter = createFilter(Teacher::getPhone);
        filterRow.getCell(phoneNumberColumn).setComponent(phoneFilter);

        TextField mailFilter = createFilter(Teacher::getEmail);
        filterRow.getCell(mailColumn).setComponent(mailFilter);
    }

    private TextField createFilter(Function<Teacher, String> getValue) {
        TextField filter = new TextField();
        filter.setPlaceholder(FILTER);
        filter.setClearButtonVisible(true);
        filter.setWidth("100%");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> dataProvider.addFilter(
                teacher -> StringUtils.containsIgnoreCase(getValue.apply(teacher), filter.getValue())));
        return filter;
    }
}