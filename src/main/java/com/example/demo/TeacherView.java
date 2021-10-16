package com.example.demo;

import com.example.demo.dto.PersonDto;
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
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@PageTitle("Teachers")
@Route(value = "teachers", layout = MainLayout.class)
@SpringComponent
@UIScope
public class TeacherView extends HorizontalLayout {

    public static final String FILTER = "Filter";
    private Grid<Teacher> grid;
    private ListDataProvider<Teacher> dataProvider;
    private Button addNew;
    @Autowired
    private DialogNewPerson dialogNewPerson;
    @Autowired
    private TeacherService teacherService;

    private Grid.Column<Teacher> idColumn;
    private Grid.Column<Teacher> firstNameColumn;
    private Grid.Column<Teacher> lastNameColumn;

    public TeacherView() {
        addClassName("hello-world-view");
        setSizeFull();
        createGrid();
        createButton();
        add(grid, addNew);
        add(addNew);
    }

    private void createButton() {
        addNew = new Button("New teacher");
        addNew.addClickListener(e -> {

            dialogNewPerson.open();
        });
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

        dataProvider = new ListDataProvider<>(getTeachers());
        grid.setDataProvider(dataProvider);
    }

    private void addColumnsToGrid() {
        createIdColumn();
        createFirstNameColumn();
        createLastNameColumn();
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


    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();

        TextField idFilter = new TextField();
        idFilter.setPlaceholder(FILTER);
        idFilter.setClearButtonVisible(true);
        idFilter.setWidth("100%");
        idFilter.setValueChangeMode(ValueChangeMode.EAGER);
        idFilter.addValueChangeListener(event -> dataProvider.addFilter(
                teacher -> StringUtils.containsIgnoreCase(teacher.getId().toString(), idFilter.getValue())));
        filterRow.getCell(idColumn).setComponent(idFilter);

        TextField firstNameFilter = new TextField();
        firstNameFilter.setPlaceholder(FILTER);
        firstNameFilter.setClearButtonVisible(true);
        firstNameFilter.setWidth("100%");
        firstNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        firstNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(teacher -> StringUtils.containsIgnoreCase(teacher.getFirstName(), firstNameFilter.getValue())));
        filterRow.getCell(firstNameColumn).setComponent(firstNameFilter);

        TextField lastNameFilter = new TextField();
        lastNameFilter.setPlaceholder(FILTER);
        lastNameFilter.setClearButtonVisible(true);
        lastNameFilter.setWidth("100%");
        lastNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        lastNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(teacher -> StringUtils.containsIgnoreCase(teacher.getLastName(), lastNameFilter.getValue())));
        filterRow.getCell(lastNameColumn).setComponent(lastNameFilter);

    }

    private List<Teacher> getTeachers() {
        return Arrays.asList(
                createTeacher(4957, "Amarachi", " Nkechi"),
                createTeacher(675, "Bonelwa", "Ngqawana"),
                createTeacher(6816, "Debashis", " Bhuiyan"),
                createTeacher(5144, "Jacqueline", " Asong"));
    }

    private Teacher createTeacher(long id, String firstName, String lastName) {
        return Teacher.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}