/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.orderdocument1c;

import com.haulmont.cuba.gui.screen.*;
import com.company.thesissummer.entity.OrderDocument1C;

@UiController("thesissummer$OrderDocument1C.edit")
@UiDescriptor("order-document1c-edit.xml")
@EditedEntityContainer("orderDocument1CDc")
@LoadDataBeforeShow
public class OrderDocument1CEdit extends StandardEditor<OrderDocument1C> {
}