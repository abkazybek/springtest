/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui;


import com.company.thesissummer.web.ckeditor.CKEditor;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.ContentMode;
import com.haulmont.cuba.gui.components.VBoxLayout;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.web.gui.components.JavaScriptComponent;
import com.haulmont.cuba.web.widgets.WebJarResource;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;

import javax.inject.Inject;
import java.util.Map;

@WebJarResource({"jquery:jquery.min.js"})
@JavaScript({
        "ckeditor-connector.js",
        "vaadin://resources/ckeditor/ckeditor.js",
        "vaadin://resources/ckeditor/config.js",
        "vaadin://resources/ckeditor/styles.js"
})
@StyleSheet({
        "vaadin://resources/ckeditor/contents.css",
        "vaadin://resources/ckeditor/skins/moono-lisa/editor.css"
})
@UiController("thesissummer$NewScreen")
@UiDescriptor("new-screen.xml")
public class NewScreen extends Screen {

    @Inject
    private VBoxLayout editorBox;

    @Inject
    private Notifications notifications;

    private CKEditor ckEditor;

    @Subscribe
    public void onInit(InitEvent event) {
        ckEditor = new CKEditor();
        ckEditor.addValueChangeListener(valueChangeEvent ->
                notifications.create()
                        .withCaption("ValueChange Event, User originated: " + valueChangeEvent.isUserOriginated())
                        .withDescription(valueChangeEvent.getValue())
                        .withContentMode(ContentMode.HTML)
                        .withType(Notifications.NotificationType.TRAY)
                        .show());

        editorBox.unwrap(AbstractOrderedLayout.class).addComponent(ckEditor, 0);
    }

}