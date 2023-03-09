package com.gbs.software.utils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

public class UIUtils {

    public static Button createPrimaryButton(String text) {
        return createButton(text, ButtonVariant.LUMO_PRIMARY);
    }


    public static Button createButton(String text, ButtonVariant... variants){
        Button button = new Button(text);
        button.addThemeVariants(variants);
        button.getElement().setAttribute("aria-label", text);
        return button;
    }
}
