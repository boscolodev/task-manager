package com.gbs.software.views.user;

import com.gbs.software.model.dto.UserDTO;
import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import java.util.stream.Stream;

public class UserGridDetailView extends FormLayout {

    TextArea txtBiografia = new TextArea("Biografia");
    TextField txtGithub = new TextField("GitHub: ");

    public UserGridDetailView(){

        Stream.of(txtBiografia, txtGithub).forEach(
                field -> {
                    ((AbstractSinglePropertyField) field).setReadOnly(true);
                    add(field);
                }
        );

        setResponsiveSteps(new ResponsiveStep("0", 2));
        setColspan(txtBiografia, 2);
        setColspan(txtGithub, 1);

    }

    public void setDetail(UserDTO dto) {
        txtBiografia.setValue(dto.getBiografia());
        txtGithub.setValue(dto.getGithub());
    }
}
