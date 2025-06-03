package com.crud.ui.demo.EditOrders;

import java.util.List;

import com.crud.ui.demo.OrderService.OrderService;
import com.crud.ui.demo.model.Order;
import com.crud.ui.demo.repository.OrderRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("EditOrdersView")
public class EditOrdersView extends VerticalLayout {

    private Grid<Order> grid;

    private Button deleteButton = new Button("Delete");

    private Button cancelButton = new Button("Cancel");

    private Button saveButton = new Button("Save");

    public EditOrdersView(OrderRepository orderRepository) {
        // FormLayout formLayout = new FormLayout();

        this.grid = new Grid<>(Order.class, false);
        grid.addColumn(Order::getId).setHeader("ID").setSortable(true).setWidth("20px");
        grid.addColumn(Order::getStatus).setHeader("Status").setSortable(true);
        grid.addColumn(Order::getOrderDate).setHeader("OrderDate").setSortable(true);
        // List<Order> orders = orderService.getAllOrders();

        // Grid<Order> grid = new Grid<>(Order.class);

        // grid.setItems(orders);
        HorizontalLayout buttonLayout = new HorizontalLayout();
        deleteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.setWidth("50px");
        buttonLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        buttonLayout.setSpacing(false);
        buttonLayout.add(deleteButton, new HorizontalLayout(cancelButton, saveButton));

        add(grid, buttonLayout);

    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

}
