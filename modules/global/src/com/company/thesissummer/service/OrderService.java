/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface OrderService {
    String NAME = "thesissummer_OrderService";



    void saveXML();




    /*

       List<OrderRevaluation> getActiveRevaluationXML();


    List<Meeting> getInactiveMeeting();

    List<UserVoteResult> getUserVoteList(UUID meetingId);

    void savePresenceVoteRes(UUID meetingId, String voteResStr);

    String getIsViewPresenceButtons(UUID meetingId);

    void saveAgendaVoteRes(UUID meetingId, String voteResStr);

    void saveApproveAgendaRes(UUID meetingId, String voteResStr);

    String getIsViewAgendaButtons(UUID meetingId);

    String getIsViewApproveButtons(UUID meetingId);

    String getIsViewQuestionVoteButtons(UUID meetingId, UUID questionId);

    String getProcessStep(UUID meetingId);

    String getIsSecretary(UUID meetingId);

    void uploadProtocol(UUID meetingId, UUID fileId);

    public String getProtocolForSign(UUID meetingId);

    void saveMeetingStatus(UUID meetingId, String statusStr);

    String getCurrentUserInitials(); */
}