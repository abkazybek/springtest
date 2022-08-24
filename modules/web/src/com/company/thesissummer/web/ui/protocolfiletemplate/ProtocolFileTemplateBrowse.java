/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.protocolfiletemplate;

import com.haulmont.cuba.gui.screen.*;
import com.company.thesissummer.entity.ProtocolFileTemplate;

@UiController("thesissummer$ProtocolFileTemplate.browse")
@UiDescriptor("protocol-file-template-browse.xml")
@LookupComponent("protocolFileTemplatesTable")
@LoadDataBeforeShow
public class ProtocolFileTemplateBrowse extends StandardLookup<ProtocolFileTemplate> {
}