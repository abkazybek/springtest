/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.typeprotocolkk;

import com.haulmont.cuba.gui.screen.*;
import com.company.thesissummer.entity.protocolkk.TypeProtocolKK;

@UiController("thesissummer$TypeProtocolKK.edit")
@UiDescriptor("type-protocol-kk-edit.xml")
@EditedEntityContainer("typeProtocolKKDc")
@LoadDataBeforeShow
public class TypeProtocolKKEdit extends StandardEditor<TypeProtocolKK> {
}