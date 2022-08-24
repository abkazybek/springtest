/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.web.ui.orderdocument;

import com.company.thesissummer.entity.OrderDocument1C;
import com.company.thesissummer.web.ui.orderdocument1c.OrderDocument1CEdit;
import com.haulmont.cuba.gui.actions.list.CreateAction;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.thesis.web.actions.PrintReportAction;
import com.haulmont.thesis.web.ui.basicdoc.editor.AbstractDocEditor;
import com.haulmont.thesis.web.voice.VoiceActionPriorities;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.thesis.core.entity.DocCategory;
import com.company.thesissummer.entity.OrderDocument;

import javax.inject.Inject;
import javax.inject.Named;
import com.haulmont.cuba.gui.components.Button;

import java.awt.event.ActionEvent;
import java.util.Map;

import static com.haulmont.thesis.web.voice.VoiceCompanionsRepository.voiceCompanion;

public class OrderDocumentEdit<T extends OrderDocument> extends AbstractDocEditor<T> {

    @Inject
    protected LookupPickerField<DocCategory> docCategory;

    OrderDocument orderDocument;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        initVoiceControl();
    }

    @Override
    protected String getHiddenTabsConfig() {
        return "openHistoryTab,securityTab,cardProjectsTab,correspondenceHistoryTab,docTransferLogTab,cardLinksTab,docLogTab,versionsTab";
    }

    @Override
    protected void addPrintDocActions() {
        super.addPrintDocActions();
        printButton.addAction(new PrintReportAction("printExecutionList", this, "printDocExecutionListReportName"));
    }

    protected void initVoiceControl() {
        voiceCompanion(docCategory).setPriorityOffset(VoiceActionPriorities.TAB_SHEET + 10);
    }

    @Override
    protected void fillHiddenTabs() {
        hiddenTabs.put("attachmentsTab", getMessage("attachmentsTab"));
        hiddenTabs.put("office", getMessage("office"));
        hiddenTabs.put("docTreeTab", getMessage("docTreeTab"));
        hiddenTabs.put("processTab", getMessage("processTab"));
        if (getAccessData().getNotVersion()) {
            hiddenTabs.put("cardCommentTab", getMessage("cardCommentTab"));
        }
        super.fillHiddenTabs();
    }
}