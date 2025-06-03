package com.crud.ui.demo.components;

// Removed incorrect import for ChangeHandler

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.ui.demo.model.Customer;
import com.crud.ui.demo.repository.CustomerRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;



@SpringComponent
@UIScope
public class CustomerEditor extends VerticalLayout implements KeyNotifier {

    public interface ChangeHandler {
        void onChange();
    }

    private final CustomerRepository repository;

    /**
     * The currently edited customer
     */
    private Customer customer;

    /* Fields to edit properties in Customer entity */
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");

    /* Action buttons */

    Button save = new Button("save", VaadinIcon.CHECK.create());
    Button cancel = new Button("cancel");
    Button delete = new Button("delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Customer> binder = new Binder<>(Customer.class);
    private ChangeHandler changeHandler;



    @Autowired
    public CustomerEditor(CustomerRepository repository) {
        this.repository = repository;

        add(firstName, lastName, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);


        // COnfigure and style components
        setSpacing(true);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        
        addKeyPressListener(Key.ENTER, e -> save());


        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editCustomer(customer));
        setVisible(false);
    }


    void delete() {
        repository.delete(customer);
        changeHandler.onChange();
    }


    void save(){
        repository.save(customer);
        changeHandler.onChange();
    }


    public final void editCustomer(Customer c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            // In a more complex app, you might want to load 
            // the entity/DTO with lazy loaded relations for editing
            customer = repository.findById(c.getId()).get();
        } else {
            customer = c;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        binder.setBean(customer);

        setVisible(true);

        // Focus first name by default
        firstName.focus();
    }

    public void setChangeHandler(ChangeHandler h) {

        //ChangeHandler is notified when either save or delete is clicked

        changeHandler = h;
    }

}
