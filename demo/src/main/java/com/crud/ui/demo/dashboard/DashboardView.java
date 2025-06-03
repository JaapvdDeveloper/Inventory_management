package com.crud.ui.demo.dashboard;

import java.util.List;

import com.crud.ui.demo.OrderService.OrderService;
import com.crud.ui.demo.model.Order;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("DashboardView")
public class DashboardView extends VerticalLayout {

    public DashboardView(OrderService orderService) {;

        List<Order> orders = orderService.getAllOrders();

        Grid<Order> grid = new Grid<>(Order.class);
        grid.setItems(orders);
        
        
        add(grid);

    }

   



}
