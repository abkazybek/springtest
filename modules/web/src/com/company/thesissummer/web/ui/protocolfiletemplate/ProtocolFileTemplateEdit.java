/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.protocolfiletemplate;

import com.haulmont.cuba.gui.screen.*;
import com.company.thesissummer.entity.ProtocolFileTemplate;

@UiController("thesissummer$ProtocolFileTemplate.edit")
@UiDescriptor("protocol-file-template-edit.xml")
@EditedEntityContainer("protocolFileTemplateDc")
@LoadDataBeforeShow
public class ProtocolFileTemplateEdit extends StandardEditor<ProtocolFileTemplate> {
}